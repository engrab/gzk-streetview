package com.megaappsinc.gps.street.view.live.maps.navigation.route.adapters;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.megaappsinc.gps.street.view.live.maps.navigation.route.R;

import java.util.List;

public class VoiceNavigationAdapter extends RecyclerView.Adapter<VoiceNavigationAdapter.ViewHolder>
{
    private Activity context;
    private List<String> list;

    public VoiceNavigationAdapter(Activity context, List<String> list)
    {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.voice_navigation_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.text.setText(list.get(position));
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView text;

        ViewHolder(View itemView)
        {
            super(itemView);
            text = itemView.findViewById(R.id.texticon_new);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + list.get(getAdapterPosition())));
                    mapIntent.setPackage("com.google.android.apps.maps");
                    if (mapIntent.resolveActivity(context.getPackageManager()) != null)
                    {
                        context.startActivity(mapIntent);
                    }
                }
            });
        }
    }
}
