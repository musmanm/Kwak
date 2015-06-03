package com.example.muhammadmajeed.insultgenerator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.muhammadmajeed.insultgenerator.LiveInsultList;
import com.example.muhammadmajeed.insultgenerator.R;

public class A_Fantasy extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a__fantasy);

        Button insult = (Button) findViewById(R.id.insult);

        insult.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        TextView c_text = (TextView)findViewById(R.id.c_text);
                        c_text.setText(LiveInsultList.data.getInsult());
                    }
                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_a__fantasy, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
