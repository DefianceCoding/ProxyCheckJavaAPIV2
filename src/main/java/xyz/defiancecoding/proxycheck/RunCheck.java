package xyz.defiancecoding.proxycheck;

import xyz.defiancecoding.proxycheck.api.proxycheck.dashboard.DashboardUtil;
import xyz.defiancecoding.proxycheck.exceptions.InvalidParameterException;

import java.io.IOException;
import java.util.ArrayList;

public class RunCheck {

    public static void main(String args[]) throws IOException {

        DashboardUtil dashboard = new DashboardUtil();

        ArrayList<String> ipArray = new ArrayList<String>();

        ipArray.add("1.1.1.1");
        ipArray.add("2.2.2.2");
        ipArray.add("3.3.3.3");
        ipArray.add("4.4.4.4");
        ipArray.add("5.5.5.5");

        try {
            System.out.println(dashboard.modifyList("blacklist", "remove", "504152-5u7n30-bv3o81-187971", ipArray));
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }
    }

}
