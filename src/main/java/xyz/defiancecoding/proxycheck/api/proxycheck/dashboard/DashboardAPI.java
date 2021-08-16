package xyz.defiancecoding.proxycheck.api.proxycheck.dashboard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import xyz.defiancecoding.proxycheck.api.proxycheck.dashboard.results.UsageResults;
import xyz.defiancecoding.proxycheck.exceptions.InvalidParameterException;


public class DashboardAPI
{
  private final DashboardUtil dashboardUtil = new DashboardUtil();

  public String getList(String apiKey, ListSelection listSelection) throws InvalidParameterException {
    return this.dashboardUtil.basicListAccess(listSelection, ListAction.LIST, apiKey);
  }


  public String setList(String apiKey, ListSelection listSelection, ArrayList<String> ipArray) throws InvalidParameterException {
    return this.dashboardUtil.modifyList(listSelection, ListAction.SET, apiKey, ipArray);
  }

  public String clearList(String apiKey, ListSelection listSelection) throws InvalidParameterException {
    return this.dashboardUtil.basicListAccess(listSelection, ListAction.CLEAR, apiKey);
  }

  public String addArrayToList(String apiKey, ListSelection listSelection, ArrayList<String> ipArray) throws InvalidParameterException {
    return this.dashboardUtil.modifyList(listSelection, ListAction.ADD, apiKey, ipArray);
  }

  public String removeArrayFromList(String apiKey, ListSelection listSelection, ArrayList<String> ipArray) throws InvalidParameterException {
    return this.dashboardUtil.modifyList(listSelection, ListAction.REMOVE, apiKey, ipArray);
  }

  public String exportQueries(boolean useJsonFormatting, String apiKey) {
    return this.dashboardUtil.exportQueries(useJsonFormatting, apiKey);
  }

  public String exportUsage(String apiKey) {
    return this.dashboardUtil.exportUsage(apiKey);
  }

  public String exportTags(String apiKey, int limit, int offset, long startTime, long endTime) {
    return this.dashboardUtil.exportTags(apiKey, limit, offset, startTime, endTime);
  }

  public String exportTags(String apiKey, int limit, int offset, int addresses, int days) {
    return this.dashboardUtil.exportTags(apiKey, limit, offset, addresses, days);
  }

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
