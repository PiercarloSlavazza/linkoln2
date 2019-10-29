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

### Download the library
*  Linkoln v. 2.0.9 is available as a Java library here: [http://dev.igsg.cnr.it/linkoln/linkoln-2.0.9.jar](http://dev.igsg.cnr.it/linkoln/linkoln-2.0.9.jar)

### Testing the library

```java
	
	public static void main(String[] args) {
			
		String text = "vedi lett. e), comma 2, art. 2 del decreto del Ministero delle finanze del 25 novembre 1998, n. 418";

		LinkolnDocument linkolnDocument = Linkoln.run(text);
		
		if( !linkolnDocument.hasFailed()) {
			
			System.out.println("\nList of identified legal references:");
			
			for(AnnotationEntity entity : linkolnDocument.getAnnotationEntities()) {
				
				if(entity instanceof Reference) {
					
					System.out.println("\n" + entity.getClass().getSimpleName() + " found: \n" + ((Reference) entity));
				}
			}
			
			System.out.println("\n\nOriginal text with hyperlinks:\n\n" + linkolnDocument.getFinalHtml());
		}	
	}
```


