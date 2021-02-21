	
package com.sga.services;

import com.sga.entities.DonCheque;
import com.sga.entities.DonEspece;
import com.sga.entities.Donneur;
import com.sga.helpers.SGAUtil;
import com.sga.repositories.*;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.MessageInterpolator;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class DonEspeceForm
{
    private static final String CHAMP_DATE_DON="dateDon";
    private static final String CHAMP_MONTANT="montant";
    private static final String CHAMP_DONNEUR="donneur";
    public static final String INTERNAL_ID_DON = "idDon";


    private Map<String,String> erreurs=new HashMap<String,String>();

    public Map<String, String> getErreurs() {
        return erreurs;
    }


    public DonEspece creerDonEspece(HttpServletRequest request)
    {
        String montant = getValeurChamp(request,CHAMP_MONTANT);
        String dateDon = getValeurChamp(request,CHAMP_DATE_DON);
        String idDonneur = getValeurChamp(request,CHAMP_DONNEUR);


        DonEspece donEspece = new DonEspece();

        double valeurMontant=-1;
        try {
            valeurMontant=validationMontant(montant);
        }catch(Exception e) {
            setErreurs(CHAMP_MONTANT, e.getMessage());
        }
        donEspece.setMontant(valeurMontant);

        try {
            validationDate( dateDon );
        } catch ( Exception e ) {
            setErreurs( CHAMP_DATE_DON, e.getMessage() );
        }
        donEspece.setDateDon(SGAUtil.StringToLocalDate(dateDon));

        Donneur donneur = (Donneur) request.getAttribute(CHAMP_DONNEUR);
        donEspece.setDonneur(donneur);

        if(getErreurs().isEmpty())
        {
            HibernateDonEspecePersister donEspecePersister=new HibernateDonEspecePersister();
            donEspecePersister.create(donEspece);
        }

        return donEspece;

    }
    
    public DonEspece modifierDonEspece(HttpServletRequest request)
    {
        String montant = getValeurChamp(request,CHAMP_MONTANT);
        String dateDon = getValeurChamp(request,CHAMP_DATE_DON);
        String idDonneur = getValeurChamp(request,CHAMP_DONNEUR);

        Long id=Long.parseLong(getValeurChamp(request,INTERNAL_ID_DON));
        HibernateDonEspecePersister donEspecePersister = new HibernateDonEspecePersister();
        DonEspece donEspece =donEspecePersister.read(id);

        double valeurMontant=-1;
        try {
            valeurMontant=validationMontant(montant);
        }catch(Exception e) {
            setErreurs(CHAMP_MONTANT, e.getMessage());
        }
        donEspece.setMontant(valeurMontant);

        try {
            validationDate( dateDon );
        } catch ( Exception e ) {
            setErreurs( CHAMP_DATE_DON, e.getMessage() );
        }
        donEspece.setDateDon(SGAUtil.StringToLocalDate(dateDon));


        if(getErreurs().isEmpty())
        {
            donEspecePersister.update(donEspece);
        }

        return donEspece;

    }

    //validation du montant
    private double validationMontant( String montant ) throws Exception {
        double temp;
        if ( montant != null ) {
            try {
                temp = Double.parseDouble( montant );
                if ( temp < 0 ) {
                    throw new Exception( "Le montant doit etre un nombre positif." );
                }
            } catch ( NumberFormatException e ) {
                temp = -1;
                throw new Exception( "Le montant doit etre un nombre." );
            }
        } else {
            temp = -1;
            throw new Exception( "Merci d'entrer un montant." );
        }
        return temp;
    }
    /* ajoute un message correspondant au champ specifie a la map des erreurs */

    private void setErreurs(String champ, String message) {
        erreurs.put(champ, message);
    }

    /* methode utilitaire qui retourne null si un champ est vide, et son contenu sinon */

    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
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

}
