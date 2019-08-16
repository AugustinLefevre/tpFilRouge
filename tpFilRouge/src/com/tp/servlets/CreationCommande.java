package com.tp.servlets;
import java.util.HashMap;
import java.util.Map;

 
import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import com.tp.beans.Commande;
import com.tp.beans.Client;
import com.tp.forms.CreationCommandeForm;

import com.tp.dao.ClientDAO;
import com.tp.dao.CommandeDAO;
import com.tp.dao.DAOFactory;
 
public class CreationCommande extends HttpServlet {
	public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_COMMANDE = "commande";
    public static final String ATT_FORM     = "form";
    public static final String SESSION_COMMANDES = "commandes";
    public static final String SESSION_CLIENTS   = "clients";
    public static final String APPLICATION_CLIENTS   = "initClients";
    public static final String APPLICATION_COMMANDES = "initCommandes";
 
    public static final String VUE_SUCCES   = "/WEB-INF/afficherCommande.jsp";
    public static final String VUE_FORM     = "/WEB-INF/creerCommande.jsp";
 
    private ClientDAO clientDao;
    private CommandeDAO commandeDao;
    
    public void init() throws ServletException {
        /* Récupération d'une instance de nos DAO Client et Commande */
        this.clientDao = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getClientDao();
        this.commandeDao = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getCommandeDao();
    }
    
    
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* À la réception d'une requête GET, simple affichage du formulaire */
        this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
    }
 
    
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        CreationCommandeForm form = new CreationCommandeForm(clientDao, commandeDao);
 
        /* Traitement de la requête et récupération du bean en résultant */
        Commande commande = form.creerCommande( request, this.getServletConfig().getInitParameter("chemin"));
 
        /* Ajout du bean et de l'objet métier à l'objet requête */
        request.setAttribute( ATT_COMMANDE, commande );
        request.setAttribute( ATT_FORM, form );
 
        if ( form.getErreurs().isEmpty() ) {
        	HttpSession session = request.getSession();
        	Map<Long, Client> clients = (HashMap<Long, Client>) session.getAttribute( SESSION_CLIENTS );
        	Map<Long, Commande> commandes = (HashMap<Long, Commande>) session.getAttribute(SESSION_COMMANDES);
        	
        	if ( clients == null ) {
                clients = new HashMap<Long, Client>();
            }
            clients.put( commande.getClient().getId(), commande.getClient() );
            session.setAttribute(SESSION_CLIENTS, clients);
        	
        	if ( commandes == null ) {
                commandes = new HashMap<Long, Commande>();
            }
        	commandes.put(commande.getId(), commande);
        	session.setAttribute(SESSION_COMMANDES, commandes);
        	/* Si aucune erreur, alors affichage de la fiche récapitulative */
            this.getServletContext().getRequestDispatcher(VUE_SUCCES).forward( request, response );
        } else {
            /* Sinon, ré-affichage du formulaire de création avec les erreurs */
            this.getServletContext().getRequestDispatcher(VUE_FORM).forward( request, response );
        }
    }
}