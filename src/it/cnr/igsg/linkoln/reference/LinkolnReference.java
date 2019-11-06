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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import it.cnr.igsg.linkoln.entity.Reference;

public final class LinkolnReference {

	/*
	 * 
	 * Presentation interface for the extracted references.
	 * 
	 * - getCitation()
	 * - getLinkolnIdentifiers()
	 * 
	 */
	
	private String type = ""; //LegislationReference, CaseLawReference, etc.
	
	private String citation = null;
	
	private Collection<LinkolnIdentifier> identifiers = new ArrayList<LinkolnIdentifier>();
	
	LinkolnReference() {
		
		//not to be used
	}

	LinkolnReference(Reference annotationEntity) {
		
		this.citation = annotationEntity.getText();
		
		this.type = annotationEntity.getClass().getSimpleName();
	}

	void addIdentifier(LinkolnIdentifier linkolnIdentifier) {
		
		if(linkolnIdentifier != null) identifiers.add(linkolnIdentifier);
	}
	
	public String getCitation() {
		
		return this.citation;
	}
	
	public Collection<LinkolnIdentifier> getLinkolnIdentifiers() {
		
		return Collections.unmodifiableCollection(identifiers);
	}

	public String getType() {
		
		return type;
	}
}
