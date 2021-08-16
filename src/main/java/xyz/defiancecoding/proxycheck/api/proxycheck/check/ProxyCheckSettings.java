package xyz.defiancecoding.proxycheck.api.proxycheck.check;

public class ProxyCheckSettings
{
  private String api_key = "6t0l02-643w48-111876-2m1i08";
  private boolean check_vpn = true;
  private boolean check_asn = true;
  private boolean check_node = true;
  private boolean check_time = true;
  private int check_risk = 1;
  private boolean check_port = true;
  private boolean check_seen = true;
  private int max_detection_days = 7;
  private String ver;
  private String tag = "Defiant-Proxy-Detection-System";
  
  public String getApi_key() {
    return this.api_key;
  }
  
  public void setApi_key(String api_key) {
    this.api_key = api_key;
  }
  
  public boolean isCheck_vpn() {
    return this.check_vpn;
  }
  
  public void setCheck_vpn(boolean check_vpn) {
    this.check_vpn = check_vpn;
  }
  
  public boolean isCheck_asn() {
    return this.check_asn;
  }
  
  public void setCheck_asn(boolean check_asn) {
    this.check_asn = check_asn;
  }
  
  public boolean isCheck_node() {
    return this.check_node;
  }
  
  public void setCheck_node(boolean check_node) {
    this.check_node = check_node;
  }
  
  public boolean isCheck_time() {
    return this.check_time;
  }
  
  public void setCheck_time(boolean check_time) {
    this.check_time = check_time;
  }
  
  public int setRiskLevel() {
    return this.check_risk;
  }
  public void setCheck_risk(int check_risk) {
    this.check_risk = check_risk;
  }
  
  public boolean isCheck_port() {
    return this.check_port;
  }
  
  public void setCheck_port(boolean check_port) {
    this.check_port = check_port;
  }
  
  public boolean isCheck_seen() {
    return this.check_seen;
  }
  
  public void setCheck_seen(boolean check_seen) {
    this.check_seen = check_seen;
  }
  
  public int getMax_detection_days() {
    return this.max_detection_days;
  }
  
  public void setMax_detection_days(int max_detection_days) {
    this.max_detection_days = max_detection_days;
  }
  
  public String getTag() {
    return this.tag;
  }
  
  public void setTag(String set_tag) {
    this.tag = set_tag;
  }
  
  public void setVer(String ver) {
    this.ver = ver;
  }
  
  public String getVer() {
    return this.ver;
  }
}
