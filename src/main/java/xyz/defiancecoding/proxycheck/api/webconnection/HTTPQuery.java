package xyz.defiancecoding.proxycheck.api.webconnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import xyz.defiancecoding.proxycheck.exceptions.InvalidHTTPQueryException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HTTPQuery {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public HTTPQuery() {

    }

    private void close() throws IOException {
        httpClient.close();
    }


    /**
     *
     * @param url Address you want to send a GET HTTP request to
     * @return String response of HTTP GET request
     */
    public String sendGet(String url) {
        HttpGet get = new HttpGet(url);
        try (CloseableHttpResponse response = httpClient.execute(get)) {
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                return EntityUtils.toString(entity);
            }
            close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param url Address you want to send a POST HTTP request.
     * @param postData List of NameValuePairs that get added to the URL request
     * @return String response of the POST HTTP request
     */

    public String sendPOST(String url, List<NameValuePair> postData) {
        HttpPost post = new HttpPost(url);

        List<NameValuePair> postParams = new ArrayList<>(postData);
        postParams.add(new BasicNameValuePair(HttpHeaders.USER_AGENT, "ProxyCheck-IO-Java-API"));
        postParams.add(new BasicNameValuePair(HttpHeaders.ACCEPT_ENCODING, "UTF-8"));
        postParams.add(new BasicNameValuePair(HttpHeaders.CONTENT_TYPE, "application/json"));

        try {
            post.setEntity(new UrlEncodedFormEntity(postParams));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try (CloseableHttpResponse response = httpClient.execute(post)) {
            String responseEntity = EntityUtils.toString(response.getEntity());
            if (response.getStatusLine().toString().contains("200")) {
                return responseEntity;
            } else {
                throw new InvalidHTTPQueryException("[Defiant-Debug] Error getting result from API: " + response.getStatusLine());
            }
        } catch (IOException | InvalidHTTPQueryException e) {
            e.printStackTrace();
        }
        return null;
    }


}
