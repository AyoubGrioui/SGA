package com.sga.services;

import com.sga.entities.Structure;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


public class StructureForm {
	
	public static final String CHAMP_NOM="nomStructure"; 
	public static final String CHAMP_DATE_CREATION="dateCreationStructure";
	public static final String CHAMP_EMAIL="emailStructure";
	public static final String CHAMP_ADRESSE="adresseStructure";
	public static final String CHAMP_SITE_WEB="siteWeb";
	public static final String CHAMP_OBJECTIF="objectif";
	
	private String resultat;
	private Map<String,String> erreurs=new HashMap<String,String>();
	
	public String getResultat() {
		return resultat;
	}
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	public Structure creerStructure(HttpServletRequest request) {
		Structure structure = null;
	
		
		
		
		
		
		
		
		
		
		return structure;
	}
	
}
