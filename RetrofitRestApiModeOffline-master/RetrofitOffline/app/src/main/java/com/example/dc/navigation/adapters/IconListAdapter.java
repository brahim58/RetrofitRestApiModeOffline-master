package com.example.dc.navigation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dc.navigation.R;
import com.example.dc.navigation.models.Icon;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class IconListAdapter extends RecyclerView.Adapter<IconListAdapter.ViewHolder>  {


    private ArrayList<Icon> itemList;
    private Context context;

    public IconListAdapter(Context context, ArrayList<Icon> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_icon, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Icon contact = (Icon) itemList.get(position);

        holder.txtIconTitle.setText(itemList.get(position).getName());
        holder.txtIconDate.setText(itemList.get(position).getDesc());
        ImageView imageView = holder.imageViewEvent;

               Picasso.with(context)
                .load(itemList.get(position).getUrlImage())
                .resize(300, 300)
                .centerCrop()
                .into(imageView);


        holder.rowId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

            }
        });
    }



    static class ViewHolder extends RecyclerView.ViewHolder   {
        LinearLayout rowId;
        TextView txtIconTitle;
        TextView txtIconDate;
        ImageView imageViewEvent;

        public ViewHolder(View itemView) {
            super(itemView);
            rowId =  itemView.findViewById(R.id.rowId);
            txtIconTitle =  itemView.findViewById(R.id.txt_event_title);
            txtIconDate =  itemView.findViewById(R.id.txt_event_date);
            imageViewEvent = itemView.findViewById(R.id.image_icon);
        }
    }
}
