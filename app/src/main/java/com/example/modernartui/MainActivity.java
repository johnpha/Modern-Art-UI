package com.example.modernartui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.net.Uri;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private SeekBar seekBar;
    int numPanel = 8;
    private LinearLayout[] panels = new LinearLayout[numPanel];
    private int[] originalColors = new int[numPanel];

    //String URL= "http://www.moma.org";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i = 0; i < numPanel; i++) {
            String panelID = "colorPanel"+ (i + 1);
            int resID = getResources().getIdentifier(panelID,"id", getPackageName());
            panels[i] = (LinearLayout) findViewById(resID);
        }

        seekBar = (SeekBar) findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(this);
        getOriginalColors();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.more_info){
            showMoreInfo();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        setPanelsColors(i);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void  showMoreInfo(){

        MoreInfoDialog moreInfoFragment = new MoreInfoDialog();
        moreInfoFragment.show(getFragmentManager(), "moreInfo");

    }

    private void setPanelsColors(int progressChanged) {
        for (int i = 0; i < numPanel; i++) {
            int color = originalColors[i];
            if (color != 0xffffffff && color != 0xff888888) {

                int invertColor = (0xFFFFFF - color) | 0xFF000000;
                int r = (color >> 16) & 0x000000FF;
                int g = (color >> 8) & 0x000000FF;
                int b = (color >> 0) & 0x000000FF;
                int invr = (invertColor >> 16) & 0x000000FF;
                int invg = (invertColor >> 8) & 0x000000FF;
                int invb = (invertColor >> 0) & 0x000000FF;

                int newColor = Color.rgb(
                        (int) (r + ((invr - r) * (progressChanged / 100f))),
                        (int) (g + ((invg - g) * (progressChanged / 100f))),
                        (int) (b + ((invb - b) * (progressChanged / 100f))));

                panels[i].setBackgroundColor(newColor);
            }
        }
    }
    private void getOriginalColors(){
        for (int i = 0; i < numPanel; i++) {
            originalColors[i] = ((ColorDrawable) panels[i].getBackground())
                    .getColor();
        }
    }
}