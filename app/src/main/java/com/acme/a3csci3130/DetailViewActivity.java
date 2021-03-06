package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Displays the details of a business with the option to edit and delete it.
 */
public class DetailViewActivity extends Activity {

    private Button submitButton;
    private EditText businessNumberField, nameField, addressField;
    private Spinner primaryBusinessField, provinceField;
    Business receivedBusinessInfo;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());
        receivedBusinessInfo = (Business)getIntent().getSerializableExtra("Business");

        submitButton = (Button) findViewById(R.id.submitButton);
        businessNumberField = (EditText) findViewById(R.id.business_number);
        nameField = (EditText) findViewById(R.id.name);
        addressField = (EditText) findViewById(R.id.address);

        //spinner adapters is linked to array and a layout
        //initializeSpinner(primaryBusinessField, (Spinner) findViewById(R.id.primary_business), R.array.primary_business_array);
        //initializeSpinner(provinceField, (Spinner) findViewById(R.id.province), R.array.province_array);
        ArrayAdapter businessAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.primary_business_array,
                android.R.layout.simple_spinner_item);
        //link the adapter to the spinner
        primaryBusinessField = (Spinner) findViewById(R.id.primary_business);
        primaryBusinessField.setAdapter(businessAdapter);

        ArrayAdapter provinceAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.province_array,
                android.R.layout.simple_spinner_item);
        //link the adapter to the spinner
        provinceField = (Spinner) findViewById(R.id.province);
        provinceField.setAdapter(provinceAdapter);

        if(receivedBusinessInfo != null){
            businessNumberField.setText(receivedBusinessInfo.businessNumber);
            nameField.setText(receivedBusinessInfo.name);

            primaryBusinessField.setSelection(businessAdapter.getPosition(receivedBusinessInfo.primaryBusiness), true);
            addressField.setText(receivedBusinessInfo.address);
            provinceField.setSelection(provinceAdapter.getPosition(receivedBusinessInfo.province), true);
        }
    }

    /**
     * Updates a business with the inputted data in the details view.
     * @param v The passed in view
     */
    public void updateBusiness(View v) {
        //each entry has a unique ID

        String businessID = receivedBusinessInfo.bid;
        String businessNumber = businessNumberField.getText().toString();
        String name = nameField.getText().toString();
        String primaryBusiness = primaryBusinessField.getSelectedItem().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getSelectedItem().toString();
        Business business = new Business(businessID, businessNumber, name, primaryBusiness, address, province);
        appState.firebaseReference.child(businessID).setValue(business);

        finish();

    }

    /**
     * Erases a business from the database
     * @param v The passed in View
     */
    public void eraseBusiness(View v)
    {
        String businessID = receivedBusinessInfo.bid;
        appState.firebaseReference.child(businessID).removeValue();
        finish();

    }
}
