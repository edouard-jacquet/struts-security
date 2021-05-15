package com.fr.struts.plugins.security.acl.components;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

import com.fr.struts.plugins.security.acl.ACL;
import com.fr.struts.plugins.security.acl.bean.AclPermission;
import com.fr.struts.plugins.security.acl.evaluator.AclPermissionEvaluator;
import com.fr.struts.plugins.security.core.authentication.Authentication;
import com.fr.struts.plugins.security.core.evaluator.PermissionEvaluator;

public final class Utils {

	private static final Pattern PATTERN_ACL = Pattern.compile("([\\w]+@[\\w\\(\\)]+=[\\w]+)");
	private static final PermissionEvaluator evaluator = new AclPermissionEvaluator();
	private static final ScriptEngineManager engineManager = new ScriptEngineManager();
	private static final ScriptEngine engine = Utils.engineManager.getEngineByName("JavaScript");

	private Utils() {
	}

	/**
	 * Evalue les permissions de l'utilisateur avec celles requises.
	 *
	 * @param authentication L'authentification liée à l'utilisateur.
	 * @param control        Les permissions requises (StrutsAclExpression).
	 * @return true si autorisé, false sinon.
	 */
	public static Boolean evaluate(Authentication authentication, String control) {
		Set<String> required = Utils.extractRequired(control);
		Map<String, Boolean> authorizations = Utils.evaluateRequired(authentication, required);
		return Utils.evaluateControl(control, authorizations);
	}

	/**
	 * Extrait les permissions requires.
	 *
	 * @param control Les permissions requises (StrutsAclExpression)
	 * @return les permissions.
	 */
	private static Set<String> extractRequired(String control) {
		Set<String> required = new HashSet<String>();

		Matcher matcher = Utils.PATTERN_ACL.matcher(control);

		while (matcher.find()) {
			required.add(matcher.group());
		}

		return required;
	}

	/**
	 * Evalue les permissions requises par rapport à l'utilisateur.
	 *
	 * @param authentication L'authentification liée à l'utilisateur.
	 * @param required       Les permissions requises.
	 * @return Les permissions évaluées.
	 */
	private static Map<String, Boolean> evaluateRequired(Authentication authentication, Set<String> required) {
		Map<String, Boolean> authorizations = new HashMap<String, Boolean>();

		for (String requirement : required) {
			AclPermission permission = Utils.convertStringToAcl(requirement);
			authorizations.put(requirement, Utils.evaluator.evaluate(authentication, permission));
		}

		return authorizations;
	}

	/**
	 * Convertie une permission de la StrutsAclExpression en AclPermission.
	 *
	 * @param required StrutsAclExpression.
	 * @return La permission.
	 */
	private static AclPermission convertStringToAcl(String required) {
		String[] permission = StringUtils.split(required, "@|=");
		return new AclPermission(permission[0], permission[1], ACL.valueOf(permission[2]));
	}

	/**
	 * Evalue la StrutsAclExpression
	 *
	 * @param control        Les permissions requises (StrutsAclExpression)
	 * @param authorizations Les permissions évaluées.
	 * @return true si autorisé, false sinon.
	 */
	private static Boolean evaluateControl(String control, Map<String, Boolean> authorizations) {
		String expression = Utils.generateExpression(control);
		expression = Utils.replaceAuthorization(expression, authorizations);
		expression = Utils.replaceOperator(expression);

		try {
			return (Boolean) Utils.engine.eval(expression);
		} catch (ScriptException exception) {
			return Boolean.FALSE;
		}
	}

	/**
	 * Génère l'expression à évaluer.
	 *
	 * @param control Les permissions requises (StrutsAclExpression)
	 * @return l'expression formatée.
	 */
	private static String generateExpression(String control) {
		String expression = control;

		expression = RegExUtils.replacePattern(expression, "\t|n|\r", " ");
		expression = RegExUtils.replacePattern(expression, "\\s{2,}", " ");
		expression = StringUtils.trim(expression);

		return expression;
	}

	/**
	 * Remplace les Permissions dans la StrutsAclExpression par le Boolean
	 * correspondant.
	 *
	 * @param expression     L'expression à évaluer.
	 * @param authorizations Les Permissions.
	 * @return l'expression formatée.
	 */
	private static String replaceAuthorization(String expression, Map<String, Boolean> authorizations) {
		String newExpression = expression;

		for (Map.Entry<String, Boolean> entry : authorizations.entrySet()) {
			newExpression = StringUtils.replace(newExpression, entry.getKey(), entry.getValue().toString());
		}

		return newExpression;
	}

	/**
	 * Remplace les Operators dans la StrutsAclExpression par leur équivalents.
	 *
	 * @param expression L'expression à évaluer.
	 * @return l'expression formatée.
	 */
	private static String replaceOperator(String expression) {
		String newExpression = expression;

		newExpression = StringUtils.replace(newExpression, "AND", "&&");
		newExpression = StringUtils.replace(newExpression, "OR", "||");

		return newExpression;
	}

}
