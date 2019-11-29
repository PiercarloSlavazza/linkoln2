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
	private String subject = null;
	
	
	private Collection<LinkolnIdentifier> linkolnIdentifiers = new ArrayList<LinkolnIdentifier>();
	
	LinkolnReference() {
		
		//not to be used
	}

	LinkolnReference(Reference entity) {
		
		this.citation = entity.getText();
		
		this.type = entity.getClass().getSimpleName();
		
		this.authority = entity.getAuthorityValue();
		this.authoritySection = entity.getRelatedValue("CL_AUTH_SECTION");
		this.detachedAuthority = entity.getRelatedValue("CL_DETACHED_SECTION");
		
		this.city = entity.getCity();
		this.region = entity.getRelatedValue("REGION");

		this.docType = entity.getDocumentTypeValue();
		this.number = entity.getNumber();
		this.year = entity.getYear();
		this.date = entity.getRelatedValue("DOC_DATE");
		this.caseNumber = entity.getRelatedValue("CASENUMBER");
		this.applicant = entity.getApplicant();
		this.defendant = entity.getDefendant();
		this.subject = entity.getSubject();
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
		
		if(type == null) return null;
		if(type.trim().length() < 1) return null;
		return type.trim();
	}

	public String getAuthority() {
		if(authority == null) return null;
		if(authority.trim().length() < 1) return null;
		return authority.trim();
	}

	public String getAuthoritySection() {
		if(authoritySection == null) return null;
		if(authoritySection.trim().length() < 1) return null;
		return authoritySection.trim();
	}

	public String getDetachedAuthority() {
		if(detachedAuthority == null) return null;
		if(detachedAuthority.trim().length() < 1) return null;
		return detachedAuthority.trim();
	}

	public String getDocType() {
		if(docType == null) return null;
		if(docType.trim().length() < 1) return null;
		return docType.trim();
	}

	public String getNumber() {
		if(number == null) return null;
		if(number.trim().length() < 1) return null;
		return number.trim();
	}

	public String getYear() {
		if(year == null) return null;
		if(year.trim().length() < 1) return null;
		return year.trim();
	}

	public String getDate() {
		if(date == null) return null;
		if(date.trim().length() < 1) return null;
		return date.trim();
	}

	public String getCaseNumber() {
		if(caseNumber == null) return null;
		if(caseNumber.trim().length() < 1) return null;
		return caseNumber.trim();
	}

	public String getCity() {
		if(city == null) return null;
		if(city.trim().length() < 1) return null;
		return city.trim();
	}

	public String getRegion() {
		if(region == null) return null;
		if(region.trim().length() < 1) return null;
		return region.trim();
	}

	public String getApplicant() {
		if(applicant == null) return null;
		if(applicant.trim().length() < 1) return null;
		return applicant.trim();
	}

	public String getDefendant() {
		if(defendant == null) return null;
		if(defendant.trim().length() < 1) return null;
		return defendant.trim();
	}
	
	public String getSubject() {
		if(subject == null) return null;
		if(subject.trim().length() < 1) return null;
		return subject.trim();
	}
	
}
