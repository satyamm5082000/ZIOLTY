package com.example.otdapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewUser extends AppCompatActivity {


    EditText Password;
    EditText Cpassword;
    EditText name;
    EditText mobile;
    EditText email;
    EditText address;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    Button bttn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);



        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();


        Cpassword = findViewById(R.id.cpassword);
        Password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        mobile = findViewById(R.id.mobile);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        bttn=findViewById(R.id.submit);

        bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createNewUserIdPassword();

            }
        });
    }


    public  void  createNewUserIdPassword () {

        final String pass = Password.getText().toString();
        final String Cpass = Cpassword.getText().toString();
        final String name = this.name.getText().toString();
        final String mobile = this.mobile.getText().toString();
        final String email = this.email.getText().toString();
        final String address = this.address.getText().toString();


        if (mobile.length() > 10 || mobile.length()<10) {

            Toast.makeText(NewUser.this, "Mobile No is greater than 10", Toast.LENGTH_SHORT).show();
        } else if (pass.length() < 6) {

            Toast.makeText(NewUser.this, "Password is less than 6", Toast.LENGTH_SHORT).show();
        }

        else if (!pass.equalsIgnoreCase(Cpass)) {

            Toast.makeText(NewUser.this, "Password and Confirm Passsword are not equal", Toast.LENGTH_SHORT).show();
        }else {


            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(NewUser.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information


                                FirebaseUser user = mAuth.getCurrentUser();


                                writeNewUser(user.getUid(), name, mobile, email, address);

                                updateUI(user);


                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(NewUser.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });

        }
    }


    public void updateUI (FirebaseUser User){

        if (User!=null) {

            Toast.makeText(NewUser.this," Now Login ",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(NewUser.this,HomePage.class);
            startActivity(intent);
            finish();;
        }



    }

    private void writeNewUser(String userId, String name,String mobile, String email,String address) {

        User user = new User(name, mobile, email, address);

        mDatabase.child("users").child(userId).setValue(user);
    }



}
