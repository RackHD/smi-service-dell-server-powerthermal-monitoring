/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 * 
 */

package com.dell.isg.aps.powerthermal.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rahman.muhammad
 *
 */
public class SetPowerThermalAggRequest {
	
	protected int powerCap=0;
	protected boolean enableCapping;
	List <BasePowerThermalRequest> servers=null;
	
	public int getPowerCap() {
		return powerCap;
	}

	public void setPowerCap(int powerCap) {
		this.powerCap = powerCap;
	}
	public List<BasePowerThermalRequest> getServers() {
		if (servers==null){
			servers=new ArrayList<BasePowerThermalRequest>(); 
		}
		return servers;
	}

	public void setServers(List<BasePowerThermalRequest> servers) {
		this.servers = servers;
	}

	public boolean isEnableCapping() {
		return enableCapping;
	}

	public void setEnableCapping(boolean enableCapping) {
		this.enableCapping = enableCapping;
	}

}
