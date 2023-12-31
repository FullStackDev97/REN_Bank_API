/**
 * Copyright :     <br/>
 *
 * @version 1.0<br/>
 */
package com.banque.entity;

import java.io.Serializable;

/**
 * Represente une entite.
 */
public interface IEntity extends Serializable {

	/**
	 * Recupere l'id du compte.
	 *
	 * @return l'id du compte.
	 */
	public abstract Integer getId();

	/**
	 * Fixe l'id du compte.
	 *
	 * @param unId
	 *            l'id du compte.
	 */
	public abstract void setId(Integer unId);

}