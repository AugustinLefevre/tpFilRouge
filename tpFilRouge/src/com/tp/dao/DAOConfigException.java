package com.tp.dao;
/* Erreurs liées à la configuration du DAO et du driver JDBC.
 * 
 * Afin de cacher la nature du mode de stockage des données au reste de l’application,
 * c’est une bonne pratique de masquer les exceptions spécifiques (celles qui surviennent
 * au runtime, c’est-à-dire lors de l’exécution) derrière des exceptions propres au DAO.
 * Notre objectif ici, c’est de faire en sorte que depuis l’extérieur de la couche de données,
 * aucune de ces exceptions ne sorte directement sous cette forme.
 */
public class DAOConfigException extends RuntimeException {
	public DAOConfigException(String message) {
		super(message);
	}
	public DAOConfigException(String message, Throwable cause) {
		super(message ,cause);
	}
	public DAOConfigException(Throwable cause) {
		super(cause);
	}
}
