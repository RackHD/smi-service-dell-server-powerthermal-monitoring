/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 *
 */
package com.dell.isg.aps.powerthermal.util;

import org.apache.commons.lang3.StringUtils;


public class ExtractValueUtil {

	/*
	 *  Utility method to extract numerical values 
	 *  from given string  	
	 */
		
		public static int findPowerValue(String str){
		    String delimiter=" ";	
			str = StringUtils.substringBefore(str, delimiter);
		   if(StringUtils.isNotBlank(str))
			   return Integer.parseInt(str);
		   else
			  return 0;
			   
		}
		
}
