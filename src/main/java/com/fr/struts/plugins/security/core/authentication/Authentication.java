package com.fr.struts.plugins.security.core.authentication;

import java.io.Serializable;

import com.fr.struts.plugins.security.core.bean.Authority;

public interface Authentication extends Serializable {

	/**
	 * Retourne l'identité de l'utilisateur connecté.
	 *
	 * @return l'identité.
	 */
	public Object getPrincipal();

	/**
	 * Retourne les informations qui prouvent que l'utilisateur est correct.
	 *
	 * @return les informations.
	 */
	public Object getCredentials();

	/**
	 * Retourne les informations complémentaires liées à l'utilisateur.
	 *
	 * @return les informations liées.
	 */
	public Object getDetails();

	/**
	 * Défini les informations complémentaires liées à l'utilisateur.
	 *
	 * @param details les informations à lier.
	 */
	public void setDetails(Object details);

	/**
	 * Récupère l'autorité liée à l'utilisateur.
	 *
	 * @return l'autorité liée.
	 */
	public Authority getAuthority();

	/**
	 * Défini l'autorité liée à l'utilisateur.
	 *
	 * @param authority l'autorité à lier.
	 */
	public void setAuthority(Authority authority);

	/**
	 * Détermine si l'utilisateur est correctement authentifié.
	 *
	 * @return true si correct.
	 */
	public boolean isAuthenticated();

	/**
	 * Défini si l'utilisateur est correctement authentifié.
	 *
	 * @param authenticated état authentification.
	 */
	public void setAuthenticated(boolean authenticated);

}
