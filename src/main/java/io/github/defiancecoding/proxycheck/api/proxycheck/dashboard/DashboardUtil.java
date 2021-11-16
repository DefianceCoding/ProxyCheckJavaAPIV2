package io.github.defiancecoding.proxycheck.api.proxycheck.dashboard;

import java.util.ArrayList;
import java.util.List;

import io.github.defiancecoding.proxycheck.DebugHandler;
import io.github.defiancecoding.proxycheck.exceptions.InvalidParameterException;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import io.github.defiancecoding.proxycheck.api.webconnection.HTTPQuery;


public class DashboardUtil
{
  private final HTTPQuery httpQuery = new HTTPQuery();
  private String baseURL = "https://proxycheck.io/dashboard/";

  private final DebugHandler debugHandler = new DebugHandler();

  /**
   *
   * Used to get output your previous detections.
   *
   * @param useJsonFormat exports detection as a JSON Type String
   * @param apiKey APIKey from https://proxycheck.io/
   * @param limit limit of detections to output
   * @param offset how many detections to skip before reading
   * @return jsonString response from HTTPQuery
   */

  public String exportDetections(boolean useJsonFormat, String apiKey, int limit, int offset) {
    int useJson = useJsonFormat ? 1 : 0;
    this.baseURL += "export/detections/";
    this.baseURL += "?json" + useJson;
    this.baseURL += "&key=" + apiKey;
    this.baseURL += "&limit=" + limit;
    this.baseURL += "&offset=" + offset;
    
    return this.httpQuery.sendGet(this.baseURL);
  }


  /**
   * Used to grab the tags sent along with your proxycheck queries and their stats.
   *
   * @param apiKey APIKey from https://proxycheck.io/
   * @param limit limit of detections to output
   * @param offset how many detections to skip before reading
   * @param addresses amount of addresses to query
   * @param days amount of days to search back
   * @return jsonString response from HTTPQuery
   */

  public String exportTags(String apiKey, int limit, int offset, int addresses, int days) {
    this.baseURL += "export/tags/";
    this.baseURL += "?key=" + apiKey;
    this.baseURL += "&limit=" + limit;
    this.baseURL += "&offset=" + offset;
    this.baseURL += "&addresses=" + addresses;
    this.baseURL += "&days=" + days;
    return this.httpQuery.sendGet(this.baseURL);
  }


  /**
   * Used to grab the tags sent along with your proxycheck queries and their stats.
   *
   * @param apiKey APIKey from https://proxycheck.io
   * @param limit number of tags to list
   * @param offset how many tags to skip
   * @param start start date in long Milliseconds Time Units
   * @param end start date in long Milliseconds Time Units
   * @return jsonString response from HTTPQuery
   */
  public String exportTags(String apiKey, int limit, int offset, long start, long end) {
    resetBaseURL();
    this.baseURL += "export/tags/";
    this.baseURL += "?key=" + apiKey;
    this.baseURL += "&limit=" + limit;
    this.baseURL += "&offset=" + offset;
    this.baseURL += "&start=" + start;
    this.baseURL += "&end=" + end;
    return this.httpQuery.sendGet(this.baseURL);
  }

  /**
   * Grabs your account usage
   *
   * @param apiKey APIKey from https://proxycheck.io/
   * @return jsonString response from HTTPQuery
   */
  public String exportUsage(String apiKey) {
    resetBaseURL();
    this.baseURL += "export/usage/";
    this.baseURL += "?key=" + apiKey;
    return this.httpQuery.sendGet(this.baseURL);
  }

  /**
   *
   * @param useJsonFormat Tells the API to print the result in Json Formatting or not
   * @param apiKey APIKey from https://www.proxycheck.io
   * @return response of all your queries
   */
  public String exportQueries(boolean useJsonFormat, String apiKey) {
    resetBaseURL();
    int useJson = useJsonFormat ? 1 : 0;
    this.baseURL += "export/queries/";
    this.baseURL += "?json=" + useJson;
    this.baseURL += "&key=" + apiKey;
    return this.httpQuery.sendGet(this.baseURL);
  }

  /**
   * Basic list access that allows you to CLEAR or LIST a list stored on your dashboard
   *
   * @param listSelection Which list to access
   * @param listAction What to do with said list
   * @param apiKey APIKey from https://proxycheck.io
   * @return jsonString response from HTTPQuery
   * @throws InvalidParameterException Throws when you input an invalid parameter
   */
  public String basicListAccess(ListSelection listSelection, ListAction listAction, String apiKey) throws InvalidParameterException {
    resetBaseURL();
    if (listAction.equals(ListAction.ADD) || listAction.equals(ListAction.REMOVE) || listAction.equals(ListAction.SET)) {
      throw new InvalidParameterException("You cannot add POST data to this method");
    }
    this.baseURL += listSelection.getListType() + "/" + listAction.getAction();
    this.baseURL += "/?key=" + apiKey;
    return this.httpQuery.sendGet(this.baseURL);
  }

  /**
   *  Modify selected list with selected action and ipArray
   *
   * @param listSelection Which list to access
   * @param listAction What to do with said list
   * @param apiKey APIKey from https://proxycheck.io
   * @param ipArray List of ips you want to modify to the list
   * @return jsonString response from HTTPQuery
   * @throws InvalidParameterException Throws when you input an invalid parameter
   */

  public String modifyList(ListSelection listSelection, ListAction listAction, String apiKey, ArrayList<String> ipArray) throws InvalidParameterException {
    resetBaseURL();
    this.baseURL += listSelection.getListType() + "/" + listAction.getAction() + "/";
    this.baseURL += "?key=" + apiKey;
    List<NameValuePair> postParams = new ArrayList<>();

    if (ipArray != null) {
      if (listAction.equals(ListAction.LIST) || listAction.equals(ListAction.CLEAR)) {
        throw new InvalidParameterException("You cannot send an ipArray with ACTIONS:[LIST/CLEAR] || VALID VALUES [ADD/REMOVE/SET]");
      }
      postParams.add(new BasicNameValuePair("data", ipArray.toString()));
    }
    return this.httpQuery.sendPOST(this.baseURL, postParams);
  }
  
  private void resetBaseURL() {
    this.baseURL = "https://proxycheck.io/dashboard/";
  }

  private boolean isDebug;

  private void setDebug(boolean debugMode) {
    this.isDebug = debugMode;
  }

  private boolean isDebug(){
    return this.isDebug;
  }
}
