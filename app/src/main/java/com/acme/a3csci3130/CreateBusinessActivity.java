package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Creates a business object and saves it in the database using user-inputted info.
 */
public class CreateBusinessActivity extends Activity {

    private Button submitButton;
    private EditText businessNumberField, nameField, addressField;
    private Spinner primaryBusinessField, provinceField;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business_activity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        businessNumberField = (EditText) findViewById(R.id.business_number);
        nameField = (EditText) findViewById(R.id.name);
        addressField = (EditText) findViewById(R.id.address);

        //spinner adapters is linked to array and a layout
        //initializeSpinner(primaryBusinessField, (Spinner) findViewById(R.id.primary_business), R.array.primary_business_array);
        //initializeSpinner(provinceField, (Spinner) findViewById(R.id.province), R.array.province_array);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this,
                R.array.primary_business_array,
                android.R.layout.simple_spinner_item);
        //link the adapter to the spinner
        primaryBusinessField = (Spinner) findViewById(R.id.primary_business);
        primaryBusinessField.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(
                this,
                R.array.province_array,
                android.R.layout.simple_spinner_item);
        //link the adapter to the spinner
        provinceField = (Spinner) findViewById(R.id.province);
        provinceField.setAdapter(adapter);
    }

    //didn't work for some reason
    /*private void initializeSpinner(Spinner spinner, Spinner adaptedSpinner, int array){
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this,
                array,
                android.R.layout.simple_spinner_item);
        //link the adapter to the spinner
        spinner = adaptedSpinner;
        spinner.setAdapter(adapter);
    }*/

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String businessID = appState.firebaseReference.push().getKey();
        String businessNumber = businessNumberField.getText().toString();
        String name = nameField.getText().toString();
        String primaryBusiness = primaryBusinessField.getSelectedItem().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getSelectedItem().toString();
        Business business = new Business(businessID, businessNumber, name, primaryBusiness, address, province);

        appState.firebaseReference.child(businessID).setValue(business);

        finish();

    }
}
