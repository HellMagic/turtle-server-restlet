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
 * Date: 13-9-18
 * Time: PM1:47
 */
public class LessonDataResource extends ServerResource {
//
//    private SimpleDiskCache cache;
//
//    @Override
//    protected void doInit() throws ResourceException {
//        super.doInit();
//        File cacheDir = new File(this.getCacheDir(), DEFAULT_CACHE_DIR);
//
//        try {
//            cache = SimpleDiskCache.open(cacheDir, APP_VERSION, Integer.MAX_VALUE);
//        } catch (IOException e) {
//            Toast.makeText(this, "init cache failed, " + e.getMessage(), Toast.LENGTH_LONG).show();
//            e.printStackTrace();
//        }
//
//    }

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

//    public synchronized void downloadFile(URL url){
//        try {
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            try {
//                Log.i(TAG, "start downloading," + url.toString());
//
//                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//                Map<String, Serializable> metadata = new HashMap<String, Serializable>();
//                Map<String, List<String>> headers = urlConnection.getHeaderFields();
//                for (String key : headers.keySet()) {
//                    if (!TextUtils.isEmpty(key)) {
//                        metadata.put(key, headers.get(key).get(0));
//                    }
//                }
//                cache.put(url.toString(), in, metadata);
//                Log.i(TAG, "download completed," + url.toString());
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                urlConnection.disconnect();
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Get
    public Representation get() {
        addAccessToken();



        URI uri = getReference().toUri();
        String url = "http://192.168.3.100" + uri.getRawPath();
        Logger.i("LD:" + url);
        ClientResource resource = new ClientResource(url);
        return resource.get();
    }

    @Post
    public Representation post(Representation request) {
        addAccessToken();
        Logger.i("LDPOST:");
        return null;
    }

}
