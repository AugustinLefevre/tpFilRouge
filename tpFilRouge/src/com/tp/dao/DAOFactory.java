package com.tp.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
 * Factory va être responsable de :
 *   – lire les informations de configuration depuis le fichier properties ;
 *   – charger le driver JDBC du SGBD utilisé ;
 *   – fournir une connexion à la base de données.
 */
public class DAOFactory {
	private static final String FICHIER_PROPERTIES = "/com/tp/dao/dao.properties";
	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
	private static final String PROPERTY_MOT_DE_PASSE = "motdepasse";
	
	private String 	url;
	private String 	username;
	private String 	password;

	DAOFactory ( String url , String username , String password ){
		this.url= url;
		this.username = username ;
		this.password = password ;
	}
	public static DAOFactory getInstance() throws DAOConfigException {
		Properties properties = new Properties();
		String url;
		String driver;
		String nomUtilisateur;
		String motDePasse;
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream(FICHIER_PROPERTIES ) ;
		
		if ( fichierProperties == null ) {
			throw new DAOConfigException("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable.");
		}
		try {
			properties.load(fichierProperties);
			url = properties.getProperty(PROPERTY_URL);
			driver = properties.getProperty(PROPERTY_DRIVER);
			nomUtilisateur = properties.getProperty(PROPERTY_NOM_UTILISATEUR);
			motDePasse = properties.getProperty(PROPERTY_MOT_DE_PASSE);
		} catch ( IOException e ) {
			throw new DAOConfigException ( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e);
		} try {
			Class.forName(driver);
		} catch (ClassNotFoundException e ) {
			throw new DAOConfigException ("Le driver est introuvable dans le classpath.", e );
		}
		DAOFactory instance = new DAOFactory ( url ,nomUtilisateur , motDePasse ) ;
		return instance ;
	}
	/* Méthode chargée de fournir une connexion à la base de données */
	Connection getConnection ()
		throws SQLException {
			return DriverManager.getConnection(url, username, password);
	}
	/*Méthodes de récupération de l'implémentation des différents DAO*/
	public ClientDAO getClientDao() {
	return new ClientDaoImpl(this);
	}
	public CommandeDAO getCommandeDao() {
		return new CommandeDaoImpl(this);
	}

}
