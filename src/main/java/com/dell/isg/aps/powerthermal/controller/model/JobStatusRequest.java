/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
/**
 * 
 */
package com.dell.isg.aps.powerthermal.controller.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rahman.muhammad
 *
 */
public class JobStatusRequest {

    private String serverAddress;
    private List<String> jobs;


    public String getServerAddress() {
        return serverAddress;
    }


    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }


    public List<String> getJobs() {

        if (jobs == null) {
            jobs = new ArrayList<String>();
        }
        return jobs;
    }


    public void setJobs(List<String> jobs) {
        this.jobs = jobs;
    }

}
