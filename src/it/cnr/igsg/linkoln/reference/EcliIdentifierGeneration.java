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
package it.cnr.igsg.linkoln.reference;

import it.cnr.igsg.linkoln.LinkolnDocument;
import it.cnr.igsg.linkoln.entity.CaseLawReference;
import it.cnr.igsg.linkoln.entity.Reference;

public class EcliIdentifierGeneration implements IdentifierGeneration {

	@Override
	public LinkolnIdentifier getLinkolnIdentifier(LinkolnDocument linkolnDocument, Reference annotationEntity) {

		if( !(annotationEntity instanceof CaseLawReference)) return null;

		CaseLawReference entity = (CaseLawReference) annotationEntity; 
		
		LinkolnIdentifier linkolnIdentifier = new LinkolnIdentifier();
		linkolnIdentifier.setType(Identifiers.ECLI);
		
		//String urlPrefix = "https://e-justice.europa.eu/ecli/";  //Funziona meglio così: https://e-justice.europa.eu/ecli/it/ECLI:CODE.html 
		String urlPrefix = "https://e-justice.europa.eu/ecli/it/";
		
		String auth = entity.getAuthority();
		String year = entity.getYear();
		String number = entity.getNumber();
		String subject = entity.getRelatedValue("SUBJECT");
		
		if(auth == null) {
			
			//TODO make assumptions based on the metadata of the input document?
			if(linkolnDocument.getAuthority().toUpperCase().indexOf("CASS") > -1) auth = "IT_CASS";
			if(linkolnDocument.getAuthority().toUpperCase().indexOf("COST") > -1) auth = "IT_COST";
			
			//TODO allargare alle autorità emananti di merito e appello?? -> ECLI CODES...
			
			if(auth == null) return null;
		}
		
		if(auth.equals("IT_COST") && year != null && number != null) {

			linkolnIdentifier.setCode("ECLI:IT:COST:" + year + ":" + number);
		}

		if(auth.equals("IT_CASS") && year != null && number != null) {
			
			if(subject != null) {

				if(subject.toUpperCase().startsWith("C")) subject = "CIV";
				if(subject.toUpperCase().startsWith("P")) subject = "PEN";
			
			} else if( !linkolnDocument.getSector().equals("")) {
				
				if(linkolnDocument.getSector().toUpperCase().startsWith("C")) subject = "CIV";
				if(linkolnDocument.getSector().toUpperCase().startsWith("P")) subject = "PEN";
				if(linkolnDocument.getSector().toUpperCase().startsWith("L")) subject = "CIV"; //TODO Lavoro? => civile
				
			} else {
				
				//TODO guess sector based on other references (eg.: many references to the civil or penal code)
				
				return null;
			}
			
			if(subject == null || subject.equals("")) return null;
			
			linkolnIdentifier.setCode("ECLI:IT:CASS:" + year + ":" + number + subject);
		}
		

		//TODO CDS, CONT
		
		
		
		//TODO merito???
		
		
		
		if( !linkolnIdentifier.getCode().equals("")) {
			
			linkolnIdentifier.setUrl(urlPrefix + linkolnIdentifier.getCode() + ".html");
			
			return linkolnIdentifier;
		}

		return null;
	}
	
}
