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
package it.cnr.igsg.linkoln.entity;

import it.cnr.igsg.linkoln.service.impl.Util;

public class EuropeanLegislationReference extends LegislationReference {

	@Override
	public String getEntityName() {

		return "EU_LEG_REF";
	}
	
	public String getCelex() {
		
		return getCelex("en");
	}
	
	public String getCelex(String lang) {
		
		lang = lang.toUpperCase();
		
		if(getRelatedEntity("EU_LEG_ALIAS") != null) {
			
			return getAliasUrl(getRelatedEntity("EU_LEG_ALIAS"), lang);
		}

		String celex = "";
		
		String type = "";
		String sector = "";
		String celexNumber = "";
		String celexYear = "";
				
        String prefix = "http://eur-lex.europa.eu/legal-content/" + lang + "/TXT/?uri=CELEX:";

        
        AnnotationEntity docType = getRelatedEntity("EU_LEG_DOCTYPE");
        
        if(docType == null) {
        	
        		docType = getRelatedEntity("LEG_DOCTYPE");
        }
        
        if(docType == null) {
        	
    			docType = getRelatedEntity("DOCTYPE");
        }
    
        if(docType == null) {
        	
        		return "";
        }
		
        if(docType.getValue().equals("DIRECTIVE")) { sector="3"; type = "L"; }
        if(docType.getValue().equals("REGULATION")) { sector="3"; type = "R"; }
        if(docType.getValue().equals("RECOMMENDATION")) { sector="3"; type = "H"; }
        if(docType.getValue().equals("DECISION")) { sector="3"; type = "D"; }
        
        AnnotationEntity number = getRelatedEntity("NUMBER");
        
        if(number != null) {
        	
        		celexNumber = Util.readFirstNumber(number.getValue());
        		celexYear = "";
        		
        		if(number.getRelatedEntity("YEAR") != null) {
        		
        			celexYear = Util.readLastNumber(number.getValue()); //temp
        		}
        }
        
        if(celexYear.equals("")) {
        	
        		AnnotationEntity date = getRelatedEntity("DATE");
        		
        		if(date != null && date.getRelatedEntity("YEAR") != null) {
        			
        			celexYear = date.getRelatedEntity("YEAR").getValue();
        		}
        }
        
		if(celexNumber.length() == 1) { celexNumber = "000" + celexNumber; }
		if(celexNumber.length() == 2) { celexNumber = "00" + celexNumber; }
		if(celexNumber.length() == 3) { celexNumber = "0" + celexNumber; }

		//TODO Year deve essere letto in altro modo!

		celex = prefix + sector + celexYear + type + celexNumber;
		
		//Non produrre un identificatore Celex se manca l'anno o il numero
		if(celexYear.equals("") || celexNumber.equals("")) {
			
			return "";
		}
		
		return celex;
	}

	private String getAliasUrl(AnnotationEntity alias, String lang) {
		
		String celex = "";
		
        String prefix = "http://eur-lex.europa.eu/legal-content/" + lang + "/TXT/?uri=CELEX:";

        String celexSuffix = "";
    	
        String artValue = "";
        
		AnnotationEntity partitionEntity = getRelatedEntity("LEG_PARTITION");
		
		if(partitionEntity != null) {
			
			if(partitionEntity.getRelatedEntity("ARTICLE") != null) {
				
				artValue += partitionEntity.getRelatedEntity("ARTICLE").getValue();
			}
		}
        
		/*
        if(alias.getRelatedEntity("LEG_PARTITION") != null && alias.getRelatedEntity("LEG_PARTITION").getRelatedEntity("ARTICLE") != null) {
        	
        		artValue = alias.getRelatedEntity("LEG_PARTITION").getRelatedEntity("ARTICLE").getValue();
        }
        */
	
        if(artValue.length()>0) {
			
			if(artValue.length()==1) artValue = "00" + artValue;
			if(artValue.length()==2) artValue = "0" + artValue;
			celexSuffix = artValue;
			
		} else {
			
			celexSuffix = "/TXT";
		}

        if(alias.getValue().equals("EU_TUE")) celex = "11992M";
        
        if(alias.getValue().equals("EU_TFUE")) celex = "12008E";
		
		
		return prefix + celex + celexSuffix;
	}
	
	public String getEli() {
		
		return getEli("en");
	}
	
	public String getEli(String lang) {
		
		lang = lang.toLowerCase();
		
		if(getRelatedEntity("EU_LEG_ALIAS") != null) {
			
			return "";
		}
		
		if(lang.equals("en")) lang = "eng";
		if(lang.equals("it")) lang = "ita";
		if(lang.equals("fr")) lang = "fra";
		if(lang.equals("es")) lang = "spa";
		if(lang.equals("de")) lang = "deu";
		
		String prefix = "http://data.europa.eu/eli";

		//[NUMBER:37-2003-EC:43:291] "2003/37/EC" 
		String number = "";
		String year = "";
		String type = "";
		
		AnnotationEntity docType = getRelatedEntity("EU_LEG_DOCTYPE");
	        
		if(docType == null) docType = getRelatedEntity("LEG_DOCTYPE");
		if(docType == null) docType = getRelatedEntity("DOCTYPE");    
		if(docType == null) return "";

		if(docType.getValue().equals("DIRECTIVE")) type = "dir";
		if(docType.getValue().equals("REGULATION")) type = "reg";
		if(docType.getValue().equals("DECISION")) type = "dec";
		
		if(type.equals("")) return "";
		
		AnnotationEntity numberEntity = getRelatedEntity("NUMBER");

		if(numberEntity == null) return "";
		
		//Per adesso considera soltanto i reference espressi con anno e numero dentro il tag NUMBER
		
		String[] numberItems = numberEntity.getValue().split("\\-");
		
		if(numberItems.length < 2) return "";
		
		number = numberItems[0];
		year = numberItems[1];
		
		if(number.equals("") || year.equals("")) return "";
		
		String eliPartition="";
		
		AnnotationEntity partitionEntity = getRelatedEntity("LEG_PARTITION");
		
		if(partitionEntity != null) {
			
			if(partitionEntity.getRelatedEntity("ARTICLE") != null) {
				
				eliPartition += "/art_" + partitionEntity.getRelatedEntity("ARTICLE").getValue();

				if(partitionEntity.getRelatedEntity("PARAGRAPH") != null) {
					
					eliPartition += "/par_" + partitionEntity.getRelatedEntity("PARAGRAPH").getValue();
				}
			}
		}
			
		String eli = prefix + "/" + type + "/" + year + "/" + number + eliPartition + "/oj";
		
		String url = eli + "/" + lang;

		return url;
	}
}
