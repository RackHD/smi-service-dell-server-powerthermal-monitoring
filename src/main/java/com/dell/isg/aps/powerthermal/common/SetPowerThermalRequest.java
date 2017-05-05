/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
/**
 * 
 */
package com.dell.isg.aps.powerthermal.common;

/**
 * @author rahman.muhammad
 *
 */
public class SetPowerThermalRequest extends BasePowerThermalRequest {

    protected boolean enableCapping;
    protected int powerCap;


    public boolean isEnableCapping() {
        return enableCapping;
    }


    public void setEnableCapping(boolean enableCapping) {
        this.enableCapping = enableCapping;
    }


    public int getPowerCap() {
        return powerCap;
    }


    public void setPowerCap(int powerCap) {
        this.powerCap = powerCap;
    }

}
