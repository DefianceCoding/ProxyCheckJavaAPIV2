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

    //HashMap Stuff Start//
    private HashMap<String, String> attackHistory = new HashMap<>();

    private String total;
    private String vulnerabilityProbing;
    private String loginAttempt;
    private String registrationAttempt;
    //hashMap Stuff Stop//

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
        this.attackHistory = attackHistory;
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

    public HashMap<String, String> getAttackHistory() {
        return attackHistory;
    }

    public void setAttackHistory(HashMap<String, String> attackHistory) {
        this.attackHistory = attackHistory;
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


    @Override
    public String toString() {
        return "ProxyResults{" +
                "status='" + status + '\'' +
                ", node='" + node + '\'' +
                ", ip='" + ip + '\'' +
                ", asn='" + asn + '\'' +
                ", provider='" + provider + '\'' +
                ", continent='" + continent + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", isoCode='" + isoCode + '\'' +
                ", proxy='" + proxy + '\'' +
                ", type='" + type + '\'' +
                ", port='" + port + '\'' +
                ", risk='" + risk + '\'' +
                ", attackHistory=" + attackHistory +
                ", total='" + total + '\'' +
                ", vulnerabilityProbing='" + vulnerabilityProbing + '\'' +
                ", loginAttempt='" + loginAttempt + '\'' +
                ", registrationAttempt='" + registrationAttempt + '\'' +
                ", lastSeenHuman='" + lastSeenHuman + '\'' +
                ", lastSeenUnix='" + lastSeenUnix + '\'' +
                ", queryTime='" + queryTime + '\'' +
                '}';
    }

}
