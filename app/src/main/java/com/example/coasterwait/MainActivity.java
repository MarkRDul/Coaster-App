package com.example.coasterwait;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Ride> rides;
    private ArrayList<JSONObject> json;
    private Button mNextButton;
    private TextView mTextView;
    //Code for loading JSON not mine, from Stack Exchange because I
    //Would not have had any idea how to do this
    //From Hershey Park API - Download JSON from here: https://hpapp.hersheypa.com/v1/rides and put into assets
    public String loadJSONFromAsset(){
        String json = null;
        try {
            InputStream is = getAssets().open("rides(11).json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNextButton=findViewById(R.id.next_button);
        mTextView=findViewById(R.id.title_text);

        String jsonstring = loadJSONFromAsset();
        json = new ArrayList<JSONObject>();
        rides=new ArrayList<Ride>();
        //Log.d("t","t1");
        try {
            JSONArray jsonArray  = new JSONArray(jsonstring);
            for (int x=0;x<jsonArray.length();x++){
                json.add(new JSONObject(jsonArray.getString(x)));
            }

            int wait=0;
            //Log.d("t","t2");
            for (int x=0;x<json.size(); x++) {
                //Log.d("t","t3");
                JSONObject current = json.get(x);
                int id=Integer.parseInt(current.get("id").toString());
                String name=current.get("name").toString();
                String description=current.get("description").toString();
                /**********************************************/
                if (current.has("wait")){wait=Integer.parseInt(current.get("wait").toString());}
                else{wait=-1;}
                /**********************************************/
                Boolean status=true;
                if (current.get("status")=="0"){status=false;}
                /**********************************************/
                //Log.d("t",new JSONObject(new JSONArray(current.get("types").toString()).get(0).toString()).get("id").toString());
                int type=Integer.parseInt(new JSONObject(new JSONArray(current.get("types").toString()).get(0).toString()).get("id").toString());
                int area=Integer.parseInt(new JSONObject(current.get("parkarea").toString()).get("id").toString());
                int rating=Integer.parseInt(new JSONObject(current.get("rating").toString()).get("id").toString());
                /**********************************************/
                Boolean fasttrack = false;
                if (current.get("fasttrack")=="1"){fasttrack=true;}
                JSONArray heights = new JSONArray(current.get("height").toString());
                int lowheight=Integer.parseInt(new JSONObject(heights.get(0).toString()).get("id").toString());
                int highheight=Integer.parseInt(new JSONObject(heights.get(heights.length()-1).toString()).get("id").toString());
                String video=current.get("video").toString();
                rides.add(new Ride(id, name,description, wait, status, type, area, rating, fasttrack, lowheight, highheight, video));
                Log.d("Ride "+x, rides.get(x).toString());
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RideList.class);
                Bundle b = new Bundle();
                b.putParcelableArrayList("FullRideList",rides);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }
}
