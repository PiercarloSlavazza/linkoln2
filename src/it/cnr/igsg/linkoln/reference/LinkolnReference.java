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
	 * Presentation of the extracted references.
	 * 
	 * - getCitation()
	 * - getLinkolnIdentifiers()
	 * 
	 */
	
	private String citation = null;

	private String type = null; //LegislationReference, CaseLawReference, etc.
	private String authority = null;
	private String authoritySection = null;
	private String detachedAuthority = null;
	private String docType = null;
	private String number = null;
	private String year = null;
	private String date = null;
	private String caseNumber = null;
	private String city = null;
	private String region = null;
	private String applicant = null;
	private String defendant = null;
	
	
	private Collection<LinkolnIdentifier> linkolnIdentifiers = new ArrayList<LinkolnIdentifier>();
	
	LinkolnReference() {
		
		//not to be used
	}

	LinkolnReference(Reference entity) {
		
		this.citation = entity.getText();
		
		this.type = entity.getClass().getSimpleName();
		
		this.authority = entity.getAuthority();
		this.authoritySection = entity.getRelatedValue("CL_AUTH_SECTION");
		this.detachedAuthority = entity.getRelatedValue("CL_DETACHED_SECTION");
		this.docType = entity.getDocumentType();
		this.number = entity.getNumber();
		this.year = entity.getYear();
		this.date = entity.getRelatedValue("DATE");
		this.caseNumber = entity.getRelatedValue("CASENUMBER");
		this.city = entity.getRelatedValue("CITY");
		this.region = entity.getRelatedValue("REGION");
		this.applicant = entity.getRelatedValue("APPLICANT");
		this.defendant = entity.getRelatedValue("DEFENDANT");
	}

	void addLinkolnIdentifier(LinkolnIdentifier linkolnIdentifier) {
		
		if(linkolnIdentifier != null) linkolnIdentifiers.add(linkolnIdentifier);
	}
	
	public String getCitation() {
		
		return this.citation;
	}
	
	public Collection<LinkolnIdentifier> getLinkolnIdentifiers() {
		
		return Collections.unmodifiableCollection(linkolnIdentifiers);
	}

	public String getType() {
		return type;
	}

	public String getAuthority() {
		return authority;
	}

	public String getAuthoritySection() {
		return authoritySection;
	}

	public String getDetachedAuthority() {
		return detachedAuthority;
	}

	public String getDocType() {
		return docType;
	}

	public String getNumber() {
		return number;
	}

	public String getYear() {
		return year;
	}

	public String getDate() {
		return date;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public String getCity() {
		return city;
	}

	public String getRegion() {
		return region;
	}

	public String getApplicant() {
		return applicant;
	}

	public String getDefendant() {
		return defendant;
	}
	
	
}
