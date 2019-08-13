package com.mudrahome.MHLMS.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.mudrahome.MHLMS.Fragments.LeadDetailsFragment;
import com.mudrahome.MHLMS.Models.LeadDetails;
import com.mudrahome.MHLMS.R;
import com.mudrahome.MHLMS.ViewHolders.LeadListViewHolder;

import java.util.ArrayList;
import java.util.List;


public class LeadsItemAdapter extends RecyclerView.Adapter<LeadListViewHolder> implements Filterable {
    private List<LeadDetails> leads;
    private Context context;
    private String currentUserType;
    private List<LeadDetails> allLeads;

    public LeadsItemAdapter(List<LeadDetails> leadDetails, Context context, String currentUserType) {
        this.leads = leadDetails;
        this.context = context;
        this.currentUserType = currentUserType;
    }

    @NonNull
    @Override
    public LeadListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lead_list_item, parent, false);
        return new LeadListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LeadListViewHolder holder, int i) {
        final LeadDetails model = leads.get(i);

        holder.name.setText(model.getName());
        holder.loanAmount.setText(model.getLoanAmount());
        holder.loanType.setText(model.getLoanType());
        holder.location.setText(model.getLocation());

        holder.status.setText(model.getStatus());
        holder.date.setText(model.getDate());

        if (currentUserType.equals(context.getString(R.string.telecaller)))
            holder.assignedTo.setText(model.getAssignedTo());
        else if (currentUserType.equals(context.getString(R.string.salesman))) {
            holder.assignText.setText("Assginer");
            holder.assignedTo.setText(model.getAssigner());
        } else
            holder.assignLayout.setVisibility(View.GONE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LeadDetailsFragment leadDetailsFragment = new LeadDetailsFragment(model, context);
                leadDetailsFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), "f");
            }
        });
    }

    @Override
    public int getItemCount() {
        allLeads = new ArrayList<>(leads);
        return leads.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<LeadDetails> filterList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filterList.addAll(leads);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (LeadDetails item : leads) {
                    if (item.getName().toLowerCase().contains(filterPattern) ||
                            item.getAssignedTo().toLowerCase().contains(filterPattern) ||
                            item.getAssigner().toLowerCase().contains(filterPattern) ||
                            item.getLocation().toLowerCase().contains(filterPattern) ||
                            item.getStatus().toLowerCase().contains(filterPattern)) {
                        filterList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filterList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            leads.clear();
            if (results.values != null)
                leads.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}