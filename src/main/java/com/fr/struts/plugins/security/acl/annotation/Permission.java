package com.fr.struts.plugins.security.acl.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fr.struts.plugins.security.acl.ACL;

/**
 * Vérifie si l'utilisateur possède la permission nécessaire, avec le bon niveau
 * ACL.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Permission {

	/**
	 * Le domaine (groupe) du droit.
	 *
	 * @return le domaine
	 */
	String domain();

	/**
	 * Le nom du droit.
	 *
	 * @return le nomn
	 */
	String right();

	/**
	 * Le niveau ACL nécessaire.
	 *
	 * @return le niveau
	 */
	ACL value() default ACL.NONE;

}
