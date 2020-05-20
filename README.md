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

### Web Demo
*  Play with Linkoln at: [http://dev.igsg.cnr.it:8080/linkoln](http://dev.igsg.cnr.it:8080/linkoln)

#### Download the binary
*  Linkoln 2.0 is available as a Java library here: [http://dev.igsg.cnr.it/linkoln/linkoln-2.2.0.jar](http://dev.igsg.cnr.it/linkoln/linkoln-2.2.0.jar)


#### Testing the library
```java
import it.cnr.igsg.linkoln.Linkoln;
import it.cnr.igsg.linkoln.LinkolnDocument;
import it.cnr.igsg.linkoln.reference.LinkolnIdentifier;
import it.cnr.igsg.linkoln.reference.LinkolnReference;

public class Test {

	public static void main(String[] args) {
	
		String text = "vedi lett. e), comma 2, art. 2 del decreto del Ministero delle finanze del 25 novembre 1998, n. 418";

		LinkolnDocument linkolnDocument = Linkoln.run(text);
		
		if( !linkolnDocument.hasFailed()) {
			
			System.out.println("\n1) List of identified legal references:");
			
			for(LinkolnReference reference : linkolnDocument.getReferences()) {
				
				System.out.println("\n\t- " + reference.getType() + " found: \"" + reference.getCitation() + "\"");
				
				for(LinkolnIdentifier identifier : reference.getLinkolnIdentifiers()) {
					
					System.out.println("\t\t " + identifier.getType() + " (" + identifier.getCode() + ") URL: " + identifier.getUrl());
				}
			}
			
			System.out.println("\n\n2) HTML:\n\n" + linkolnDocument.getRendering("html"));
		}	
	}
}
```

#### Returns
```pre
1) List of identified legal references:

	- LegislationReference found: "lett. e), comma 2, art. 2 del decreto del Ministero delle finanze del 25 novembre 1998, n. 418"
		 URN (urn:nir:ministero.finanze:decreto:1998-11-25;418~art2-com2-lete) URL: http://www.normattiva.it/uri-res/N2Ls?urn:nir:ministero.finanze:decreto:1998-11-25;418~art2-com2-lete


2) HTML:

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML//EN" "xhtml-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<body><pre>
vedi <a href="http://www.normattiva.it/uri-res/N2Ls?urn:nir:ministero.finanze:decreto:1998-11-25;418~art2-com2-lete" target="_blank">lett. e), comma 2, art. 2 del decreto del Ministero delle finanze del 25 novembre 1998, n. 418</a>
</pre></body></html>
```


