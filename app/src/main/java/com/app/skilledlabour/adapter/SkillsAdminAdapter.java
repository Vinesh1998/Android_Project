package com.app.skilledlabour.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.app.skilledlabour.R;
import com.app.skilledlabour.models.Skill;
import java.util.Collections;
import java.util.List;

public class SkillsAdminAdapter extends RecyclerView.Adapter<SkillsAdminAdapter.MyBook> {
    List<Skill> list = Collections.emptyList();
    Context mContext;
    public SkillsAdminAdapter(List<Skill> list, Context context){
        this.list = list;
        this.mContext = context;
    }
    @NonNull
    @Override
    public MyBook onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.skill_item, parent, false);
        return new SkillsAdminAdapter.MyBook(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyBook holder, int position) {
        Skill skill = list.get(position);
        holder.skillTitle.setText(skill.getName());
        holder.skillCard.setOnClickListener(view->{

        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    static class MyBook extends RecyclerView.ViewHolder{
        // Here we hold the MyDoctorItems
        TextView skillTitle;
        ImageView skillItemImg;
        CardView skillCard;
        public MyBook(@NonNull View itemView) {
            super(itemView);
            skillCard = itemView.findViewById(R.id.skillCard);
            skillItemImg = itemView.findViewById(R.id.skillItemImg);
            skillTitle = itemView.findViewById(R.id.skillTitle);
        }
    }
}
