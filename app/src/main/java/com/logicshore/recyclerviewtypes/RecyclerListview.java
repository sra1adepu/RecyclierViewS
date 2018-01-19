package com.logicshore.recyclerviewtypes;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

public class RecyclerListview extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> master_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_listview);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        //Recycler View Defalut Supported View Is Vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        //For Horizontal
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
// here instead of false if  we use true the become last to first...
        recyclerView.setLayoutManager(linearLayoutManager);
        new TransferDetails().execute();
    }

    private class TransferDetails extends AsyncTask<String,String,String> {
        ProgressDialog pd = new ProgressDialog(RecyclerListview.this);
        String s;
        @Override
        protected void onPreExecute() {
            pd.setMessage("Please wait...");
            pd.show();
            pd.setCanceledOnTouchOutside(false);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpClient httpClient= new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("http://192.168.1.5:81/LogicShore.svc/DDRFTRANSFERDetails");
            try {
                HttpResponse response=httpClient.execute(httpGet);
                Log.d("Response",response.toString());
                s= EntityUtils.toString(response.getEntity());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return s;
        }

        @Override
        protected void onPostExecute(String s) {
            pd.dismiss();
            master_value= new ArrayList();
            try{
                JSONObject jsonObject =new JSONObject(s);
                JSONArray jsonArray=jsonObject.getJSONArray("Details");
                if(jsonArray.length()>0){
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject js=jsonArray.getJSONObject(i);
                        master_value.add(js.getString("MASTER_VALUE"));
                        Log.d("MasterValues",js.getString("MASTER_VALUE").toString());
                    }
                }
                RecyclerListviewAdapter recyclerListviewAdapter = new RecyclerListviewAdapter(RecyclerListview.this,master_value);
                recyclerView.setAdapter(recyclerListviewAdapter);


            }catch (Exception e){
                e.printStackTrace();
            }
            super.onPostExecute(s);
        }
    }
}
