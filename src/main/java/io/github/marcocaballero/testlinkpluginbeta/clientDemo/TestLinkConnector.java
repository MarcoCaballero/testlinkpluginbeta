package io.github.marcocaballero.testlinkpluginbeta.clientDemo;

import java.util.Scanner;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.model.TestProject;
import io.github.marcocaballero.testlinkpluginbeta.TestLinkPlugin;

/**
 * TestLink Connector class.
 * 
 * @author Marco
 *
 */
public class TestLinkConnector {
	/**
	 * TestLink API object.
	 */
	static TestLinkAPI api = null;
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

	/* Menú Utils */
	static Scanner sc = new Scanner(System.in);
	static int selected = -1;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Integer projectId = -1;
		Integer planId = -1;
		Integer buildId = -1;
		Integer testId = -1;
		Integer statusId = -1;

		System.out.println("Welcome to the TestLinkPlugin CLI");
		do {
			System.out.println("Choose an option by typing one of the following numbers:\n");
			System.out.println("Display TestLink API Info -------------------->" + 1);
			System.out.println("Display TestLink API Ping (hello alias) ------>" + 2);
			System.out.println("Display TestLink API TestProjects ------------>" + 3);
			System.out.println("Display TestLink API TestProjects TestPlans -->" + 4);
			System.out.println("Display TestLink API TestPlans Builds -------->" + 5);
			System.out.println("Display TestLink API TestPlans TestCases ----->" + 6);
			System.out.println("Execute TestLink API TestCase ---------------->" + 7);
			System.out.println("Shut down ------------------------------------>" + -1 + "\n");

			System.out.println("Write an option: ");

			selected = sc.nextInt();

			switch (selected) {
			case 1:
				System.out.println("TestLink API RPC Info: " + plugin.getTestLinkInfo() + "\n\n\n");
				break;
			case 2:
				System.out.println("Api ping (alias to say hello): " + plugin.sayHello() + "\n\n\n");
				break;
			case 3:
				displaysProjects(); // Displays projects as a table
				break;
			case 4:
				displaysProjects(); // Displays projects as a table
				System.out.println("Select the project by typing the project ID: ");
				projectId = sc.nextInt();
				displaysPlans(projectId); // Displays projects plans as a tableplanId = 0;
				projectId = -1;
				break;
			case 5:
				displaysProjects(); // Displays projects as a table
				System.out.println("Select the project by typing the project ID: ");
				projectId = sc.nextInt();
				displaysPlans(projectId); // Displays projects plans as a table
				System.out.println("Select the plan by typing the plan ID: ");
				planId = sc.nextInt();
				displaysBuilds(planId); // Displays build for a plan as a table
				planId = -1;
				projectId = -1;
				break;
			case 6:
				displaysProjects(); // Displays projects as a table
				System.out.println("Select the project by typing the project ID: ");
				projectId = sc.nextInt();
				displaysPlans(projectId); // Displays projects plans as a table
				System.out.println("Select the plan by typing the plan ID: ");
				planId = sc.nextInt();
				displaysBuilds(planId); // Displays build for a plan as a table
				System.out.println("Select the build by typing the build ID: ");
				buildId = sc.nextInt();
				displayTestCases(planId, buildId);
				planId = -1;
				projectId = -1;
				buildId = -1;
				break;
			case 7:
				displaysProjects(); // Displays projects as a table
				System.out.println("Select the project by typing the project ID: ");
				projectId = sc.nextInt();
				displaysPlans(projectId); // Displays projects plans as a table
				System.out.println("Select the plan by typing the plan ID: ");
				planId = sc.nextInt();
				displaysBuilds(planId); // Displays build for a plan as a table
				System.out.println("Select the build by typing the build ID: ");
				buildId = sc.nextInt();
				displayTestCases(planId, buildId);
				System.out.println("Select the TestCase by typing the ID: ");
				testId = sc.nextInt();
				System.out.println("Select the Status by typing the ID: ");
				System.out.println("1.- BLOCKED");
				System.out.println("2.- FAILED");
				System.out.println("3.- NOT_RUN");
				System.out.println("4.- PASSED");
				statusId = sc.nextInt();
				plugin.executeTest(testId, planId, buildId, "Test notes ...", statusSelector(statusId));
				planId = -1;
				projectId = -1;
				buildId = -1;
				statusId = -1;
				testId = -1;
				break;
			case -1:
				System.out.println("Shutting down... TestLink Plugin 2017\n\n\n\n");
				break;

			default:
				System.out.println("INVALID OPTION !!");
				break;
			}
		} while (selected != -1);
	}

	private static ExecutionStatus statusSelector(Integer statusId) {
		switch (statusId) {
		case 1:
			return ExecutionStatus.BLOCKED;
		case 2:
			return ExecutionStatus.FAILED;
		case 3:
			return ExecutionStatus.NOT_RUN;
		case 4:
			return ExecutionStatus.PASSED;

		default:
			return ExecutionStatus.NOT_RUN;
		}
	}
	private static void displayTestCases(Integer planId, Integer buildId) {
		// TODO Auto-generated method stub
		System.out.println("__________________________________________________________________________________________________________________________________________________________");
		System.out.println("__________________________________________________________________Test Cases Table_______________________________________________________________________");
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%10s %30s %20s %50s", "TestCase ID", "TestCase NAME", "TestCase Status", "TestCase STEPS Actions");
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
		for (TestCase test : plugin.getTestCasesForTestPlan(planId, buildId)) {
			System.out.format("%10s %30s %20s %50s", test.getId(), test.getName(), test.getExecutionStatus().toString(), test.getSteps().get(0).getActions());
			System.out.println();
		}
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------\n\n\n");
	}

	private static void displaysProjects() {
		System.out.println("__________________________________________________________________________________________________________________________________________________________");
		System.out.println("_________________________________________________________________Test Project Table_______________________________________________________________________");
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%10s %30s %20s %10s %80s", "PROJECT ID", "PROJECT NAME", "ACTIVE?", "REQS?", "NOTES");
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
		for (TestProject project : plugin.getProjects()) {
			System.out.format("%10d %30s %20s %10s %80s", project.getId(), project.getName(), project.isActive().toString(), project.isEnableRequirements().toString(), project.getNotes());
			System.out.println();
		}
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------\n\n\n");
	}

	private static void displaysPlans(Integer projectId) {
		System.out.println("__________________________________________________________________________________________________________________________________________________________");
		System.out.println("__________________________________________________________________Test Plans Table_______________________________________________________________________");
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%10s %30s %20s %10s %20s", "PLAN ID", "PLAN NAME", "PROJECT NAME", "ACTIVE?", "PUBLIC?", "NOTES");
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
		for (TestPlan plan : plugin.getProjectTestPlans(projectId)) {
			System.out.format("%10d %30s %20s %10s %20s", plan.getId(), plan.getName(), plan.getProjectName(), plan.isActive().toString(), plan.isPublic().toString(), plan.getNotes());
			System.out.println();
		}
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------\n\n\n");
	}

	private static void displaysBuilds(Integer planId) {
		System.out.println("__________________________________________________________________________________________________________________________________________________________");
		System.out.println("_______________________________________________________________________Builds Table_______________________________________________________________________");
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%10s %30s %10s %50s", "BUILD ID", "BUILD NAME", "PLAN ID", "NOTES");
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
		for (Build build : plugin.getTestPlansBuilds(planId)) {
			System.out.format("%10d %30s %10d %50s", build.getId(), build.getName(), build.getTestPlanId(), build.getNotes());
			System.out.println();
		}
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------\n\n\n");
	}
}
