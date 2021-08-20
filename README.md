# Welcome to ProxyCheck Java API!

# !PAGE UNDER CONSTRUCTION!
SEE JAVADOCS/SOURCE CODE FOR HELP UNTIL I GET FINISHED
Java-Docs: https://defiancecoding.github.io/


This API is for use with https://proxycheck.io/ for proxy detection services.

# Usage

**Main API**
```xml
<!-- Main API -->
<dependency>
  <groupId>io.github.defiancecoding</groupId>
  <artifactId>ProxyCheckJavaAPI</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>

```


**Side stuff** 
```xml

<!-- SOURCE CODE -->
<dependency>
  <groupId>io.github.defiancecoding</groupId>
  <artifactId>ProxyCheckJavaAPI</artifactId>
  <version>1.0-SNAPSHOT</version>
  <classifier>sources</classifier>
</dependency>

<!-- JAVA DOCS -->
<dependency>
  <groupId>io.github.defiancecoding</groupId>
  <artifactId>ProxyCheckJavaAPI</artifactId>
  <version>1.0-SNAPSHOT</version>
  <classifier>javadoc</classifier>
</dependency>
```


## Dashboard

```java
package io.github.defiancecoding.proxycheck.examples;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.github.defiancecoding.proxycheck.api.proxycheck.dashboard.DashboardAPI;
import io.github.defiancecoding.proxycheck.api.proxycheck.dashboard.DashboardUtil;
import io.github.defiancecoding.proxycheck.api.proxycheck.dashboard.ListAction;
import io.github.defiancecoding.proxycheck.api.proxycheck.dashboard.ListSelection;
import io.github.defiancecoding.proxycheck.api.proxycheck.dashboard.results.UsageResults;
import io.github.defiancecoding.proxycheck.exceptions.InvalidParameterException;

import java.util.ArrayList;
import java.util.HashMap;

public class DashboardExample {

    private static final DashboardAPI dashboardAPI = new DashboardAPI();

    public static void main(String[] args) throws InvalidParameterException, JsonProcessingException {

        ArrayList<String> ipArray = new ArrayList<>();

        ipArray.add("1.1.1.1");
        ipArray.add("2.2.2.2");
        ipArray.add("3.3.3.3");
        ipArray.add("4.4.4.4");
        ipArray.add("5.5.5.5");
        ipArray.add("6.6.6.6");
        ipArray.add("7.7.7.7");
        ipArray.add("8.8.8.8");

        // Here we can choose to change ListSelection to whatever we want like BLACKLIST, or CORS
        dashboardAPI.addArrayToList("APIKey", ListSelection.WHITELIST, ipArray);
        // Alternatively you could call upon the dashboard util for more raw based calls
        DashboardUtil dashboardUtil = new DashboardUtil();
        dashboardUtil.modifyList(ListSelection.WHITELIST, ListAction.ADD, "APIKey", ipArray);


        // if we wanted to remove the ips rather than add,
        dashboardAPI.removeArrayFromList("APIKey", ListSelection.WHITELIST, ipArray);
        // Or the alternative
        dashboardUtil.modifyList(ListSelection.WHITELIST, ListAction.REMOVE, "APIKey", ipArray);


        // if we wanted to set a list to the array instead of Adding or Removing, we'd
        dashboardAPI.setList("APIKey", ListSelection.WHITELIST, ipArray);
        // Or the alternative
        dashboardUtil.modifyList(ListSelection.WHITELIST, ListAction.SET, "APIKey", ipArray);

        // if we just wanted to list one of the lists
        dashboardAPI.getList("APIKey", ListSelection.WHITELIST);
        // Or the alternative
        dashboardUtil.basicListAccess(ListSelection.WHITELIST, ListAction.LIST, "APIKey");

        // if we want to clear any list
        dashboardAPI.clearList("APIKey", ListSelection.WHITELIST);
        // or
        dashboardUtil.basicListAccess(ListSelection.WHITELIST, ListAction.CLEAR, "APIKey");


        // Export your queries?
        dashboardAPI.exportQueries(true, "APIKey");
        // Or the alternative
        dashboardUtil.exportQueries(true, "APIKey");

        // Export the tags youve sent
        dashboardAPI.exportTags("APIKey", 100, 0,1, 7);
        // or
        dashboardAPI.exportTags("APIKey", 100, 0, 1000000L, 100000L);
        // same thing for if you use dashboardUtil instead;


        // Get all your detections mapped into a set for you?
        HashMap<String, JsonNode> mappedDetections = dashboardAPI.mapExportedQueriesToArray("APIKey");
        dashboardUtil.exportDetections(true, "APIKey", 50, 0);

        // to loop all the mapped ones

        StringBuilder builder = new StringBuilder();
        for (JsonNode jsonNode : mappedDetections.values()){
            builder.append(jsonNode.toPrettyString());
        }

        System.out.println("builder#toString() : " + builder);


        //Last bit, you wanna grab your account usage?

        UsageResults usageResults = dashboardAPI.mapUsage("APIKey");

        System.out.println("usageResults: "
                + usageResults.getPlanTier()
                + usageResults.getDailyLimit()
                + usageResults.getQueriesTotal()
                + usageResults.getQueriesToday()
                + usageResults.getBurstTokensAvailable()
        );

        // or
        System.out.println("usageResults debug/raw: " + usageResults.toString());


    }
}

```




## Proxy Check

```java
package io.github.defiancecoding.proxycheck.examples;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.defiancecoding.proxycheck.api.proxycheck.check.ProxyCheckSettings;
import io.github.defiancecoding.proxycheck.api.proxycheck.check.results.ProxyResults;

import java.io.IOException;

public class ProxyCheck {

    private static io.github.defiancecoding.proxycheck.api.proxycheck.check.ProxyCheck proxyCheck = new io.github.defiancecoding.proxycheck.api.proxycheck.check.ProxyCheck();
    private static ProxyCheckSettings settings = new ProxyCheckSettings();
    
    private static void setupProxycheckSettings(){
        settings.setApi_key("APIKey");
        settings.setCheck_vpn(true);
        settings.setCheck_asn(true);
        settings.setCheck_node(true);
        settings.setCheck_time(true);
        settings.setRiskLevel(2);
        settings.setCheck_port(true);
        settings.setCheck_seen(true);
        settings.setMax_detection_days(7);
        settings.setVer("Ver");
        settings.setTag("ProxyCheckJavaAPI");

    }

    public static void main(String args[]) throws IOException {
        setupProxycheckSettings();
        
        // Get reponse as a Json but in String format
        String jsonReponse = proxyCheck.getLookupResponse("1.1.1.1");
        
        // Get response from api as a JsonNode
        JsonNode rawJson = proxyCheck.getRawJsonNode("1.1.1.1");
        
        // Breakdown JsonNode into getters and setters
        ProxyResults results = proxyCheck.getAndMapResults("1.1.1.1");
        
        String ip = results.getIp();
        String asn = results.getAsn();
        String proxy = results.getProxy();
        //.... keep going
        
    }

}

```





# Legal
- I am not affiliated with https://proxycheck.io/ and it's trademarks and copyrights, they are their own entity seperate from me (DefianceCoding). I am a freelancer on the side that likes to take on projects and had the permission to make, host, and distribute this package.
- THIS SOFTWARE IS OPEN SOURCE AND IS RELEASED "AS-IS", and is no guarantee for updates or bug fixes.
