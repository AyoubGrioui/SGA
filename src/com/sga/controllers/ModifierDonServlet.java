package com.sga.controllers;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sga.entities.Don;
import com.sga.entities.DonCheque;
import com.sga.entities.DonEspece;
import com.sga.entities.DonVersement;
import com.sga.entities.Donneur;
import com.sga.entities.Structure;
import com.sga.repositories.HibernateDonPersister;
import com.sga.repositories.HibernateStructurePersister;
import com.sga.services.DonChequeForm;
import com.sga.services.DonEspeceForm;
import com.sga.services.DonVersementForm;

/**
 * Servlet implementation class DashBoardDonateurServlet
 */
@WebServlet("/modifierDon")
public class ModifierDonServlet extends HttpServlet {

	public static final String VUE_MODIF_DON        = "/WEB-INF/modifDon.jsp";
    public static final String ATT_DONFORM = "donForm";
    public static final String ATT_DON = "don";
    private static final String ATT_LIST_STRUCTURE = "structureList";
    public static final String PARAM_TYPEDON = "typeDon";

    
	public static final String PARAMETRE_ID_DON = "donID";
	
	public static final String VUE = "/listeDon";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException{
    	
    	 HibernateDonPersister donPersister =new HibernateDonPersister();

 		
 		/*Recuperation du param */
 		String idDon = getValeurParametre(request, PARAMETRE_ID_DON);
 		
 		// si l'id n'est pas vide 
 		
 		if(idDon != null) {
 			
 			Long id = Long.parseLong(idDon);

 				//suppression de l'adherent de la BD
 				Don don = donPersister.read(id);
 				request.setAttribute(ATT_DON, don);
 				
 				HibernateStructurePersister structurePersister =new HibernateStructurePersister();
 		        List<Structure> structureList = structurePersister.getAll();

 		        request.setAttribute(ATT_LIST_STRUCTURE,structureList);
 		        this.getServletContext().getRequestDispatcher( VUE_MODIF_DON ).forward( request, response );
 			}
 		
 		else {
 			this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
 		}

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String typeDon=(String) request.getParameter(PARAM_TYPEDON);
        
        if (typeDon.equals("espece"))
        {
            DonEspeceForm donForm = new DonEspeceForm();
            DonEspece don = donForm.modifierDonEspece(request);
            request.setAttribute(ATT_DONFORM,donForm);
            request.setAttribute(ATT_DON,don);
        }
        else if (typeDon.equals("cheque"))
        {
            DonChequeForm donForm = new DonChequeForm();
            DonCheque don = donForm.modifierDonCheque(request);
            request.setAttribute(ATT_DONFORM,donForm);
            request.setAttribute(ATT_DON,don);
        }
        else if(typeDon.equals("versement"))
        {
            DonVersementForm donForm = new DonVersementForm();
            DonVersement don = donForm.modifierDonVersement(request);
            request.setAttribute(ATT_DONFORM,donForm);
            request.setAttribute(ATT_DON,don);
        }

        
        this.getServletContext().getRequestDispatcher( VUE_MODIF_DON).forward( request, response );

    }
    
    /*
     * Méthode utilitaire qui retourne null si un paramètre est vide, et son
     * contenu sinon.
     */
    private static String getValeurParametre( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }

}
