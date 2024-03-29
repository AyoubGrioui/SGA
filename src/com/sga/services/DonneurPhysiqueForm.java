package com.sga.services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sga.entities.Donneur;
import com.sga.entities.DonneurPhysique;
import com.sga.entities.Structure;
import com.sga.repositories.HibernateDonneurPersister;
import com.sga.repositories.HibernateDonneurPhysiquePersister;

public class DonneurPhysiqueForm {

    private static final String CHAMP_NOM            = "nomDonneurPhysique";
    private static final String CHAMP_PRENOM         = "prenomDonneurPhysique";
    private static final String CHAMP_CIN            = "cinDonneurPhysique";
    private static final String CHAMP_EMAIL          = "emailDonneurMorale";
    private static final String CHAMP_TELEPHONE      = "telephoneDonneurMorale";
    private static final String CHAMP_ADRESSE        = "adresseDonneurMorale";
    private static final String CHAMP_MOT_DE_PASSE   = "motDePasseDonneurMorale";
    public static final String  ATT_STRUCTURE        = "structure";
    public static final String  INTERNAL_ID_DONATEUR = "idDonneur";

    private Map<String, String> erreurs              = new HashMap<String, String>();
    private String              resultat;
    private String              errorMessage;

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public DonneurPhysique creerDonneurPhysique( HttpServletRequest request ) {

        String nom = getValeurChamp( request, CHAMP_NOM );
        String prenom = getValeurChamp( request, CHAMP_PRENOM );
        String cin = getValeurChamp( request, CHAMP_CIN );
        String email = getValeurChamp( request, CHAMP_EMAIL );
        String telephone = getValeurChamp( request, CHAMP_TELEPHONE );
        String adresse = getValeurChamp( request, CHAMP_ADRESSE );

        DonneurPhysique donneurPhysique = new DonneurPhysique();

        try {
            validationNom( nom );
        } catch ( Exception e ) {
            setErreurs( CHAMP_NOM, e.getMessage() );
        }
        donneurPhysique.setNom( nom );

        try {
            validationPrenom( prenom );
        } catch ( Exception e ) {
            setErreurs( CHAMP_PRENOM, e.getMessage() );
        }
        donneurPhysique.setPrenom( prenom );

        try {
            validationCin( cin );
        } catch ( Exception e ) {
            setErreurs( CHAMP_CIN, e.getMessage() );
        }
        donneurPhysique.setCin( cin );

        donneurPhysique.setMotDePasse( cin + "@" + LocalDate.now().getYear() );

        try {
            validationEmail( email );
        } catch ( Exception e ) {
            setErreurs( CHAMP_EMAIL, e.getMessage() );
        }
        donneurPhysique.setEmail( email );

        try {
            validationTelephone( telephone );
        } catch ( Exception e ) {
            setErreurs( CHAMP_TELEPHONE, e.getMessage() );
        }
        donneurPhysique.setTelephone( telephone );

        try {
            validationAdresse( adresse );
        } catch ( Exception e ) {
            setErreurs( CHAMP_ADRESSE, e.getMessage() );
        }
        donneurPhysique.setAdresse( adresse );

        HttpSession session = request.getSession();
        Structure structure = (Structure) session.getAttribute( ATT_STRUCTURE );

        if ( structure == null ) {
            errorMessage = "Merci de Creer une structure pour l'Association";
            setErreurs( ATT_STRUCTURE, errorMessage );
        }

        donneurPhysique.setStructure( structure );

        if ( getErreurs().isEmpty() ) {
            HibernateDonneurPhysiquePersister donneurPhysiquePersister = new HibernateDonneurPhysiquePersister();
            long id = donneurPhysiquePersister.create( donneurPhysique );
            donneurPhysique.setIdDonneur( id );
        }
        return donneurPhysique;
    }

    public DonneurPhysique modifierDonneurPhysique( HttpServletRequest request ) {

        String nom = getValeurChamp( request, CHAMP_NOM );
        String prenom = getValeurChamp( request, CHAMP_PRENOM );
        String cin = getValeurChamp( request, CHAMP_CIN );
        String email = getValeurChamp( request, CHAMP_EMAIL );
        String telephone = getValeurChamp( request, CHAMP_TELEPHONE );
        String adresse = getValeurChamp( request, CHAMP_ADRESSE );
        String motDePasse = getValeurChamp( request, CHAMP_MOT_DE_PASSE );

        Long id = Long.parseLong( getValeurChamp( request, INTERNAL_ID_DONATEUR ) );
        HibernateDonneurPhysiquePersister donneurPhysiquePers = new HibernateDonneurPhysiquePersister();
        DonneurPhysique donneurPhysique = donneurPhysiquePers.read( id );

        try {
            validationNom( nom );
        } catch ( Exception e ) {
            setErreurs( CHAMP_NOM, e.getMessage() );
        }
        donneurPhysique.setNom( nom );

        try {
            validationPrenom( prenom );
        } catch ( Exception e ) {
            setErreurs( CHAMP_PRENOM, e.getMessage() );
        }
        donneurPhysique.setPrenom( prenom );

        try {
            validationCin( cin );
        } catch ( Exception e ) {
            setErreurs( CHAMP_CIN, e.getMessage() );
        }
        donneurPhysique.setCin( cin );

        try {
            if ( email == null || !email.equals( donneurPhysique.getEmail() ) )
                validationEmail( email );
        } catch ( Exception e ) {
            setErreurs( CHAMP_EMAIL, e.getMessage() );
        }
        donneurPhysique.setEmail( email );

        try {
            validationTelephone( telephone );
        } catch ( Exception e ) {
            setErreurs( CHAMP_TELEPHONE, e.getMessage() );
        }
        donneurPhysique.setTelephone( telephone );

        try {
            validationAdresse( adresse );
        } catch ( Exception e ) {
            setErreurs( CHAMP_ADRESSE, e.getMessage() );
        }
        donneurPhysique.setAdresse( adresse );

        try {
            validationMotDePasse( motDePasse );
        } catch ( Exception e ) {
            setErreurs( CHAMP_MOT_DE_PASSE, e.getMessage() );
        }
        donneurPhysique.setMotDePasse( motDePasse );

        if ( getErreurs().isEmpty() ) {
            HibernateDonneurPhysiquePersister donneurPhysiquePersister = new HibernateDonneurPhysiquePersister();
            donneurPhysiquePersister.update( donneurPhysique );
        }

        return donneurPhysique;
    }

    // Fonction de validation du nom
    private void validationNom( String nom ) throws Exception {
        if ( nom != null ) {
            if ( nom.length() < 2 ) {
                throw new Exception( "Le nom  doit contenir au moins 2 caract�res." );
            }
        } else {
            throw new Exception( "Merci d'entrer le nom." );
        }
    }

    // Fonction de validation du prenom
    private void validationPrenom( String prenom ) throws Exception {
        if ( prenom != null ) {
            if ( prenom.length() < 2 ) {
                throw new Exception( "Le prenom doit contenir au moins 2 caract�res." );
            }
        } else {
            throw new Exception( "Merci d'entrer le prenom." );
        }
    }

    // Fonction de validation du cin
    private void validationCin( String cin ) throws Exception {
        if ( cin != null ) {
            if ( cin.length() < 3 && cin.length() > 9 ) {
                throw new Exception( "ce CIN n'est pas valide." );
            }
        } else {
            throw new Exception( "Merci d'entrer le cin." );
        }
    }

    /* Foction de validation d'un adresse email */

    private void validationEmail( String email ) throws Exception {
        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new Exception( "Merci de saisir une adresse mail valide." );
        } else if ( email == null ) {
            throw new Exception( "Merci de saisir une adresse mail." );
        } else {
            if ( !isEmailUnique( email ) )
                throw new Exception( "l'email que vous avez entrer déja lier a un Donnateur ." );

        }
    }

    private boolean isEmailUnique( String mail ) {
        HibernateDonneurPersister donneurPersister = new HibernateDonneurPersister();
        Donneur donneur = donneurPersister.getByEmail( mail );

        if ( donneur == null )
            return true;

        return false;
    }

    /* Fonction de validation de numero de telephone */

    private void validationTelephone( String telephone ) throws Exception {
        if ( telephone != null ) {
            if ( !telephone.matches( "^\\d{10}$" ) ) {
                throw new Exception( "Le numero de telephone doit uniquement contenir 10 chiffres." );
            }
        } else {
            throw new Exception( "Merci d'entrer un numero de telephone." );
        }
    }

    /* Fonction de validation de l'adresse */

    private void validationAdresse( String adresse ) throws Exception {
        if ( adresse != null ) {
            if ( adresse.length() < 6 ) {
                throw new Exception( "l'adresse du client doit contenir au moins 6 caracteres" );
            }
        } else {
            throw new Exception( "Merci d'entrer un adresse du client" );
        }
    }

    // Foction de validation du mot de passe
    private void validationMotDePasse( String motDePasse ) throws Exception {
        if ( motDePasse == null ) {
            throw new Exception( "Merci de saisir un mot de passe." );
        }
    }

    /* ajoute un message correspondant au champ specifie a la map des erreurs */

    private void setErreurs( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * methode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon
     */

    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }

}
