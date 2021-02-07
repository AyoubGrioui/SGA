package com.sga.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sga.entities.Adherent;
import com.sga.entities.Depense;
import com.sga.repositories.HibernateAdherentPersister;

/**
 * Servlet implementation class SupprimerAdherent
 */
@WebServlet("/supprimerAdherent")
public class SupprimerAdherentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String PARAMETRE_ID_ADHERENT = "adherentID";
	
	public static final String VUE = "/listeDesAdherents";
	
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        HibernateAdherentPersister adherentPersister =new HibernateAdherentPersister();

		
		/*Recuperation du param */
		String idAdherent = getValeurParametre(request, PARAMETRE_ID_ADHERENT);
		
		
		// si l'id et la map ne sont pas vides 
		
		if(idAdherent != null ) {
				
				Long id = Long.parseLong(idAdherent);
				//suppression de l'adherent de la BD
				Adherent adherent = adherentPersister.read(id);
				adherentPersister.delete(adherent);
				}
				
				
				/* Redirection vers la fiche récapitulative */
		        response.sendRedirect( request.getContextPath() + VUE );
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
