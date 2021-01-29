package com.sga.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.MessageInterpolator;
import javax.validation.Validation;
import javax.validation.Validator;

import com.sga.repositories.Repository;
import com.sga.repositories.RepositoryFactory;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

import com.sga.entities.Depense;

public class DepenseForm {


    public static final String CHAMP_MONTANT="montantDepense";
    public static final String CHAMP_DATE_DEPENSE="dateDepense";
    public static final String CHAMP_TYPE_DEPENSE="typeDepense";

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate temp;

    private Map<String,String> erreurs=new HashMap<String,String>();

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Depense creerDepense(HttpServletRequest request) {

        String montant = getValeurChamp(request,CHAMP_MONTANT);
        String dateDepense = getValeurChamp(request,CHAMP_DATE_DEPENSE);
        String typedepense = getValeurChamp(request,CHAMP_TYPE_DEPENSE);

        Depense depense = new Depense();

        String message=getValidationMessage(depense,CHAMP_MONTANT);
        double valeurMontant=-1;
        try {
            valeurMontant=validationMontant(montant);
        }catch(Exception e) {
            setErreurs(CHAMP_MONTANT, message);
        }

        depense.setMontant(valeurMontant);

        message=getValidationMessage(depense,CHAMP_DATE_DEPENSE);
        if(! (message == null)) {
            setErreurs(CHAMP_DATE_DEPENSE, message);
        }else {
            temp = LocalDate.parse(dateDepense, formatter);
            depense.setDateDepense(temp);
        }

        message=getValidationMessage(depense,CHAMP_TYPE_DEPENSE);
        if(! (message == null)) {
            setErreurs(CHAMP_TYPE_DEPENSE, message);
        }else {
            depense.setTypeDepense(typedepense);
        }

        RepositoryFactory repFactory = new RepositoryFactory();
        Repository rep = repFactory.getDepenseRepository();
        rep.create(depense);

        return depense;
    }

    // get validation message

    private String getValidationMessage(Depense obj,String champ) {
        Validator validator = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(
                        (MessageInterpolator) new ResourceBundleMessageInterpolator(
                                new PlatformResourceBundleLocator( "MyMessages" )
                        )
                )
                .buildValidatorFactory()
                .getValidator();


        String	message = validator.validateProperty( obj,champ).iterator().next().getMessage();


        return message;

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

    private static String getValeurChamp(HttpServletRequest request,String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if(valeur == null || valeur.trim().length()==0) {
            return null;
        }
        else {
            return valeur;
        }
    }
}