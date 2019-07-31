package com.development.mhleadmanagementsystemdev.Fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.development.mhleadmanagementsystemdev.Managers.Alarm;
import com.development.mhleadmanagementsystemdev.Models.LeadDetails;
import com.development.mhleadmanagementsystemdev.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@SuppressLint("ValidFragment")
public class TelecallerEditLeadFragment extends AppCompatDialogFragment {
    private String strAssignedTo;
    private OnSubmitClickListener listener;
    private ArrayAdapter<CharSequence> assignedToAdapter;
    private Spinner assignedToSpinner;
    List salesPersonList = new ArrayList();

    private EditText customerName;
    private EditText loanAmount;
    private EditText contactNumber;
    private EditText telecallerReason;
    private Button datePickerButton;
    private Button timePickerButton;
    private TextView dateTextView;
    private TextView timeTextView;

    private LinearLayout reminderLayout;

    private int mYear, mMonth, mDay, mHour, mMinute;
    private int alarmYear, alarmMonth, alarmDay, alarmHour, alarmMinute;


    private LeadDetails leadDetails;


    public TelecallerEditLeadFragment(LeadDetails leadDetails, List arrayList, OnSubmitClickListener listener) {
        this.leadDetails = leadDetails;
        this.listener = listener;
        this.salesPersonList = arrayList;
    }

    public static TelecallerEditLeadFragment newInstance(
            LeadDetails leadDetails, List arrayList, OnSubmitClickListener listener) {

        TelecallerEditLeadFragment f = new TelecallerEditLeadFragment(leadDetails, arrayList, listener);
        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_telecaller_edit_lead_details_, null);

        customerName = v.findViewById(R.id.customer_name);
        loanAmount = v.findViewById(R.id.loan_amount);
        contactNumber = v.findViewById(R.id.contact_number);
        assignedToSpinner = v.findViewById(R.id.assign_to);
        datePickerButton = v.findViewById(R.id.date_picker);
        timePickerButton = v.findViewById(R.id.time_picker);
        dateTextView = v.findViewById(R.id.date);
        timeTextView = v.findViewById(R.id.time);
        reminderLayout = v.findViewById(R.id.reminder_layout);
        telecallerReason = v.findViewById(R.id.telecaller_reason);

        customerName.setText(leadDetails.getName());
        loanAmount.setText(leadDetails.getLoanAmount());
        contactNumber.setText(leadDetails.getContactNumber());
        telecallerReason.setText(leadDetails.getTelecallerRemarks());

        if (leadDetails.getSalesmanRemarks().equals("Customer Interested but Document Pending") ||
                leadDetails.getSalesmanRemarks().equals("Customer follow Up")) {
            reminderLayout.setVisibility(View.VISIBLE);
        } else {
            reminderLayout.setVisibility(View.GONE);

            dateTextView.setText("DD/MM/YYYY");
            timeTextView.setText("hh:mm");
        }

        // AssignedTo Spinner
        assignedToAdapter = new ArrayAdapter<CharSequence>(
                getContext(),
                android.R.layout.simple_spinner_item, salesPersonList);
        assignedToAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assignedToSpinner.setAdapter(assignedToAdapter);

        assignedToSpinner.setSelection(salesPersonList.indexOf(leadDetails.getAssignedTo()));

        assignedToSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                strAssignedTo = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                alarmDay = dayOfMonth;
                                alarmMonth = monthOfYear;
                                alarmYear = year;

                                dateTextView.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                datePickerDialog.show();
            }
        });

        timePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                alarmHour = hourOfDay;
                                alarmMinute = minute;

                                timeTextView.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });

        builder.setView(v)
                .setTitle("Edit details")
                .setPositiveButton("Make changes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!telecallerReason.getText().toString().isEmpty()) {
                            leadDetails.setName(customerName.getText().toString());
                            leadDetails.setLoanAmount(loanAmount.getText().toString());
                            leadDetails.setContactNumber(contactNumber.getText().toString());
                            leadDetails.setTelecallerRemarks(telecallerReason.getText().toString());


                            Alarm alarm = new Alarm(getContext());

                            if (leadDetails.getSalesmanRemarks().equals("Customer Interested but Document Pending") ||
                                    leadDetails.getSalesmanRemarks().equals("Customer follow Up")) {

                                if (!dateTextView.getText().toString().equals("DD/MM/YYYY") &&
                                        !timeTextView.getText().toString().equals("hh:mm")) {

                                    Calendar c = Calendar.getInstance();

                                    c.set(Calendar.DAY_OF_MONTH, alarmDay);
                                    c.set(Calendar.MONTH, alarmMonth);
                                    c.set(Calendar.YEAR, alarmYear);
                                    c.set(Calendar.HOUR_OF_DAY, alarmHour);
                                    c.set(Calendar.MINUTE, alarmMinute);
                                    c.set(Calendar.SECOND, 0);

                                    alarm.startAlarm(c, leadDetails.getName());
                                }

                            } else {
                                alarm.cancelAlarm(leadDetails.getName());
                            }
                            listener.onSubmitClicked(strAssignedTo, leadDetails);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setCancelable(false);

        return builder.create();
    }

    public interface OnSubmitClickListener {
        void onSubmitClicked(String dialogAssignedTo, LeadDetails leadDetails);
    }
}