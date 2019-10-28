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
package it.cnr.igsg.linkoln.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	
	private static final Pattern digits = Pattern.compile("\\d+");

	
	public static Map<String,String> token2code = null;
	private static final File MUNICIPALITIES = new File("codiciTokenized.txt");
	
	public static boolean initMunicipalities() throws IOException {
		
		if(Util.token2code != null) {
			
			return false;
		}
		
		if( !MUNICIPALITIES.exists()) {
			
			return false;
		}
	
		Util.token2code = new HashMap<String,String>();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(MUNICIPALITIES)));
		String l = null;

	    while( ( l = reader.readLine() ) != null ) {

			int split = l.indexOf(" ");
			String code = l.substring(0, split).trim();
			String namedEntity = l.substring(split).trim().toLowerCase();
		
			Util.token2code.put(namedEntity, code);
	    }
	    reader.close();   

		return true;
	}
	
	public static final String readFirstNumber(String text) {
	
		Matcher matcher = digits.matcher(text);
		String number = "";
		
		while(matcher.find()) {
			
			number = text.substring(matcher.start(), matcher.end());			
			break;
		}
		
		return number;
	}

	public static final String readLastNumber(String text) {
		
		Matcher matcher = digits.matcher(text);
		String number = "";
		
		while( matcher.find() ) {
			number = text.substring(matcher.start(), matcher.end());
		}
		
		return number;
	}
	
	public static final String removeAllAnnotations(String text) {
		
		Cleaner ac = new Cleaner();
		
		try {
			
			ac.yyreset(new StringReader(text));
			
			ac.yylex();
			
		} catch (IOException e) {

			e.printStackTrace();
			
			return "";
		}
		
		return ac.getOutput();
	}

	public static final String tokenize(String text) {
		
		Tokenizer t = new Tokenizer();
		
		try {
			
			t.yyreset(new StringReader(text));
			
			t.yylex();
			
		} catch (IOException e) {

			e.printStackTrace();
			
			return "";
		}

		return t.getOutput().toLowerCase().replaceAll("\\s+", " ").trim();
	}
	
	public static final boolean isFutureReference(int year, String metaYear) {
		
		if(metaYear.length() < 1) {
			return false;
		}
		
		int value = 0;
		
		try {
			
			value = Integer.valueOf(metaYear);
			
		} catch (NumberFormatException e) {

			return false;
		}
	
		if(metaYear.length() == 2) {
			
			if(value < 21) {
				
				metaYear = "20" + metaYear;
				
			} else {
				
				metaYear = "19" + metaYear;
			}
		}
		
		value = Integer.valueOf(metaYear);
		
		if(year > value) {
			
			return true;
		}
		
		return false;
	}
	
	public static String getEqualPart(String a, String b) {
		
		for(int i = 0; i < a.length(); i++) {
			
			Character ai = a.charAt(i);
			
			if(i >= b.length()) {
				
				return a.substring(0, i);
			}
			
			Character bi = b.charAt(i);

			if( !ai.equals(bi)) {
				
				return a.substring(0, i);
			}
		}
		
		return a;
	}
	
	public static String normalizeYear(String year) {
		
		if(year.length() == 2) {

			int value = Integer.valueOf(year);

			if(value < 21) {
				
				year = "20" + year;
				
			} else {
				
				year = "19" + year;
			}
		
		}
		
		return year;
	}
	
}
