package com.fr.struts.plugins.security.core.authentication;

public interface AuthenticationManager {

	/**
	 * Authentifie l'utilisateur.
	 *
	 * @param authentication l'authentification associée.
	 * @return l'authentification associée.
	 */
	public Authentication authenticate(Authentication authentication);

	/**
	 * Met à jour l'authentification associée à l'utilisateur.
	 *
	 * @param authentication l'authentification associée.
	 * @return l'authentification associée.
	 */
	public Authentication refresh(Authentication authentication);

	/**
	 * Invalide l'authentification de l'utilisateur.
	 */
	public void invalidate();

	/**
	 * Retourne l'authentification associée à l'utilisateur.
	 *
	 * @return l'authentification associée.
	 */
	public Authentication getAuthentication();

}
