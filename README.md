#-------------------------------------------------------------------------------
# Copyright (c) 2016-2021 Institute of Legal Information and Judicial Systems IGSG-CNR (formerly ITTIG-CNR)
# 
# This program and the accompanying materials  are made available under the terms of the GNU General Public
# License as published by the Free Software Foundation; either version 3 of the License, or (at your option)
# any later version. 
# You may not use this work except in compliance with the Licence.
# You may obtain a copy of the Licence at: https://www.gnu.org/licenses/gpl-3.0.txt
# Unless required by applicable law or agreed to in writing, software distributed under the Licence is 
# distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the Licence for the specific language governing permissions and limitations under the Licence.
#  
# Authors: Lorenzo Bacci (IGSG-CNR)
#-------------------------------------------------------------------------------
![alt text](./linkoln-logo.png)

**Linkoln** is a software developed at IGSG-CNR (*Istituto di Informatica Giuridica e Sistemi Giudiziari* del *Consiglio Nazionale delle Ricerche*) for the automatic detection and linking of legal references contained in legal texts written in Italian.


### Features
*  identification of legislative references;
*  identification of case-law references;
*  identification of legislative aliases;
*  identification of partitions (articles, paragraphs, letters, etc.) in references;
*  identification of multiple references;
*  identification of European legislation;
*  identification of CJEU, ECHR and EPO case-laws;
*  identification of legacy authorities and types of document for both legislative and case-law references;
*  generation of standard identifiers for the cited documents (URN-NIR, CELEX, ELI, ECLI);
*  generation of URLs for accessing the cited documents.

### Input
*  Either a fragment of text or an entire document written in Italian, in plain-text format or in an annotated format (HTML, XML).

### Output
*  A collection of all the legal references found in the input;
*  the original text or document annotated with hypertextual links in correspondence of the identified citations.

