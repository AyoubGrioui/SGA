package com.sga.controllers;

import java.io.File;
import java.io.IOException;
 
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.sga.entities.Donneur;
import com.sga.helpers.EmailUtility;
import com.sga.repositories.HibernateDonneurPersister;
 
/**
 * A servlet that takes message details from user and send it as a new e-mail
 * through an SMTP server.
 *
 * @author www.codejava.net
 *
 */
@WebServlet("/envoyerMail")
public class EmailSendingServlet extends HttpServlet {
    private String host;
    private String port;
    private String user;
    private String pass;
    private static final String PARAM_ID_DONNEUR="donneurID";
 
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }
 
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
    	HibernateDonneurPersister donneurPersister =new HibernateDonneurPersister();
    	Donneur donneur = donneurPersister.read(Long.parseLong(getValeurChamp(request, PARAM_ID_DONNEUR)));
    	
    	
    	
    	// reads form fields
        String recipient =donneur.getEmail();
        String subject ="Suivi de Don";
        String content = donneur.getMotDePasse();   
        String successMsg = null;
        String errorMsg = null;
        
 
        try {
            EmailUtility.sendEmail(host, port, user, pass, recipient, subject,
                    content);
            successMsg = "The e-mail was sent successfully";
        } catch (Exception ex) {
            ex.printStackTrace();
            errorMsg = "There were an error: " + ex.getMessage();
        } finally {
            HttpSession session =request.getSession();
            
            session.setAttribute("erreurMsg", errorMsg);
    		session.setAttribute("successMsg",successMsg);        	
            response.sendRedirect(request.getContextPath() + "/listeDesDonateurs");


        }
        
    }
    
	//methode utilitaire qui retourne null si un champ est vide, et son contenu sinon 

	private static String getValeurChamp(HttpServletRequest request,String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if(valeur == null || valeur.trim().length()==0) {
			return null;
		}
		else {
			return valeur;
		}
	}
}
