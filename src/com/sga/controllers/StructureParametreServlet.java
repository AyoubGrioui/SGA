package com.sga.controllers;

import com.sga.entities.Structure;
import com.sga.services.StructureForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public static final String ATT_STRUCTUREFORM = "structureForm";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( VUE_STRUCTURE_PARAMETRE ).forward( request, response );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StructureForm structureForm = new StructureForm();
        Structure structure=structureForm.creerStructure(req);

        req.setAttribute(ATT_STRUCTUREFORM,structureForm);

        this.getServletContext().getRequestDispatcher(ATT_STRUCTUREFORM ).forward( req, resp);
    }
}