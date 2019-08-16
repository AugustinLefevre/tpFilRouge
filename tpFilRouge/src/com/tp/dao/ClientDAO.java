package com.tp.dao;

import java.util.List;
import com.tp.beans.Client;
 /*
  * create interface with basic methodes 
  * 
  */
public interface ClientDAO {
    void creer(Client client) throws DAOException;
    Client trouver(long id) throws DAOException;
    List<Client> lister() throws DAOException;
    void supprimer(Client client) throws DAOException;
}