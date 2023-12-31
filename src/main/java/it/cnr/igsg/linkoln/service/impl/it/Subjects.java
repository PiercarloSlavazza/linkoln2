/* The following code was generated by JFlex 1.7.0 */

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
 package it.cnr.igsg.linkoln.service.impl.it;

import java.io.IOException;
import java.io.StringReader;

import it.cnr.igsg.linkoln.entity.*;
import it.cnr.igsg.linkoln.service.*;

import it.cnr.igsg.linkoln.service.impl.Util;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0
 * from the specification file <tt>jflex/Subjects.jflex</tt>
 */
public class Subjects extends LinkolnAnnotationService {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int lkn = 2;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\13\1\2\1\0\1\13\1\1\22\0\1\13\5\0\1\5"+
    "\1\0\1\14\1\14\2\0\1\14\1\3\1\53\1\54\1\52\1\52"+
    "\1\52\1\52\1\52\1\52\1\52\1\52\1\52\1\52\1\15\1\12"+
    "\5\0\1\26\1\7\1\40\1\62\1\36\1\23\1\30\1\71\1\47"+
    "\1\23\1\65\1\60\1\56\1\6\1\51\1\11\1\23\1\32\1\24"+
    "\1\44\1\42\1\34\1\73\1\23\1\23\1\23\1\66\1\0\1\67"+
    "\1\0\1\4\1\0\1\25\1\20\1\37\1\61\1\35\1\16\1\27"+
    "\1\70\1\46\1\16\1\64\1\57\1\55\1\17\1\50\1\22\1\16"+
    "\1\31\1\21\1\43\1\41\1\33\1\72\1\16\1\16\1\16\12\0"+
    "\1\0\44\0\1\0\5\0\1\0\3\0\1\0\5\0\1\0\5\0"+
    "\1\16\1\16\4\16\1\0\1\16\1\16\1\16\2\16\1\16\1\16"+
    "\2\16\1\0\1\16\1\16\1\16\3\16\2\0\1\16\1\16\2\16"+
    "\3\0\1\16\1\16\4\16\1\0\1\16\1\16\1\16\2\16\1\16"+
    "\1\16\2\16\1\0\1\16\1\16\1\16\3\16\2\0\1\16\1\16"+
    "\2\16\53\0\1\16\1\16\6\0\2\45\66\0\1\16\1\16\4\0"+
    "\1\16\1\16\17\0\1\10\u1c88\0\1\16\1\16\216\0\2\16\42\0"+
    "\1\16\1\16\u0144\0\1\13\20\0\1\3\1\3\4\0\1\0\16\0"+
    "\1\0\1\0\u0100\0\1\63\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\udee5\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\4\1\2\2\50\0\2\3\1\0\1\3\12\0"+
    "\3\4\1\0\1\4\5\0\1\5\71\0\1\6\17\0";

  private static int [] zzUnpackAction() {
    int [] result = new int[146];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\74\0\170\0\264\0\360\0\u012c\0\360\0\u0168"+
    "\0\u01a4\0\u01e0\0\u021c\0\u0258\0\u0294\0\u02d0\0\u030c\0\170"+
    "\0\u0348\0\u0384\0\u03c0\0\u03fc\0\u0438\0\u0474\0\u04b0\0\u04ec"+
    "\0\u0528\0\u0564\0\u05a0\0\u05dc\0\u0618\0\u0654\0\u0690\0\u06cc"+
    "\0\u0708\0\u0744\0\u0780\0\u07bc\0\u07f8\0\u0834\0\u0870\0\u08ac"+
    "\0\u08e8\0\u0924\0\u0960\0\u099c\0\u09d8\0\u0a14\0\u0a50\0\u0a8c"+
    "\0\360\0\u0ac8\0\u0b04\0\u0b40\0\u0b7c\0\u0bb8\0\u0bf4\0\u0c30"+
    "\0\u0c6c\0\u0ca8\0\u0ce4\0\u0d20\0\u0d5c\0\u0d98\0\360\0\u0dd4"+
    "\0\u0e10\0\u0e10\0\u0e4c\0\u0e88\0\u0ec4\0\u0f00\0\u0f3c\0\u0f78"+
    "\0\360\0\u0fb4\0\u0ff0\0\u102c\0\u1068\0\u10a4\0\u10e0\0\u111c"+
    "\0\u1158\0\u1194\0\u11d0\0\u120c\0\u1248\0\u1284\0\u12c0\0\u12fc"+
    "\0\u1338\0\u1374\0\u13b0\0\u13ec\0\u1428\0\u0b40\0\u1464\0\u14a0"+
    "\0\u14dc\0\u1518\0\u1554\0\u1590\0\u15cc\0\u1608\0\u1644\0\u0e4c"+
    "\0\u1680\0\u16bc\0\u16f8\0\u1734\0\u1770\0\u17ac\0\u17e8\0\u1824"+
    "\0\u1860\0\u189c\0\u18d8\0\u1914\0\u1950\0\u198c\0\u19c8\0\u1a04"+
    "\0\u1a40\0\u1a7c\0\u1ab8\0\u1af4\0\u1b30\0\u1b6c\0\u1ba8\0\u1be4"+
    "\0\u1c20\0\u1c5c\0\360\0\u1c98\0\u1cd4\0\u1d10\0\u1d4c\0\u1d88"+
    "\0\u1dc4\0\u1e00\0\u1e3c\0\u1e78\0\u1eb4\0\u1ef0\0\u1f2c\0\u1f68"+
    "\0\u1fa4\0\u1fe0";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[146];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\1\4\4\3\2\5\1\3\1\5\4\3\27\5"+
    "\1\3\5\5\2\3\6\5\1\3\2\5\1\6\1\3"+
    "\4\5\66\7\1\10\5\7\11\0\1\11\10\0\1\11"+
    "\2\0\2\12\2\0\2\13\2\0\2\14\2\15\14\0"+
    "\2\16\11\0\2\17\4\0\1\20\6\0\1\11\10\0"+
    "\1\11\2\0\2\12\2\0\2\13\2\0\2\14\2\15"+
    "\14\0\2\16\11\0\2\17\107\0\1\11\10\0\1\11"+
    "\2\0\2\12\2\0\2\13\2\0\2\14\2\15\14\0"+
    "\2\16\2\21\7\0\2\17\56\0\1\22\50\0\2\23"+
    "\2\0\2\24\66\0\2\25\76\0\2\26\6\0\3\27"+
    "\101\0\2\30\42\0\2\31\16\0\3\32\51\0\2\33"+
    "\72\0\2\34\130\0\3\35\65\0\2\36\63\0\2\37"+
    "\30\0\1\40\10\0\1\40\111\0\2\41\45\0\1\42"+
    "\10\0\1\42\2\0\1\42\106\0\2\43\60\0\2\44"+
    "\6\0\2\45\45\0\1\46\10\0\1\46\2\0\1\46"+
    "\14\0\2\47\64\0\2\50\102\0\2\51\30\0\2\52"+
    "\2\0\1\53\5\0\1\52\66\0\1\54\10\0\1\54"+
    "\137\0\3\55\14\0\1\56\10\0\1\56\17\0\2\57"+
    "\14\0\2\60\15\0\1\61\1\62\4\61\2\0\1\61"+
    "\1\0\4\61\7\0\2\63\16\0\1\61\5\0\1\64"+
    "\1\61\6\0\1\61\2\0\2\61\31\0\2\65\72\0"+
    "\2\66\6\0\2\66\7\0\4\66\23\0\2\67\2\0"+
    "\1\70\3\0\1\71\1\0\1\67\6\0\1\71\2\0"+
    "\2\12\10\0\2\72\7\0\2\73\1\0\1\67\1\0"+
    "\2\16\23\0\1\74\10\0\1\74\64\0\1\26\10\0"+
    "\1\26\2\0\1\26\114\0\3\75\2\67\32\0\1\76"+
    "\10\0\1\76\2\0\1\76\47\0\1\77\1\100\4\77"+
    "\2\0\1\77\1\0\4\77\27\0\1\101\2\102\3\0"+
    "\1\103\1\77\6\0\1\77\2\0\2\77\41\0\2\104"+
    "\36\0\2\52\2\0\1\53\3\0\1\105\1\0\1\52"+
    "\6\0\1\105\6\0\2\106\2\0\2\14\43\0\1\107"+
    "\10\0\1\107\71\0\1\110\64\0\1\111\10\0\1\111"+
    "\115\0\2\112\66\0\2\113\14\0\1\67\61\0\2\114"+
    "\33\0\1\61\150\0\2\115\13\0\1\61\1\62\4\61"+
    "\2\0\1\61\1\0\4\61\27\0\1\61\5\0\2\61"+
    "\6\0\1\61\2\0\2\61\5\0\2\65\2\0\1\116"+
    "\3\0\1\71\1\0\1\65\6\0\1\71\14\0\2\72"+
    "\34\0\4\117\1\120\4\0\4\117\50\0\1\121\6\0"+
    "\2\67\2\0\1\70\3\0\1\71\1\0\1\67\6\0"+
    "\1\71\2\0\2\12\10\0\2\72\14\0\2\16\23\0"+
    "\1\122\10\0\1\122\111\0\2\24\102\0\3\32\55\0"+
    "\2\123\66\0\2\124\134\0\1\67\31\0\2\67\6\0"+
    "\2\75\37\0\1\77\150\0\2\125\13\0\1\77\1\100"+
    "\4\77\2\0\1\77\1\0\4\77\27\0\1\77\5\0"+
    "\2\77\6\0\1\77\2\0\2\77\35\0\2\126\72\0"+
    "\2\127\76\0\2\26\44\0\1\130\10\0\1\130\57\0"+
    "\1\131\1\0\2\131\1\0\1\131\11\0\2\131\1\0"+
    "\1\131\1\0\1\131\1\0\1\131\1\0\1\131\1\0"+
    "\1\131\1\0\1\131\1\0\1\131\1\0\1\131\2\0"+
    "\1\131\1\0\1\131\4\0\1\131\1\0\1\131\1\0"+
    "\1\131\2\0\1\131\3\0\1\131\1\0\1\131\6\0"+
    "\1\132\10\0\1\132\64\0\1\133\10\0\1\133\2\0"+
    "\1\133\34\0\2\134\70\0\2\135\50\0\2\136\6\0"+
    "\3\136\32\0\1\137\10\0\1\137\55\0\4\117\1\120"+
    "\3\0\1\140\4\117\4\0\1\140\6\0\2\141\4\0"+
    "\2\142\25\0\1\121\13\0\1\143\10\0\1\143\133\0"+
    "\2\144\22\0\1\145\10\0\1\145\63\0\1\146\10\0"+
    "\1\146\2\0\1\146\4\0\2\147\104\0\2\42\64\0"+
    "\2\150\6\0\3\150\71\0\3\41\74\0\2\151\32\0"+
    "\1\152\10\0\1\152\2\0\1\152\53\0\1\131\1\0"+
    "\2\131\1\0\1\131\3\0\1\153\5\0\2\131\1\0"+
    "\1\131\1\0\1\131\1\0\1\131\1\0\1\131\1\0"+
    "\1\131\1\0\1\131\1\0\1\131\1\0\1\131\2\0"+
    "\1\131\1\0\1\131\4\0\1\131\1\0\1\131\1\0"+
    "\1\131\2\0\1\131\3\0\1\131\1\0\1\131\37\0"+
    "\2\154\43\0\1\146\10\0\1\146\2\0\1\146\114\0"+
    "\3\155\53\0\2\74\52\0\1\156\10\0\1\156\104\0"+
    "\2\157\106\0\3\27\51\0\2\31\54\0\1\160\10\0"+
    "\1\160\136\0\3\161\16\0\1\162\10\0\1\162\2\0"+
    "\1\162\114\0\5\67\57\0\2\163\43\0\1\56\10\0"+
    "\1\56\35\0\2\60\26\0\1\164\10\0\1\164\54\0"+
    "\2\153\1\0\2\153\1\0\1\153\3\0\1\165\5\0"+
    "\2\153\1\0\1\153\1\0\1\153\1\0\1\153\1\0"+
    "\1\153\1\0\1\153\1\0\1\153\1\0\1\153\1\0"+
    "\1\153\2\0\1\153\1\0\2\153\3\0\1\153\1\0"+
    "\1\153\1\0\1\153\2\0\1\153\3\0\1\153\1\0"+
    "\1\153\45\0\3\74\101\0\2\166\25\0\1\167\10\0"+
    "\1\167\2\0\1\167\117\0\2\170\32\0\1\171\10\0"+
    "\1\171\2\0\1\171\55\0\1\172\10\0\1\172\65\0"+
    "\1\173\10\0\1\173\57\0\1\174\10\0\1\174\66\0"+
    "\1\52\133\0\1\175\56\0\2\176\46\0\1\177\10\0"+
    "\1\177\110\0\2\57\44\0\1\200\10\0\1\200\66\0"+
    "\1\201\70\0\1\67\124\0\2\202\101\0\1\175\14\0"+
    "\1\203\12\0\1\204\10\0\1\204\66\0\1\65\73\0"+
    "\1\117\71\0\1\205\10\0\1\205\2\0\1\205\104\0"+
    "\2\67\100\0\2\146\72\0\2\206\40\0\1\207\10\0"+
    "\1\207\143\0\2\210\15\0\1\211\61\0\2\211\1\0"+
    "\2\211\1\0\1\211\3\0\1\212\5\0\2\211\1\0"+
    "\1\211\1\0\1\211\1\0\1\211\1\0\1\211\1\0"+
    "\1\211\1\0\1\211\1\0\1\211\1\0\1\211\2\0"+
    "\1\211\1\0\2\211\3\0\1\211\1\0\1\211\1\0"+
    "\1\211\2\0\1\211\3\0\1\211\1\0\1\211\52\0"+
    "\1\213\73\0\1\213\14\0\1\214\4\0\66\215\1\0"+
    "\73\215\1\216\5\215\54\0\1\217\76\0\2\220\76\0"+
    "\3\221\14\0\1\222\10\0\1\222\143\0\1\117\4\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[8220];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\2\1\1\11\1\1\1\11\1\1\50\0\1\11"+
    "\1\1\1\0\1\1\12\0\1\11\2\1\1\0\1\1"+
    "\5\0\1\11\71\0\1\11\17\0";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[146];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true iff the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true iff the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
	
	/* Custom java code */

    @Override
	public String language() { return "IT"; }

	@Override
	public String version() { return "0.5"; }


	/* An empty default constructor is required to comply with LinkolnService */
	
	public Subjects() { }
	
	@Override
	public final boolean run() {
		
		try {
			
			yyreset(new StringReader(getInput()));
			yylex();
			
		} catch (IOException e) {

			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	@Override
	protected void addValue() {
	
		//annotationEntity.setValue("value");
	} 



  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Subjects(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 382) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
        return YYEOF;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { addText(yytext());
	position++;
            } 
            // fall through
          case 7: break;
          case 2: 
            { addText(yytext());
		position++;
            } 
            // fall through
          case 8: break;
          case 3: 
            { annotate(new Subject(), "CRIMINAL", true, true);
            } 
            // fall through
          case 9: break;
          case 4: 
            { annotate(new Subject(), "CIVIL", true, true);
            } 
            // fall through
          case 10: break;
          case 5: 
            { addText(yytext());
		position--; //account for the following {LKN_C} character
		yybegin(YYINITIAL);
            } 
            // fall through
          case 11: break;
          case 6: 
            { addText(yytext()); 
	yybegin(lkn);
            } 
            // fall through
          case 12: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }

  /**
   * Runs the scanner on input files.
   *
   * This is a standalone scanner, it will print any unmatched
   * text to System.out unchanged.
   *
   * @param argv   the command line, contains the filenames to run
   *               the scanner on.
   */
  public static void main(String argv[]) {
    if (argv.length == 0) {
      System.out.println("Usage : java Subjects [ --encoding <name> ] <inputfile(s)>");
    }
    else {
      int firstFilePos = 0;
      String encodingName = "UTF-8";
      if (argv[0].equals("--encoding")) {
        firstFilePos = 2;
        encodingName = argv[1];
        try {
          java.nio.charset.Charset.forName(encodingName); // Side-effect: is encodingName valid? 
        } catch (Exception e) {
          System.out.println("Invalid encoding '" + encodingName + "'");
          return;
        }
      }
      for (int i = firstFilePos; i < argv.length; i++) {
        Subjects scanner = null;
        try {
          java.io.FileInputStream stream = new java.io.FileInputStream(argv[i]);
          java.io.Reader reader = new java.io.InputStreamReader(stream, encodingName);
          scanner = new Subjects(reader);
          while ( !scanner.zzAtEOF ) scanner.yylex();
        }
        catch (java.io.FileNotFoundException e) {
          System.out.println("File not found : \""+argv[i]+"\"");
        }
        catch (java.io.IOException e) {
          System.out.println("IO error scanning file \""+argv[i]+"\"");
          System.out.println(e);
        }
        catch (Exception e) {
          System.out.println("Unexpected exception:");
          e.printStackTrace();
        }
      }
    }
  }


}
