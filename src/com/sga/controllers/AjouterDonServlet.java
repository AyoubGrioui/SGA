package com.sga.controllers;

import com.sga.entities.*;
import com.sga.repositories.RepositoryFactory;
import com.sga.services.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public static final String ATT_ANCIENDONNEUR = "donneurlist";
    public static final String ATT_DONNEUR = "donneur";
    public static final String PARAM_TYPEDECOMPTE = "accountType";
    public static final String PARAM_TYPEDONATEUR = "typeDonateur";
    public static final String PARAM_TYPEDON = "typeDon";
    public static final String PARAM_ANCIENDONNEUR = "listAncienDonneur";


    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

		/*
		 * RepositoryFactory repFactory = new RepositoryFactory(); Repository rep1 =
		 * repFactory.getDonneurMoralRepository(); List<Donneur> donneurList=
		 * rep1.getAll();
		 * 
		 * Repository rep2 = repFactory.getDonneurPhysiqueRepository();
		 * donneurList.addAll(rep2.getAll());
		 * 
		 * request.setAttribute(ATT_ANCIENDONNEUR,donneurList);
		 */

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

         RepositoryFactory repFactory = new RepositoryFactory();


     }

     if (typeDon.equals("espece"))
     {
         DonEspeceForm donForm = new DonEspeceForm();
         DonEspece espece = donForm.creerDonEspece(req);
         req.setAttribute(ATT_DONFORM,donForm);
     }
     else if (typeDon.equals("cheque"))
     {
         DonChequeForm donForm = new DonChequeForm();
         DonCheque don = donForm.creerDonCheque(req);
         req.setAttribute(ATT_DONFORM,donForm);
     }
     else if(typeDon.equals("versement"))
     {
         DonVersementForm donForm = new DonVersementForm();
         DonVersement don = donForm.creerDonVersement(req);
         req.setAttribute(ATT_DONFORM,donForm);
     }

    }
}
