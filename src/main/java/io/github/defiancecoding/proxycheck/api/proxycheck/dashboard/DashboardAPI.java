package io.github.defiancecoding.proxycheck.api.proxycheck.dashboard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;

import io.github.defiancecoding.proxycheck.api.proxycheck.dashboard.results.UsageResults;
import io.github.defiancecoding.proxycheck.api.webconnection.HTTPQuery;
import io.github.defiancecoding.proxycheck.exceptions.InvalidParameterException;


public class DashboardAPI
{
  private final DashboardUtil dashboardUtil = new DashboardUtil();


  /**
   * Get a printout of the selected list from our API
   *
   * @param apiKey APIKey from https://proxycheck.io
   * @param listSelection Which list do you want to modify
   * @return jsonString response from HTTPQuery;
   * @see HTTPQuery
   * @throws InvalidParameterException
   */
  public String getList(String apiKey, ListSelection listSelection) throws InvalidParameterException {
    return this.dashboardUtil.basicListAccess(listSelection, ListAction.LIST, apiKey);
  }


  /**
   * THIS METHOD WILL OVERWRITE YOUR CURRENT DATA IN THE LIST
   *
   * @param apiKey APIKey from https://proxycheck.io
   * @param listSelection Which list do you want to modify
   * @param ipArray Array of ips that you want to set a list to
   * @return jsonString response from HTTPQuery
   * @throws InvalidParameterException
   */
  public String setList(String apiKey, ListSelection listSelection, ArrayList<String> ipArray) throws InvalidParameterException {
    return this.dashboardUtil.modifyList(listSelection, ListAction.SET, apiKey, ipArray);
  }

  /**
   * Clears the selected list
   *
   * @param apiKey APIKey from https://proxycheck.io
   * @param listSelection Which list do you want to modify
   * @return jsonString response from HTTPQuery
   * @throws InvalidParameterException
   */
  public String clearList(String apiKey, ListSelection listSelection) throws InvalidParameterException {
    return this.dashboardUtil.basicListAccess(listSelection, ListAction.CLEAR, apiKey);
  }

  /**
   * Adds the ipArray to the selected list
   *
   * @param apiKey APIKey from https://proxycheck.io
   * @param listSelection Which list do you want to modify
   * @param ipArray Array of ips to pass an ADD command with the HTTPQuery
   * @return jsonString response from HTTPQuery
   * @throws InvalidParameterException
   */
  public String addArrayToList(String apiKey, ListSelection listSelection, ArrayList<String> ipArray) throws InvalidParameterException {
    return this.dashboardUtil.modifyList(listSelection, ListAction.ADD, apiKey, ipArray);
  }


  /**
   * Removes the ipArray from the selected list
   *
   * @param apiKey APIKey from https://proxycheck.io
   * @param listSelection Which list do you want to modify
   * @param ipArray Array of ips to pass a REMOVE command with the HTTPQuery
   * @return jsonString response from HTTPQuery
   * @throws InvalidParameterException
   */
  public String removeArrayFromList(String apiKey, ListSelection listSelection, ArrayList<String> ipArray) throws InvalidParameterException {
    return this.dashboardUtil.modifyList(listSelection, ListAction.REMOVE, apiKey, ipArray);
  }

  /**
   * Export your queries
   *
   * @param useJsonFormatting get response as JSON String
   * @param apiKey APIKey from https://proxycheck.io
   * @return jsonString response from HTTPQuery
   */
  public String exportQueries(boolean useJsonFormatting, String apiKey) {
    return this.dashboardUtil.exportQueries(useJsonFormatting, apiKey);
  }


  /**
   * Export your API Usage
   *
   * @param apiKey APIKey from https://proxycheck.io
   * @return jsonString response from HTTPQuery
   */
  public String exportUsage(String apiKey) {
    return this.dashboardUtil.exportUsage(apiKey);
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
  public String exportTags(String apiKey, int limit, int offset, long startTime, long endTime) {
    return this.dashboardUtil.exportTags(apiKey, limit, offset, startTime, endTime);
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
    return this.dashboardUtil.exportTags(apiKey, limit, offset, addresses, days);
  }


  /**
   * Maps all your queries to a JsonNode all mapped into a HashMap K: String V: JsonNode
   *
   * @param apiKey APIKey from https://proxycheck.io
   * @return HashMap of Queries stored as JsonNode
   * @throws JsonProcessingException
   */
  public HashMap<String, JsonNode> mapExportedQueriesToArray(String apiKey) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode rawNode = mapper.readTree(exportQueries(true, apiKey));
    
    HashMap<String, JsonNode> queryLists = new HashMap<>();
    
    JsonNode today = rawNode.path("TODAY");
    JsonNode yesterday = rawNode.path("YESTERDAY");
    queryLists.put("TODAY", today);
    queryLists.put("YESTERDAY", yesterday);
    
    for (int i = 2; i < 30; i++) {
      JsonNode subNode = rawNode.path(i + " DAYS AGO");
      queryLists.put(String.valueOf(i), subNode);
    } 
    return queryLists;
  }


  /**
   * Maps your usage to an instanced class
   *
   * @param apikey String - APIKey from https://proxycheck.io
   * @return returns an instanced class with getters and setters for ease of access
   * @see UsageResults
   * @throws JsonProcessingException
   */
  public UsageResults mapUsage(String apikey) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode rawNode = mapper.readTree(exportUsage(apikey));
    
    UsageResults usageResults = new UsageResults();
    usageResults.setBurstTokensAvailable(rawNode.get("Burst Tokens Available").asInt());
    usageResults.setQueriesToday(rawNode.get("Queries Today").asInt());
    usageResults.setDailyLimit(rawNode.get("Daily Limit").asInt());
    usageResults.setQueriesTotal(rawNode.get("Queries Total").asInt());
    usageResults.setPlanTier(rawNode.get("Plan Tier").asText());
    
    return usageResults;
  }


}
