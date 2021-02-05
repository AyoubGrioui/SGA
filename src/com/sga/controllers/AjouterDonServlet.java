package com.sga.controllers;

import com.sga.entities.*;
import com.sga.repositories.*;
import com.sga.services.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet( "/ajouterDon" )
public class AjouterDonServlet extends HttpServlet {

    /**
     * 
     */
    private static final long  serialVersionUID = 1L;
    public static final String VUE_AJOUTER_DON  = "/WEB-INF/ajouterDonPage.jsp";
    public static final String ATT_DONFORM= "donForm";
    public static final String ATT_DONNEURFORM = "donneurForm";
    public static final String ATT_SESSION_USER  = "user";
    public static final String ATT_DONNEUR_MORALE_LIST = "donneurMoralelist";
    public static final String ATT_DONNEUR_PHYSIQUE_LIST = "donneurPhysiquelist";
    public static final String ATT_DONNEUR = "donneur";
    public static final String ATT_DON="don";
    public static final String PARAM_TYPEDECOMPTE = "accountType";
    public static final String PARAM_TYPEDONATEUR = "typeDonateur";
    public static final String PARAM_TYPEDON = "typeDon";
    public static final String PARAM_ANCIENDONNEUR = "listAncienDonneur";
    private static final String ATT_LIST_STRUCTURE = "structureList";


    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        HibernateStructurePersister structurePersister =new HibernateStructurePersister();
        List<Structure> structureList = structurePersister.getAll();

        HibernateDonneurMoralPersister DonneurMoralPersister = new HibernateDonneurMoralPersister();
        List<DonneurMoral> donneurMoraleList =DonneurMoralPersister.getAll();

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

     if (accountType.equals("nouveau"))
     {
         if (typeDonateur.equals("physique"))
         {
             DonneurPhysiqueForm donneurForm = new DonneurPhysiqueForm();
             DonneurPhysique donneur = donneurForm.creerDonneurPhysique(req);
             req.setAttribute(ATT_DONNEURFORM,donneurForm);
             req.setAttribute(ATT_DONNEUR,donneur);
         }
         else
         {
             DonneurMoraleForm donneurForm = new DonneurMoraleForm();
             DonneurMoral donneur = donneurForm.creerDonneurMorale(req);
             req.setAttribute(ATT_DONNEURFORM,donneurForm);
             req.setAttribute(ATT_DONNEUR,donneur);
         }
     }
     else
     {

         String id =(String) req.getParameter(PARAM_ANCIENDONNEUR);

         HibernateDonneurPersister donneurPersister=new HibernateDonneurPersister();
         Donneur donneur = donneurPersister.read(Long.parseLong(id));
         req.setAttribute(ATT_DONNEUR,donneur);

     }

     if (typeDon.equals("espece"))
     {
         DonEspeceForm donForm = new DonEspeceForm();
         DonEspece don = donForm.creerDonEspece(req);
         req.setAttribute(ATT_DONFORM,donForm);
         req.setAttribute(ATT_DON,don);
     }
     else if (typeDon.equals("cheque"))
     {
         DonChequeForm donForm = new DonChequeForm();
         DonCheque don = donForm.creerDonCheque(req);
         req.setAttribute(ATT_DONFORM,donForm);
         req.setAttribute(ATT_DON,don);
     }
     else if(typeDon.equals("versement"))
     {
         DonVersementForm donForm = new DonVersementForm();
         DonVersement don = donForm.creerDonVersement(req);
         req.setAttribute(ATT_DONFORM,donForm);
         req.setAttribute(ATT_DON,don);
     }

     this.getServletContext().getRequestDispatcher(VUE_AJOUTER_DON).forward(req,resp);

    }
}
