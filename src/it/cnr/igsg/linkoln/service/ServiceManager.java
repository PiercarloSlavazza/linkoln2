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
package it.cnr.igsg.linkoln.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ServiceLoader;

import it.cnr.igsg.linkoln.Linkoln;
import it.cnr.igsg.linkoln.service.impl.FinalizeAnnotations;
import it.cnr.igsg.linkoln.service.impl.HtmlRenderer;
import it.cnr.igsg.linkoln.service.impl.it.Abbreviations;
import it.cnr.igsg.linkoln.service.impl.it.AddPartitionsToReferences;
import it.cnr.igsg.linkoln.service.impl.it.AliasPartitions;
import it.cnr.igsg.linkoln.service.impl.it.AliasReferences;
import it.cnr.igsg.linkoln.service.impl.it.Aliases;
import it.cnr.igsg.linkoln.service.impl.it.ArticleNumbers;
import it.cnr.igsg.linkoln.service.impl.it.Articles;
import it.cnr.igsg.linkoln.service.impl.it.CaseLawAuthorities;
import it.cnr.igsg.linkoln.service.impl.it.CaseNumbers;
import it.cnr.igsg.linkoln.service.impl.it.Commas;
import it.cnr.igsg.linkoln.service.impl.it.Dates;
import it.cnr.igsg.linkoln.service.impl.it.DetachedSections;
import it.cnr.igsg.linkoln.service.impl.it.DocTypes;
import it.cnr.igsg.linkoln.service.impl.it.Geos;
import it.cnr.igsg.linkoln.service.impl.it.Items;
import it.cnr.igsg.linkoln.service.impl.it.JointCaseNumbers;
import it.cnr.igsg.linkoln.service.impl.it.Journals;
import it.cnr.igsg.linkoln.service.impl.it.LegislationAuthorities;
import it.cnr.igsg.linkoln.service.impl.it.Letters;
import it.cnr.igsg.linkoln.service.impl.it.Ministries;
import it.cnr.igsg.linkoln.service.impl.it.NamedEntities;
import it.cnr.igsg.linkoln.service.impl.it.NationalAuthorities;
import it.cnr.igsg.linkoln.service.impl.it.Numbers;
import it.cnr.igsg.linkoln.service.impl.it.Parties;
import it.cnr.igsg.linkoln.service.impl.it.Partitions;
import it.cnr.igsg.linkoln.service.impl.it.References;
import it.cnr.igsg.linkoln.service.impl.it.ReferencesLaw;
import it.cnr.igsg.linkoln.service.impl.it.RegionalCaseLawAuthorities;
import it.cnr.igsg.linkoln.service.impl.it.RegionalLegislationAuthorities;
import it.cnr.igsg.linkoln.service.impl.it.RvNumbers;
import it.cnr.igsg.linkoln.service.impl.it.Sections;
import it.cnr.igsg.linkoln.service.impl.it.Stopwords;
import it.cnr.igsg.linkoln.service.impl.it.Subjects;
import it.cnr.igsg.linkoln.service.impl.it.Vs;


public class ServiceManager {

	//Java Service Provider Interface - Service loader
	private ServiceLoader<LinkolnService> LinkolnServiceLoader = null;
	
	private Collection<LinkolnService> services = null;
	

	
	//singleton pattern	
	private static ServiceManager serviceManager = null;
	
	public static ServiceManager getInstance() {
		
		if(serviceManager == null) {
			
			serviceManager = new ServiceManager();
		}
		
		return serviceManager;
	}
	
	private ServiceManager() {
		
		services = new ArrayList<LinkolnService>();
		
		initServices();
	}
	
	
	/*
	 * Init every available service implementation once.
	 */
	private void initServices() {
		
		LinkolnServiceLoader = ServiceLoader.load(LinkolnService.class);
		
		int counter = 0;
		
		for(LinkolnService service : LinkolnServiceLoader) {
			
			if( !service.language().equalsIgnoreCase("it")) continue;
			
			if(Linkoln.DEBUG) System.out.println("Loading Service " + service.getDescription());
			
			counter++;
			service.setIndex(counter);

			services.add(service);
		}
		
		if(counter == 0) {
			
			//Manually add services -- Do not use SPI for the moment
			
			services.add(new Articles());
			services.add(new Commas());
			services.add(new Letters()); //Must run before stopwords (lettera a comma 1)
			services.add(new Stopwords());
			services.add(new Geos());
			services.add(new DetachedSections());
			services.add(new CaseLawAuthorities());
			services.add(new Sections());
			services.add(new LegislationAuthorities());
			services.add(new RegionalCaseLawAuthorities());
			services.add(new RegionalLegislationAuthorities());
			services.add(new Dates());
			services.add(new JointCaseNumbers());
			services.add(new CaseNumbers());
			services.add(new RvNumbers());
			services.add(new Numbers());
			services.add(new Items());
			services.add(new Journals());
			services.add(new Aliases());	
			services.add(new Ministries());
			services.add(new NationalAuthorities());
			services.add(new DocTypes());
			services.add(new Subjects());
			services.add(new Abbreviations());
			services.add(new Vs());
			services.add(new NamedEntities());
			services.add(new Parties());
			services.add(new ReferencesLaw());
			services.add(new References());
			services.add(new AliasPartitions());
			services.add(new AliasReferences());
			services.add(new ArticleNumbers());
			services.add(new Partitions());
			services.add(new AddPartitionsToReferences());
			services.add(new FinalizeAnnotations());
			services.add(new HtmlRenderer());
		}
	}
	
	/*
	 * Returns a fixed-order collection of the available services in a given language 
	 * and jurisdiction (if specified), along with the available default services.
	 */
	public Collection<LinkolnService> getServices(String lang) {
		
		Collection<LinkolnService> selectedServices = new ArrayList<LinkolnService>();

		for(LinkolnService service : services) {
			
			selectedServices.add(service);
		}
		
		return Collections.unmodifiableCollection(selectedServices);
	}
}
