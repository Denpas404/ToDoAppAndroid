package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText itemEditText;
    private Button addButton;
    private ListView listView;

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemEditText = findViewById(R.id.item_EditText);
        addButton = findViewById(R.id.add_Button);
        listView = findViewById(R.id.item_listView);

        items = FileHelper. readData(this);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        addButton.setOnClickListener(this);
        listView.setOnItemClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_Button:
                String itemEntered = itemEditText.getText().toString();
                adapter.add(itemEntered);
                itemEditText.setText("");

                FileHelper.writeData(items, this);

                Toast.makeText(this, "Item added", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        items.remove(i);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Item Removed", Toast.LENGTH_SHORT).show();
    }
}