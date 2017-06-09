/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */

package com.dell.isg.aps.powerthermal.common;

import java.util.ArrayList;
import java.util.List;
import com.dell.isg.aps.commons.model.server.inventory.HwPowerMonitoring;

/**
 * @author rahman.muhammad
 *
 */
public class HwMonitoringAgg {

protected long currentReading;
protected long currentAvgReading;
protected long avgWarningThreshhold;
protected long avgFailureThreshhold;
protected List<HwPowerMonitoring> HwPowerMonitoring;

public long getCurrentReading() {
	return currentReading;
}
public void setCurrentReading(long currentReading) {
	this.currentReading = currentReading;
}
public long getCurrentAvgReading() {
	return currentAvgReading;
}
public void setCurrentAvgReading(long currentAvgReading) {
	this.currentAvgReading = currentAvgReading;
}
public long getAvgWarningThreshhold() {
	return avgWarningThreshhold;
}
public void setAvgWarningThreshhold(long avgWarningThreshhold) {
	this.avgWarningThreshhold = avgWarningThreshhold;
}
public long getAvgFailureThreshhold() {
	return avgFailureThreshhold;
}
public void setAvgFailureThreshhold(long avgFailureThreshhold) {
	this.avgFailureThreshhold = avgFailureThreshhold;
}
public List<HwPowerMonitoring> getHwPowerMonitoring() {
	
	if(HwPowerMonitoring==null){
		 HwPowerMonitoring=new ArrayList<HwPowerMonitoring>();
	 }
	
	return HwPowerMonitoring;
}
public void setHwPowerMonitoring(List<HwPowerMonitoring> hwPowerMonitoring) {
	HwPowerMonitoring = hwPowerMonitoring;
} 


}
