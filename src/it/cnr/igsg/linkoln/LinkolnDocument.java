/********************************************************************************************************
Copyright (c) 2016-2021 Institute of Legal Information and Judicial Systems IGSG-CNR (formerly ITTIG-CNR)

This program and the accompanying materials  are made available under the terms of the GNU General Public
License as published by the Free Software Foundation; either version 3 of the License, or (at your option)
any later version. 
You may not use this work except in compliance with the Licence.
You may obtain a copy of the Licence at: https://www.gnu.org/licenses/gpl-3.0.txt
Unless required by applicable law or agreed to in writing, software distributed under the Licence is 
distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the Licence for the specific language governing permissions and limitations under the Licence.
 
Authors: Lorenzo Bacci (IGSG-CNR)
********************************************************************************************************/

package it.cnr.igsg.linkoln;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import it.cnr.ittig.linkoln.entity.AnnotationEntity;
import it.cnr.ittig.linkoln.entity.CaseLawReference;
import it.cnr.ittig.linkoln.entity.EuropeanCaseLawReference;
import it.cnr.ittig.linkoln.entity.EuropeanLegislationReference;
import it.cnr.ittig.linkoln.entity.LegislationReference;
import it.cnr.ittig.linkoln.entity.Reference;
import it.cnr.ittig.linkoln.service.LinkolnAnnotationService;
import it.cnr.ittig.linkoln.service.impl.HtmlPostProcessing;
import it.cnr.ittig.linkoln.service.impl.HtmlPreProcessing;

public class LinkolnDocument {


	/* TODO Ogni text deve avere associata una lingua. Si possono avere più text in lingue diverse nello stesso linkolnDocument */
	
	
	/*
	 * Input text and language handling.
	 */
	
	private String language = "";
	
	private boolean failed = false;
	
	private String identifier = "";
	
	private String plainText = "";
	
	private String originalText = "";
	
	private String finalHtml = "";
	
	private ArrayList<Object> originalContents = new ArrayList<Object>();
	
	private Set<Integer> cuts = new HashSet<Integer>();
	
	/*
	 * Altro:
	 */
	
	private long executionTime = 0;
	
	public long getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}

	public boolean hasFailed() {
		return failed;
	}

	public void setFailed(boolean failedExecution) {
		this.failed = failedExecution;
	}

	public String getIdentifier() {
		return identifier;
	}

	void setIdentifier(String identifier) {
		
		this.identifier = identifier;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setText(String text) {
		
		this.originalText = text;
				
		htmlPreProcessing();
	}
	
	public String getFinalHtml() {
	
		return finalHtml;
	}
	
	//Initialize "originalContents", "cuts" and set plainText
	private void htmlPreProcessing() {
		
		HtmlPreProcessing hpp = new HtmlPreProcessing();
		
		try {
			
			hpp.yyreset(new StringReader(originalText));
			
			hpp.yylex();
			
		} catch (IOException e) {

			e.printStackTrace();
			
			return;
		}
		
		StringBuilder plainContents = new StringBuilder();
		
		for(Object content : hpp.getContents()) {
			
			if(content instanceof StringBuilder) {
				
				plainContents.append(content.toString());
			}
		}
		
		plainText = plainContents.toString();
			
		cuts = hpp.getCuts();
		
		originalContents = hpp.getContents();
	}
	
	//Set "finalHtml"
	void htmlPostProcessing() {
		
		LinkolnAnnotationService htmlService = getAnnotationService("HtmlRendering");
		
		if(Linkoln.DEBUG_HTML) {
			
			htmlService = getAnnotationService("HtmlDebugRendering");
		}

		HtmlPostProcessing hpp = new HtmlPostProcessing();
		
		hpp.setCuts(cuts);
		
		try {
			
			hpp.yyreset(new StringReader(htmlService.getOutput()));
			
			hpp.yylex();
			
		} catch (IOException e) {

			e.printStackTrace();
			
			return;
		}
		
		ArrayList<String> finalContents = new ArrayList<String>();
		
		int index = 0;
		
		for(Object originalContent : originalContents) {
			
			if(originalContent instanceof HtmlAnnotation) {
				
				finalContents.add(((HtmlAnnotation) originalContent).toString());
				continue;
			}
			
			finalContents.add(hpp.getContents().get(index).toString());
			index++;
		}
		
		StringBuilder finalContent = new StringBuilder();
		
		for(String content : finalContents) {
			
			finalContent.append(content);
		}

		finalHtml = finalContent.toString();
	}
	
	
	
	

	
	/*
	 * Text Annotations
	 */
	
	private Collection<LinkolnAnnotationService> annotationServices = null;

	protected Collection<AnnotationEntity> entities = new ArrayList<AnnotationEntity>();
	
	protected Map<String,AnnotationEntity> id2entity = new HashMap<String,AnnotationEntity>();
	
	protected String getNextId() {
		
		return String.valueOf(entities.size() + 1);
	}
	
	public void addAnnotationEntity(AnnotationEntity entity) {
		
		if( !entity.getId().equals("") && id2entity.get(entity.getId()) != null) {
			
			//AnnotationEntity già presente nel linkoln document, non fare altro
			return;
		}

		//Nuova Annotation Entity
		
		String id = getNextId();
		
		entity.setId(id);
		entities.add(entity);
		id2entity.put(entity.getId(), entity);
	}

	public void replaceAnnotationEntity(AnnotationEntity oldEntity, AnnotationEntity newEntity) {
		
		entities.remove(oldEntity);
		entities.add(newEntity);
		id2entity.put(newEntity.getId(), newEntity);
	}

	public AnnotationEntity getAnnotationEntity(String id) {
		
		return id2entity.get(id);
	}
	
	public Collection<AnnotationEntity> getAnnotationEntities() {
		
		return Collections.unmodifiableCollection(entities);
	}
	
	
	LinkolnDocument() {
		
		init();
	}
	
	
	private void init() {
		
		//init or reset the legal references collection

		annotationServices = new ArrayList<LinkolnAnnotationService>();
		
		errors = new ArrayList<String>();
		
		messages = new ArrayList<String>();
	}
	
	public Collection<LinkolnAnnotationService> getAnnotationServices() {
		
		return Collections.unmodifiableCollection(this.annotationServices);
	}
	
	public boolean addAnnotationService(LinkolnAnnotationService service) {
		
		return this.annotationServices.add(service);
	}

	public final String getAnnotatedText() {
		
		//The original text annotated in a specific format.
		
		if(annotationServices.size() == 0) {
			
			return this.plainText;
		}
		
		return ((ArrayList<LinkolnAnnotationService>) annotationServices).get(annotationServices.size() - 1).getOutput();
	}
	
	public LinkolnAnnotationService getAnnotationService(String serviceClassName) {
		
		for(LinkolnAnnotationService service : this.getAnnotationServices() ) {
			
			if(service.getClass().getSimpleName().equalsIgnoreCase(serviceClassName)) {
				
				return service;
			}
		}
		
		return null;
	}
	
	
	

	
	/*
	 * Debug, messages and errors.
	 */
	
	//Error and warning captured during the execution of the Engine
	private Collection<String> errors = null;
	private Collection<String> messages = null;

	public final Collection<String> getErrors() {

		return Collections.unmodifiableCollection(errors);
	}

	public final Collection<String> getMessages() {

		return Collections.unmodifiableCollection(messages);
	}

	
	public String getReferenceTable() {
		
		String html = "<table class='table table-striped mt-5'><thead><tr><th scope='col'>#</th><th scope='col'>Text</th><th scope='col'>Features</th><th scope='col'>Identifiers</th></tr></thead><tbody>"; 		
		
		int count = 0;
		
		for(AnnotationEntity entity : getAnnotationEntities()) {
			
			if(entity instanceof Reference) {
				
				if( !hasIdentifiers(entity)) continue;
				
				count++;
				
				html += "<tr><th scope='row'>" + count + "</th>";
				
				html += "<td>" + entity.getText() + "</td>";
				
				html += "<td>" + readFeatures(entity) + "</td>";
				
				html += "<td>" + readIdentifiers(entity) + "</td></tr>";
			}
		}
		
		html += "</tbody></table>";
		
		return html;
	}
	
	private String readFeatures(AnnotationEntity entity) {
		
		//docType, authority, number, date, caseNumber, partition
		String type = "";
		String auth = "";
		String number = "";
		String date = "";
		String partition = "";
		
		if(entity.getRelatedEntity("DOCTYPE") != null) type = entity.getRelatedEntity("DOCTYPE").getValue();
		if(entity.getRelatedEntity("CL_DOCTYPE") != null) type = entity.getRelatedEntity("CL_DOCTYPE").getValue();
		if(entity.getRelatedEntity("LEG_DOCTYPE") != null) type = entity.getRelatedEntity("LEG_DOCTYPE").getValue();
		if(entity.getRelatedEntity("EU_LEG_DOCTYPE") != null) type = entity.getRelatedEntity("EU_LEG_DOCTYPE").getValue();
		
		if(entity.getRelatedEntity("AUTH") != null) auth = entity.getRelatedEntity("AUTH").getValue();
		if(entity.getRelatedEntity("CL_AUTH") != null) auth = entity.getRelatedEntity("CL_AUTH").getValue();
		if(entity.getRelatedEntity("EU_CL_AUTH") != null) auth = entity.getRelatedEntity("EU_CL_AUTH").getValue();
		if(entity.getRelatedEntity("LEG_AUTH") != null) auth = entity.getRelatedEntity("LEG_AUTH").getValue();
		if(entity.getRelatedEntity("EU_LEG_AUTH") != null) auth = entity.getRelatedEntity("EU_LEG_AUTH").getValue();
		
		if(entity.getRelatedEntity("NUMBER") != null) number = entity.getRelatedEntity("NUMBER").getValue();
		
		if(entity.getRelatedEntity("DATE") != null) date = entity.getRelatedEntity("DATE").getValue();
		if(entity.getRelatedEntity("DOC_DATE") != null) date = entity.getRelatedEntity("DOC_DATE").getValue();
		
		if(entity.getRelatedEntity("LEG_PARTITION") != null) partition = entity.getRelatedEntity("LEG_PARTITION").getValue();

		String html = "";
		
		if( !type.equals("")) html += "<strong>Document type:</strong>&nbsp;<em>" + type + "</em><br/>";
		if( !auth.equals("")) html += "<strong>Authority:</strong>&nbsp;<em>" + auth + "</em><br/>";
		if( !number.equals("")) html += "<strong>Number:</strong>&nbsp;<em>" + number + "</em><br/>";
		if( !date.equals("")) html += "<strong>Date:</strong>&nbsp;<em>" + date + "</em><br/>";
		if( !partition.equals("")) html += "<strong>Partition:</strong>&nbsp;<em>" + partition + "</em><br/>";
		
		return html;
	}
	
	private boolean hasIdentifiers(AnnotationEntity entity) {
		
		if(entity instanceof EuropeanLegislationReference) {
			
			if( !((EuropeanLegislationReference) entity).getCelex(getLanguage()).equals("") ) return true;
			
			if( !((EuropeanLegislationReference) entity).getEli(getLanguage()).equals("") ) return true;
		}
		
		if(entity instanceof LegislationReference) {
			
			if( !((LegislationReference) entity).getUrnNir().equals("") ) return true;
		}
		
		if(entity instanceof EuropeanCaseLawReference) {
			
			if( !((EuropeanCaseLawReference) entity).getEcli().equals("") ) return true;
		
		}
		
		if(entity instanceof CaseLawReference) {
			
			if( !((CaseLawReference) entity).getEcli().equals("") ) return true;
		
		}
		
		return false;
	}
	
	private String readIdentifiers(AnnotationEntity entity) {
		
		String html = "";
				
		if(entity instanceof EuropeanLegislationReference) {
			
			if( !((EuropeanLegislationReference) entity).getCelex(getLanguage()).equals("") ) {
				
				html += "<a href='" + ((EuropeanLegislationReference) entity).getCelex(getLanguage()) + "'>[CELEX]</a><br/>";
			}
		}
		
		if(entity instanceof EuropeanLegislationReference) {
			
			if( !((EuropeanLegislationReference) entity).getEli(getLanguage()).equals("") ) {
				
				html += "<a href='" + ((EuropeanLegislationReference) entity).getEli(getLanguage()) + "'>[ELI]</a><br/>";
			}
		}
		
		if(entity instanceof EuropeanCaseLawReference) {
			
			if( !((EuropeanCaseLawReference) entity).getEcli().equals("") ) {
				
				html += "<a href='" + ((EuropeanCaseLawReference) entity).getEcli() + "'>[ECLI]</a><br/>";
			}
		}
		
		return html;
	}
	
}
