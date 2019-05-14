package com.novisync.mypayment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Next extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_next );
        Toast.makeText( getApplicationContext()," Hello",Toast.LENGTH_LONG ).show();
    }
}
