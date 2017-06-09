/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
/**
 * 
 */
package com.dell.isg.aps.powerthermal.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dell.isg.aps.powerthermal.common.BasePowerThermalRequest;
import com.dell.isg.aps.powerthermal.common.Credentials;
import com.dell.isg.aps.powerthermal.common.EnumDefinition;
import com.dell.isg.aps.powerthermal.common.HwMonitoringAgg;
import com.dell.isg.aps.powerthermal.common.SetPowerThermalAggRequest;
import com.dell.isg.aps.powerthermal.common.SetPowerThermalRequest;
import com.dell.isg.aps.powerthermal.util.ExtractValueUtil;
import com.dell.isg.aps.powerthermal.util.PowerThermalTransformer;
import com.dell.isg.aps.adapter.server.model.PowerMonitoring;
import com.dell.isg.aps.adapter.server.model.WsmanCredentials;
import com.dell.isg.aps.adapter.server.powerthermal.IPowerThermalAdapter;
import com.dell.isg.aps.adapter.server.powerthermal.PowerThermalAdapterImpl;
import com.dell.isg.aps.commons.model.server.JobStatus;
import com.dell.isg.aps.commons.model.server.inventory.HwPowerMonitoring;

/**
 * @author rahman.muhammad
 *
 */

@Component
public class PowerThermalInfrastructureImpl implements IPowerThermalInfrastructure {
	private final static int THREAD_POOL_SIZE=10;
	private static final Logger logger = LoggerFactory.getLogger(PowerThermalInfrastructureImpl.class.getName());

	@Override
	public HwPowerMonitoring collectPowerMonitoring(String address, String userName, String password) throws Exception {

		HwPowerMonitoring hwMonitoring = new HwPowerMonitoring();
		IPowerThermalAdapter adapter = new PowerThermalAdapterImpl();
		PowerMonitoring pwMonitoring = adapter.collectPowerMonitoring(address, userName, password);
		hwMonitoring = PowerThermalTransformer.transformPowerMonitor(pwMonitoring);
		return hwMonitoring;

	}


	@Override
	public JobStatus setPowerThermalCapping(SetPowerThermalRequest request) throws Exception {

		IPowerThermalAdapter adapter = new PowerThermalAdapterImpl();
		WsmanCredentials credentials = new WsmanCredentials();
		credentials.setAddress(request.getServerAddress());
		credentials.setUserName(request.getUserName());
		credentials.setPassword(request.getPassword());

		JobStatus status = null;
		String powerSettings = request.isEnableCapping() ? EnumDefinition.ENABLED.toString() : EnumDefinition.DISABLED.toString();

		adapter.enablePowerCapping(credentials, powerSettings);

		if (request.getPowerCap() > 0) {
			adapter.setPowerCapping(credentials, String.valueOf(request.getPowerCap()));
		}

		status = adapter.createConfigJob(credentials);
		status.setServerAddress(request.getServerAddress());
		status.setDescription("Configure Power and Thermal consumption job");

		return status;
	}


	@Override
	public HwMonitoringAgg collectPowerThermalAll(List<BasePowerThermalRequest> request) throws Exception {
		
		long currentReading=0,currentAvgReading=0,avgWarningThreshhold=0,avgFailureThreshhold=0;
		HwMonitoringAgg agg=new HwMonitoringAgg();
		Credentials cred=null;
		PowerMonitoringCollectionTask executor=null;
		IPowerThermalAdapter adapter = new PowerThermalAdapterImpl();
		ExecutorService executorService= Executors.newFixedThreadPool(THREAD_POOL_SIZE);


		if (request !=null && request.size()>0){

			for (BasePowerThermalRequest item : request){
				cred=new Credentials(item.getServerAddress(), item.getUserName(),item.getPassword());
				executor=new PowerMonitoringCollectionTask(agg.getHwPowerMonitoring(),adapter, cred);
				executorService.execute(executor);			
			}

		}

		executorService.shutdown();
		while (!executorService.isTerminated()){
			logger.info("PowerMonitoring Service collection data ....\n ");
			TimeUnit.SECONDS.sleep(5);
		}

				 
		
		 
		 for (HwPowerMonitoring item : agg.getHwPowerMonitoring()) {
			 currentReading+= ExtractValueUtil.findPowerValue(item.getCurrentReading());
			 avgWarningThreshhold+=ExtractValueUtil.findPowerValue(item.getWarningThreshold());
			 avgFailureThreshhold+=ExtractValueUtil.findPowerValue(item.getFailureThreshold());
		 }
		 
		 int listSize=agg.getHwPowerMonitoring().size(); 
		 agg.setCurrentReading(currentReading);
		 agg.setCurrentAvgReading(currentReading/listSize);
		 agg.setAvgWarningThreshhold(avgWarningThreshhold/listSize);
		 agg.setAvgFailureThreshhold(avgFailureThreshhold/listSize);


		return agg;
	}



	@Override
	public List<JobStatus> setPowerThermalCappingOnAll(SetPowerThermalAggRequest request) throws Exception {

		IPowerThermalAdapter adapter = new PowerThermalAdapterImpl();
		ArrayList <JobStatus> jobs = new ArrayList<JobStatus>();
		ExecutorService executorService= Executors.newFixedThreadPool(THREAD_POOL_SIZE);
		PowerMonitoringCapTask executor=null;
		SetPowerThermalRequest req=null; 
		boolean enableCapping=true;
		int capValue=0;
		
		
		if (request !=null && request.getServers().size()>0){
             enableCapping=request.isEnableCapping(); 
			 capValue=request.getPowerCap()/request.getServers().size();
			 
			for (BasePowerThermalRequest item : request.getServers()){
				req=new SetPowerThermalRequest();
				req.setEnableCapping(enableCapping);
				req.setPowerCap(capValue);
				req.setServerAddress(item.getServerAddress());
				req.setUserName(item.getUserName());
				req.setPassword(item.getPassword());
				executor=new PowerMonitoringCapTask(jobs,adapter,req);
				executorService.execute(executor);			
			}

		}

		executorService.shutdown();
		while (!executorService.isTerminated()){
			logger.info("PowerMonitoring Service configuring PowerCap ....\n ");
			TimeUnit.SECONDS.sleep(3);
		}

	 return jobs;
	}

	
	
}

