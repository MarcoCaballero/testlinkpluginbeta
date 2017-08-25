package io.github.marcocaballero.testlinkpluginbeta;

import java.net.MalformedURLException;
import java.net.URL;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;
import io.github.marcocaballero.testlinkpluginbeta.services.TestLinkInfoService;
import io.github.marcocaballero.testlinkpluginbeta.services.TestProjectService;

/**
 * @author Marco
 *
 */
public class TestLinkPlugin {
	/**
	 * TestLink URL.
	 */
	private String url;

	/**
	 * TestLink User key.
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

	/* Services (non-javadoc) */

	/**
	 * Test project service
	 */
	private TestProjectService projectService;
	/**
	 * Test project service
	 */
	private TestLinkInfoService infoService;

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
	public TestLinkPlugin(String url, String devKey) {
		this.url = url;
		this.devKey = devKey;
		this.connect();

		/* Initialize services */
		this.projectService = new TestProjectService(this);
		this.infoService = new TestLinkInfoService(this);
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
	 * @return api
	 */
	public TestLinkAPI getApi() {
		return api;
	}

	/**
	 * @param api
	 */
	public void setApi(TestLinkAPI api) {
		this.api = api;
	}

	/**
	 * @return project service
	 */
	public TestProjectService getProjectService() {
		return projectService;
	}

	/**
	 * @return the info service
	 */
	public TestLinkInfoService getInfoService() {
		return infoService;
	}

	
	
}
