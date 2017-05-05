/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
/**
 * 
 */
package com.dell.isg.aps.powerthermal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dell.isg.aps.powerthermal.common.SetPowerThermalRequest;
import com.dell.isg.aps.powerthermal.infrastructure.IPowerThermalInfrastructure;
import com.dell.isg.aps.commons.model.server.JobStatus;
import com.dell.isg.aps.commons.model.server.inventory.HwPowerMonitoring;

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

}
