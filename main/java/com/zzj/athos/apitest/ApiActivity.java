package com.zzj.athos.apitest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ApiActivity extends Activity {

    TextView original_information;
    TextView final_information;
    EditText insert_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);
        original_information = findViewById(R.id.origin_information);
        final_information = findViewById(R.id.final_information);
        insert_code = findViewById(R.id.insert_code);
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadURL("https://www.sojson.com/open/api/weather/json.shtml?city="+insert_code.getText().toString());
            }
        });

    }

    public void ReadURL(String url)
    {
        new AsyncTask<String,Void,String>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onCancelled(String s) {
                super.onCancelled(s);
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
            }

            @Override
            protected String doInBackground(String... strings) {
                try {
                    URL url=new URL(strings[0]);
                    URLConnection urlConnection=url.openConnection();
                    InputStream is=urlConnection.getInputStream();
                    InputStreamReader isr=new InputStreamReader(is);
                    BufferedReader br=new BufferedReader(isr);
                    String line;
                    StringBuilder builder=new StringBuilder();
                    while((line=br.readLine())!=null)
                    {
                        builder.append(line);
                    }
                    is.close();
                    br.close();
                    System.out.println(builder.toString());
                    return builder.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                final_information.setText(s);
                super.onPostExecute(s);
            }
        }.execute(url);
    }
}