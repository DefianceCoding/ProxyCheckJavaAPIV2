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

        try {

            ProxyResults result = new ProxyResults();

            JsonNode rawNode = getRawJsonNode(ip);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode subNode = mapper.readTree(rawNode.get(ip).asText());
            System.out.println("tempTest: Map: " + subNode.toPrettyString());
            result.setIp(ip);
            result.setAsn(subNode.get("asn").asText());
            result.setProvider(subNode.get("provider").asText());
            result.setContinent(subNode.get("continent").asText());
            result.setCountry(subNode.get("country").asText());
            result.setIsoCode(subNode.get("isocode").asText());
            result.setLatitude(subNode.get("latitude").asDouble());
            result.setLongitude(subNode.get("longitude").asDouble());
            result.setProxy(subNode.get("proxy").asText());
            result.setType(subNode.get("type").asText());
            result.setRisk(subNode.get("risk").asInt());

            return result;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }



}
