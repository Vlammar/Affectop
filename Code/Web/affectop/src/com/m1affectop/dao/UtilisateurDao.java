package com.m1affectop.dao;

import java.util.List;

import com.m1affectop.beans.Utilisateur;

public interface UtilisateurDao {
    void ajouter( Utilisateur utilisateur ) throws DaoException;
    List<Utilisateur> lister() throws DaoException;
}