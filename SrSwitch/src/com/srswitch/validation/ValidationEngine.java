package com.srswitch.validation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.srswitch.exception.SystemException;

/**
 * @author nitin.malik
 * 
 * Acts as a validation engine for creating KnowledgeBase and Stateful Knowledge Sessions
 * through which rules are invoked.
 * 
 * Drools 5.5.0 framework is used for performing general data validations.
 *
 */
public class ValidationEngine {

	private static final Logger LOGGER = LoggerFactory.getLogger(ValidationEngine.class);
	private static boolean isDebugEnabled = LOGGER.isDebugEnabled(); 
	
	public static KnowledgeBase knowledgeBase;
	
	
	/**
	 * Create and return the knowledge Base from which Stateful Knowledge Session 
	 * will be created which stores and executes on the runtime data.
	 * Data are inserted into session and process instances are started.
	 * 
	 * @return
	 * @throws Exception
	 */
	public static KnowledgeBase createKnowledgeBase()throws Exception{
		
		if(knowledgeBase == null){
			if(isDebugEnabled) LOGGER.debug("Creating Fresh Knowledge Base");
			knowledgeBase = createKnowledgeBase(getDRLResourceList());
			return knowledgeBase;
		}
		
		if(isDebugEnabled) LOGGER.debug("Returning Existing Knowledge Base : " + knowledgeBase);
		return knowledgeBase;
	}
	
	
	/**
	 * Create and returns the KnowledgeBase which is a repository for all applications knowledge 
	 * definitions. It will contain all the rules, processes and functions.
	 * 
	 * The packages containing rules are added from KnowledgeBuilder into KnowledgeBase.
	 * 
	 * @return
	 * @throws Exception
	 */
	private static KnowledgeBase createKnowledgeBase(List<String> drlFileList)throws Exception{
		
		KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		
		for(String drlFile : drlFileList){
			kBuilder.add(ResourceFactory.newClassPathResource(drlFile), ResourceType.DRL);
		}
		
		if(kBuilder.hasErrors()){
			
			LOGGER.error("Following Errors Occured while preparing Knowledge Builder : ");
			Iterator<KnowledgeBuilderError> iterator = kBuilder.getErrors().iterator();
			while(iterator.hasNext()){
				KnowledgeBuilderError builderError = iterator.next();
				LOGGER.error("---------Error-------- " + 
						"\n\tMessage: " + builderError.getMessage() + 
						"\n\tResource: " + builderError.getResource() + 
						"\tSeverity: " + builderError.getSeverity());
				LOGGER.error("" + builderError);
			}
			throw new SystemException("Exception occured while creating knowledge base");
		}
		
		KnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();
		kBase.addKnowledgePackages(kBuilder.getKnowledgePackages());

		return kBase;
	}
		
	/**
	 * Method populates the resource file list
	 * 
	 * @return
	 * @throws Exception
	 */
	private static List<String> getDRLResourceList()throws Exception{
		
		if(isDebugEnabled)  LOGGER.debug("Getting DRL Resource File List from Folder Specified in Property file");
		List<String> resourceList = new ArrayList<String>();
		
		//TODO : Get list of DRL Resource files from Folder specified in property files
		resourceList.add("com/srswitch/validation/rules/SW_GeneralValidationRules.drl");
		resourceList.add("com/srswitch/validation/rules/SW_SalesValidationRules.drl");
		resourceList.add("com/srswitch/validation/rules/SW_DepositValidationRules.drl");
		
		/*resourceList.add("com/sidreya/srswitch/validation/rules/SW_GetProductListValidationRules.drl");*/

		return resourceList;
	}
	
}
