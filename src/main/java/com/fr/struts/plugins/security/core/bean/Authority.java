package com.fr.struts.plugins.security.core.bean;

import java.io.Serializable;

public interface Authority extends Serializable {

	/**
	 * Retourne l'identifiant lié.
	 *
	 * @return l'identifiant lié.
	 */
	public int getIdentifier();

}
