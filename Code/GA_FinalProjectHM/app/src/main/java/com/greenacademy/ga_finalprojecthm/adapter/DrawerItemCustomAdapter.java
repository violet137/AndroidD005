package com.greenacademy.ga_finalprojecthm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.model.DataIcon;

/**
 * Created by thepa on 10/30/2017.
 */

public class DrawerItemCustomAdapter extends ArrayAdapter<DataIcon> {

    Context mContext;
    int layoutResourceId;
    DataIcon data[] = null;

    public DrawerItemCustomAdapter(@NonNull Context context, int resource, @NonNull DataIcon[] data) {
        super(context, resource, data);
        this.mContext = context;
        this.layoutResourceId = resource;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        listItemView = inflater.inflate(layoutResourceId, parent, false);

        TextView tvTitle = listItemView.findViewById(R.id.tvTitle);

        DataIcon dataIcon = data[position];

        tvTitle.setText(dataIcon.getTitle());
        tvTitle.setCompoundDrawablesWithIntrinsicBounds(dataIcon.getIcon(), 0, 0, 0);

        return listItemView;
    }
}
