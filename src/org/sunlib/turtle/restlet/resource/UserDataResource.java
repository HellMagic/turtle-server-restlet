package org.sunlib.turtle.restlet.resource;

import org.restlet.engine.header.Header;
import org.restlet.engine.header.HeaderConstants;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.restlet.util.Series;
import org.sunlib.turtle.restlet.Constants;
import org.sunlib.turtle.restlet.Logger;

import java.net.URI;

/**
 * User: fxp
 * Date: 13-9-23
 * Time: PM3:28
 */
public class UserDataResource extends ServerResource {

    public void addAccessToken() {
        Series<Header> responseHeaders = (Series<Header>)
                getResponseAttributes().get(HeaderConstants.ATTRIBUTE_HEADERS);
        if (responseHeaders == null) {
            responseHeaders = new Series(Header.class);
            getResponseAttributes().put(HeaderConstants.ATTRIBUTE_HEADERS,
                    responseHeaders);
        }
        responseHeaders.add(new Header("Access-Token", Constants.getAccessToken()));
    }

    @Get
    public Representation get() {
        addAccessToken();
        URI uri = getReference().toUri();
        String url = "http://192.168.3.100" + uri.getRawPath();
        Logger.i("UD:" + url);
        ClientResource resource = new ClientResource(url);
        return resource.get();
    }

    @Post
    public Representation post(Representation request) {
        addAccessToken();
        Logger.i("UDPOST:");
        return null;
    }
}
