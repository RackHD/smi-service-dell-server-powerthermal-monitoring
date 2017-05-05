/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
/**
 * 
 */
package com.dell.isg.aps.powerthermal.infrastructure;

import com.dell.isg.aps.powerthermal.common.SetPowerThermalRequest;
import com.dell.isg.aps.adapter.server.model.PowerMonitoring;
import com.dell.isg.aps.commons.model.server.JobStatus;
import com.dell.isg.aps.commons.model.server.inventory.HwPowerMonitoring;

/**
 * @author rahman.muhammad
 *
 */
public interface IPowerThermalInfrastructure {

    public HwPowerMonitoring collectPowerMonitoring(String address, String userName, String password) throws Exception;


    public JobStatus setPowerThermalCapping(SetPowerThermalRequest request) throws Exception;
}
