package com.sga.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sga.entities.Structure;
import com.sga.repositories.HibernateStructurePersister;
import com.sga.repositories.Repository;

import com.sga.repositories.RepositoryFactory;
import com.sga.services.StructureForm;

/**
 * Servlet implementation class DashBoardDonateurServlet
 */
@WebServlet( "/structureParametre" )
public class StructureParametreServlet extends HttpServlet {

    /**
     *
     */
    private static final long  serialVersionUID        = 1L;
    public static final String VUE_STRUCTURE_PARAMETRE = "/WEB-INF/structureParametrePage.jsp";
    public static final String ATT_STRUCTURE="structure";
    public static final String ATT_STRUCTURE_FORM="structureForm";
    
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException 
    {
        this.getServletContext().getRequestDispatcher( VUE_STRUCTURE_PARAMETRE ).forward( request, response );
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session =req.getSession();
		Structure structure = (Structure) session.getAttribute(ATT_STRUCTURE);
    	
        StructureForm structureForm=new StructureForm();
        
        if(structure!=null)
        {
        	structure = structureForm.modifierStructure(req);
        }
        else
        {
        	structure = structureForm.creerStructure(req);
        }

        req.setAttribute(ATT_STRUCTURE_FORM, structureForm);
        session.setAttribute(ATT_STRUCTURE,structure);


        this.getServletContext().getRequestDispatcher( VUE_STRUCTURE_PARAMETRE ).forward( req, resp );

    }

}