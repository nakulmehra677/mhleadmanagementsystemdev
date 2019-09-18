package com.mudrahome.mhlms.Managers;

import com.mudrahome.mhlms.models.LeadDetails;
import com.mudrahome.mhlms.models.TimeModel;

import java.util.ArrayList;

public class UpdateLead {
    private LeadDetails leadDetails;

    public LeadDetails getLeadDetails() {
        return leadDetails;
    }

    public void setLeadDetails(LeadDetails leadDetails) {
        this.leadDetails = leadDetails;
    }

    public UpdateLead(LeadDetails leadDetails) {
        this.leadDetails = leadDetails;
    }

    public void taleCaller(String customerName, String loanAmount, String contactNumber, ArrayList<String> telecallerReason) {
        leadDetails.setName(customerName);
        leadDetails.setLoanAmount(loanAmount);
        leadDetails.setContactNumber(contactNumber);
        leadDetails.setTelecallerRemarks(telecallerReason);
    }

    public void time() {
        TimeModel timeModel = getTimeModel();

        leadDetails.setDate(timeModel.getDate());
        leadDetails.setTime(timeModel.getTime());
        leadDetails.setTimeStamp(timeModel.getTimeStamp());
    }

    public void assignedToDetails(String assignedTo, String uid){
        leadDetails.setAssignedTo(assignedTo);
        leadDetails.setAssignedToUId(uid);

        TimeModel timeModel = getTimeModel();

        leadDetails.setAssignDate(timeModel.getDate());
        leadDetails.setAssignTime(timeModel.getTime());
    }

    private TimeModel getTimeModel(){
        TimeManager timeManager = new TimeManager();
        TimeModel timeModel = timeManager.getTime();
        return  timeModel;
    }
}