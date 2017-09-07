package io.github.marcocaballero.testlinkpluginbeta;

import java.net.MalformedURLException;
import java.net.URL;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
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

	/**
	 * execute tests
	 * 
	 */
	public void executeTest() {
//		this.api.reportTCResult(testCaseId, testCaseExternalId, testPlanId, status, buildId, buildName, notes, guess, bugId, platformId, platformName, customFields, overwrite)
	}
	
	/**
	 * @return Project Test plans
	 */
	public TestPlan[] getProjectTestPlans(Integer projectId) {
		return this.api.getProjectTestPlans(projectId);
	}
	
	/**
	 * @return Test plans builds
	 */
	public Build[] getTestPlansBuilds(Integer testPlanId) {
		return this.api.getBuildsForTestPlan(testPlanId);
	}
	
	public TestCase[] getTestCasesForTestPlan(Integer testPlanId, Integer buildId){
		return this.api.getTestCasesForTestPlan(testPlanId, null, buildId, null, null, null, null, null, null, true, null);
	}
	

}
