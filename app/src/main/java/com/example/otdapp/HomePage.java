package com.example.otdapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePage extends AppCompatActivity {

    private EditText Email_field;
    private EditText Password;
    final String CHANNEL_ID = "personal_notifications";
    final int NOTIFICATION_ID = 001;
     Button button;
    private FirebaseAuth mAuth;
     TextView newUser;
     TextView forgot;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);





        button = findViewById(R.id.logIn);
        mAuth = FirebaseAuth.getInstance();
        Email_field = findViewById(R.id.email45);
        Password = findViewById(R.id.password);
        forgot = findViewById(R.id.forget);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(HomePage.this);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("ZIOLTY..........");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();



               startSignIn();

            }
        });

        newUser = findViewById(R.id.newUser);
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomePage.this,NewUser.class);
                startActivity(intent);
                finish();
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                String reset ;
                reset = Email_field.getText().toString();
//                Log.e("Inside forgotOnclick","true");
                if (!TextUtils.isEmpty(reset)) {
//                    Log.e("Inside resetEmail","true");
                    mAuth.sendPasswordResetEmail(reset)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressDialog = new ProgressDialog(HomePage.this);
                                    progressDialog.setCancelable(false);
                                    progressDialog.setMessage("PLEASE WAIT..........");
                                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                    progressDialog.show();
                                    if (task.isSuccessful()) {
                                        progressDialog.dismiss();

                                   Toast.makeText(HomePage.this,"Reset Link is send in your email",Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
            }
        });
    }

    private void startSignIn () {

        String email = Email_field.getText().toString();
        String pass = Password.getText().toString();

        if (TextUtils.isEmpty(email)) {
            progressDialog.dismiss();
            Toast.makeText(this, "Email is empty", Toast.LENGTH_SHORT).show();
        }

        else  if (TextUtils.isEmpty(pass)) {
            progressDialog.dismiss();
            Toast.makeText(this, "Password is empty", Toast.LENGTH_SHORT).show();
        }

        else if (pass.length()<6){
            progressDialog.dismiss();
            Toast.makeText(HomePage.this,"Invalid Password (It can't be less than 6 digit)",Toast.LENGTH_SHORT).show();
        }
        else {

            mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                FirebaseUser user = mAuth.getCurrentUser();
                                progressDialog.dismiss();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                progressDialog.dismiss();

                                Toast.makeText(HomePage.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //Log.e("User Email= ",currentUser.getEmail().isEmpty());
        updateUI(currentUser);
    }

   public void updateUI (FirebaseUser User){

        if (User!=null) {

            Toast.makeText(HomePage.this, "WELCOME TO ZIOLTY", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(HomePage.this, first_main_page.class);
            startActivity(intent);
            finish();
        }



   }

//   public void displayNotification (){
//
//       createNotificationChannel ();
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(HomePage.this,CHANNEL_ID);
//        builder.setSmallIcon(R.drawable.ic_notifications_active_black_24dp);
//        builder.setContentText("this is simple Notification...");
//        builder.setContentTitle("Simple Notification");
//        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
//
//        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
//        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());
//
//   }
//
//    public void createNotificationChannel (String id  , String name ,int importance){
//
//            NotificationChannel channel = new NotificationChannel(id , name, importance);
//            channel.setShowBadge(true);
//
//            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//            assert notificationManager != null;
//            notificationManager.createNotificationChannel(channel);
//
//            channel.setSound(null,null);
//
//
//
//        }
//    }



}
