package com.tp.servlets;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListeClients extends HttpServlet {
	// public static final String ATT_CLIENT = "client";
	// public static final String ATT_FORM = "form";
	 public static final String VUE = "/WEB-INF/afficherListeClient.jsp";
	 public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	     this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	 }
}
