package com.tp.servlets;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.beans.Client;
import com.tp.forms.CreationClientForm;

import com.tp.dao.ClientDAO;
import com.tp.dao.DAOFactory;

 public class CreationClient extends HttpServlet {
	  
	 public static final String ATT_CLIENT = "client";
	 public static final String ATT_FORM   = "form";
	 public static final String SESSION_CLIENTS = "clients";
	 public static final String VUE_SUCCES = "/WEB-INF/afficherClient.jsp";
	 public static final String VUE_FORM = "/WEB-INF/creerClient.jsp";
	 
	 private ClientDAO clientDao;
	 public static final String CONF_DAO_FACTORY = "daofactory";
	 
	 public void init() throws ServletException {
	        /* Récupération d'une instance de notre DAO Utilisateur */
	        this.clientDao = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getClientDao();
	      //  System.out.println(clientDao);
	 }
	 
	 public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		 this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
	 }
	
	 public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	     CreationClientForm form = new CreationClientForm(clientDao);
	 
	     String chemin = this.getServletConfig().getInitParameter("chemin");
	     Client client = form.creerClient(request, chemin);
	 
	     request.setAttribute( ATT_CLIENT, client );
	     request.setAttribute( ATT_FORM, form );
	 
	     if ( form.getErreurs().isEmpty() ) {
	        HttpSession session = request.getSession();
	        Map<Long, Client> clients = (HashMap<Long, Client>) session.getAttribute( SESSION_CLIENTS );
	        if ( clients == null ) {
	            clients = new HashMap<Long, Client>();
	        }
	        clients.put( client.getId(), client );
	        session.setAttribute( SESSION_CLIENTS, clients );
	        this.getServletContext().getRequestDispatcher( VUE_SUCCES ).forward( request, response );
	        } else {
	            this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
	           
	        }
	     }
 }
