package com.development.mhleadmanagementsystemdev.Helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.development.mhleadmanagementsystemdev.Interfaces.OnFetchLeadListListener;
import com.development.mhleadmanagementsystemdev.Interfaces.OnFetchSalesPersonListListener;
import com.development.mhleadmanagementsystemdev.Interfaces.OnFetchUserDetailsListener;
import com.development.mhleadmanagementsystemdev.Interfaces.OnUpdateLeadListener;
import com.development.mhleadmanagementsystemdev.Interfaces.OnUploadCustomerDetailsListener;
import com.development.mhleadmanagementsystemdev.Models.LeadDetails;
import com.development.mhleadmanagementsystemdev.Models.UserDetails;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    Context context;
    private long nodes = 0;
    private boolean isAdmin = false;

    public FirebaseDatabaseHelper() {

    }

    public FirebaseDatabaseHelper(Context context) {
        this.context = context;
    }

    public void uploadCustomerDetails(OnUploadCustomerDetailsListener onUploadCustomerdetails,
                                      LeadDetails leadDetails) {

        Log.i("No of Nodes", "Uploading");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("leadList").push();

        String key = myRef.getKey();
        leadDetails.setKey(key);
        myRef.setValue(leadDetails);
        onUploadCustomerdetails.onDataUploaded();
    }

    public void fetchSalesPersonsByLocation(
            final OnFetchSalesPersonListListener onFetchSalesPersonListListener, String location) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("userList");
        Log.i("Users", "Fetching Users...");

        final List<UserDetails> salesPersonList = new ArrayList<>();
        final List<String> salesPersonNameList = new ArrayList<>();

        myRef.orderByChild("location").equalTo(location)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            UserDetails userDetails = snapshot.getValue(UserDetails.class);

                            if (userDetails.getUserType().equals("Salesman")) {
                                salesPersonList.add(userDetails);
                                salesPersonNameList.add(userDetails.getUserName());
                            }
                        }
                        onFetchSalesPersonListListener.onListFetched(salesPersonList, salesPersonNameList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void updateLeadDetails(OnUpdateLeadListener onUpdateLeadListener, LeadDetails updateLead) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("leadList").child(updateLead.getKey());
        myRef.setValue(updateLead);

        onUpdateLeadListener.onLeadUpdated();

    }

    public void getUserDetails(final OnFetchUserDetailsListener listener, String uId) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("userList");

        myRef.orderByChild("uId").equalTo(uId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            UserDetails userDetails = snapshot.getValue(UserDetails.class);
                            listener.onSuccess(userDetails);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void getLeadList(final OnFetchLeadListListener listener) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("leadList");
       // Query query = databaseReference.orderByKey().startAt(key).limitToLast(20);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                LeadDetails leadDetails = dataSnapshot.getValue(LeadDetails.class);
                listener.onSuccess(leadDetails);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("ERROR", "getChapterList(), onCancelled", new Exception(databaseError.getMessage()));
            }
        });
    }
}