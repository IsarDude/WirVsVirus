package com.wirVsVirus.shopping;

import android.content.Context;
import android.nfc.Tag;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StoreListAdapter extends ArrayAdapter<Store> {
    static final String TAG ="StoreListAdapter";
    private Context mContext;
    int mResource;
    public StoreListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Store> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getMarkttyp();
        String address = getItem(position).getStrasse();
        String number = getItem(position).getHausnr();
        int status = getItem(position).getActivity();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent, false );

        TextView marketName = (TextView) convertView.findViewById(R.id.name);
        TextView marketAddress = (TextView) convertView.findViewById(R.id.street);
        TextView marketNumber = (TextView) convertView.findViewById(R.id.number);
        TextView marketStatus = (TextView) convertView.findViewById(R.id.status);

        marketName.setText(name);
        marketAddress.setText(address);
        marketNumber.setText(number);
        marketStatus.setText(""+status);

        return convertView;

    }
}
