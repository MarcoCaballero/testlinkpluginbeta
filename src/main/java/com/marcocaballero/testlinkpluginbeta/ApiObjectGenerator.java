/**
 * 
 */
package com.marcocaballero.testlinkpluginbeta;

import java.net.MalformedURLException;
import java.net.URL;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.model.TestProject;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

/**
 * API Object Generator class.
 *
 * @author Marco
 *
 */
public class ApiObjectGenerator {

	/**
	 * TestLink URL.
	 */
	private String url;

	/**
	 * TestLink User devkey.
	 */
	private String devKey;

	/**
	 * TestLink API object.
	 */
	private TestLinkAPI api;

	/**
	 * TestLink URL object.
	 */
	private URL testlinkURL;

	/**
	 * TestLink projects.
	 */
	private TestProject[] projects;

	/**
	 * Constructor with parameters.
	 * 
	 * <p>
	 * Instantiates TestLink API. It also checks the devKey to connect and throws a TestLinkAPIException if it is
	 * invalid.
	 * </p>
	 * 
	 * @param url
	 * @param devKey
	 * @param api
	 * @param testlinkURL
	 */
	public ApiObjectGenerator(String url, String devKey, TestLinkAPI api, URL testlinkURL) {
		this.url = url;
		this.devKey = devKey;
		this.api = api;
		this.testlinkURL = testlinkURL;
		this.connect();
		this.getProjectsfromApi();
	}

	/**
	 * ping method is an alias for sayHello.
	 * 
	 * @return Hello message
	 */
	public String sayHello() {
		return api.ping();
	}

	/**
	 * 
	 * Creates a URL object from the String representation of the TestLink URL, and Instantiates TestLink services on
	 * the API object
	 * 
	 */
	private void connect() {
		try {
			testlinkURL = new URL(url);
		} catch (MalformedURLException mue) {
			mue.printStackTrace(System.err);
			System.exit(-1);
		}

		try {
			api = new TestLinkAPI(testlinkURL, devKey);
		} catch (TestLinkAPIException te) {
			te.printStackTrace(System.err);
			System.exit(-1);
		}
	}

	/**
	 * Retrieves all Test Projects from TestLink.
	 * 
	 * @return Hello message
	 */
	private void getProjectsfromApi() {
		this.projects = api.getProjects();
	}

	/**
	 * @return TestLink URL (String)
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url TestLink URL (String)
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return user API key
	 */
	public String getDevKey() {
		return devKey;
	}

	/**
	 * @param devKey user API key
	 */
	public void setDevKey(String devKey) {
		this.devKey = devKey;
	}

	/**
	 * @return TestLink API object
	 */
	public TestLinkAPI getApi() {
		return api;
	}

	/**
	 * @param api TestLink API object
	 */
	public void setApi(TestLinkAPI api) {
		this.api = api;
	}

	/**
	 * @return TestLink URL object
	 */
	public URL getTestlinkURL() {
		return testlinkURL;
	}

	/**
	 * @param testlinkURL TestLink URL object
	 */
	public void setTestlinkURL(URL testlinkURL) {
		this.testlinkURL = testlinkURL;
	}

	/**
	 * @return test projects 
	 */
	public TestProject[] getProjects() {
		return projects;
	}

	/**
	 * @param projects test projects
	 */
	public void setProjects(TestProject[] projects) {
		this.projects = projects;
	}

}
