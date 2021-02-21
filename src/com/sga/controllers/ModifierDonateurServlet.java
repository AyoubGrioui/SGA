package com.sga.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sga.entities.Donneur;
import com.sga.entities.DonneurMoral;
import com.sga.entities.DonneurPhysique;
import com.sga.services.DonneurMoraleForm;
import com.sga.services.DonneurPhysiqueForm;
import com.sga.entities.Structure;
import com.sga.repositories.HibernateDonneurPersister;
import com.sga.repositories.HibernateStructurePersister;

/**
 * Servlet implementation class DashBoardDonateurServlet
 */
@WebServlet("/modifierDonateur")
public class ModifierDonateurServlet extends HttpServlet {

	public static final String VUE_MODIF_DONATEUR = "/WEB-INF/modifDonateur.jsp";
    public static final String ATT_DONATEURFORM = "donneurForm";
    public static final String ATT_DONATEUR = "donneur";
    private static final String ATT_LIST_STRUCTURE = "structureList";
    public static final String PARAM_TYPEDONATEUR = "typeDonateur";
	//public static final String INTERNAL_ID_DONATEUR = "ID";
	public static final String INTERNAL_ID_DONATEUR = "idDonneur";

    
	public static final String PARAMETRE_ID_DONATEUR = "donneurID";
	
	public static final String VUE = "/listeDonateur";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException{
    	
    	 HibernateDonneurPersister donneurPersister =new HibernateDonneurPersister();

 		
 		/*Recuperation du param */
 		String idDonneur = getValeurParametre(request, PARAMETRE_ID_DONATEUR);
 		
 		// si l'id n'est pas vide 
 		
 		if(idDonneur != null) {
 			
 			Long id = Long.parseLong(idDonneur);

 			HttpSession session = request.getSession();
 				//suppression de l'adherent de la BD
 				Donneur donneur = donneurPersister.read(id);
 				request.setAttribute(ATT_DONATEUR, donneur);
 				
 				HibernateStructurePersister structurePersister =new HibernateStructurePersister();
 		        List<Structure> structureList = structurePersister.getAll();

 		        request.setAttribute(ATT_LIST_STRUCTURE,structureList);
 		        session.setAttribute(INTERNAL_ID_DONATEUR, id);

 		        
 		        this.getServletContext().getRequestDispatcher( VUE_MODIF_DONATEUR ).forward( request, response );
 			}
 		
 		else {
 			this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
 		}

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	
        String typeDonateur=(String) request.getParameter(PARAM_TYPEDONATEUR);
        
        if (typeDonateur.equals("physique"))
        {
            DonneurPhysiqueForm donneurForm = new DonneurPhysiqueForm();
            DonneurPhysique donneur = donneurForm.modifierDonneurPhysique(request);
            request.setAttribute(ATT_DONATEURFORM,donneurForm);
            request.setAttribute(ATT_DONATEUR,donneur);
        }
        else
        {
            DonneurMoraleForm donneurForm = new DonneurMoraleForm();
            DonneurMoral donneur = donneurForm.modifierDonneurMorale(request);
            request.setAttribute(ATT_DONATEURFORM,donneurForm);
            request.setAttribute(ATT_DONATEUR,donneur);
        }

        this.getServletContext().getRequestDispatcher( VUE_MODIF_DONATEUR).forward( request, response );

    }
    
    /*
     * MÃ©thode utilitaire qui retourne null si un paramÃ¨tre est vide, et son
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
