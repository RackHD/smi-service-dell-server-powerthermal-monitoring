/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
/**
 *
 */
package com.dell.isg.aps.powerthermal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.dell.isg.aps.powerthermal.common.BasePowerThermalRequest;
import com.dell.isg.aps.powerthermal.common.HwMonitoringAgg;
import com.dell.isg.aps.powerthermal.common.SetPowerThermalAggRequest;
import com.dell.isg.aps.powerthermal.common.SetPowerThermalRequest;
import com.dell.isg.aps.powerthermal.common.UriConstants;
import com.dell.isg.aps.powerthermal.controller.model.Version;
import com.dell.isg.smi.commons.model.server.JobStatus;
import com.dell.isg.smi.commons.model.server.inventory.HwPowerMonitoring;

import io.swagger.annotations.ApiOperation;

/**
 * @author rahman.muhammad
 *
 */

@RestController
public interface IPowerThermalController {

    @ApiOperation(value = UriConstants.VERSION, nickname = "PowerThermal-version", notes = "This Webmethod returns the deployed Power and Thermal microservice version", response = Version.class)
    @RequestMapping(value = UriConstants.VERSION, method = RequestMethod.GET, produces = "application/json")
    public Version version();


    @ApiOperation(value = UriConstants.POWER_THERMAL, nickname = "PowerThermal", notes = "This Webmethod returns Power and Thermal data for a server system", response = HwPowerMonitoring.class)
    @RequestMapping(value = UriConstants.POWER_THERMAL, method = RequestMethod.POST, produces = "application/json")
    public HwPowerMonitoring getThermalPower(@RequestBody BasePowerThermalRequest request);


    @ApiOperation(value = UriConstants.POWER_THERMAL, nickname = "PowerThermalCap", notes = "This Webmethod configure physical server for power consumption, by setting power value," + " server consume power within defined limits", response = JobStatus.class)
    @RequestMapping(value = UriConstants.POWER_THERMAL, method = RequestMethod.PUT, produces = "application/json")
    public JobStatus setPowerThermalCapping(@RequestBody SetPowerThermalRequest request);

   
    @ApiOperation(value = UriConstants.POWER_THERMAL_ALL, nickname = "PowerThermalAll", notes = "This Webmethod returns aggregated power consumption data for all servers system", response = HwMonitoringAgg.class)
    @RequestMapping(value = UriConstants.POWER_THERMAL_ALL, method = RequestMethod.POST, produces = "application/json")
    public HwMonitoringAgg getPowerThermalAll(@RequestBody List<BasePowerThermalRequest> request);

    
    @ApiOperation(value = UriConstants.POWER_THERMAL_ALL, nickname = "PowerThermalCapAll", notes = "This Webmethod configure physical servers for power consumption, by setting powercap value," + " all servers are bound to consume power within defined limits", response = ArrayList.class)
    @RequestMapping(value = UriConstants.POWER_THERMAL_ALL, method = RequestMethod.PUT, produces = "application/json")
    public List<JobStatus> setPowerThermalCappingOnAll(@RequestBody SetPowerThermalAggRequest request);

     
    
}
