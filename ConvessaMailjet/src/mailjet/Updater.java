package mailjet;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.json.*;
import com.convessa.conversation.application.mastermind.model.*;

public class Updater {
	
	private UserInfoManager manager;
	private UserInfoProvider provider;
	private static final String BASIC_AUTH = null;
	private static final String[] PROPERTY_METHODS = {"getBetaDaysAgo","getSignupDaysAgo","getTimeZoneId", 
			"getFirstUsedDaysAgo", "getLastUsedDaysAgo", "getLocale"};
	
	public Updater(){
		this.manager = UserInfoManager.getInstance();
	}
	public Updater(String scenario){
		this.manager = UserInfoManager.getInstance();
		this.provider = UserInfoProvider.getInstance();
		this.provider.setScenario(Scenario.valueOf(scenario));
	}
	
	// Bulk contact update
	protected String postToMailjet(JSONObject contacts, String listID) throws IOException{
		HttpURLConnection http = (HttpURLConnection)
				(new URL("https://api.mailjet.com/v3/REST/contactslist/" + listID + "/managemanycontacts")).openConnection();
 
    	OutputStream out = setupHttpURLConnection(http);
    	out.write(contacts.toString().getBytes());
    	out.close();
    	http.disconnect();
    	
    	return http.getResponseMessage();
	}
	
	// Bulk contact update w/o particular contact list
	protected String postToMailjet(JSONObject contacts) throws IOException{
		HttpURLConnection http = (HttpURLConnection)
				(new URL("https://api.mailjet.com/v3/REST/contact/managemanycontacts")).openConnection();
		
		OutputStream out = setupHttpURLConnection(http);
    	out.write(contacts.toString().getBytes());
    	out.close();
    	http.disconnect();
    	
    	return http.getResponseMessage();
	}
	protected JSONObject getContacts() throws NoSuchMethodException, IllegalAccessException, 
										IllegalArgumentException, InvocationTargetException, JSONException{
		List<UserInfo> users = manager.getUsers();
		JSONArray contacts = new JSONArray();
		JSONObject result = new JSONObject();
		
		for(UserInfo user : users){
			JSONObject contact = new JSONObject();
			JSONObject properties = new JSONObject();
			
			/* Assumes all relevant properties have a "get<PROPERTY_NAME>" method */
			for(int i = 0; i < PROPERTY_METHODS.length; i++){
				Method method = user.getClass().getMethod(PROPERTY_METHODS[i]);
				properties.put(PROPERTY_METHODS[i].substring(3), method.invoke(user));
			}
			
			contact.put("Email", user.getEmail());
			contact.put("Properties", properties);
			contacts.put(contact);
		}
		result.put("Contacts", contacts);
		return result;
	}
	
	protected JSONObject setListAction(JSONObject contactsJSON, String action) throws JSONException{
		contactsJSON.put("Action", action);
		return contactsJSON;
	}
	
	private OutputStream setupHttpURLConnection(HttpURLConnection http) throws IOException{
		http.setDoOutput(true);
    	http.setUseCaches(false);
    	http.setRequestProperty("Content-Type","application/json; charset=UTF-8");
    	http.setRequestProperty("Authorization", BASIC_AUTH);
    	
    	return http.getOutputStream();
	}
}
