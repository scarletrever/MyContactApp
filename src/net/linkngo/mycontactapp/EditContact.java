package net.linkngo.mycontactapp;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class EditContact extends Activity{

	// Allows access to data in the EditTexts

	EditText firstName;
	EditText lastName;
	EditText phoneNumber;
	EditText emailAddress;
	EditText homeAddress;

	// The database tool class

	DBTools dbTools = new DBTools(this);

	// Sets up everything when the Activity is displayed

	public void onCreate(Bundle savedInstanceState) {


Log.d("EDIT", "on Create");
		// Get saved data if there is any

		super.onCreate(savedInstanceState);

Log.d("EDIT", "super");
		// Designate that edit_contact.xml is the interface used

		setContentView(R.layout.edit_contact);

		// Get the EditText objects

		firstName = (EditText) findViewById(R.id.firstNameEditText);
		lastName = (EditText) findViewById(R.id.lastNameEditText);
		phoneNumber = (EditText) findViewById(R.id.phoneNumberEditText);
		emailAddress = (EditText) findViewById(R.id.emailAddressEditText);
		homeAddress = (EditText) findViewById(R.id.homeAddressEditText);

		// Intent defines that an operation will be performed

Log.d("EDIT", "get text");
		Intent theIntent = getIntent();

Log.d("EDIT", "intent");
		// Get the extended data provided to this activity
		// putExtra("contactId", contactIdValue); in MainActivity
		// will pass contactId here

		String contactId = theIntent.getStringExtra("contactId");

Log.d("EDIT", "contactID");
Log.d("EDIT", contactId);
		// Get the HashMap of data associated with the contactId

		HashMap<String, String> contactList = dbTools.getContactInfo(contactId);

Log.d("EDIT", "contactList");
		// Make sure there is something in the contactList

		if(contactList.size()!=0) {
			// Put the values in the EditText boxes
			if(contactList.containsKey("firstName")){
				Log.d("EDIT", "firstName KEY FOUND");
			String t = contactList.get("firstName");
			firstName.setText(t);
			Log.d("EDIT", t);
			}
			Log.d("EDIT", "firstName skipped");
			lastName.setText(contactList.get("lastName"));
			Log.d("EDIT", "lastName done");
			phoneNumber.setText(contactList.get("phoneNumber"));
			emailAddress.setText(contactList.get("emailAddress"));
			homeAddress.setText(contactList.get("homeAddress"));

Log.d("EDIT", "values set");
Log.d("EDIT", "on Create over");
		}
	}
	public void editContact(View view) {
		HashMap<String, String> queryValuesMap =  new  HashMap<String, String>();

		// Get the EditText objects

		firstName = (EditText) findViewById(R.id.firstNameEditText);
		lastName = (EditText) findViewById(R.id.lastNameEditText);
		phoneNumber = (EditText) findViewById(R.id.phoneNumberEditText);
		emailAddress = (EditText) findViewById(R.id.emailAddressEditText);
		homeAddress = (EditText) findViewById(R.id.homeAddressEditText);

		// Intent defines that an operation will be performed

		Intent theIntent = getIntent();

		// Get the extended data provided to this activity
		// putExtra("contactId", contactIdValue); in MainActivity
		// will pass contactId here

		String contactId = theIntent.getStringExtra("contactId");

		// Put the values in the EditTexts in the HashMap
		
		queryValuesMap.put("contactId", contactId);
		queryValuesMap.put("firstName", firstName.getText().toString());
		queryValuesMap.put("lastName", lastName.getText().toString());
		queryValuesMap.put("phoneNumber", phoneNumber.getText().toString());
		queryValuesMap.put("emailAddress", emailAddress.getText().toString());
		queryValuesMap.put("homeAddress", homeAddress.getText().toString());

		// Send the HashMap to update the data in the database
		
		dbTools.updateContact(queryValuesMap);
		
		// Call for MainActivity
		
		this.callMainActivity(view);

	}
	public void removeContact(View view) {
		Intent theIntent = getIntent();
		String contactId = theIntent.getStringExtra("contactId");
		
		// Call for the contact with the contactId provided
		// to be deleted
		
		dbTools.deleteContact(contactId);
		
		// Call for MainActivity
		
		this.callMainActivity(view);

	}
	
	// Calls for a switch to the MainActivity
	
	public void callMainActivity(View view) {
		
		// getApplication returns an Application object which allows 
		// you to manage your application and respond to different actions.
		// It returns an Application object which extends Context.
		
		// A Context provides information on the environment your application 
		// is currently running in. It provides services like how tp obtain 
		// access to a database and preferences.
		
		// Google says a Context is an entity that represents various 
		// environment data. It provides access to local files, databases, 
		// class loaders associated to the environment, services including 
		// system-level services, and more.
		
		// The following Intent states that you want to switch to a new 
		// Activity being the MainActivity
		
		Intent objIntent = new Intent(getApplication(), MainActivity.class);
		startActivity(objIntent);
	}
}