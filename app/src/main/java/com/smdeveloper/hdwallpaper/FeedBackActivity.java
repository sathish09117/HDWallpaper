package com.smdeveloper.hdwallpaper;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.smdeveloper.hdwallpaper.Model.FeedBackModel;

public class FeedBackActivity extends AppCompatActivity {

    MaterialEditText edtFeedback,edtName;
    Button btnSubmit;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        edtName = (MaterialEditText)findViewById(R.id.edtName);

        edtFeedback=(MaterialEditText)findViewById(R.id.edtFeedback);

        btnSubmit = (Button)findViewById(R.id.btnSubmit);

        //Init Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_feedback=database.getReference("FeedBackModel");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(FeedBackActivity.this);
                mDialog.setMessage("Please waiting...");
                mDialog.show();
                table_feedback.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {

                        mDialog.dismiss();
                        FeedBackModel feedBackModel = new FeedBackModel(edtFeedback.getText().toString());
                        table_feedback.child(edtName.getText().toString()).setValue(feedBackModel);
                        //   Toast.makeText(FeedBack.this, "SignUp Successfully!", Toast.LENGTH_SHORT).show();
                        finish();

                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
