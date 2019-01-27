
package com.axonpoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *   	Copyright (C) 2006 by Citicorp Development Center, Inc. All rights 
 *      reserved. Citicorp Development Center, Inc. claims copyright in 
 *      this computer program as an unpublished work, one or more versions 
 *      of which were first used to provide services to customers on the 
 *      dates indicated in the foregoing notice. Claim of copyright does 
 *      not imply waiver of other rights
 *
 *       NOTICE OF PROPRIETARY RIGHTS
 *
 *      This program is a confidential trade secret and the property of 
 *      Citicorp Development Center, Inc. Use, examination, reproduction, 
 *      disassembly, decompiling, transfer and/or disclosure to others 
 *      of all or any part of this software program are strictly prohibited 
 *      except by express written agreement with Citicorp Development 
 *      Center, Inc. 
 * 
 * @author Citi
 *
 */
 
@SpringBootApplication
@EnableAutoConfiguration
public class AxonPOC_DemoApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(AxonPOC_DemoApplication.class, args);
    }
}
