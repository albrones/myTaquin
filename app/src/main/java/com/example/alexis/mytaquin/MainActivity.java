package com.example.alexis.mytaquin;

/**
 * Created by Alexis on 25/06/2017.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView instructions = (TextView) findViewById(R.id.instructions);
        instructions.setText(instructions.getText());
        final Spinner spinnerImageWidth = (Spinner) findViewById(R.id.spinnerImageWidth);
        ArrayAdapter<CharSequence> mySpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.imageWidth, android.R.layout.simple_spinner_item);
        mySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerImageWidth.setAdapter(mySpinnerAdapter);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        final ImageAdapter myImageAdapter = new ImageAdapter(this);
        gridview.setAdapter(myImageAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                String imageWidth =  spinnerImageWidth.getSelectedItem().toString();
                int side;
                if (imageWidth.equals("5x5")){
                    side= 5;

                }
                else if (imageWidth.equals("4x4")){
                    side= 4;
                }
                else {
                    side = 3;
                }

                Intent i = new Intent(getApplicationContext(), GameViewActivity.class);
                i.putExtra("position", position);
                i.putExtra("side", side);
                startActivity(i);
            }
        });
    }
}
