package com.example.tixigo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tixigo.modem.Train;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class List_Activity extends AppCompatActivity {

    private static  final String API_KEY="55a5721fd9msh0a7cebae3e4f66ep17f4abjsnc60fd3c6f976";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_);

        new ListDown().execute(API_KEY);
    }
    public class ListDown extends AsyncTask<String,Void, List<Train>>{
        @Override
        protected void onPreExecute(){
            Toast.makeText(getApplicationContext(), "Loading List of Trains", Toast.LENGTH_LONG).show();
        }

        @Override
        protected List<Train> doInBackground(String... values) {
            String apikey= values[0];
            List<Train> trains= new ArrayList<>();

            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"search\":\"Rajdhani\"}");
            Request request = new Request.Builder()
                    .url("https://trains.p.rapidapi.com/")
                    .post(body)
                    .addHeader("x-rapidapi-host", "trains.p.rapidapi.com")
                    .addHeader("x-rapidapi-key",apikey)
                    .addHeader("content-type", "application/json")
                    .addHeader("accept", "application/json")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                ResponseBody resposebody= response.body();
                String jsonString = resposebody.string();
                JSONArray jsonArray= new JSONArray(jsonString);

                for(int i=0 ;i< jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    Train train =new Train(jsonObject);

                    trains.add(train);
                }
                return trains;

            } catch (IOException | JSONException e) {
               return null;
            }

        }

        @Override
        protected void onPostExecute(List<Train> trains){
            ListView listView= findViewById(R.id.train_list);
            Listview_adapter adapter=new Listview_adapter(trains);
            listView.setAdapter(adapter);
        }
    }
}