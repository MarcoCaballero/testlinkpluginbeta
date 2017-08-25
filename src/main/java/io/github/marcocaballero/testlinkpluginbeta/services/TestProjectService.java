package io.github.marcocaballero.testlinkpluginbeta.services;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.model.TestProject;
import io.github.marcocaballero.testlinkpluginbeta.TestLinkPlugin;

public class TestProjectService {

	/**
	 * TestLink projects.
	 */
	private TestLinkAPI api;

	/**
	 * TestLink projects.
	 */
	private TestProject[] projects;

	/**
	 * @param plugin
	 */
	public TestProjectService(TestLinkPlugin plugin) {
		this.api = plugin.getApi();
		this.projects = api.getProjects();
	}

	/**
	 * @param plugin
	 * @param projects
	 */
	public TestProjectService(TestLinkPlugin plugin, TestLinkAPI api) {
		this.api = api;
		this.projects = api.getProjects();
		
	}

	/**
	 * @return test projects
	 */
	public TestProject[] getProjects() {
		return projects;
	}

}
