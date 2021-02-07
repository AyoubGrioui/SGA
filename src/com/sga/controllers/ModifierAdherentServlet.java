package com.sga.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sga.entities.Adherent;
import com.sga.entities.Structure;
import com.sga.repositories.HibernateAdherentPersister;
import com.sga.repositories.HibernateStructurePersister;
import com.sga.services.AdherentForm;

/**
 * Servlet implementation class ModifierAdherent
 */
@WebServlet("/modifierAdherent")
public class ModifierAdherentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public static final String VUE_AJOUTER_ADHERENT = "/WEB-INF/ajouterAdherent.jsp";
    public static final String ATT_ERREURS ="erreurs";
    public static final String ATT_ADHERENT="adherent";
    public static final String ATT_LIST_STRUCTURE ="StructureList";
    
    public static final String PARAMETRE_ID_ADHERENT = "adherentID";
	
	public static final String VUE = "/listeDesAdherents";


    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
    	
    	HibernateAdherentPersister adherentPersister =new HibernateAdherentPersister();

		
		/*Recuperation du param */
		String idAdherent = getValeurParametre(request, PARAMETRE_ID_ADHERENT);
		
		
		// si l'id et la map ne sont pas vides 
		
		if(idAdherent != null ) {
				
				Long id = Long.parseLong(idAdherent);
				//suppression de l'adherent de la BD
				Adherent adherent = adherentPersister.read(id);
				request.setAttribute(ATT_ADHERENT, adherent);
				
		        HibernateStructurePersister structurePersister =new HibernateStructurePersister();
		        List<Structure> structureList = structurePersister.getAll();

		        request.setAttribute(ATT_LIST_STRUCTURE,structureList);
		        
		        this.getServletContext().getRequestDispatcher( VUE_AJOUTER_ADHERENT ).forward( request, response );
				}
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdherentForm adherentForm = new AdherentForm();
        Adherent adherent = adherentForm.modifierAdherent(request);

        Map<String, String> erreurs = adherentForm.getErreurs();
        request.setAttribute(ATT_ERREURS,erreurs);
        request.setAttribute(ATT_ADHERENT,adherent);


        this.getServletContext().getRequestDispatcher(VUE).forward( request , response );
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
