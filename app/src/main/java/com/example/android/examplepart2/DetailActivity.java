package com.example.android.examplepart2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    private TextView tvName, tvPhoneNumber, tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setTitle("Detail Contact");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvName = (TextView) findViewById(R.id.tv_name);
        tvPhoneNumber = (TextView) findViewById(R.id.tv_phone_number);
        tvEmail = (TextView) findViewById(R.id.tv_email);

        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String phoneNumber = i.getStringExtra("phoneNumber");
        String email = i.getStringExtra("email");
        final Person person = new Person(name, phoneNumber, email);
        display(person);
        tvPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + person.getPhoneNumber()));
                startActivity(callIntent);
            }
        });

        tvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", person.getEmail(), null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contoh Subjek");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Ini isi text atau body dari email");
                startActivity(Intent.createChooser(emailIntent, "Sent email..."));
            }
        });
    }

    private void display(Person person) {
        tvName.setText(person.getName());
        tvPhoneNumber.setText(person.getPhoneNumber());
        tvEmail.setText(person.getEmail());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
