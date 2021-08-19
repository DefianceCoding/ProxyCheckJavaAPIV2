package com.github.defiancecoding.proxycheck.api.proxycheck.dashboard;

public enum ListSelection
{
  BLACKLIST, WHITELIST, CORS;
  
  public String getListType() {
    switch (this) {
      case BLACKLIST:
        return "blacklist";
      case WHITELIST:
        return "whitelist";
      case CORS:
        return "cors";
    } 
    return null;
  }
}
