package com.sga.controllers;

import com.sga.entities.*;
import com.sga.repositories.*;
import com.sga.services.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet( "/ajouterDon" )
public class AjouterDonServlet extends HttpServlet {

    /**
     * 
     */
    private static final long  serialVersionUID = 1L;
    public static final String VUE_AJOUTER_DON  = "/WEB-INF/ajouterDonPage.jsp";
    public static final String ATT_SESSION_USER  = "user";
    public static final String ATT_DONNEUR_MORALE_LIST = "donneurMoralelist";
    public static final String ATT_DONNEUR_PHYSIQUE_LIST = "donneurPhysiquelist";
    public static final String ATT_DONNEUR = "donneur";
    public static final String ATT_DON="don";
    public static final String PARAM_TYPEDECOMPTE = "accountType";
    public static final String PARAM_TYPEDONATEUR = "typeDonateur";
    public static final String PARAM_TYPEDON = "typeDon";
    public static final String PARAM_ANCIENDONNEUR_MORALE = "listAncienDonneurMorale";
    public static final String PARAM_ANCIENDONNEUR_PHYSIQUE = "listAncienDonneurPhysique";
    private static final String ATT_LIST_STRUCTURE = "structureList";
    private static final String SUCCESS_MSG = "successMsg";
    private static final String ERREUR_MSG = "erreurMsg";
    public static final String ATT_DONNEUR_PHYSIQUE_FORM = "donneurPhysiqueForm";
    public static final String ATT_DONNEUR_MORALE_FORM = "donneurMoraleForm";
    public static final String ATT_DON_ESPECE_FORM= "donEspeceForm";
    public static final String ATT_DON_VERSEMENT_FORM= "donVersementForm";
    public static final String ATT_DON_CHEQUE_FORM= "donChequeForm";
    public static final String PARAM_CHOIX_LISTE= "choixDonneur";
    

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
    	
        HibernateDonneurMoralPersister DonneurMoralPersister = new HibernateDonneurMoralPersister();
        List<DonneurMoral> donneurMoraleList =DonneurMoralPersister.getAll();

    	
        HibernateStructurePersister structurePersister =new HibernateStructurePersister();
        List<Structure> structureList = structurePersister.getAll();
        
        HibernateDonneurPhysiquePersister DonneurPhysiquePersister = new HibernateDonneurPhysiquePersister();
        List<DonneurPhysique> donneurPhysiqueList =DonneurPhysiquePersister.getAll();
        
        request.setAttribute(ATT_LIST_STRUCTURE,structureList);
        request.setAttribute(ATT_DONNEUR_MORALE_LIST,donneurMoraleList);
        request.setAttribute(ATT_DONNEUR_PHYSIQUE_LIST,donneurPhysiqueList);

        this.getServletContext().getRequestDispatcher( VUE_AJOUTER_DON ).forward( request, response );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
     String accountType=(String) req.getParameter(PARAM_TYPEDECOMPTE);
     String typeDonateur=(String) req.getParameter(PARAM_TYPEDONATEUR);
     String typeDon=(String) req.getParameter(PARAM_TYPEDON);
     String choixDonneur = (String) req.getParameter(PARAM_CHOIX_LISTE);
     
     DonneurPhysiqueForm donneurPhysiqueForm = new DonneurPhysiqueForm();
     DonneurMoraleForm donneurMoraleForm = new DonneurMoraleForm();
     DonEspeceForm donEspeceForm = new DonEspeceForm();
     DonChequeForm donChequeForm = new DonChequeForm();
     DonVersementForm donVersementForm = new DonVersementForm();

     String successMsg = null;
     String erreurMsg = null;
     boolean flag = false;
     
     
     if (accountType.equals("nouveau"))
     {
         if (typeDonateur.equals("physique"))
         {
             DonneurPhysique donneur = donneurPhysiqueForm.creerDonneurPhysique(req);
             if(!donneurPhysiqueForm.getErreurs().isEmpty()) flag = true;
             req.setAttribute(ATT_DONNEUR,donneur);
         }
         else
         {
             DonneurMoral donneur = donneurMoraleForm.creerDonneurMorale(req);
             if(!donneurMoraleForm.getErreurs().isEmpty()) flag = true;
             req.setAttribute(ATT_DONNEUR,donneur);
         }

     }
     else
     {
    	 String id = "";

    	 if(choixDonneur.equals("physique")) {
    		 id =(String) req.getParameter(PARAM_ANCIENDONNEUR_PHYSIQUE);
    	 }else {
    		 id =(String) req.getParameter(PARAM_ANCIENDONNEUR_MORALE);
    	 }
         
         HibernateDonneurPersister donneurPersister=new HibernateDonneurPersister();
         Donneur donneur = new Donneur();
    	 try {
    		 donneur = donneurPersister.read(Long.parseLong(id));
    	 }catch (Exception e) {
    		 erreurMsg = "Veuillez selectionner un donateur.";
    	 }
         req.setAttribute(ATT_DONNEUR,donneur);

     }

     if (typeDon.equals("espece"))
     {
         DonEspece don = donEspeceForm.creerDonEspece(req);
         if(!donEspeceForm.getErreurs().isEmpty()) flag = true;
         req.setAttribute(ATT_DON,don);
     }
     else if (typeDon.equals("cheque"))
     {

         DonCheque don = donChequeForm.creerDonCheque(req);
         if(!donChequeForm.getErreurs().isEmpty()) flag = true;
         req.setAttribute(ATT_DON,don);
     }
     else if(typeDon.equals("versement"))
     {
         DonVersement don = donVersementForm.creerDonVersement(req);
         if(!donVersementForm.getErreurs().isEmpty()) flag = true;
         req.setAttribute(ATT_DON,don);
     }
     if(flag) {
    	 erreurMsg = "Veuillez vérifier les champs saisies.";
     }else {
    	 successMsg = "Le don a été bien enregistré.";
     }
     req.setAttribute(ERREUR_MSG, erreurMsg);
     req.setAttribute(SUCCESS_MSG, successMsg);
     req.setAttribute(ATT_DON_VERSEMENT_FORM,donVersementForm);
     req.setAttribute(ATT_DON_CHEQUE_FORM,donChequeForm);
     req.setAttribute(ATT_DON_ESPECE_FORM,donEspeceForm);
     req.setAttribute(ATT_DONNEUR_MORALE_FORM,donneurMoraleForm);
     req.setAttribute(ATT_DONNEUR_PHYSIQUE_FORM,donneurPhysiqueForm);
     this.getServletContext().getRequestDispatcher(VUE_AJOUTER_DON).forward(req,resp);

    }
}
