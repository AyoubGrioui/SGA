package com.sga.services;

import com.sga.entities.DonCheque;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class DonChequeForm {
	public static final String CHAMP_NUMERO_COMPTE_BANQUE="numeroCompteBanqueDonCheque";
	public static final String CHAMP_DATE_CHEQUE="dateChequeDonCheque";
	public static final String CHAMP_DATE_DEPOT="dateDepotDonCheque";
	public static final String CHAMP_NOM_BANQUE="nomBanqueDonCheque";
	
	private String resultat;
	private Map<String,String> erreurs=new HashMap<String,String>();
	
	public String getResultat() {
		return resultat;
	}
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	public DonCheque creerDonCheque(HttpServletRequest request) {
		DonCheque donCheque = null;
		
		return donCheque;
	}

// class GFG {
	     
	// Returns true if given 
	// card number is valid
	static boolean checkLuhn(String cardNo)
	{
	    int nDigits = cardNo.length();
	 
	    int nSum = 0;
	    boolean isSecond = false;
	    for (int i = nDigits - 1; i >= 0; i--) 
	    {
	 
	        int d = cardNo.charAt(i) - '0';
	 
	        if (isSecond == true)
	            d = d * 2;
	 
	        // We add two digits to handle
	        // cases that make two digits 
	        // after doubling
	        nSum += d / 10;
	        nSum += d % 10;
	 
	        isSecond = !isSecond;
	    }
	    
	    // ++
	    return (nSum % 10 == 0);
	}
	 
	    
	 
	
}
