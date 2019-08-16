package com.tp.forms;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

import java.io.IOException;

import javax.servlet.ServletException;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.InputStream;
import com.tp.beans.Client;

import java.io.File;
import java.io.FileOutputStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;


import com.tp.dao.ClientDAO;
import com.tp.dao.DAOException;
//import eu.medsea.mimeutil.MimeUtil;

public class CreationClientForm {
	
	 private static final String CHAMP_NOM = "nomClient";
	 private static final String CHAMP_PRENOM = "prenomClient";
	 private static final String CHAMP_ADRESSE = "adresseClient";
	 private static final String CHAMP_TELEPHONE = "telephoneClient";
	 private static final String CHAMP_EMAIL = "emailClient";
	 
	 private static final String CHAMP_IMAGE = "photoClient";
	 private static final int TAILLE_TAMPON = 10240;
	 private ClientDAO clientDao;
	 private String resultat;
	 private Map<String, String> erreurs = new HashMap<String, String>();

	 public CreationClientForm( ClientDAO clientDao ) {
	        this.clientDao = clientDao;
	 }
	 
	 public Map<String, String> getErreurs() {
	     return erreurs;
	 }
	 
	 public String getResultat() {
		 return resultat;
	 }
	 
	 public Client creerClient( HttpServletRequest request, String chemin ) {
	        String nom = getValeurChamp( request, CHAMP_NOM );
	        String prenom = getValeurChamp( request, CHAMP_PRENOM );
	        String adresse = getValeurChamp( request, CHAMP_ADRESSE );
	        String telephone = getValeurChamp( request, CHAMP_TELEPHONE );
	        String email = getValeurChamp( request, CHAMP_EMAIL );
	        String photo = null;
	        System.out.println("hello" + nom);
	 
	        Client client = new Client();
	        
	        traiterNom( nom, client );
	        traiterPrenom( prenom, client );
	        traiterAdresse( adresse, client );
	        traiterTelephone( telephone, client );
	        traiterEmail( email, client );
	        traiterImage( client, request, chemin );
	 
	        if ( erreurs.isEmpty() ) {
	        	clientDao.creer( client );
	            resultat = "Succès de la création du client.";
	        } else {
	           resultat = "Echec de la creation client" + erreurs.toString();
	        }
	 
	        return client;
	    }
	 
	 private void traiterNom(String nom, Client client) {
	        try{
	            validationNom(nom);
	        } catch(FormValidationException e) {
	            setErreur( CHAMP_NOM, e.getMessage() );
	        }
	        client.setNom(nom);
	 }
	 private void traiterPrenom(String prenom, Client client) {
	        try{
	            validationNom(prenom);
	        } catch(FormValidationException e) {
	            setErreur( CHAMP_PRENOM, e.getMessage() );
	        }
	        client.setPrenom(prenom);
	 }
	 private void traiterAdresse(String adresse, Client client) {
	        try{
	            validationNom(adresse);
	        } catch(FormValidationException e) {
	            setErreur( CHAMP_ADRESSE, e.getMessage() );
	        }
	        client.setAdresse(adresse);
	 }
	 private void traiterTelephone(String tel, Client client) {
	        try{
	            validationNom(tel);
	        } catch(FormValidationException e) {
	            setErreur( CHAMP_TELEPHONE, e.getMessage() );
	        }
	        client.setTelephone(tel);
	 }
	 private void traiterEmail(String email, Client client) {
	        try{
	            validationNom(email);
	        } catch(FormValidationException e) {
	            setErreur( CHAMP_EMAIL, e.getMessage() );
	        }
	        client.setEmail(email);
	 }
	 private void traiterImage( Client client, HttpServletRequest request, String chemin ) {
	        String image = null;
	        try {
	            image = validationImage( request, chemin );
	        } catch ( FormValidationException e ) {
	            setErreur( CHAMP_IMAGE, e.getMessage() );
	        }
	        client.setPhoto(image);
	   }
	 
	 public String validationImage ( HttpServletRequest request , String chemin ) throws FormValidationException {
		 String nomFichier = null;
		 InputStream contenuFichier = null;
		 try {
			 Part part = request.getPart(CHAMP_IMAGE);
			 nomFichier = getNomFichier(part); 

			 if ( nomFichier != null && !nomFichier.isEmpty() ) {
				 nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1).substring(nomFichier.lastIndexOf('\\') + 1);
				 contenuFichier = part.getInputStream();
				 resultat = contenuFichier.toString();
//				 MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeDetector");
//				 Collection<?> mimeTypes = MimeUtil.getMimeTypes(new BufferedInputStream(request.getPart(  "photoClient" ).getInputStream()));
//               resultat = nomFichier;

				 if(request.getServletContext().getMimeType( nomFichier ).startsWith("image")) {
					 ecrireFichier(contenuFichier, nomFichier, chemin);
				 }
				 else {
					 throw new FormValidationException("Ce fichier doit etre une image");
				 }

			 }

		 } catch ( IllegalStateException e ) {
			 e . printStackTrace () ;
			throw new FormValidationException( "Le fichier envoyé ne doit pas dépasser 1Mo." );
		 } catch ( IOException e ) {
			 e . printStackTrace () ;
			throw new FormValidationException( "Erreur de configuration du serveur." );
		 } catch ( ServletException e ) {
			 e . printStackTrace();
			throw new FormValidationException( "Ce type de requête n'est pas supporté, merci d'utiliser le formulaire prévu pour envoyer votre fichier." );
		 }

		 return nomFichier;
	 }
	 
	 private void validationNom( String nom ) throws FormValidationException {
	        if ( nom != null ) {
	            if ( nom.length() < 2 ) {
	            	
	                throw new FormValidationException( "Le nom d'utilisateur doit contenir au moins 2 caractères." );
	            }
	        } else {
	            throw new FormValidationException( "Merci d'entrer un nom d'utilisateur." );
	        }
	    }
	 
	    private void validationPrenom( String prenom ) throws FormValidationException {
	        if ( prenom != null && prenom.length() < 2 ) {
	            throw new FormValidationException( "Le prénom d'utilisateur doit contenir au moins 2 caractères." );
	        }
	    }
	 
	    private void validationAdresse( String adresse ) throws FormValidationException {
	        if ( adresse != null ) {
	            if ( adresse.length() < 10 ) {
	                throw new FormValidationException( "L'adresse de livraison doit contenir au moins 10 caractères." );
	            }
	        } else {
	            throw new FormValidationException( "Merci d'entrer une adresse de livraison." );
	        }
	    }
	 
	    private void validationTelephone( String telephone ) throws FormValidationException {
	        if ( telephone != null ) {
	            if ( !telephone.matches( "^\\d+$" ) ) {
	                throw new FormValidationException( "Le numéro de téléphone doit uniquement contenir des chiffres." );
	            } else if ( telephone.length() < 4 ) {
	                throw new FormValidationException( "Le numéro de téléphone doit contenir au moins 4 chiffres." );
	            }
	        } else {
	            throw new FormValidationException( "Merci d'entrer un numéro de téléphone." );
	        }
	    }
	 
	    private void validationEmail( String email ) throws FormValidationException {
	        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	            throw new FormValidationException( "Merci de saisir une adresse mail valide." );
	        }
	    }
	    
	    private static String getNomFichier ( Part part ) {
	        for ( String contentDisposition : part.getHeader("content-disposition").split(";")) {
	        	if ( contentDisposition.trim().startsWith("filename")) {
	        		return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
	        	}
	    	}
	        	return null ;
	    	}
	    
	    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
	        String valeur = request.getParameter( nomChamp );
	        if ( valeur == null || valeur.trim().length() == 0 ) {
	            return null;
	        } else {
	            return valeur;
	        }
	    }
	    private void setErreur( String champ, String message ) {
	        erreurs.put( champ, message );
	    }
	    private void ecrireFichier ( InputStream contenu , String nomFichier , String chemin ) throws FormValidationException {

	    		BufferedInputStream entree = null ;
	    		BufferedOutputStream sortie = null ;
	    		try {
	    			entree = new BufferedInputStream(contenu ,TAILLE_TAMPON );
	    			sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);
	    			byte [] tampon = new byte[TAILLE_TAMPON];
	    			int longueur = 0;
	    			while((longueur = entree.read(tampon)) > 0)
	    			{
	    				sortie.write(tampon ,0 , longueur);
	    			}
	    		} catch(Exception e) {
	    			throw new FormValidationException("Erreur lors de l'ecriture");
	    		}finally {
	    		    try {
	    				sortie.close();
	    			} catch ( IOException ignore ) {
	    			}
	    			try {
	    				entree.close () ;
	    			} catch ( IOException ignore ) {
	    			}
	    		}
	    }
}
