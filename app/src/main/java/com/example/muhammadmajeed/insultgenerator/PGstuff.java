package com.example.muhammadmajeed.insultgenerator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class PGstuff extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pgstuff);
    }

    public void onClickClassical (View view) {
        LiveInsultList.data.setType(TYPE.CLASSICAL);
        Intent i = new Intent (this, P_Classical.class);
        startActivity(i);
    }
    public void onClickHiphop (View view) {
        LiveInsultList.data.setType(TYPE.HIPHOP);
        Intent i = new Intent (this, P_Fantasy.class);
        startActivity(i);
    }

    public void onClickScifi (View view) {
        LiveInsultList.data.setType(TYPE.SCIFI);
        Intent i = new Intent (this, P_Scifi.class);
        startActivity(i);
    }

    public void onClickBritish (View view) {
        LiveInsultList.data.setType(TYPE.BRITISH);
        Intent i = new Intent (this, P_British.class);
        startActivity(i);
    }
    public void onClickNormal (View view) {
        LiveInsultList.data.setType(TYPE.NORMAL);
        Intent i = new Intent (this, P_Normal.class);
        startActivity(i);
    }
    public void onClickSmart (View view) {
        LiveInsultList.data.setType(TYPE.SMART);
        Intent i = new Intent (this, P_Smart.class);
        startActivity(i);
    }
    public void onClickMama (View view) {
        LiveInsultList.data.setType(TYPE.MAMA);
        Intent i = new Intent (this, P_Mama.class);
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pgstuff, menu);
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
