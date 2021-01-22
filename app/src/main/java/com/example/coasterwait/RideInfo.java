package com.example.coasterwait;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RideInfo extends AppCompatActivity {

    private TextView mRideName;
    private TextView mRideStatus;
    private TextView mRideDescription;
    private TextView mWait;
    private TextView mRating;
    private Button mBackButton;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ride_info);

        mRideName=(TextView)findViewById(R.id.ride_name);
        mRideStatus=(TextView)findViewById(R.id.ride_status);
        mRideDescription=(TextView)findViewById(R.id.ride_description);
        mWait=(TextView)findViewById(R.id.ride_wait);
        mRating=(TextView)findViewById(R.id.ride_rating);
        mBackButton=(Button)findViewById(R.id.back_button);

        Bundle b = this.getIntent().getExtras();
        Ride ride = b.getParcelable("Ride");
        Log.d("Test",ride.toString());

        mRideName.setText(ride.getName());
        if (ride.isStatus()){mRideStatus.setText("Status: Open");}
        else{mRideName.setText("Status: Closed");}
        mRideDescription.setText(ride.getDescription());
        if (ride.getWait()==-1){mWait.setText("Wait Time: Unknown");}
        else{mWait.setText("Wait Time: "+ride.getWait()+" Minutes");}
        mRating.setText("Thrill Rating: "+ride.getRating());

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
