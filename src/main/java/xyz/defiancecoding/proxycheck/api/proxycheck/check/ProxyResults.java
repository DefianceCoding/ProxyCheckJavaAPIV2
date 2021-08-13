package xyz.defiancecoding.proxycheck.api.proxycheck.check;

public class ProxyResults {

    private String ip;
    private String asn;
    private String provider;
    private String isoCode;
    private String country;
    private String continent;
    private double latitude;
    private double longitude;
    private String proxy;
    private String type;
    private int risk;

    public ProxyResults() {
    }

    public ProxyResults newInstance(String ip, String asn, String provider, String isoCode, String country, String continent, double latitude, double longitude, String proxy, String type, int risk) {
        ProxyResults result = new ProxyResults();
        this.ip = ip;
        this.asn = asn;
        this.provider = provider;
        this.isoCode = isoCode;
        this.country = country;
        this.continent = continent;
        this.latitude = latitude;
        this.longitude = longitude;
        this.proxy = proxy;
        this.type = type;
        this.risk = risk;
        return result;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAsn() {
        return this.asn;
    }

    public void setAsn(String asn) {
        this.asn = asn;
    }

    public String getProvider() {
        return this.provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getProxy() {
        return this.proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRisk() {
        return this.risk;
    }

    public void setRisk(int risk) {
        this.risk = risk;
    }

    public void setIsoCode(String isoCode){
        this.isoCode = isoCode;
    }

    public String getIsoCode(){
        return this.isoCode;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }


    public String toString() {
        return "Results{ip='" + this.ip + '\'' + ", asn='" + this.asn + '\'' + ", provider='" + this.provider + '\'' + ", country='" + this.country + '\'' + ", isoCode='" + this.isoCode + '\'' + ", continent='" + this.continent + '\'' + ", latitude='" + this.latitude + '\'' + ", longitude='" + this.longitude + '\'' + ", proxy='" + this.proxy + '\'' + ", type='" + this.type + '\'' + ", risk='" + this.risk + '\'' + '}';
    }

}
