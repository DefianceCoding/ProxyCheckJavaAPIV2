package xyz.defiancecoding.proxycheck.api.proxycheck.dashboard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import xyz.defiancecoding.proxycheck.api.proxycheck.dashboard.results.UsageResults;
import xyz.defiancecoding.proxycheck.exceptions.InvalidParameterException;

import java.util.ArrayList;
import java.util.HashMap;

public class DashboardAPI {

    private final DashboardUtil dashboardUtil = new DashboardUtil();

    public DashboardAPI(){

    }


    /**
     *
     * @param apiKey APIKey from https://proxycheck.io/
     * @param listSelection Valid Lists = ListSelection.[WHITELIST/BLACKLIST/CORS]
     * @return String response from APICall
     * @throws InvalidParameterException
     */
    public String getList(String apiKey, ListSelection listSelection) throws InvalidParameterException {
        return dashboardUtil.basicListAccess(listSelection, ListAction.LIST, apiKey);
    }

    /**
     *
     * @param apiKey APIKey from https://proxycheck.io/
     * @param listSelection listSelection Valid Lists = ListSelection.[WHITELIST/BLACKLIST/CORS]
     * @param ipArray ArrayList of ips you intend to set and override the old
     * @return String response from APICall
     * @throws InvalidParameterException
     */
    public String setList(String apiKey, ListSelection listSelection, ArrayList<String> ipArray) throws InvalidParameterException {
        return dashboardUtil.modifyList(listSelection, ListAction.SET, apiKey, ipArray);
    }


    /**
     *
     * @param apiKey APIKey from https://proxycheck.io/
     * @param listSelection Valid Lists = ListSelection.[WHITELIST/BLACKLIST/CORS]
     * @return String response from APICall
     * @throws InvalidParameterException
     */
    public String clearList(String apiKey, ListSelection listSelection) throws InvalidParameterException {
        return dashboardUtil.basicListAccess(listSelection, ListAction.CLEAR, apiKey);
    }

    /**
     *
     * @param apiKey APIKey from https://proxycheck.io/
     * @param listSelection Valid Lists = ListSelection.[WHITELIST/BLACKLIST/CORS]
     * @param ipArray ArrayList of ips you intend to add
     * @return String response from APICall
     * @throws InvalidParameterException
     */
    public String addArrayToList(String apiKey, ListSelection listSelection, ArrayList<String> ipArray) throws InvalidParameterException {
        return dashboardUtil.modifyList(listSelection, ListAction.ADD, apiKey, ipArray);
    }

    /**
     *
     * @param apiKey APIKey from https://proxycheck.io/
     * @param listSelection Valid Lists = ListSelection.[WHITELIST/BLACKLIST/CORS]
     * @param ipArray ArrayList of ips you intend to remove
     * @return String response from APICall
     * @throws InvalidParameterException
     */
    public String removeArrayFromList(String apiKey, ListSelection listSelection, ArrayList<String> ipArray) throws InvalidParameterException {
        return dashboardUtil.modifyList(listSelection, ListAction.REMOVE, apiKey, ipArray);
    }

    /**
     *
     * @param useJsonFormatting
     * @param apiKey APIKey from https://proxycheck.io/
     * @return String response from APICall
     */
    public String exportQueries(boolean useJsonFormatting, String apiKey){
        return dashboardUtil.exportQueries(useJsonFormatting, apiKey);
    }

    /**
     *
     * @param apiKey APIKey from https://proxycheck.io/
     * @return String response from APICall
     */
    public String exportUsage(String apiKey){
        return dashboardUtil.exportUsage(apiKey);
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
    public String exportTags(String apiKey, int limit, int offset, long startTime, long endTime){
        return dashboardUtil.exportTags(apiKey, limit, offset, startTime, endTime);
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
        return dashboardUtil.exportTags(apiKey, limit, offset, addresses, days);
    }

    /**
     *
     * @param apiKey APIKey from https://proxycheck.io/
     * @return HashMap of all the queries isolated into individual JsonNodes
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

        for (int i = 2; i < 30; i++){
            JsonNode subNode = rawNode.path(i + " DAYS AGO");
            queryLists.put(String.valueOf(i), subNode);
        }
        return queryLists;
    }

    /**
     *
     * @param apikey APIKey from https://proxycheck.io/
     * @return UsageResults, maps data to class for easier access
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
