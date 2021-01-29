package com.sga.services;

import com.sga.entities.DonCheque;
import com.sga.entities.DonEspece;
import com.sga.repositories.Repository;
import com.sga.repositories.RepositoryFactory;
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

    private Map<String,String> erreurs=new HashMap<String,String>();

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    LocalDate temp;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public DonEspece creerDonEspece(HttpServletRequest request)
    {
        String montant = getValeurChamp(request,CHAMP_MONTANT);
        String dateDon = getValeurChamp(request,CHAMP_DATE_DON);

        DonEspece donEspece = new DonEspece();

        String message=getValidationMessage(donEspece,CHAMP_DATE_DON);
        if(! (message == null)) {
            setErreurs(CHAMP_DATE_DON, message);
        }else {
            temp = LocalDate.parse(dateDon, formatter);
            donEspece.setDateDon(temp);
        }

        message=getValidationMessage(donEspece,CHAMP_MONTANT);
        double valeurMontant=-1;
        try {
            valeurMontant=validationMontant(montant);
        }catch(Exception e) {
            setErreurs(CHAMP_MONTANT, message);
        }
        donEspece.setMontant(valeurMontant);

        RepositoryFactory repFactory = new RepositoryFactory();
        Repository rep = repFactory.getDonVersementRepository();
        rep.create(donEspece);

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
    // get validation message

    private String getValidationMessage(DonEspece obj, String champ) {
        Validator validator = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(
                        (MessageInterpolator) new ResourceBundleMessageInterpolator(
                                new PlatformResourceBundleLocator("MyMessages")
                        )
                )
                .buildValidatorFactory()
                .getValidator();


        String message = validator.validateProperty(obj, champ).iterator().next().getMessage();


        return message;
    }
}
