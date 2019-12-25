package com.example.sliitlawresearchproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class FeedbackActivity extends AppCompatActivity {

    EditText Prob_Input, Feed_Input;
    Button Post_Prob, Post_Feed;

    private String currentUserID;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Prob_Input = findViewById(R.id.problem_input);
        Feed_Input = findViewById(R.id.feedback_input);
        Post_Prob = findViewById(R.id.post_problem);
        Post_Feed = findViewById(R.id.post_feedback);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        Post_Prob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prob_Save();
            }
        });

        Post_Feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Feed_Save();
            }
        });

    }

    private void Feed_Save() {

        String user_feedback = Feed_Input.getText().toString();
        if (TextUtils.isEmpty(user_feedback))
        {
            Toasty.warning(this, "Please write your feedback first....", Toast.LENGTH_SHORT).show();
        }
        else
        {
            String key = databaseReference.child("Feedback").child(currentUserID).push().getKey();
            Map<String, Object> profileMap = new HashMap<>();
            profileMap.put("uid", currentUserID);
            profileMap.put("user feedback", user_feedback);
            databaseReference.child("Feedback").child(key).setValue(profileMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if (task.isSuccessful())
                            {
                                Feed_Input.setText("");
                                Toasty.success(FeedbackActivity.this, "Thank you for feedback...", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                String message = task.getException().toString();
                                Toasty.error(FeedbackActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }

    private void Prob_Save() {

        String user_problem = Prob_Input.getText().toString();
        if (TextUtils.isEmpty(user_problem))
        {
            Toasty.warning(this, "Please write your problem first....", Toast.LENGTH_SHORT).show();
        }
        else
        {
            String key = databaseReference.child("Problem").child(currentUserID).push().getKey();
            Map<String, Object> profileMap = new HashMap<>();
            profileMap.put("uid", currentUserID);
            profileMap.put("user problem", user_problem);
            databaseReference.child("Problem").child(key).setValue(profileMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if (task.isSuccessful())
                            {
                                Prob_Input.setText("");
                                Toasty.success(FeedbackActivity.this, "Problem Added Successfully...", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                String message = task.getException().toString();
                                Toasty.error(FeedbackActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
