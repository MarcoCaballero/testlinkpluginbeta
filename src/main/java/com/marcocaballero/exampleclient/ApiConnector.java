package com.marcocaballero.exampleclient;

import com.marcocaballero.testlinkpluginbeta.ApiObjectGenerator;

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

		ApiObjectGenerator objectApi = new ApiObjectGenerator(url, devKey, null, null); // Instantiates the Testlink services

		System.out.println("Api ping:" + objectApi.sayHello()); // Call the API to sayHello

		System.out.println("Api project:" + objectApi.getProjects()[0].getName()); // Display first project name
	}
}
