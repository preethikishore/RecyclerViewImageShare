package com.allureinfosystems.recyclerviewimageshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    String[] url = {
            "https://en.wikipedia.org/wiki/Mourning_dove",
            "https://en.wikipedia.org/wiki/Macaw",
            "https://en.wikipedia.org/wiki/Peafowl",
    };
    String[] birds = {"Dove Bird","Macaw","Peacock",};

    private RecyclerView.Adapter DataAdapter;
    int [] birds_image = { R.drawable.dove,R.drawable.macaw,R.drawable.peacock};
    static ArrayList<HashMap<String, String>> dataItem;
    HashMap<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        dataItem = new ArrayList<>();

        for (int i = 0; i < birds.length ; i++) {

            map = new HashMap<String, String>();
            map.put("Bname", birds[i]);
            map.put("Bimage", birds_image[i] + "");
            map.put("Burl",url[i]);
            dataItem.add(map);
        }
        DataAdapter = new mDataAdapter(getApplicationContext(),dataItem);
        recyclerView.setAdapter(DataAdapter);

    }
}



