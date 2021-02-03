package com.sga.services;

import com.sga.entities.Depense;
import com.sga.helpers.SGAUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class DepenseForm {


    public static final String CHAMP_MONTANT="montantDepense";
    public static final String CHAMP_DATE_DEPENSE="dateDepense";
    public static final String CHAMP_TYPE_DEPENSE="typeDepense";


    private Map<String,String> erreurs=new HashMap<String,String>();

    public Map<String, String> getErreurs()
    {
        return erreurs;
    }

    public Depense creerDepense(HttpServletRequest request) {

        String montant = getValeurChamp(request,CHAMP_MONTANT);
        String dateDepense = getValeurChamp(request,CHAMP_DATE_DEPENSE);
        String typedepense = getValeurChamp(request,CHAMP_TYPE_DEPENSE);

        Depense depense = new Depense();

        
        double valeurMontant= 0;
        try
        {
            valeurMontant=validationMontant(montant);
        }
        catch(Exception e) {
            setErreurs(CHAMP_MONTANT, e.getMessage());
        }
        depense.setMontant(valeurMontant);

        try
        {
           validationTypeDepense(typedepense);
        }catch(Exception e) {
            setErreurs(CHAMP_TYPE_DEPENSE, e.getMessage());
        }

        depense.setTypeDepense(typedepense);
        
        try {
            validationDate( dateDepense );
        } catch ( Exception e ) {
            setErreurs( CHAMP_DATE_DEPENSE, e.getMessage() );
        }
        depense.setDateDepense(SGAUtil.StringToLocalDate(dateDepense));

/*        if(erreurs.isEmpty())
        {
            RepositoryFactory repFactory = new RepositoryFactory();
            Repository rep = repFactory.getDepenseRepository();
            rep.create(depense);
        }*/


        return depense;
    }

    //validation du montant
    private double validationMontant( String montant ) throws Exception {
        double temp=0;
        if ( montant != null )
        {
            try {
                temp = Double.parseDouble( montant );
                if ( temp <= 0 ) {
                    throw new Exception( "Le montant doit etre un nombre positif." );
                }
            } catch ( NumberFormatException e ) {
                throw new Exception( "Le montant doit etre un nombre." );
            }
        } else {
            throw new Exception( "Merci d'entrer un montant." );
        }
        return temp;
    }

    /* ajoute un message correspondant au champ specifie a la map des erreurs */

    private void setErreurs(String champ, String message)
    {
        erreurs.put(champ, message);
    }

    /* methode utilitaire qui retourne null si un champ est vide, et son contenu sinon */

    private static String getValeurChamp(HttpServletRequest request,String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if(valeur == null || valeur.trim().length()==0) {
            return null;
        }
        else {
            return valeur;
        }
    }
    
	//validation date
	private void validationDate( String date ) throws Exception {

        if ( date == null )
        {
            throw new Exception( "Merci d'entrer une date." );
        }

    }
	
	//validation date
	private void validationTypeDepense( String typeDepense ) throws Exception {

        if ( typeDepense == null ) {
            throw new Exception( "Merci d'entrer un type de depense." );    
        }
    }
}