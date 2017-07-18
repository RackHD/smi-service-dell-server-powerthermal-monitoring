/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
/**
 * 
 */
package com.dell.isg.aps.powerthermal.service;

import com.dell.isg.aps.powerthermal.common.BasePowerThermalRequest;
import com.dell.isg.aps.powerthermal.common.HwMonitoringAgg;
import com.dell.isg.aps.powerthermal.common.SetPowerThermalAggRequest;
import com.dell.isg.aps.powerthermal.common.SetPowerThermalRequest;

import java.util.List;

import com.dell.isg.smi.commons.model.server.JobStatus;
import com.dell.isg.smi.commons.model.server.inventory.HwPowerMonitoring;

/**
 * @author rahman.muhammad
 *
 */
public interface IPowerThermalService {

    public HwPowerMonitoring collectPowerMonitoring(String address, String userName, String password) throws Exception;
    public HwMonitoringAgg collectPowerThermalAll(List<BasePowerThermalRequest> request) throws Exception;
     	
   
    public JobStatus setPowerThermalCapping(SetPowerThermalRequest request) throws Exception;
    public List<JobStatus> setPowerThermalCappingOnAll(SetPowerThermalAggRequest request) throws Exception;

}
