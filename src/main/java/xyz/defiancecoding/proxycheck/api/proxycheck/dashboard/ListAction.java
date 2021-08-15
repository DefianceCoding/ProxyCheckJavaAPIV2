package xyz.defiancecoding.proxycheck.api.proxycheck.dashboard;

public enum ListAction {

    LIST, CLEAR, ADD, REMOVE, SET;

    public String getAction(){
        switch(this) {

            case LIST:
                return "list";
            case CLEAR:
                return "clear";
            case ADD:
                return "add";
            case REMOVE:
                return "remove";
            case SET:
                return "set";
            default:
                return null;
        }
    }


}
