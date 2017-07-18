/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
/**
 * 
 */
package com.dell.isg.aps.powerthermal.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.dell.isg.smi.commons.elm.exception.RuntimeCoreException;

/**
 * @author rahman.muhammad
 *
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadArgumentException extends RuntimeCoreException {
    private static final long serialVersionUID = 1L;

}
