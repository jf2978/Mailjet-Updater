package mailjet;

import java.util.Random;

import org.json.JSONObject;

public class UpdaterTester {
	private static final String[] SCENARIOS = {"BETA_DAY_3","BETA_DAY_7", "BETA_DAY_14"};

	public static void main(String[] args){
		try {
			// Pick Random Scenario
			Random random = new Random();
			int num = random.nextInt(3);
			String scenario = SCENARIOS[num];
			Updater updater = new Updater(scenario);
			System.out.println("\n\nScenario: " + scenario);
			
			// Set Updater Contacts, ListID + Action 
			JSONObject json = updater.getContacts();
			String listID = "1696956";
			json = updater.setListAction(json, "addnoforce");
			
			System.out.println(json.toString());
			System.out.println(updater.postToMailjet(json,listID));
			
		}catch (Exception e) {
			e.getMessage();
		}
	}
}
