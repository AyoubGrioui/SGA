package com.sga.controllers;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sga.entities.Structure;
import com.sga.repositories.Repository;
import com.sga.repositories.RepositoryFactory;

@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Structure data = new Structure();
		data.setSiteWeb("random");
		data.setObjectif("random");
		data.setNom("random");
		data.setAdresse("random");
		data.setEmail("random");
		data.setDateCreation(LocalDate.now());
		RepositoryFactory rf = RepositoryFactory.getInstance();
		Repository<Structure> repo = rf.getStructureRepository();
		repo.create(data);
		response.getWriter().append(repo.read(2L).getNom());
	}

}
