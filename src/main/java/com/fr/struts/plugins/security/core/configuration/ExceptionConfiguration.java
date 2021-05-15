package com.fr.struts.plugins.security.core.configuration;

public class ExceptionConfiguration {

	private String name;
	private String className;
	private String result;
	private String status;

	public ExceptionConfiguration(String name, String className, String result, String status) {
		this.name = name;
		this.className = className;
		this.result = result;
		this.status = status;
	}

	/**
	 * Retourne le nom associé à l'exception.
	 *
	 * @return le nom.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Retourne la classe associée à l'exception.
	 *
	 * @return la classe.
	 */
	public String getClassName() {
		return this.className;
	}

	/**
	 * Retourne le résultat à renvoyer si l'exception est levée.
	 *
	 * @return le résultat.
	 */
	public String getResult() {
		return this.result;
	}

	/**
	 * Retourne le code "HTTP" à renvoyer si l'exception est levée.
	 *
	 * @return le code.
	 */
	public String getStatus() {
		return this.status;
	}

}
