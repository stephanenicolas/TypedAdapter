package com.github.typedadapter.ui;

import java.io.IOException;
import java.util.List;

import com.github.typedadapter.R;
import com.github.typedadapter.R.layout;
import com.github.typedadapter.db.RevolutionReader;
import com.github.typedadapter.model.Revolution;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SampleActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initRevolutionList();
    }

    private void initRevolutionList() {
        try {
            RevolutionReader reader = new RevolutionReader();
            List<Revolution> readRevolutions = reader.readRevolutions(this, "revolutions.txt");
            getListView().setAdapter(new RevolutionAdapter(this, readRevolutions));
        } catch (IOException e) {
            Toast.makeText(this, "Impossible to load data.", Toast.LENGTH_SHORT);
            e.printStackTrace();
        }
    }

}

