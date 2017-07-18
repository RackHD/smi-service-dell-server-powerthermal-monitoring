/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
/**
 * 
 */
package com.dell.isg.aps.powerthermal.util;

import com.dell.isg.smi.adapter.server.model.HeadRoom;
import com.dell.isg.smi.adapter.server.model.PowerMonitoring;
import com.dell.isg.smi.adapter.server.model.PowerStatistics;
import com.dell.isg.smi.commons.elm.utilities.DateTimeUtils;
import com.dell.isg.smi.commons.model.server.inventory.HwPowerMonitoring;

/**
 * @author rahman.muhammad
 *
 */
public class PowerThermalTransformer {

    public static HwPowerMonitoring transformPowerMonitor(PowerMonitoring powerMonitoring) {

        HwPowerMonitoring hwPowerMonitoring = new HwPowerMonitoring();
        if (powerMonitoring == null) {
            return hwPowerMonitoring;
        }
        hwPowerMonitoring.setPowerCap(powerMonitoring.getPowerCap());
        hwPowerMonitoring.setProfile(powerMonitoring.getProfile());
        hwPowerMonitoring.setWarningThreshold(powerMonitoring.getWarningThreshold());
        hwPowerMonitoring.setFailureThreshold(powerMonitoring.getFailureThreshold());
        hwPowerMonitoring.setProbeName(powerMonitoring.getProbeName());
        hwPowerMonitoring.setCurrentReading(powerMonitoring.getCurrentReading());

        PowerStatistics powerStatistics = powerMonitoring.getPowerStatistics();
        if (powerStatistics != null) {
            hwPowerMonitoring.setEnergyConsumption(powerStatistics.getEnergyConsumption());
            hwPowerMonitoring.setEnergyConsumptionStartDateTime(DateTimeUtils.getIsoDateString(powerStatistics.getEnergyConsumptionStartDateTime(), TransformerAssemblerConstants.POWER_MONITORING_DATE_FORMAT));
            hwPowerMonitoring.setEnergyConsumptionEndDateTime(DateTimeUtils.getIsoDateString(powerStatistics.getEnergyConsumptionEndDateTime(), TransformerAssemblerConstants.POWER_MONITORING_DATE_FORMAT));

            hwPowerMonitoring.setSystemPeakPower(powerStatistics.getSystemPeakPower());
            hwPowerMonitoring.setSystemPeakPowerStartDateTime(DateTimeUtils.getIsoDateString(powerStatistics.getSystemPeakPowerStartDateTime(), TransformerAssemblerConstants.POWER_MONITORING_DATE_FORMAT));
            hwPowerMonitoring.setSystemPeakPowerEndDateTime(DateTimeUtils.getIsoDateString(powerStatistics.getSystemPeakPowerEndDateTime(), TransformerAssemblerConstants.POWER_MONITORING_DATE_FORMAT));

            hwPowerMonitoring.setSystemPeakAmps(powerStatistics.getSystemPeakAmps());
            hwPowerMonitoring.setPeakAmpsStartDateTime(DateTimeUtils.getIsoDateString(powerStatistics.getPeakAmpsStartDateTime(), TransformerAssemblerConstants.POWER_MONITORING_DATE_FORMAT));
            hwPowerMonitoring.setPeakAmpsEndDateTime(DateTimeUtils.getIsoDateString(powerStatistics.getPeakAmpsEndDateTime(), TransformerAssemblerConstants.POWER_MONITORING_DATE_FORMAT));

            hwPowerMonitoring.setLastHourPeakAverage(powerStatistics.getLastHourPeakAverage());
            hwPowerMonitoring.setLastHourPeakTime(DateTimeUtils.getIsoDateString(powerStatistics.getLastHourPeakTime(), TransformerAssemblerConstants.POWER_MONITORING_DATE_FORMAT));
            hwPowerMonitoring.setLastHourPeakMax(powerStatistics.getLastHourPeakMax());
            hwPowerMonitoring.setLastHourPeakMaxTime(DateTimeUtils.getIsoDateString(powerStatistics.getLastHourPeakMaxTime(), TransformerAssemblerConstants.POWER_MONITORING_DATE_FORMAT));
            hwPowerMonitoring.setLastHourPeakMin(powerStatistics.getLastHourPeakMin());
            hwPowerMonitoring.setLastHourPeakMinTime(DateTimeUtils.getIsoDateString(powerStatistics.getLastHourPeakMinTime(), TransformerAssemblerConstants.POWER_MONITORING_DATE_FORMAT));

            hwPowerMonitoring.setLastDayPeakAverage(powerStatistics.getLastDayPeakAverage());
            hwPowerMonitoring.setLastDayPeakTime(DateTimeUtils.getIsoDateString(powerStatistics.getLastDayPeakTime(), TransformerAssemblerConstants.POWER_MONITORING_DATE_FORMAT));
            hwPowerMonitoring.setLastDayPeakMax(powerStatistics.getLastDayPeakMax());
            hwPowerMonitoring.setLastDayPeakMaxTime(DateTimeUtils.getIsoDateString(powerStatistics.getLastDayPeakMaxTime(), TransformerAssemblerConstants.POWER_MONITORING_DATE_FORMAT));
            hwPowerMonitoring.setLastDayPeakMin(powerStatistics.getLastDayPeakMin());
            hwPowerMonitoring.setLastDayPeakMinTime(DateTimeUtils.getIsoDateString(powerStatistics.getLastDayPeakMinTime(), TransformerAssemblerConstants.POWER_MONITORING_DATE_FORMAT));

            hwPowerMonitoring.setLastWeekPeakAverage(powerStatistics.getLastWeekPeakAverage());
            hwPowerMonitoring.setLastWeekPeakTime(DateTimeUtils.getIsoDateString(powerStatistics.getLastWeekPeakTime(), TransformerAssemblerConstants.POWER_MONITORING_DATE_FORMAT));
            hwPowerMonitoring.setLastWeekPeakMax(powerStatistics.getLastWeekPeakMax());
            hwPowerMonitoring.setLastWeekPeakMaxTime(DateTimeUtils.getIsoDateString(powerStatistics.getLastWeekPeakMaxTime(), TransformerAssemblerConstants.POWER_MONITORING_DATE_FORMAT));
            hwPowerMonitoring.setLastWeekPeakMin(powerStatistics.getLastWeekPeakMin());
            hwPowerMonitoring.setLastWeekPeakMinTime(DateTimeUtils.getIsoDateString(powerStatistics.getLastWeekPeakMinTime(), TransformerAssemblerConstants.POWER_MONITORING_DATE_FORMAT));
        }

        HeadRoom headRoom = powerMonitoring.getHeadRoom();
        if (headRoom != null) {
            hwPowerMonitoring.setSystemInstantaneousHeadroom(headRoom.getSystemInstantaneousHeadroom());
            hwPowerMonitoring.setSystemPeakHeadroom(headRoom.getSystemPeakHeadroom());
        }
        return hwPowerMonitoring;
    }

}
