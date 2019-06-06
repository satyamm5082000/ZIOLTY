package com.example.otdapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class myAccount extends AppCompatActivity {

    private FirebaseAuth mAuth;
    TextView textView;
    TextView textView1;
    User user;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    TextView Name;
    TextView Mobile;
    TextView Address;
    ImageView imageView;
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        user = new User();
        mAuth = FirebaseAuth.getInstance();
        textView = findViewById(R.id.email);
        textView1 = findViewById(R.id.chpass);
        Name = findViewById(R.id.name);
        Mobile = findViewById(R.id.no);
        Address = findViewById(R.id.address);
        imageView = findViewById(R.id.edit);
        imageView2 = findViewById(R.id.profile);
        firebaseUser = mAuth.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference().child(firebaseUser.getUid()).child("ProfilePic");

        if (firebaseUser.getPhotoUrl() != null)
            Glide.with(this).load(firebaseUser.getPhotoUrl()).placeholder(R.drawable.ic_person_black_24dp).into(imageView2);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(firebaseUser.getUid());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                Log.e("myAccount",user.getName());
                Log.e("myAccount",user.getAddress());
                Log.e("myAccount",user.getEmail());
                Log.e("myAccount",user.getMobile_no());
                Name.setText(user.getName()+"");
                Address.setText(user.getAddress()+"");
                Mobile.setText(user.getMobile_no()+"");
                textView.setText(user.getEmail()+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, 1);

                }
        }});
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String reset ;
                reset = textView.getText().toString().trim();

                if (!TextUtils.isEmpty(reset)) {
                    mAuth.sendPasswordResetEmail(reset)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        Toast.makeText(myAccount.this,"Reset Link is send in your email",Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri fullPhotoUri = data.getData();
            Bitmap thumbnail;
            try{
                thumbnail = MediaStore.Images.Media.getBitmap(getContentResolver(),fullPhotoUri);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            if (fullPhotoUri.getLastPathSegment() != null)
              storageReference = storageReference.child(fullPhotoUri.getLastPathSegment());
             storageReference.putFile(fullPhotoUri).continueWithTask(task -> {
                 if (!task.isSuccessful()){
                     if (task.getException() != null)
                         throw task.getException();
                 }
                 return storageReference.getDownloadUrl();
             }).addOnCompleteListener( task ->{
                 if (task.isSuccessful()){
                     Uri downloadUri = task.getResult();
                     if(downloadUri == null){
                         return;
                     }
                     UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setPhotoUri(downloadUri).build();
                     firebaseUser.updateProfile (profileChangeRequest).addOnCompleteListener(task1->{
                        if (task1.isSuccessful())
                            Toast.makeText(myAccount.this,"Profile picture uploaded!!",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(myAccount.this,"Failed to update profile picture!!",Toast.LENGTH_SHORT).show();
                     });
                 }
             }).addOnFailureListener(e -> Toast.makeText(myAccount.this,"Failed to update profile picture!!",Toast.LENGTH_SHORT).show());

        }
    }
}
