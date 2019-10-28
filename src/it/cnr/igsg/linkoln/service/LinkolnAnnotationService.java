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

import it.cnr.igsg.linkoln.Linkoln;
import it.cnr.igsg.linkoln.entity.AnnotationEntity;
import it.cnr.igsg.linkoln.service.impl.Util;

public abstract class LinkolnAnnotationService extends LinkolnService {

	/*
	 * Annotation Services realized with JFlex.
	 */
	
	private String input = "";
	
	private String output = "";
	
	private StringBuilder after = null;
	
	public String getInput() {
		
		return input;
	}

	public String getOutput() {
		
		return output;
	}
	
	@Override
	protected final void beforeRun() {
		
		//Add blanks at the beginning and the the end of the input for edge parsing 
		
		input =  " " + getLinkolnDocument().getAnnotatedText() + " ";
		
		after = new StringBuilder();
	}

	@Override
	protected final boolean afterRun() {
		
		output = after.toString();
		
		boolean error = false;
		
		/*
		Check the text before and after: it must match (besides annotations)
		*/
		
		String cleanBefore = Util.removeAllAnnotations(input);
		String cleanAfter = Util.removeAllAnnotations(output);

		if( !cleanBefore.equals(cleanAfter)) {
			
			System.err.println(this.getDescription() + " - Annotation service error: before and after text don't match!");
			System.err.println("BEFORE:" + input);
			System.err.println("AFTER: " + output);
			System.err.println("CLEANBEFORE:" + cleanBefore);
			System.err.println("CLEANAFTER: " + cleanAfter);
			System.err.println("EQUALPART: " + Util.getEqualPart(cleanBefore, cleanAfter));
			
			if(Linkoln.FORCE_EXIT_AFTER_SERVICE_FAILURE) {
			
				error = true;
			}
		}
		

		
		//TODO check after the run that there are no nested annotations
		
		//TODO check after the run that there are no unbalanced annotations
		
		//Remove the artificial blanks
		
		if(output.length() > 2) {
		
			if(output.substring(0, 1).equals(" ")) {
				
				output = output.substring(1);
			}

			if(output.substring(output.length()-1, output.length()).equals(" ")) {
				
				output = output.substring(0, output.length()-1);
			}
		}
		
		return !error;
	}
	

	/* ...text text [LKN:ANNOTATION_NAME:NORMALIZED_VALUE:ID]text included in the annotation[/LKN] text text... */
	final static protected String NS = "LKN";
	final static protected String O = "[";
	final static protected String C = "]";
	final static protected String SEP = ":";
	final static protected String OPEN = O + NS + SEP;
	final static protected String CLOSE = O + "/" + NS + C;
	

	protected final void addText(String text) {
		
		after.append(text);
	}

	protected final void addEntity(AnnotationEntity entity) {
		
		addEntity(entity, true);
	}
	
	protected final void addEntity(AnnotationEntity entity, boolean serialize) {
		
		getLinkolnDocument().addAnnotationEntity(entity);
		
		if(serialize) {
		
			after.append(serializeEntity(entity));
		}
	}

	private String serializeEntity(AnnotationEntity entity) {
		
		//TODO syntax checks
		//normalizedValue must not contain SEP or O or C symbols 

		StringBuilder annotation = new StringBuilder();

		annotation.append(OPEN + entity.getEntityName() + SEP + entity.getValue() + SEP + entity.getId() + C);
		
		annotation.append(entity.getText());
		
		annotation.append(CLOSE);
		
		return annotation.toString();
	}

	public abstract String yytext();
	public abstract int yylength();
	public abstract void yybegin(int newState);
	public abstract void yypushback(int number);
	public abstract int yystate();
	
	protected int position = 0;
	
	public int getPosition() {
	
		return position;
	}

	protected AnnotationEntity annotationEntity = null;
	
	protected int offset = 0;
	protected int length = 0;
	protected String text = "";
	
	protected final void start(AnnotationEntity newEntity, int state, boolean leftEdge, boolean rightEdge) {

		text = yytext();
		
		if(leftEdge) {
			
			addText(text.substring(0,1));
			position++;
			text = text.substring(1);
		}
	
		yypushback(text.length());
	
		if(rightEdge) {
		
			text = text.substring(0, text.length()-1);
		}
		
		annotationEntity = newEntity;

		if(annotationEntity != null) {
			
			annotationEntity.setPosition(position);
			annotationEntity.setText(Util.removeAllAnnotations(text));
		}
				
		offset = 0;
		length = text.length();		
		yybegin(state);	
	}
	
	protected final void annotate(AnnotationEntity newEntity, String value, boolean leftEdge, boolean rightEdge) {
		
		text = yytext();
		
		if(leftEdge) {
			
			addText(text.substring(0,1));
			position++;
			text = text.substring(1);
		}
	
		annotationEntity = newEntity;
		annotationEntity.setPosition(position);
		annotationEntity.setValue(value.toUpperCase());
	
		if(rightEdge) {
		
			text = text.substring(0, text.length()-1);
			yypushback(1);
		}

		position += text.length();

		annotationEntity.setText(Util.removeAllAnnotations(text));
		addEntity(annotationEntity);
	}

	protected final AnnotationEntity retrieveEntity(String text) {
		
		//TODO Replace with a proper RegEx

		text = text.substring(text.indexOf(":") + 1);
		text = text.substring(text.indexOf(":") + 1);
		text = text.substring(text.indexOf(":") + 1);
		
		String id = text.substring(0, text.indexOf("]"));

		return getLinkolnDocument().getAnnotationEntity(id);
	}
	
	/*
	 * Replace old entity with new annotation entity, serialize new annotation entity
	 */
	protected final void replaceEntity(AnnotationEntity oldEntity, AnnotationEntity newEntity) {
		
		newEntity.setPosition(oldEntity.getPosition());
		newEntity.setText(oldEntity.getText());
		newEntity.setValue(oldEntity.getValue());
		newEntity.setId(oldEntity.getId());
		
		//TODO Che si fa con le related entities? Teoricamente non ci dovrebbero mai essere ma andrebbero gestite comunque.
		
		getLinkolnDocument().replaceAnnotationEntity(oldEntity, newEntity);

		after.append(serializeEntity(newEntity));
	}
	
	protected void save(AnnotationEntity entity, String value) {
		
		save(entity, value, true);
	}
	
	protected void save(AnnotationEntity entity, String value, boolean serialize) {
		
		entity.setPosition(position);
		entity.setValue(value.toUpperCase());
		entity.setText(Util.removeAllAnnotations(yytext()));

		if(annotationEntity != null) {
			
			entity.addRelatedEntity(annotationEntity);
			annotationEntity.addRelatedEntity(entity);
		}
		
		addEntity(entity, serialize);
		
		offset += yylength();
		position += yylength();	
	}
	
	protected final void checkEnd() {
		
		offset++;
		position++;
		
		if(offset >= length) {
			
			if(offset > length) {
				yypushback(1);
				position--;
			}
			
			if(annotationEntity != null) {
			
				addValue();
				addEntity(annotationEntity);
			}

			yybegin(0);
		}
		
		if(annotationEntity == null) {
			
			addText(yytext());
		}
	}
	
	protected void addValue() {
		
	}
}
