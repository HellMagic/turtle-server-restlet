package org.sunlib.turtle.restlet;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;
import org.restlet.routing.Template;
import org.sunlib.turtle.restlet.resource.LessonDataResource;
import org.sunlib.turtle.restlet.resource.UserDataResource;

/**
 * User: fxp
 * Date: 13-9-22
 * Time: PM2:38
 */
public class MainServer implements Runnable {

    @Override
    public void run() {
        Component component = new Component();
        component.getClients().add(Protocol.HTTP);

        final Router router = new Router();
        router.setDefaultMatchingMode(Template.MODE_STARTS_WITH);
        router.setRoutingMode(Router.MODE_FIRST_MATCH);
        router.attach("/exercise/v1/user_data/{path}", UserDataResource.class);
        router.attachDefault(LessonDataResource.class);
        Application application = new Application() {
            @Override
            public Restlet createInboundRoot() {
                router.setContext(getContext());
                return router;
            }
        };

        component.getDefaultHost().attach("", application);
        try {
            component.getServers().add(Protocol.HTTP, 8182).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
