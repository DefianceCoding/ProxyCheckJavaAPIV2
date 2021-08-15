package xyz.defiancecoding.proxycheck.api.proxycheck.check;

import java.util.HashMap;

public class ProxyResults {

    private String status;
    private String node;
    private String ip;
    private String asn;
    private String provider;
    private String continent;
    private String country;
    private String city;
    private String region;
    private String regionCode;
    private String latitude;
    private String longitude;
    private String isoCode;
    private String proxy;
    private String type;
    private String port;
    private String risk;

    private String total;
    private String vulnerabilityProbing;
    private String loginAttempt;
    private String registrationAttempt;
    private String forumSpam;
    private String lastSeenHuman;
    private String lastSeenUnix;
    private String queryTime;


    public ProxyResults() {

    }


    public ProxyResults newInstance(String status, String node, String ip, String asn, String provider, String continent, String country, String city, String region, String regionCode, String latitude, String longitude, String isoCode, String proxy, String type, String port, String risk, HashMap<String, String> attackHistory, String total, String vulnerabilityProbing, String loginAttempt, String registrationAttempt, String lastSeenHuman, String lastSeenUnix, String queryTime) {
        ProxyResults results = new ProxyResults();

        this.status = status;
        this.node = node;
        this.ip = ip;
        this.asn = asn;
        this.provider = provider;
        this.continent = continent;
        this.country = country;
        this.city = city;
        this.region = region;
        this.regionCode = regionCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isoCode = isoCode;
        this.proxy = proxy;
        this.type = type;
        this.port = port;
        this.risk = risk;
        this.total = total;
        this.vulnerabilityProbing = vulnerabilityProbing;
        this.loginAttempt = loginAttempt;
        this.registrationAttempt = registrationAttempt;
        this.lastSeenHuman = lastSeenHuman;
        this.lastSeenUnix = lastSeenUnix;
        this.queryTime = queryTime;
        return results;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAsn() {
        return asn;
    }

    public void setAsn(String asn) {
        this.asn = asn;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getVulnerabilityProbing() {
        return vulnerabilityProbing;
    }

    public void setVulnerabilityProbing(String vulnerabilityProbing) {
        this.vulnerabilityProbing = vulnerabilityProbing;
    }

    public String getLoginAttempt() {
        return loginAttempt;
    }

    public void setLoginAttempt(String loginAttempt) {
        this.loginAttempt = loginAttempt;
    }

    public String getRegistrationAttempt() {
        return registrationAttempt;
    }

    public void setRegistrationAttempt(String registrationAttempt) {
        this.registrationAttempt = registrationAttempt;
    }

    public String getLastSeenHuman() {
        return lastSeenHuman;
    }

    public void setLastSeenHuman(String lastSeenHuman) {
        this.lastSeenHuman = lastSeenHuman;
    }

    public String getLastSeenUnix() {
        return lastSeenUnix;
    }

    public void setLastSeenUnix(String lastSeenUnix) {
        this.lastSeenUnix = lastSeenUnix;
    }

    public String getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(String queryTime) {
        this.queryTime = queryTime;
    }

    public String getForumSpam() {
        return forumSpam;
    }

    public void setForumSpam(String forumSpam) {
        this.forumSpam = forumSpam;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ProxyResults{");
        sb.append("status='").append(status).append('\'');
        sb.append(", node='").append(node).append('\'');
        sb.append(", ip='").append(ip).append('\'');
        sb.append(", asn='").append(asn).append('\'');
        sb.append(", provider='").append(provider).append('\'');
        sb.append(", continent='").append(continent).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", region='").append(region).append('\'');
        sb.append(", regionCode='").append(regionCode).append('\'');
        sb.append(", latitude='").append(latitude).append('\'');
        sb.append(", longitude='").append(longitude).append('\'');
        sb.append(", isoCode='").append(isoCode).append('\'');
        sb.append(", proxy='").append(proxy).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", port='").append(port).append('\'');
        sb.append(", risk='").append(risk).append('\'');
        sb.append(", total='").append(total).append('\'');
        sb.append(", vulnerabilityProbing='").append(vulnerabilityProbing).append('\'');
        sb.append(", loginAttempt='").append(loginAttempt).append('\'');
        sb.append(", registrationAttempt='").append(registrationAttempt).append('\'');
        sb.append(", forumSpam='").append(forumSpam).append('\'');
        sb.append(", lastSeenHuman='").append(lastSeenHuman).append('\'');
        sb.append(", lastSeenUnix='").append(lastSeenUnix).append('\'');
        sb.append(", queryTime='").append(queryTime).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
