package com.example.jsonproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         textView=findViewById(R.id.te);
         button=findViewById(R.id.button);
       //  requestQueue= Volley.newRequestQueue(this);

        requestQueue=volleysingleton.getInstance(this).getRequestQueue();
        //convert string from json---------------------------------------//
        // Gson gson=new Gson();
       // Student student=new Student("Prakash","sainiprakasj5254gmial.com",1);
       // String json=gson.toJson(student);
       // te1.setText(json);
       // Log.d("TEST",json);
        //-----------------------------------//

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendAPIRequest();
            }
        });
    }

    private void sendAPIRequest(){
        String url="http://api.openweathermap.org/data/2.5/weather?q=Surat,IN&appid=83346e46676f562d6005fdda3381aa1e";
  //      String url= "https://raw.githubusercontent.com/ianbar20/JSON-Volley-Tutorial/master/Example-JSON-Files/Example-Object.JSON";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    /*
                    JSONArray jsonArray1 = response.getJSONArray("coord");
                    for(int i=0;i<jsonArray1.length();i++){
                        long lon=jsonArray1.getJSONObject(i).getLong("lon");
                        long lat=jsonArray1.getJSONObject(i).getLong("lat");
                        String s1=""+lon;
                        String s2=""+lat;
                        textView.append("Lat: "+s1+" Lat: "+s2+"\n");
                        */
                    JSONObject obj = response.getJSONObject("colorObject");
                    // Retrieves the string labeled "colorName" and "description" from
                    //the response JSON Object
                    //and converts them into javascript objects
                    String color = obj.getString("colorName");
                    String desc = obj.getString("description");

                    // Adds strings from object to the "data" string
                    String data = "Color Name: " + color +
                            "nDescription : " + desc;

                    // Adds the data string to the TextView "results"
                    textView.setText(data);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONArray jsonArray2=response.getJSONArray("weather");
                    for(int i=0;i<jsonArray2.length();i++){
                        String main1=jsonArray2.getJSONObject(i).getString("main");
                        textView.append("Weather: "+main1+"\n");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONArray jsonArray3=response.getJSONArray("main");
                    for(int i=0;i<jsonArray3.length();i++){
                        long lon=jsonArray3.getJSONObject(i).getLong("temp");
                        long lat=jsonArray3.getJSONObject(i).getLong("pressure");
                        long hum=jsonArray3.getJSONObject(i).getLong("humidity");
                        String s1=""+lon;
                        String s2=""+lat;
                        String s3=""+hum;
                        textView.append("Temp: "+s1+" Pressure: "+s2+" Humidity"+s3+"\n");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Fuck off",Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(request);
    }


}
