/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 * 
 */
package com.dell.isg.aps.powerthermal.infrastructure;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dell.isg.smi.adapter.server.model.PowerMonitoring;
import com.dell.isg.smi.adapter.server.powerthermal.IPowerThermalAdapter;
import com.dell.isg.smi.commons.model.server.inventory.HwPowerMonitoring;
import com.dell.isg.aps.powerthermal.common.Credentials;
import com.dell.isg.aps.powerthermal.util.PowerThermalTransformer;

/**
 * @author rahman.muhammad
 *
 */
public class PowerMonitoringCollectionTask implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(PowerMonitoringCollectionTask.class.getName());
	private IPowerThermalAdapter adapter;
	private List <HwPowerMonitoring> hwPowerMonitoring;
	private Credentials credentials;
	
	public PowerMonitoringCollectionTask(List <HwPowerMonitoring> hwPowerMonitoring,IPowerThermalAdapter adapter, Credentials credentials){
		this.hwPowerMonitoring=	hwPowerMonitoring;
		this.adapter=adapter;
		this.credentials=credentials;
	 	
	}
	
	
	
	@Override
	public void run() {
	   
		 try {
			   PowerMonitoring pwMonitoring=adapter.collectPowerMonitoring(credentials.getAddress(),credentials.getUserName(),credentials.getPassword());
			   hwPowerMonitoring.add(PowerThermalTransformer.transformPowerMonitor(pwMonitoring));
			 	 
		 }
		 catch(Exception exp){
			logger.error("TaskExecutor failed to execute wman command for power monitoring",exp); 
		 }
    	}
	
 }
