package io.github.marcocaballero.testlinkpluginbeta;

import java.net.MalformedURLException;
import java.net.URL;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.model.TestProject;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;
/**
 * @author Marco
 *
 */
public class TestLinkPlugin {

	/* Fields */

	/**
	 * TestLink API object.
	 */
	private TestLinkAPI api = null;

	/**
	 * TestLink URL object.
	 */
	private URL testlinkURL = null;

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
	 */
	public TestLinkPlugin(String url, String devKey) {
		try {
			this.testlinkURL = new URL(url);
		} catch (MalformedURLException mue) {
			mue.printStackTrace(System.err);
			System.exit(-1);
		}

		try {
			this.api = new TestLinkAPI(testlinkURL, devKey);
		} catch (TestLinkAPIException te) {
			te.printStackTrace(System.err);
			System.exit(-1);
		}
	}
	
	/**
	 * @return test projects
	 */
	public TestProject[] getProjects() {
		return this.api.getProjects();
	}
	
	/**
	 * @return the API info
	 */
	public String getTestLinkInfo() {
		return this.api.about();
	}

	/**
	 * ping method is an alias for sayHello.
	 * 
	 * @return Hello message
	 */
	public String sayHello() {
		return this.api.ping();
	}

}
