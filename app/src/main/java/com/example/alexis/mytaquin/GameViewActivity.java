package com.example.alexis.mytaquin;

/**
 * Created by Alexis on 25/06/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Chronometer;
import android.widget.GridView;


public class GameViewActivity extends Activity implements AdapterView.OnItemClickListener{
    private GridView ImageGridView;
    private ImageAdapter2 IA = null;
    private  Chronometer simpleChronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_view);
        ImageGridView = (GridView) findViewById(R.id.GameView);
        simpleChronometer = (Chronometer) findViewById(R.id.simpleChronometer); // initiate a chronometer
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        Intent i = getIntent();
        int position = i.getExtras().getInt("position");
        int side = i.getExtras().getInt("side");

        ImageGridView.setNumColumns(side);
        ImageGridView.setColumnWidth(metrics.widthPixels/side);
        if (IA == null){
            IA = new ImageAdapter2(this,position,side);
        }
        ImageGridView.setAdapter(IA);
        ImageGridView.setOnItemClickListener(this);
        simpleChronometer.start();
    }
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            IA.move(position);
            ImageGridView.invalidateViews();
            ImageGridView.setAdapter(IA);
        }
}
