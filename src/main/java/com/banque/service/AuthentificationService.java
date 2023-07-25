/**
 * Copyright :     <br/>
 *
 * @version 1.0<br/>
 */
package com.banque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banque.Subscribe;
import com.banque.dao.ICompteDAO;
import com.banque.dao.IUtilisateurDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.CompteEntity;
import com.banque.entity.ICompteEntity;
import com.banque.entity.IUtilisateurEntity;
import com.banque.entity.UtilisateurEntity;
import com.banque.service.ex.AuthentificationException;
import com.banque.service.ex.ErreurTechniqueException;
import com.banque.service.ex.MauvaisMotdepasseException;
import com.banque.service.ex.UtilisateurInconnuException;

/**
 * Gestion de l'authentification.
 */
@Service
public class AuthentificationService extends AbstractService implements
IAuthentificationService {

	@Autowired
	private IUtilisateurDAO utilisateurDAO;
	@Autowired
	private ICompteDAO compteDAO;

	/**
	 * Constructeur de l'objet.
	 */
	public AuthentificationService() {
		super();
	}

	/**
	 * Recupere la propriete <i>utilisateurDAO</i>.
	 *
	 * @return the utilisateurDAO la valeur de la propriete.
	 */
	public IUtilisateurDAO getUtilisateurDAO() {
		return this.utilisateurDAO;
	}

	/**
	 * Fixe la propriete <i>utilisateurDAO</i>.
	 *
	 * @param pUtilisateurDAO
	 *            la nouvelle valeur pour la propriete utilisateurDAO.
	 */
	public void setUtilisateurDAO(IUtilisateurDAO pUtilisateurDAO) {
		this.utilisateurDAO = pUtilisateurDAO;
		
	}
	
	

	public ICompteDAO getCompteDAO() {
		return compteDAO;
	}

	public void setCompteDAO(ICompteDAO compteDAO) {
		this.compteDAO = compteDAO;
	}

	@Override
	public IUtilisateurEntity authentifier(String pLogin, String pPassword)
			throws AuthentificationException, ErreurTechniqueException {
		if ((pLogin == null) || (pLogin.trim().length() == 0)) {
			throw new NullPointerException("login");
		}
		if ((pPassword == null) || (pPassword.trim().length() == 0)) {
			throw new NullPointerException("password");
		}
		IUtilisateurEntity resultat = null;
		try {
			resultat = this.utilisateurDAO.selectLogin(pLogin, null);
		} catch (ExceptionDao e) {
			throw new ErreurTechniqueException(e);
		}
		if (resultat == null) {
			throw new UtilisateurInconnuException();
		}
		if (!pPassword.equals(resultat.getPassword())) {
			throw new MauvaisMotdepasseException();
		}

		return resultat;
	}
	
	@Override
	public boolean souscrire(Subscribe subscriber)
			throws AuthentificationException, ErreurTechniqueException, ExceptionDao {
		
		try {
			IUtilisateurEntity new_user = new UtilisateurEntity();
			ICompteEntity new_acount = new CompteEntity();
			
			new_user.setLogin(subscriber.getUser_name());
			new_user.setPrenom(subscriber.getFirst_name());
			new_user.setNom(subscriber.getLast_name());
			new_user.setPassword(subscriber.getUser_password());
			new_user.setSex( subscriber.getSex());
			
			//return " "+this.utilisateurDAO.select(new_user.getId(), null);
			
			this.utilisateurDAO.insert(new_user, null);
			
			UtilisateurEntity usr =  (UtilisateurEntity) this.utilisateurDAO.selectLogin(new_user.getLogin(), null);
			
			//return usr.toString();
			
			new_acount.setLibelle(subscriber.getType());
			new_acount.setSolde(subscriber.getMontant());
			new_acount.setDecouvert(0.00);
			new_acount.setTaux(0.00);
			new_acount.setUtilisateurId(usr.getId());
			
			this.compteDAO.insert(new_acount, null);
			
			return true;
				
			
			
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		
		
		

		
	}
}
