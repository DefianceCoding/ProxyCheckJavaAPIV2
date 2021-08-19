package io.github.defiancecoding.proxycheck;

public class DebugHandler {

    private boolean isDebug;
    private int verboseLevel;

    //SOMETHING HERE LATER ON!

    public DebugHandler(){

    }

    private DebugHandler(boolean isDebug){
        this.isDebug = isDebug;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }
}
