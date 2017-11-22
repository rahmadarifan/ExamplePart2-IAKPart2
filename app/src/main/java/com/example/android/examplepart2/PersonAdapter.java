package com.example.android.examplepart2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ifan on 11/17/2017.
 */

public class PersonAdapter extends ArrayAdapter<Person> {

    public PersonAdapter(Context context, ArrayList<Person> personArrayList) {
        super(context, R.layout.item_person, personArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Person person = getItem(position);

        View v = convertView;

        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.item_person, null);
        }

        if (person != null) {
            TextView tvName = (TextView) v.findViewById(R.id.tv_name);
            TextView tvPhoneNumber = (TextView) v.findViewById(R.id.tv_phone_number);

            tvName.setText(person.getName());
            tvPhoneNumber.setText(person.getPhoneNumber());
        }
        return v;
    }
}
