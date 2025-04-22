package it.cnr.igsg.linkoln.service.impl.it;

import java.io.IOException;
import java.io.StringReader;

import it.cnr.igsg.linkoln.entity.*;
import it.cnr.igsg.linkoln.service.*;

/*
This grammar was added in order to compensate for the cities not recognized by Linkoln 2 (and 3).
It was necessary to add a new grammar because the authors (at the time of writing this grammar) have not made the
JFlex sources public (upon my explicit request, they refused to share them, citing the reason
that the sources were not of sufficiently good quality).

ATTENTION:
- The grammar was drafted with the help of Claude/ChatGPT, attempting to deduce the most plausible structure from the
Java code (note that it is NOT possible to reverse engineer a JFlex grammar from the generated Java code);
- The generated Java class MUST BE inserted into the pipeline BEFORE the STOP WORDS; otherwise, the grammar would need
to be more complex by accounting for the possibility of existing annotations for the STOP WORDS (e.g., in the text
"court of marsala", the preposition is considered as a "stop word");
- I don't know why it's necessary to repeat all the patterns in two rules, and I haven't had the time to optimize.

BUILD:
to generate the Java class execute:
mvn exec:java -Dexec.mainClass="jflex.Main" -Dexec.args="-d src/main/java/it/cnr/igsg/linkoln/service/impl/it src/main/java/it/cnr/igsg/linkoln/service/impl/it/Geos2.jflex"

TEST:
Unit Tests are coded in the class it.cnr.igsg.linkoln.LinkolnTest.
*/

%%

%public
%class Geos2
%extends LinkolnAnnotationService
%unicode
%ignorecase
%function yylex
%type void
%line
%column

%state lkn

%{
  StringBuffer string = new StringBuffer();

     public Geos2() {}

     @Override
      protected boolean run() {
        try {
          yyreset(new StringReader(getInput()));
          yylex();
          return true;
        } catch (IOException e) {
          e.printStackTrace();
          return false;
        }
      }

      @Override
      public String language() {
        return "IT";
      }

      @Override
      public String version() {
        return "1.1";
      }
%}

Whitespace = \r|\n|\r\n|" "|"\t"
Punctuation = "," | ";" | ":" | "'" | "." | "!" | "?" | "\"" | "(" | ")" | "[" | "]" | "{" | "}" | "\\" | "/" | "-" | "_" | "=" | "+" | "*" | "&" | "^" | "%" | "$" | "#" | "@" | "`" | "~" | "<" | ">"

%%

<YYINITIAL> {
  "aquila"     { annotate(new City(), "IT_AQ", false, false); }
  "avezzano"     { annotate(new City(), "IT_A515", false, false); }
  "barcellona pozzo di gotto"     { annotate(new City(), "IT_M228", false, false); }
  "busto arsizio"     { annotate(new City(), "IT_B300", false, false); }
  "caltagirone"     { annotate(new City(), "IT_B429", false, false); }
  "cassino"     { annotate(new City(), "IT_C034", false, false); }
  "castrovillari"     { annotate(new City(), "IT_C349", false, false); }
  "civitavecchia"     { annotate(new City(), "IT_C773", false, false); }
  "fermo"     { annotate(new City(), "IT_FM", false, false); }
  "forli'"     { annotate(new City(), "IT_D704", false, false); }
  "forlì"     { annotate(new City(), "IT_D704", false, false); }
  "gela"     { annotate(new City(), "IT_D960", false, false); }
  "ivrea"     { annotate(new City(), "IT_E379", false, false); }
  "l'aquila"     { annotate(new City(), "IT_AQ", false, false); }
  "la spezia"     { annotate(new City(), "IT_SP", false, false); }
  "lagonegro"     { annotate(new City(), "IT_E409", false, false); }
  "lamezia terme"     { annotate(new City(), "IT_M208", false, false); }
  "lanciano"     { annotate(new City(), "IT_E435", false, false); }
  "lanusei"     { annotate(new City(), "IT_E447", false, false); }
  "larino"     { annotate(new City(), "IT_E456", false, false); }
  "lecco"     { annotate(new City(), "IT_LC", false, false); }
  "locri"     { annotate(new City(), "IT_D976", false, false); }
  "lodi"     { annotate(new City(), "IT_LO", false, false); }
  "macerata"     { annotate(new City(), "IT_MC", false, false); }
  "marsala"     { annotate(new City(), "IT_E974", false, false); }
  "massa"     { annotate(new City(), "IT_F023", false, false); }
  "nocera inferiore"     { annotate(new City(), "IT_F912", false, false); }
  "nola"     { annotate(new City(), "IT_F924", false, false); }
  "palmi"     { annotate(new City(), "IT_G288", false, false); }
  "paola"     { annotate(new City(), "IT_G317", false, false); }
  "patti"     { annotate(new City(), "IT_G377", false, false); }
  "potenza"     { annotate(new City(), "IT_PZ", false, false); }
  "prato"     { annotate(new City(), "IT_PO", false, false); }
  "reggio di calabria"     { annotate(new City(), "IT_RC", false, false); }
  "rovereto"     { annotate(new City(), "IT_H612", false, false); }
  "santa maria capua vetere"     { annotate(new City(), "IT_I234", false, false); }
  "sciacca"     { annotate(new City(), "IT_I523", false, false); }
  "spoleto"     { annotate(new City(), "IT_I921", false, false); }
  "sulmona"     { annotate(new City(), "IT_I804", false, false); }
  "termini imerese"     { annotate(new City(), "IT_L112", false, false); }
  "tivoli"     { annotate(new City(), "IT_L182", false, false); }
  "torre annunziata"     { annotate(new City(), "IT_L245", false, false); }
  "trani"     { annotate(new City(), "IT_L328", false, false); }
  "trapani"     { annotate(new City(), "IT_TP", false, false); }
  "vallo della lucania"     { annotate(new City(), "IT_L628", false, false); }
  "vasto"     { annotate(new City(), "IT_E372", false, false); }
  "velletri"     { annotate(new City(), "IT_L722", false, false); }
  {Punctuation}          { addText(yytext()); position++;}
  {Whitespace}           { addText(yytext()); position++;}
  .                      { addText(yytext()); position++;}
}

<lkn> {
  "aquila"     { annotate(new City(), "IT_AQ", false, false); }
  "avezzano"     { annotate(new City(), "IT_A515", false, false); }
  "barcellona pozzo di gotto"     { annotate(new City(), "IT_M228", false, false); }
  "busto arsizio"     { annotate(new City(), "IT_B300", false, false); }
  "caltagirone"     { annotate(new City(), "IT_B429", false, false); }
  "cassino"     { annotate(new City(), "IT_C034", false, false); }
  "castrovillari"     { annotate(new City(), "IT_C349", false, false); }
  "civitavecchia"     { annotate(new City(), "IT_C773", false, false); }
  "fermo"     { annotate(new City(), "IT_FM", false, false); }
  "forli'"     { annotate(new City(), "IT_D704", false, false); }
  "forlì"     { annotate(new City(), "IT_D704", false, false); }
  "gela"     { annotate(new City(), "IT_D960", false, false); }
  "ivrea"     { annotate(new City(), "IT_E379", false, false); }
  "l'aquila"     { annotate(new City(), "IT_AQ", false, false); }
  "la spezia"     { annotate(new City(), "IT_SP", false, false); }
  "lagonegro"     { annotate(new City(), "IT_E409", false, false); }
  "lamezia terme"     { annotate(new City(), "IT_M208", false, false); }
  "lanciano"     { annotate(new City(), "IT_E435", false, false); }
  "lanusei"     { annotate(new City(), "IT_E447", false, false); }
  "larino"     { annotate(new City(), "IT_E456", false, false); }
  "lecco"     { annotate(new City(), "IT_LC", false, false); }
  "locri"     { annotate(new City(), "IT_D976", false, false); }
  "lodi"     { annotate(new City(), "IT_LO", false, false); }
  "macerata"     { annotate(new City(), "IT_MC", false, false); }
  "marsala"     { annotate(new City(), "IT_E974", false, false); }
  "massa"     { annotate(new City(), "IT_F023", false, false); }
  "nocera inferiore"     { annotate(new City(), "IT_F912", false, false); }
  "nola"     { annotate(new City(), "IT_F924", false, false); }
  "palmi"     { annotate(new City(), "IT_G288", false, false); }
  "paola"     { annotate(new City(), "IT_G317", false, false); }
  "patti"     { annotate(new City(), "IT_G377", false, false); }
  "potenza"     { annotate(new City(), "IT_PZ", false, false); }
  "prato"     { annotate(new City(), "IT_PO", false, false); }
  "reggio di calabria"     { annotate(new City(), "IT_RC", false, false); }
  "rovereto"     { annotate(new City(), "IT_H612", false, false); }
  "santa maria capua vetere"     { annotate(new City(), "IT_I234", false, false); }
  "sciacca"     { annotate(new City(), "IT_I523", false, false); }
  "spoleto"     { annotate(new City(), "IT_I921", false, false); }
  "sulmona"     { annotate(new City(), "IT_I804", false, false); }
  "termini imerese"     { annotate(new City(), "IT_L112", false, false); }
  "tivoli"     { annotate(new City(), "IT_L182", false, false); }
  "torre annunziata"     { annotate(new City(), "IT_L245", false, false); }
  "trani"     { annotate(new City(), "IT_L328", false, false); }
  "trapani"     { annotate(new City(), "IT_TP", false, false); }
  "vallo della lucania"     { annotate(new City(), "IT_L628", false, false); }
  "vasto"     { annotate(new City(), "IT_E372", false, false); }
  "velletri"     { annotate(new City(), "IT_L722", false, false); }
  {Punctuation}          { addText(yytext()); position++;}
  {Whitespace}           { addText(yytext()); position++;}
  .                      { addText(yytext()); position++;}
}
<<EOF>>                  { return; }
