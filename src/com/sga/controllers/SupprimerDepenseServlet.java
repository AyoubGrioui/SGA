package com.sga.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sga.entities.Depense;
import com.sga.repositories.HibernateDepensePersister;

/**
 * Servlet implementation class SupprimerDepense
 */
@WebServlet("/supprimerDepense")
public class SupprimerDepenseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String PARAMETRE_ID_DEPENSE = "depenseID";
	
	public static final String VUE = "/listeDepense";
	
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        HibernateDepensePersister depensePersister =new HibernateDepensePersister();

		
		/*Recuperation du param */
		String idDepense = getValeurParametre(request, PARAMETRE_ID_DEPENSE);
		
		// si l'id n'est pas vide 
		
		if(idDepense != null) {
			
			Long id = Long.parseLong(idDepense);

				//suppression de l'adherent de la BD
				Depense depense = depensePersister.read(id);
				depensePersister.delete(depense);
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
