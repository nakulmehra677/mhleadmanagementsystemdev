package com.mudrahome.mhlms.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.mudrahome.mhlms.firebase.Firestore;
import com.mudrahome.mhlms.fragments.EditPhoneFragment;
import com.mudrahome.mhlms.interfaces.FirestoreInterfaces;
import com.mudrahome.mhlms.managers.ProfileManager;
import com.mudrahome.mhlms.R;

import com.mudrahome.mhlms.sharedPreferences.UserDataSharedPreference;

import java.util.HashSet;
import java.util.Set;

public class ProfileDetailsActivity extends BaseActivity {

    private TextView profileName, profileEmail, profilePhone, profileLocation, profileDesignation;
    private Button button;

    private UserDataSharedPreference preference;
    private String userDesignation = "";
    private String userlocation = "";
    private String strContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        profileLocation = findViewById(R.id.profileLocation);
        profilePhone = findViewById(R.id.profileContact);
        profileDesignation = findViewById(R.id.profileDesignation);

        button = findViewById(R.id.edit_number);

        preference = new UserDataSharedPreference(this);
        Set<String> userType = new HashSet<>();
        userType = preference.getUserType();

        for (String s : userType) {
            if (userDesignation == "") {
                userDesignation = s;
            } else {
                userDesignation += ", " + s;
            }
        }

        Set<String> locationset;
        locationset = preference.getLocation();

        Log.d("LocationProfile", locationset.toString());
        for (String s : locationset) {
            if (userlocation == "") {
                userlocation = s;
            } else {
                userlocation += "," + s;
            }
        }

        profileName.setText(preference.getUserName());
        profileEmail.setText(preference.getUserEmail());
        profileDesignation.setText(userDesignation);
        profilePhone.setText(preference.getContactNumber());
        profileLocation.setText(userlocation);

        button.setOnClickListener(view -> EditPhoneFragment.newInstance(preference.getContactNumber(), number -> {
            Firestore firestore = new Firestore();
            ProfileManager manager = new ProfileManager();
            strContact = number;
            firestore.updateUserDetails(new FirestoreInterfaces.OnUpdateUser() {
                @Override
                public void onSuccess() {
                    preference.setContactNumber(strContact);
                    profilePhone.setText(strContact);
                    showToastMessage(R.string.updated);
                }

                @Override
                public void onFail() {
                    showToastMessage(R.string.update_fail);
                }
            }, number, manager.getuId());
        }).show(getSupportFragmentManager(), "promo"));
    }
}