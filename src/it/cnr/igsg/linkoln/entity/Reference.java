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

import java.util.HashMap;
import java.util.Map;

import it.cnr.igsg.linkoln.reference.LinkolnReference;
import it.cnr.igsg.linkoln.service.impl.Util;

public class Reference extends AnnotationEntity {

	private LinkolnReference linkolnReference = null;
	
	public LinkolnReference getLinkolnReference() {
		return linkolnReference;
	}

	public void setLinkolnReference(LinkolnReference linkolnReference) {
		this.linkolnReference = linkolnReference;
	}

	@Override
	public String getEntityName() {

		return "REF";
	}
	
	private String context = "";
	
	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	
	public Map<String,AnnotationEntity> name2sharedEntity = new HashMap<String,AnnotationEntity>();
	
	
	public boolean isReference() {
		
		boolean hasAuthority = false;
		boolean hasDocType = false;
		boolean hasNumber = false;
		boolean hasDate = false;
		
		if(getRelatedEntity("ALIAS") != null || getRelatedEntity("LEG_ALIAS") != null || getRelatedEntity("CL_ALIAS") != null) {
			
			return true;
		}
		
		if(getRelatedEntity("CL_AUTH") != null || getRelatedEntity("LEG_AUTH") != null || getRelatedEntity("EU_CL_AUTH") != null || getRelatedEntity("EU_LEG_AUTH") != null) {
			
			hasAuthority = true;
		}
		
		if(name2sharedEntity.get("CL_AUTH") != null || name2sharedEntity.get("LEG_AUTH") != null || name2sharedEntity.get("EU_CL_AUTH") != null || name2sharedEntity.get("EU_LEG_AUTH") != null) {
			
			hasAuthority = true;
		}
		
		if(getRelatedEntity("DOCTYPE") != null || getRelatedEntity("CL_DOCTYPE") != null || getRelatedEntity("LEG_DOCTYPE") != null || getRelatedEntity("EU_LEG_DOCTYPE") != null) {
			
			hasDocType = true;
		}
		
		if(name2sharedEntity.get("DOCTYPE") != null || name2sharedEntity.get("CL_DOCTYPE") != null || name2sharedEntity.get("LEG_DOCTYPE") != null || name2sharedEntity.get("EU_LEG_DOCTYPE") != null) {
			
			hasDocType = true;
		}
		
		if(getRelatedEntity("NUMBER") != null || getRelatedEntity("CASENUMBER") != null || getRelatedEntity("ALTNUMBER") != null) {
			
			hasNumber = true;
		}
		
		if(getRelatedEntity("DATE") != null) {
			
			hasDate = true;
		}
		
		if( (hasAuthority || hasDocType) && (hasNumber || hasDate) ) {
			
			return true;
		}
		
		return false;
	}
	
	public String getAuthority() {

		AnnotationEntity entity = this.getRelatedEntity("CL_AUTH");
		if(entity != null) return entity.getValue();
		
		entity = this.getRelatedEntity("LEG_AUTH");
		if(entity != null) return entity.getValue();
		
		entity = this.getRelatedEntity("EU_CL_AUTH");
		if(entity != null) return entity.getValue();
		
		entity = this.getRelatedEntity("EU_LEG_AUTH");
		if(entity != null) return entity.getValue();
		
		return null;
	}
	
	public String getDocumentType() {
		
		AnnotationEntity entity = this.getRelatedEntity("CL_DOCTYPE");
		if(entity != null) return entity.getValue();

		entity = this.getRelatedEntity("LEG_DOCTYPE");
		if(entity != null) return entity.getValue();
		
		entity = this.getRelatedEntity("EU_LEG_DOCTYPE");
		if(entity != null) return entity.getValue();
		
		entity = this.getRelatedEntity("DOCTYPE");
		if(entity != null) return entity.getValue();
		
		return null;
	}
	

	public String getNumber() {
		
		AnnotationEntity entity = this.getRelatedEntity("NUMBER");
		if(entity != null) {
			
			return Util.readFirstNumber(entity.getValue()); //TODO non è detto sia sempre il primo numero (es.: 2019/123)
		}

		return null;
	}
	
	public String getYear() {
		
		//Look into NUMBER first, then DATE
		
		AnnotationEntity entity = this.getRelatedEntity("NUMBER");
		if(entity != null) {
			
			AnnotationEntity year = entity.getRelatedEntity("YEAR");
			if(year != null) return year.getValue();
		}
		
		entity = this.getRelatedEntity("DATE");
		if(entity != null) {
			
			AnnotationEntity year = entity.getRelatedEntity("YEAR");
			if(year != null) return year.getValue();
		}

		return null;
	}
	
}
