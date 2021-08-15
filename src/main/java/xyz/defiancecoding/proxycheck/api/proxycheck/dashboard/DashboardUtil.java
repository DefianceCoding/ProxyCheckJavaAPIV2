package xyz.defiancecoding.proxycheck.api.proxycheck.dashboard;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import xyz.defiancecoding.proxycheck.api.webconnection.HTTPQuery;
import xyz.defiancecoding.proxycheck.exceptions.InvalidParameterException;

import java.util.ArrayList;
import java.util.List;

public class DashboardUtil {

    private final HTTPQuery httpQuery = new HTTPQuery();
    private String baseURL = "https://proxycheck.io/dashboard/";

    public DashboardUtil(){

    }

    /**
     *
     *
     * @param useJsonFormat boolean value of whether to get response as JSON formatted String
     * @param apiKey APIKey from https://proxycheck.io/
     * @param limit Limit of results to show
     * @param offset Number of results to offset the search
     * @return String response from APICall
     */
    public String exportDetections(boolean useJsonFormat, String apiKey, int limit, int offset){
        int useJson = useJsonFormat ? 1 : 0;
        baseURL += "export/detections/";
        baseURL += "?json" + useJson;
        baseURL += "&key=" + apiKey;
        baseURL += "&limit=" + limit;
        baseURL += "&offset=" + offset;

        return httpQuery.sendGet(baseURL);
    }

    /**
     *
     * @param apiKey APIKey from https://proxycheck.io/
     * @param limit Limit of results to show
     * @param offset Number of results to offset the search
     * @param addresses Number of different addresses to search for
     * @param days Amount of days back to search in the api
     * @return String response from APICall
     */
    public String exportTags(String apiKey, int limit, int offset, int addresses, int days){
        baseURL += "export/tags/";
        baseURL += "?key=" + apiKey;
        baseURL += "&limit=" + limit;
        baseURL += "&offset=" + offset;
        baseURL += "&addresses=" + addresses;
        baseURL += "&days=" + days;
        return httpQuery.sendGet(baseURL);
    }

    /**
     *
     * @param apiKey APIKey from https://proxycheck.io/
     * @param limit Limit of results to show
     * @param offset Number of results to offset the search
     * @param start start time of period of lookup
     * @param end end time of period of lookup
     * @return String response from APICall
     */
    public String exportTags(String apiKey, int limit, int offset, long start, long end){
        resetBaseURL();
        baseURL += "export/tags/";
        baseURL += "?key=" + apiKey;
        baseURL += "&limit=" + limit;
        baseURL += "&offset=" + offset;
        baseURL += "&start=" + start;
        baseURL += "&end=" + end;
        return httpQuery.sendGet(baseURL);
    }

    //https://proxycheck.io/dashboard/export/usage/?key=111111-222222-333333-444444

    /**
     *
     * @param apiKey APIKey from https://proxycheck.io/
     * @return String response from APICall
     */
    public String exportUsage(String apiKey){
        resetBaseURL();
        baseURL += "export/usage/";
        baseURL += "?key=" + apiKey;
        return httpQuery.sendGet(baseURL);
    }

    //https://proxycheck.io/dashboard/export/queries/?json=1&key=111111-222222-333333-444444

    /**
     *
     * @param useJsonFormat boolean value of whether to get response as JSON formatted String
     * @param apiKey APIKey from https://proxycheck.io/
     * @return String response from APICall
     */
    public String exportQueries(boolean useJsonFormat, String apiKey){
        resetBaseURL();
        int useJson = useJsonFormat ? 1 : 0;
        baseURL += "export/queries/";
        baseURL += "?json=" + useJson;
        baseURL += "&key=" + apiKey;
        return httpQuery.sendGet(baseURL);
    }


    //https://proxycheck.io/dashboard/whitelist/list/?key=111111-222222-333333-444444
    //https://proxycheck.io/dashboard/cors/list/?key=111111-222222-333333-444444

    //to-do: Add whitelist and cors manipulation


    /**
     * HANDLES [LIST and CLEAR] actions for Blacklist, Whitelist, and CORs
     *
     * @param listSelection Valid params = ListSelection.[BLACKLIST/WHITELIST/CORS]
     * @param listAction Valid params = ListAction.[LIST/CLEAR]
     * @param apiKey APIKey from https://proxycheck.io/
     * @return String response from APICall
     */

    public String basicListAccess(ListSelection listSelection, ListAction listAction, String apiKey) throws InvalidParameterException {
        resetBaseURL();
        if (listAction.equals(ListAction.ADD) || listAction.equals(ListAction.REMOVE) || listAction.equals(ListAction.SET)){
            throw new InvalidParameterException("You cannot add POST data to this method");
        }
        baseURL += listSelection.getListType() + "/" + listAction.getAction();
        baseURL += "/?key=" + apiKey;
        return httpQuery.sendGet(baseURL);
    }


    /**
     *
     * HANDLES [add/remove/set]
     *
     * @param listSelection Valid params = ListSelection.[BLACKLIST/WHITELIST/CORS]
     * @param listAction Valid params = ListAction.[ADD/REMOVE/SET]
     * @param apiKey APIKey from https://proxycheck.io/
     * @param ipArray List of IPs the action should be applied to
     * @return String response from APICall
     * @throws InvalidParameterException
     */
    public String modifyList(ListSelection listSelection, ListAction listAction, String apiKey, ArrayList<String> ipArray) throws InvalidParameterException {
        resetBaseURL();
        baseURL += listSelection.getListType() + "/" + listAction.getAction() + "/";
        baseURL += "?key=" + apiKey;
        List<NameValuePair> postParams = new ArrayList<>();

        System.out.println("DebugURL: " + baseURL + " postParams: " + postParams);

        if (ipArray != null) {
            if (listAction.equals(ListAction.LIST) || listAction.equals(ListAction.CLEAR)){
                throw new InvalidParameterException("You cannot send an ipArray with ACTIONS:[LIST/CLEAR] || VALID VALUES [ADD/REMOVE/SET]");
            }
            postParams.add(new BasicNameValuePair("data", ipArray.toString()));
        }

        return httpQuery.sendPOST(baseURL, postParams);
    }

    private void resetBaseURL(){
        baseURL = "https://proxycheck.io/dashboard/";
    }
}
