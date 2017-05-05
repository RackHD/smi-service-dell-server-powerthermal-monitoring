/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
/**
 * 
 */
package com.dell.isg.aps.powerthermal.infrastructure;

import org.springframework.stereotype.Component;

import com.dell.isg.aps.powerthermal.common.EnumDefinition;
import com.dell.isg.aps.powerthermal.common.SetPowerThermalRequest;
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
        boolean isConfigJobNeeded = false;
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

}
