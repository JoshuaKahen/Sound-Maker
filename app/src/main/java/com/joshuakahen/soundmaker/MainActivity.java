package com.joshuakahen.soundmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        setContentView(R.layout.activity_main);

        int lengthOfThings = 6;

        final MediaPlayer[] tracks = new MediaPlayer[lengthOfThings];
        tracks[0] = MediaPlayer.create(this, R.raw.laughing);
        tracks[1] = MediaPlayer.create(this, R.raw.crying);
        tracks[2] = MediaPlayer.create(this, R.raw.yelling);
        tracks[3] = MediaPlayer.create(this, R.raw.whistling);
        tracks[4] = MediaPlayer.create(this, R.raw.snoring);
        tracks[5] = MediaPlayer.create(this, R.raw.robotnoises);

        final Drawable[] pics = new Drawable[lengthOfThings];
        pics[0] = getResources().getDrawable(R.drawable.custom_button);
        pics[1] = getResources().getDrawable(R.drawable.crying_button);
        pics[2] = getResources().getDrawable(R.drawable.yelling_button);
        pics[3] = getResources().getDrawable(R.drawable.whistling_button);
        pics[4] = getResources().getDrawable(R.drawable.snoring_button);
        pics[5] = getResources().getDrawable(R.drawable.robotnoises_button);

        final String[] faces = new String[lengthOfThings];
        faces[0] = "Laughing";
        faces[1] = "Crying";
        faces[2] = "Yelling";
        faces[3] = "Whistling";
        faces[4] = "Snoring";
        faces[5] = "Robot Noises";

        final Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(this,
                R.layout.custom_spinner, faces);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(ad);


        final Button button1 = (Button) findViewById(R.id.button1);
        Button btnprv = (Button) findViewById(R.id.btnprv);
        Button btnnxt = (Button) findViewById(R.id.btnnxt);
        final int[] trackNumber = {0};

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

                switch(item){
                    case "Laughing" :
                        trackNumber[0] = 0;
                        break;
                    case "Crying" :
                        trackNumber[0] = 1;
                        break;
                    case "Yelling" :
                        trackNumber[0] = 2;
                        break;
                    case "Whistling" :
                        trackNumber[0] = 3;
                        break;
                    case "Snoring" :
                        trackNumber[0] = 4;
                        break;
                    case "Robot Noises" :
                        trackNumber[0] = 5;
                        break;
                }

                button1.setBackground(pics[trackNumber[0]]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnnxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(trackNumber[0] != (tracks.length - 1)) {
                    trackNumber[0]++;
                    button1.setBackground(pics[trackNumber[0]]);
                    mySpinner.setSelection(trackNumber[0]);
                }
            }
        });

        btnprv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(trackNumber[0] != 0) {
                    trackNumber[0]--;
                    button1.setBackground(pics[trackNumber[0]]);
                    mySpinner.setSelection(trackNumber[0]);
                }
            }
        });

        button1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.setPressed(true);
                        tracks[trackNumber[0]].setLooping(true);
                        tracks[trackNumber[0]].start();
                        break;
                    case MotionEvent.ACTION_UP:
                        v.setPressed(false);
                        tracks[trackNumber[0]].seekTo(0);
                        tracks[trackNumber[0]].pause();
                        break;
                }
                return true;
            }

        });

    }
}