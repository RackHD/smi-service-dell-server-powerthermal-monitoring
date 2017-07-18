/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
/**
 * 
 */
package com.dell.isg.aps.powerthermal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dell.isg.aps.powerthermal.common.BasePowerThermalRequest;
import com.dell.isg.aps.powerthermal.common.HwMonitoringAgg;
import com.dell.isg.aps.powerthermal.common.SetPowerThermalAggRequest;
import com.dell.isg.aps.powerthermal.common.SetPowerThermalRequest;
import com.dell.isg.aps.powerthermal.infrastructure.IPowerThermalInfrastructure;
import com.dell.isg.smi.commons.model.server.JobStatus;
import com.dell.isg.smi.commons.model.server.inventory.HwPowerMonitoring;

/**
 * @author rahman.muhammad
 *
 */

@Component
public class PowerThermalServiceImpl implements IPowerThermalService {

    @Autowired
    IPowerThermalInfrastructure powerThermalInfrastructure;


    @Override
    public HwPowerMonitoring collectPowerMonitoring(String address, String userName, String password) throws Exception {

        return powerThermalInfrastructure.collectPowerMonitoring(address, userName, password);
    }


    @Override
    public JobStatus setPowerThermalCapping(SetPowerThermalRequest request) throws Exception {
        return powerThermalInfrastructure.setPowerThermalCapping(request);
    }


	@Override
	public HwMonitoringAgg collectPowerThermalAll(List<BasePowerThermalRequest> request) throws Exception {
		
   	
		return powerThermalInfrastructure.collectPowerThermalAll(request);
		
	}


	@Override
	public List<JobStatus> setPowerThermalCappingOnAll(SetPowerThermalAggRequest request) throws Exception {
		
		return powerThermalInfrastructure.setPowerThermalCappingOnAll(request);
	}
	
	
	
	
	

}
