package com.dumv.tiemcattoc.api;

import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class RunSQL extends AsyncTask<Void,Void,Void> {
    String data;
    String sql;
    ApiRunSQL apiRunSQL;

    public RunSQL(String sql, ApiRunSQL apiRunSQL) {
        this.sql = sql;
        this.apiRunSQL = apiRunSQL;
        this.apiRunSQL.batDauChayDauSQL();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormEncodingBuilder()
                .add("sql",sql)
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.108.2/tiecmcattoc/runsql.php")
                .post(body)
                .build();
        data =null;
        try {
            Response response = client.newCall(request).execute();
            ResponseBody bodys = response.body();
            data = bodys.string();
        }catch (IOException e){
            data =null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(data==null){
           this.apiRunSQL.biloi();
        }else {
           if(data.equals("ok")){
               this.apiRunSQL.ketThuc();
           }else{
               this.apiRunSQL.biloi();
           }
        }
    }

}
