package com.example.ideasy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class List extends Activity {

    ArrayList names = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        build();

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                EditText text = (EditText) findViewById(R.id.editTextText2);
                String name = text.getText().toString();
                if(name=="" || name==null) name = "New project " + names.size();
                names.add(name);
                build();;
            }
        });
        ListView list1 = (ListView) findViewById(R.id.list);
//        list1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.textView, names));
        list1.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
//                        MainActivity();
                    }
                }
        );

    }



    public void build(){
        itemAdapter adapter = new itemAdapter(this, makeItem());
        ListView lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(adapter);
    }

    // Метод cоздания массива месяцев
    item[] makeItem() {
        item[] items = new item[names.size()];
// Сборка месяцев
        for (int i = 0; i < items.length; i++) {
            item item = new item();
            item.name = (String) names.get(i);
            items[i] = item;
        }
        return items;
    }

}