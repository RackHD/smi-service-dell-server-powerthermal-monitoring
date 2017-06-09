/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
/**
 * 
 */
package com.dell.isg.aps.powerthermal.controller;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.dell.isg.aps.powerthermal.common.BasePowerThermalRequest;
import com.dell.isg.aps.powerthermal.common.HwMonitoringAgg;
import com.dell.isg.aps.powerthermal.common.SetPowerThermalAggRequest;
import com.dell.isg.aps.powerthermal.common.SetPowerThermalRequest;
import com.dell.isg.aps.powerthermal.controller.exception.BadArgumentException;
import com.dell.isg.aps.powerthermal.controller.model.Version;
import com.dell.isg.aps.powerthermal.service.IPowerThermalService;
import com.dell.isg.aps.commons.elm.exception.RuntimeCoreException;
import com.dell.isg.aps.commons.elm.model.EnumErrorCode;
import com.dell.isg.aps.commons.model.server.JobStatus;
import com.dell.isg.aps.commons.model.server.inventory.HwPowerMonitoring;

@RestController

public class PowerThermalController implements IPowerThermalController {

	private static final Logger logger = LoggerFactory.getLogger(PowerThermalController.class.getName());
    private static final int MIN=100;
    private static final int MAX=500;
    
	@Autowired
	private IPowerThermalService powerThermalService;


	@Override
	public Version version() {
		return new Version();

	}


	@SuppressWarnings("unused")
	@Override
	public HwPowerMonitoring getThermalPower(@RequestBody BasePowerThermalRequest request) {

		logger.info("requesting power and thermal consumption data for selected {}", request.getServerAddress());

		if (request == null) {
			BadArgumentException exp = new BadArgumentException();
			exp.setErrorCode(EnumErrorCode.ENUM_INVALID_DATA);
			throw exp;
		}

		try {
			return powerThermalService.collectPowerMonitoring(request.getServerAddress(), request.getUserName(), request.getPassword());
		} catch (Exception exp) {
			logger.error(exp.toString());
			RuntimeCoreException runExp = new RuntimeCoreException(exp);
			throw runExp;
		}
	}


	@SuppressWarnings("unused")
	@Override
	public JobStatus setPowerThermalCapping(@RequestBody SetPowerThermalRequest request) {
		logger.info("requesting power and thermal consumption data for selected {}", request.getServerAddress());

		if (request == null) {
			BadArgumentException exp = new BadArgumentException();
			exp.setErrorCode(EnumErrorCode.ENUM_INVALID_DATA);
			throw exp;
		}

		try {
			return powerThermalService.setPowerThermalCapping(request);
		} catch (Exception exp) {
			logger.error(exp.toString());
			RuntimeCoreException runExp = new RuntimeCoreException(exp);
			throw runExp;
		}
	}





	@Override
	public HwMonitoringAgg getPowerThermalAll(@RequestBody  List <BasePowerThermalRequest> request) {
         
		logger.info("collecting PowerMonitoring data for {} servers", request.size());
		
		if (request == null || request.size()==0) {

			BadArgumentException exp = new BadArgumentException();
			exp.setErrorCode(EnumErrorCode.ENUM_INVALID_DATA);
			throw exp;
		}

		try {
			return powerThermalService.collectPowerThermalAll(request);
		} catch (Exception exp) {
			logger.error(exp.toString());
			RuntimeCoreException runExp = new RuntimeCoreException(exp);
			throw runExp;
		}		
		
		
   }


	@Override
	public List<JobStatus> setPowerThermalCappingOnAll(@RequestBody SetPowerThermalAggRequest request) {

		logger.info("Configuring PowerMonitoring on {} servers", request.getServers().size());
		
		if (request == null || request.getServers().size()==0) {
			BadArgumentException exp = new BadArgumentException();
			exp.setErrorCode(EnumErrorCode.ENUM_INVALID_DATA);
			throw exp;
		}

		if (request.getPowerCap()/request.getServers().size()<MIN || request.getPowerCap()/request.getServers().size()>MAX) {
			logger.info("Setting PowerCap value is out of recommended range");
			BadArgumentException exp = new BadArgumentException();
			exp.setErrorCode(EnumErrorCode.ENUM_INVALID_DATA);
			throw exp;
		}

		
		
		try {
			return powerThermalService.setPowerThermalCappingOnAll(request);
		} catch (Exception exp) {
			logger.error(exp.toString());
			RuntimeCoreException runExp = new RuntimeCoreException(exp);
			throw runExp;
		}		   	


	}

	
		
	

	
	

}
