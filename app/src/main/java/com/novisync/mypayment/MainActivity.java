package com.novisync.mypayment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.novisync.mypayment.webservices.API;
import com.novisync.mypayment.webservices.RestClient;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
String s;
    PayResponse payResponse;
    TextView done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


        ImageView cash = (ImageView) findViewById( R.id.cash );
        ImageView googlepay = (ImageView) findViewById( R.id.googlepay );
        ImageView phonepay = (ImageView) findViewById( R.id.phonepay );
       done=(TextView)findViewById( R.id.done );
       TextView back=(TextView)findViewById( R.id.back ) ;
        done.setVisibility(View.GONE);

       back.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent( getApplicationContext(), Next.class );
               startActivity( i );
           }
       } );

        cash.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = "cash";
                paymentMode();
                done.setVisibility(View.VISIBLE);
                done();

            }
        } );
        googlepay.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = "googlepay";
                paymentMode();
                done.setVisibility(View.VISIBLE);
                done();


            }
        } );
        phonepay.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = "phonepay";
                paymentMode();
                done.setVisibility(View.VISIBLE);
                done();


            }
        } );


    }
    public  void done(){
       done.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent( getApplicationContext(), Next.class );
               startActivity( i );
           }
       } );

    }


    public void paymentMode() {

        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            RestClient.client = new Retrofit.Builder().baseUrl( RestClient.baseUrl ).
                    client( okHttpClient ).
                    addConverterFactory( GsonConverterFactory
                            .create() ).build();
            API api = RestClient.client.create( API.class );

            Log.d( "TAG", "onClick: " +s);

            Call<PayResponse> call=api.payment( s );
            Log.d( "TAG", "onClick: 45" +s);
            call.enqueue( new Callback<PayResponse>() {
                @Override
                public void onResponse(Call<PayResponse> call,
                                       Response<PayResponse> response) {
                    Log.d( "TAG", "onClick: 78" +s);
                    payResponse=new PayResponse();
                    payResponse= response.body();
                    Log.d( "TAG", "onClick:val " +payResponse.getStatus());
                    if (payResponse.getStatus().equalsIgnoreCase( "true" )) {
                        Toast.makeText( MainActivity.this, "OTP has been sent", Toast.LENGTH_SHORT ).show();
                    } else {
                        Toast.makeText( MainActivity.this, "Failed", Toast.LENGTH_SHORT ).show();
                    }
                }

                @Override
                public void onFailure(Call<PayResponse> call, Throwable t) {


                    Log.d( " TAG", "onFailure:fail" + t );
                    //
                }


            } );


        } catch (Exception e) {
            System.out.print( "Exception e" + e );

        }
    }
}











