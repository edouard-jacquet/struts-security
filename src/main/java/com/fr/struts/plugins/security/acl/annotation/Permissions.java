package com.fr.struts.plugins.security.acl.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Vérifie les droits d'accès.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Permissions {

	/**
	 * La liste des permissions nécessaires.
	 *
	 * @return les permissions
	 */
	Permission[] value();

}
