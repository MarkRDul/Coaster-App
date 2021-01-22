package com.example.coasterwait;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class RideList extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener {

    private int mRideType;
    private RecyclerViewAdapter adapter;
    private Button mNextButton;
    private TextView mTextView;
    private ArrayList<String> mRideListNames;
    private ArrayList<Ride> mRideList;
    private ArrayList<Ride> mFullRideList;

    private void updateRecycler(){
        if (mRideType==1){mTextView.setText("Roller Coasters");}
        else if (mRideType==2){mTextView.setText("Water Rides");}
        else if (mRideType==3){mTextView.setText("Kids Rides");}
        else {mTextView.setText("Flat Rides");}
        mRideList=new ArrayList<>();
        mRideListNames=new ArrayList<>();
        for (int x=0;x<mFullRideList.size();x++){
            if (mFullRideList.get(x).getRideType()==mRideType){
                mRideListNames.add(mFullRideList.get(x).getName());
                mRideList.add(mFullRideList.get(x));
            }
        }
        // set up the RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.ride_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(this, mRideListNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ride_list);

        mRideType=1;
        mNextButton=(Button)findViewById(R.id.next_button);
        mTextView=(TextView)findViewById(R.id.ride_type);

        // data to populate the RecyclerView with
        mRideListNames=new ArrayList<>();
        mRideList=new ArrayList<>();
        Bundle b=this.getIntent().getExtras();
        mFullRideList=b.getParcelableArrayList("FullRideList");
        Log.d("Test",mFullRideList.toString());
        updateRecycler();

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRideType+=1;
                if (mRideType==5){
                    mRideType=1;
                }
                updateRecycler();
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(RideList.this, RideInfo.class);
        Bundle b = new Bundle();
        b.putParcelable("Ride",mRideList.get(position));
        i.putExtras(b);
        startActivity(i);
    }
}

