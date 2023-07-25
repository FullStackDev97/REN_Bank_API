/**
 * Copyright :     <br/>
 *
 * @version 1.0<br/>
 */
package com.banque.service;

import com.banque.Subscribe;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.IUtilisateurEntity;
import com.banque.service.ex.AuthentificationException;
import com.banque.service.ex.ErreurTechniqueException;

/**
 * Service d'authentification.
 */
public interface IAuthentificationService {

	/**
	 * Authentifie un utilisateur.
	 *
	 * @param pLogin
	 *            le login
	 * @param pPassword
	 *            le password
	 * @return l'utilisateur authentifie, leve une exception sinon
	 * @throws AuthentificationException
	 *             si un probleme survient
	 * @throws ErreurTechniqueException
	 *             si un probleme survient
	 * @throws NullPointerException
	 *             avec comme message le nom du parametre null
	 */
	public abstract IUtilisateurEntity authentifier(String pLogin,
			String pPassword) throws AuthentificationException,
			ErreurTechniqueException;

	
	public abstract boolean souscrire(Subscribe subscriber)throws AuthentificationException,
	ErreurTechniqueException, ExceptionDao;;

}
//com.banque.service.IAuthentificationService.authentifier(String pLogin, String pPassword)