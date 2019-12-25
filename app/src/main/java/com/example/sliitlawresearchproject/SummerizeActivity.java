package com.example.sliitlawresearchproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SummerizeActivity extends AppCompatActivity {

    TextView Prob_Input, Prob_Solution, Line_Description, Sum_Description;
    Button Sum_Problem;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference Dataref;

    String data_item_Uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summerize);

        Prob_Input = findViewById(R.id.problem_input);
        Prob_Solution = findViewById(R.id.problem_solution);
        Line_Description = findViewById(R.id.line_of_description);
        Sum_Description = findViewById(R.id.summery_of_description);
        Sum_Problem = findViewById(R.id.sum_problem);

        Intent intent = getIntent();
        data_item_Uid = intent.getStringExtra("data_item_Uid");

        firebaseDatabase = FirebaseDatabase.getInstance();
        Dataref = firebaseDatabase.getReference("Data");

        //search userto get that user's info
        Query userQuery = Dataref.orderByChild("uid").equalTo(data_item_Uid);
        //get title and description
        userQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //check until required info is received
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    //get data
                    String title = ""+ ds.child("title").getValue();
                    String description = ""+ ds.child("description").getValue();

                    //set data
                    Prob_Input.setText(title);
                    Prob_Solution.setText(description);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Sum_Problem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //search userto get that user's info
                Query userQuery = Dataref.orderByChild("uid").equalTo(data_item_Uid);
                //get title and description
                userQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //check until required info is received
                        for (DataSnapshot ds: dataSnapshot.getChildren()){
                            //get data
                            String description = ""+ ds.child("description").getValue();

                            //set data
                           Sum_Description.setText(description);
                           Line_Description.setText("3");

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

    }
}
