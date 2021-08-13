package xyz.defiancecoding.proxycheck.api.proxycheck.check;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import xyz.defiancecoding.proxycheck.api.webconnection.HTTPQuery;

import java.io.IOException;

public class ProxyCheck {

    private final ProxyCheckSettings settings = new ProxyCheckSettings();
    private final HTTPQuery httpQuery = new HTTPQuery();

    public ProxyCheck(){

    }

    private String urlBuilder(String ip){
        String baseURL = "http://proxycheck.io/v2/" + ip;
        baseURL += "?key=" + (settings.getApi_key());
        baseURL += "&vpn=" + (settings.isCheck_vpn() ? 1 : 0 );
        baseURL += "&asn=" + (settings.isCheck_asn() ? 1 : 0 );
        baseURL += "&node=" + (settings.isCheck_node() ? 1 : 0 );
        baseURL += "&time=" + (settings.isCheck_port() ? 1 : 0 );
        baseURL += "&risk=" + settings.setRiskLevel();
        baseURL += "&port=" + (settings.isCheck_port());
        baseURL += "&seen=" + (settings.isCheck_seen() ? 1 : 0);
        baseURL += "&time=" + (settings.isCheck_time() ? 1 : 0);
        baseURL += "&days=" + settings.getMax_detection_days();
        if (settings.getVer() != null) {
            baseURL += "&ver=" + settings.getVer();
        }
        baseURL += "&tag=" + (settings.getTag());

        return baseURL;
    }


    //This is what you want to access for a basic String Json format
    public String getLookupResponse(String ip){
        return httpQuery.sendGet(urlBuilder(ip));
    }


    //If you want to use JacksonDatabind for JsonHandling use the following below
    //Otherwise remove this method and replace it for one that will fit your program
    public JsonNode getRawJsonNode(String ip) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(getLookupResponse(ip));

        System.out.println("getRawJsonNode(); #JsonNode toString - " + jsonNode.toPrettyString());
        return jsonNode;
    }


    public ProxyResults mapThenReturnResult(String ip)  {
        ProxyResults result = new ProxyResults();
        try {

            JsonNode rawNode = getRawJsonNode(ip);

            result.setStatus(rawNode.get("status").asText());
            result.setNode(rawNode.get("node").asText());
            result.setQueryTime(rawNode.get("query time").asText());

            ObjectMapper mapper = new ObjectMapper();
            System.out.println("readTree: " + mapper.readTree(rawNode.get(ip).asText()));
            JsonNode subNode = mapper.readTree(String.valueOf(rawNode.get(ip)));

            System.out.println("RawNode: " + rawNode);
            System.out.println("subNode: " + subNode);



            result.setIp(ip);
            result.setProvider(subNode.get("provider").asText());
            result.setContinent(subNode.get("continent").asText());
            result.setCountry(subNode.get("country").asText());
            result.setCity(subNode.get("city").asText());
            result.setRegion(subNode.get("region").asText());
            result.setRegionCode(subNode.get("regioncode").asText());
            result.setLatitude(subNode.get("latitude").asText());
            result.setLongitude(subNode.get("longitude").asText());
            result.setIsoCode(subNode.get("isocode").asText());
            result.setProxy(subNode.get("proxy").asText());
            result.setType(subNode.get("type").asText());
            result.setPort(subNode.get("port").asText("0000").toString());
            result.setRisk(subNode.get("risk").asText());
            result.setLastSeenHuman(subNode.get("last seen human").asText());
            result.setLastSeenUnix(subNode.get("last seen unix").asText());

            return result;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }



}
