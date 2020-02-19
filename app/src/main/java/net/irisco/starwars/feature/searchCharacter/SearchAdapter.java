package net.irisco.starwars.feature.searchCharacter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.irisco.starwars.R;
import net.irisco.starwars.pojo.PeopleModel;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    List<PeopleModel> peopleModels = new ArrayList<>();
    ItemClickListener itemClick;

    public interface ItemClickListener {
        void onClick(String url);
    }

    public SearchAdapter(List<PeopleModel> people,ItemClickListener itemClick) {
        this.peopleModels = people;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {

        //TODO: Dont make a new object
        PeopleModel people = peopleModels.get(position);
        holder.addOnItemClikListener(people.getUrl());
        holder.txtName.setText(people.getName());
        holder.txtBirthDate.setText(people.getBirthYear());

    }

    @Override
    public int getItemCount() {
        return peopleModels.size();
    }

    //TODO: Use onBind
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtBirthDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtBirthDate = itemView.findViewById(R.id.txtBirthDate);
        }

        public void addOnItemClikListener(String url) {
            txtName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClick.onClick(url);
                }
            });
        }
    }
}
