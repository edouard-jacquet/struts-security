package com.fr.struts.plugins.security.core.configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.fr.struts.plugins.security.core.authentication.AuthenticationManager;
import com.fr.struts.plugins.security.core.authentication.ProviderManager;
import com.fr.struts.plugins.security.core.exception.SecurityClassNotFoundException;
import com.fr.struts.plugins.security.core.exception.SecurityInstantiationException;
import com.fr.struts.plugins.security.core.exception.SecurityReferenceNotFoundException;
import com.fr.struts.plugins.security.core.filter.Filter;
import com.fr.struts.plugins.security.core.filter.stack.DefaultFilterStack;
import com.fr.struts.plugins.security.core.filter.stack.FilterStack;

public final class SecurityConfigurator {

	private static final Configuration configuration = new Configuration();
	private static final Map<String, Object> beans = new HashMap<String, Object>();

	private SecurityConfigurator() {

	}

	/**
	 * Initialise la configuration pour le framework Struts Security.
	 *
	 * @param congigurationPath le fichier de configuration.
	 * @return la configuration.
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws SecurityInstantiationException
	 * @throws SecurityClassNotFoundException
	 * @throws XPathExpressionException
	 * @throws SecurityReferenceNotFoundException
	 */
	public static Configuration initialize(String congigurationPath) throws IOException, ParserConfigurationException,
			SAXException, XPathExpressionException, SecurityClassNotFoundException, SecurityInstantiationException,
			InvocationTargetException, NoSuchMethodException, SecurityReferenceNotFoundException {
		String xmlString = SecurityConfigurator.readFile(congigurationPath);
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		StringReader xmlStringReader = new StringReader(xmlString);
		InputSource inputSource = new InputSource(xmlStringReader);
		Document document = documentBuilder.parse(inputSource);
		Element root = document.getDocumentElement();

		SecurityConfigurator.defineBeans(root);

		SecurityConfigurator.configuration.setFilterStack(SecurityConfigurator.getFilterStack(root));
		SecurityConfigurator.configuration.setExceptionMappings(SecurityConfigurator.getExceptionMappings(root));
		SecurityConfigurator.configuration.setAuthenticationManager(SecurityConfigurator.getAuthenticationManager());

		return SecurityConfigurator.configuration;
	}

	/**
	 * Retourne la configuration définie.
	 *
	 * @return la configuration.
	 * @throws Exception
	 */
	public static Configuration getConfiguration() {
		return SecurityConfigurator.configuration;
	}

	/**
	 * Instancie les différents objets.
	 *
	 * @param root
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws SecurityInstantiationException
	 * @throws SecurityClassNotFoundException
	 * @throws XPathExpressionException
	 */
	private static void defineBeans(Element root) throws XPathExpressionException, SecurityClassNotFoundException,
			SecurityInstantiationException, InvocationTargetException, NoSuchMethodException {
		SecurityConfigurator.defineFilters(root);
	}

	/**
	 * Instancie les différents filtres.
	 *
	 * @param root le filtre.
	 * @throws XPathExpressionException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws SecurityClassNotFoundException
	 * @throws SecurityInstantiationException
	 */
	private static void defineFilters(Element root) throws XPathExpressionException, InvocationTargetException,
			NoSuchMethodException, SecurityClassNotFoundException, SecurityInstantiationException {
		XPath xPath = XPathFactory.newInstance().newXPath();

		NodeList declarations = (NodeList) xPath.evaluate("filters/filter", root, XPathConstants.NODESET);
		for (int declarationIndex = 0; declarationIndex < declarations.getLength(); declarationIndex++) {
			Element declaration = (Element) declarations.item(declarationIndex);
			String beanName = declaration.getAttribute("name");
			String className = declaration.getAttribute("class");

			try {
				Class<?> beanClass = Class.forName(className);
				Object bean = beanClass.getConstructor().newInstance();

				SecurityConfigurator.beans.put(beanName + "Filter", bean);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException exception) {
				throw new SecurityClassNotFoundException("<filter name='" + beanName + "' class='" + className + "'/>");
			}
		}
	}

	/**
	 * Défini la configuration des filtres, et de la pile.
	 *
	 * @param root le document XML de configuration.
	 * @return la pile.
	 * @throws XPathExpressionException
	 * @throws SecurityReferenceNotFoundException
	 */
	private static FilterStack getFilterStack(Element root)
			throws XPathExpressionException, SecurityReferenceNotFoundException {
		FilterStack filterStack = new DefaultFilterStack();

		XPath xPath = XPathFactory.newInstance().newXPath();

		NodeList references = (NodeList) xPath.evaluate("filter-stack/filter-reference", root, XPathConstants.NODESET);
		for (int index = 0; index < references.getLength(); index++) {
			Element reference = (Element) references.item(index);
			String name = reference.getAttribute("name");
			Filter filter = (Filter) SecurityConfigurator.beans.get(name + "Filter");

			if (filter == null) {
				throw new SecurityReferenceNotFoundException("Stack filter <reference name='" + name + "'/>");
			}

			filterStack.getStack().add(filter);
		}

		return filterStack;
	}

	/**
	 * Défini le mapping des exceptions de sécurité.
	 *
	 * @param root le document XML de configuration.
	 * @return le mapping.
	 * @throws XPathExpressionException
	 */
	private static Map<String, ExceptionConfiguration> getExceptionMappings(Element root)
			throws XPathExpressionException {
		Map<String, ExceptionConfiguration> exceptionMappings = new HashMap<String, ExceptionConfiguration>();

		XPath xPath = XPathFactory.newInstance().newXPath();

		NodeList exceptionMappingsConfiguration = (NodeList) xPath.evaluate("exception-mappings/exception", root,
				XPathConstants.NODESET);

		for (int index = 0; index < exceptionMappingsConfiguration.getLength(); index++) {
			Element exceptionMapping = (Element) exceptionMappingsConfiguration.item(index);
			String name = exceptionMapping.getAttribute("name");
			String className = exceptionMapping.getAttribute("class");
			String result = exceptionMapping.getAttribute("result");
			String status = exceptionMapping.getAttribute("status");

			ExceptionConfiguration exceptionConfiguration = new ExceptionConfiguration(name, className, result, status);
			exceptionMappings.put(className, exceptionConfiguration);
		}

		return exceptionMappings;
	}

	/**
	 * Défini le manager pour l'autentification.
	 *
	 * @return le manager.
	 */
	private static AuthenticationManager getAuthenticationManager() {
		return new ProviderManager();
	}

	/**
	 * Récupère le contenu d'un fichier dans les ressources
	 *
	 * @param fileName - chemin du fichier
	 * @return contenu du fichier (String)
	 * @throws IOException
	 */
	private static String readFile(String fileName) throws IOException {
		InputStream fileInputStream = SecurityConfigurator.class.getClassLoader().getResourceAsStream(fileName);
		BufferedReader fileBufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

		try {
			StringBuilder fileStringBuilder = new StringBuilder();
			String line;

			while ((line = fileBufferedReader.readLine()) != null) {
				fileStringBuilder.append(line);
			}

			return fileStringBuilder.toString();
		} finally {
			fileBufferedReader.close();
		}
	}

}
