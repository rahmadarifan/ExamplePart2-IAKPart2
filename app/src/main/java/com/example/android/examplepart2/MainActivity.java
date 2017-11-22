package com.example.android.examplepart2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvPerson;
    private ArrayList<Person> personArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("List of Contacts");

        lvPerson = (ListView) findViewById(R.id.lv_name_person);
        personArrayList = createDummyPerson();
        final PersonAdapter personAdapter = new PersonAdapter(this, personArrayList);

        lvPerson.setAdapter(personAdapter);

        lvPerson.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Apakah anda yakin kontak " + personArrayList.get(position).getName() + " ingin di hapus?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                personArrayList.remove(position);
                                personAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Tidak", null)
                        .show();
                return false;
            }
        });

        lvPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("name", personArrayList.get(position).getName());
                intent.putExtra("phoneNumber", personArrayList.get(position).getPhoneNumber());
                intent.putExtra("email", personArrayList.get(position).getEmail());
                startActivity(intent);
            }
        });
    }

    private ArrayList<Person> createDummyPerson() {
        ArrayList<Person> personArrayList = new ArrayList<>();
        personArrayList.add(new Person("Si A", "081234567890", "siA@gmail.com"));

        Person siB = new Person();
        siB.setName("Si B");
        siB.setPhoneNumber("01234567890");
        siB.setEmail("siB@yahoo.com");
        personArrayList.add(siB);

        personArrayList.add(new Person("Si C", "0987654321", "siC@yahoo.co.id"));

        Person siD = new Person();
        siD.setName("Si D");
        siD.setPhoneNumber("08000000000");
        siD.setEmail("siD@gmail.co.id");
        personArrayList.add(siD);

        personArrayList.add(new Person("Si E", "081234567891", "siE@gmail.com"));
        personArrayList.add(new Person("Si F", "081234567892", "siF@gmail.com"));
        personArrayList.add(new Person("Si G", "081234567893", "siG@gmail.com"));
        personArrayList.add(new Person("Si H", "081234567894", "siH@gmail.com"));
        personArrayList.add(new Person("Si I", "081234567895", "siI@gmail.com"));
        personArrayList.add(new Person("Si J", "081234567896", "siJ@gmail.com"));

        return personArrayList;
    }
}
