package io.github.marcocaballero.testlinkpluginbeta.clientDemo;

import br.eti.kinoshita.testlinkjavaapi.model.TestProject;
import io.github.marcocaballero.testlinkpluginbeta.TestLinkPlugin;

/**
 * API Connector class.
 * 
 * @author Marco
 *
 */
public class ApiConnector {
	/**
	 * TestLink-URL
	 */
	static String url = "http://localhost:80/testlink/lib/api/xmlrpc/v1/xmlrpc.php";

	/**
	 * User API Key
	 */
	static String devKey = "ee3bd5651d4901ea470565d2c4d745bf";

	/**
	 * Instantiates the plugin to conenct the API
	 */
	static TestLinkPlugin plugin = new TestLinkPlugin(url, devKey);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("TestLink API RPC Info: " + plugin.getInfoService().getTestLinkInfo() + "\n");

		System.out.println("Api ping (alias to say hello): " + plugin.getInfoService().sayHello() + "\n"); // Call the API to

		displaysProjects(); // sayHello
	}
	private static void displaysProjects() {
		System.out.println("__________________________________________________________________________________________________________________________________________________________");
		System.out.println("_________________________________________________________________Test Project Table_______________________________________________________________________");
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%10s %30s %20s %10s %80s", "PROJECT ID", "PROJECT NAME", "ACTIVE?", "REQS?", "NOTES");
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
		for (TestProject project : plugin.getProjectService().getProjects()) {
			System.out.format("%10d %30s %20s %10s %80s", project.getId(), project.getName(), project.isActive().toString(), project.isEnableRequirements().toString(), project.getNotes());
			System.out.println();
		}
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
	}
}
