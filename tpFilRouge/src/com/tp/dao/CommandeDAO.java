package com.tp.dao;

import java.util.List;

import com.tp.beans.Commande;
 
public interface CommandeDAO {
    void creer( Commande commande ) throws DAOException;
    Commande trouver( long id ) throws DAOException;
    List<Commande> lister() throws DAOException;
    void supprimer( Commande commande ) throws DAOException;
}
