package xyz.defiancecoding.proxycheck.api.proxycheck.dashboard;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import xyz.defiancecoding.proxycheck.api.webconnection.HTTPQuery;
import xyz.defiancecoding.proxycheck.exceptions.InvalidParameterException;


public class DashboardUtil
{
  private final HTTPQuery httpQuery = new HTTPQuery();
  private String baseURL = "https://proxycheck.io/dashboard/";

  public String exportDetections(boolean useJsonFormat, String apiKey, int limit, int offset) {
    int useJson = useJsonFormat ? 1 : 0;
    this.baseURL += "export/detections/";
    this.baseURL += "?json" + useJson;
    this.baseURL += "&key=" + apiKey;
    this.baseURL += "&limit=" + limit;
    this.baseURL += "&offset=" + offset;
    
    return this.httpQuery.sendGet(this.baseURL);
  }

  public String exportTags(String apiKey, int limit, int offset, int addresses, int days) {
    this.baseURL += "export/tags/";
    this.baseURL += "?key=" + apiKey;
    this.baseURL += "&limit=" + limit;
    this.baseURL += "&offset=" + offset;
    this.baseURL += "&addresses=" + addresses;
    this.baseURL += "&days=" + days;
    return this.httpQuery.sendGet(this.baseURL);
  }
  
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

  public String exportUsage(String apiKey) {
    resetBaseURL();
    this.baseURL += "export/usage/";
    this.baseURL += "?key=" + apiKey;
    return this.httpQuery.sendGet(this.baseURL);
  }

  public String exportQueries(boolean useJsonFormat, String apiKey) {
    resetBaseURL();
    int useJson = useJsonFormat ? 1 : 0;
    this.baseURL += "export/queries/";
    this.baseURL += "?json=" + useJson;
    this.baseURL += "&key=" + apiKey;
    return this.httpQuery.sendGet(this.baseURL);
  }

  public String basicListAccess(ListSelection listSelection, ListAction listAction, String apiKey) throws InvalidParameterException {
    resetBaseURL();
    if (listAction.equals(ListAction.ADD) || listAction.equals(ListAction.REMOVE) || listAction.equals(ListAction.SET)) {
      throw new InvalidParameterException("You cannot add POST data to this method");
    }
    this.baseURL += listSelection.getListType() + "/" + listAction.getAction();
    this.baseURL += "/?key=" + apiKey;
    return this.httpQuery.sendGet(this.baseURL);
  }
  
  public String modifyList(ListSelection listSelection, ListAction listAction, String apiKey, ArrayList<String> ipArray) throws InvalidParameterException {
    resetBaseURL();
    this.baseURL += listSelection.getListType() + "/" + listAction.getAction() + "/";
    this.baseURL += "?key=" + apiKey;
    List<NameValuePair> postParams = new ArrayList<>();
    
    System.out.println("DebugURL: " + this.baseURL + " postParams: " + postParams);
    
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
}
