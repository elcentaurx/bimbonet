package com.elcentaurx.bimbonet.repository;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.elcentaurx.bimbonet.data.preferences.Preferences;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationRepository {
    private Application application;
    private MutableLiveData<FirebaseUser> firebaseUserMutableLiveData;
    private MutableLiveData<Boolean> userLoggedMutableLiveData;
    private FirebaseAuth auth;
    SharedPreferences preferences;
    Preferences myPreference;

    public MutableLiveData<FirebaseUser> getFirebaseUserMutableLiveData() {
        return  firebaseUserMutableLiveData;
    }

    public  MutableLiveData<Boolean> getUserLoggedMutableLiveData() {
        return  userLoggedMutableLiveData;
    }

    public AuthenticationRepository(Application application){
        this.application = application;
        firebaseUserMutableLiveData = new MutableLiveData<>();
        userLoggedMutableLiveData = new MutableLiveData<>();
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null){
            firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
        }
    }

    public void register(String email, String pass) {
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
                    preferences = application
                            .getSharedPreferences("email", Context.MODE_PRIVATE);
                    String email = auth.getCurrentUser().getEmail();
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("email", email);
                    editor.commit();

                }
                else{
                    Toast.makeText(application,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void login(String email, String pass){
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    myPreference = new Preferences();
                    firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
                    myPreference.setDefaults("email",auth.getCurrentUser().getEmail(), application.getApplicationContext());
                }else {
                    Toast.makeText(application, task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public  void signOut(){
        auth.signOut();
        userLoggedMutableLiveData.postValue(true);

    }




}
