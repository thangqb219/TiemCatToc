package com.dumv.tiemcattoc.api;

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class GetDataFromTable extends AsyncTask<Void,Void,Void> {
    String data;
    String tableName;
    ApiGetDatFromTableRun run;

    public GetDataFromTable(String tableName, ApiGetDatFromTableRun run) {
        this.tableName = tableName;
        this.run = run;
        this.run.batDauLay();
    }

    @Override
    protected Void doInBackground(Void... voids) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://192.168.108.2/tiecmcattoc/getAllDataTable.php?name="+tableName)
                .build();

        data =null;
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            data = body.string();
        }catch (IOException e){
            data =null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(data==null){
            run.biloi();
        }else {
            run.ketThuc(data,tableName);
        }
    }

}
