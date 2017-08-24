package com.marcocaballero.exampleclient;

import com.marcocaballero.testlinkpluginbeta.TestLinkPlugin;

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
	 * @param args
	 */
	public static void main(String[] args) {

		TestLinkPlugin plugin = new TestLinkPlugin(url, devKey); // Instantiates the Testlink services

		System.out.println("Api ping:" + plugin.getProjectService().sayHello()); // Call the API to sayHello

		System.out.println("Api project:" + plugin.getProjectService().getProjects()[0].getName()); // Display first
																									// project name
	}
}
