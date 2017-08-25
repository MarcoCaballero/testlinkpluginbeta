package io.github.marcocaballero.testlinkpluginbeta.services;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import io.github.marcocaballero.testlinkpluginbeta.TestLinkPlugin;

public class TestLinkInfoService {

	/**
	 * TestLink projects.
	 */
	private TestLinkAPI api;

	/**
	 * @param plugin
	 */
	public TestLinkInfoService(TestLinkPlugin plugin) {
		this.api = plugin.getApi();
	}

	/**
	 * @return
	 */
	public String getTestLinkInfo() {
		return api.about();
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
