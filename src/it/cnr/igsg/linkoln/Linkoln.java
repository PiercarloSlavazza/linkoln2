/*******************************************************************************
 * Copyright (c) 2016-2021 Institute of Legal Information and Judicial Systems IGSG-CNR (formerly ITTIG-CNR)
 * 
 * This program and the accompanying materials  are made available under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 3 of the License, or (at your option)
 * any later version. 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at: https://www.gnu.org/licenses/gpl-3.0.txt
 * Unless required by applicable law or agreed to in writing, software distributed under the Licence is 
 * distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and limitations under the Licence.
 *  
 * Authors: Lorenzo Bacci (IGSG-CNR)
 ******************************************************************************/
package it.cnr.igsg.linkoln;

import java.io.IOException;

import it.cnr.igsg.linkoln.service.LinkolnAnnotationService;
import it.cnr.igsg.linkoln.service.LinkolnService;
import it.cnr.igsg.linkoln.service.ServiceManager;
import it.cnr.igsg.linkoln.service.impl.Util;


public class Linkoln {

	
	public final static String VERSION = "2.0.9";
	
	public static boolean DEBUG = false;	
	public static boolean DEBUG_HTML = false;
	public static boolean LOAD_MUNICIPALITIES = true;
	
	public static boolean HTML_TARGET_BLANK = true;
	
	public static boolean FORCE_EXIT_AFTER_SERVICE_FAILURE = true;
	
	
	public static String run(String text) {
		
		String output = text;
		
		LinkolnDocument linkolnDocument = LinkolnDocumentFactory.getDocument("");
		
		linkolnDocument.setText(text);
		
		//TODO Gestire errors e messages - Singoli fallimenti dei servizi devono far fallire tutta la pipeline
		
		Linkoln.run(linkolnDocument);
		
		if( !linkolnDocument.hasFailed()) {
			
			linkolnDocument.htmlPostProcessing();
			
			output = linkolnDocument.getFinalHtml();
		}
		
		return output;
	}
	
	public static void run(LinkolnDocument linkolnDocument) {
		
		long startTime = System.currentTimeMillis();
		
		if(DEBUG) System.out.println("Running LINKOLN (" + VERSION + ")...\n");
		
		if(LOAD_MUNICIPALITIES) {
		
			if(DEBUG) System.out.print("Loading municipalities... ");
			try {
			
				Util.initMunicipalities();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(DEBUG) System.out.println("Done. (" + Util.token2code.size() + ")");
		}
		
		//Run every service implementation for the specified language/jurisdiction
		for(LinkolnService service : ServiceManager.getInstance().getServices(linkolnDocument.getLanguage())) {
			
			service.initService(linkolnDocument);
			
			//Check if this service is runnable on the given input.
			//Not runnable doesn't mean fail
			if( !service.runnable()) {
				continue;
			}
			
			//Run the service
			if( !service.runService()) {
				
				//Manage failures
				//((LinkolnDocument) linkolnDocument).addError("Service failed. (" + service.getDescription() + ")");
				System.err.println("Service failed. (" + service.getDescription() + ")");
				linkolnDocument.setFailed(true);
				break; //A generic failure of a service does stop the pipeline
				
			} else {
			
				if(service instanceof LinkolnAnnotationService) {
					
					//Update the annotation history
					((LinkolnDocument) linkolnDocument).addAnnotationService((LinkolnAnnotationService) service);
				}
			}
		}
		
		if(DEBUG) {
			
			//Print the annotation history
			System.out.println("\n\nANNOTATIONS: \n");
			
			int count = 1;
			for(LinkolnAnnotationService service : ((LinkolnDocument) linkolnDocument).getAnnotationServices() ) {
				
				System.out.println("\n" + count + ") " + service.getDescription());
				System.out.println("BEFORE: " + service.getInput());
				System.out.println("AFTER: " + service.getOutput());
				count++;
			}
		}
		
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		
		linkolnDocument.setExecutionTime(elapsedTime);
	}
}
