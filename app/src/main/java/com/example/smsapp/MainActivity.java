package com.example.smsapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.smsapp.DatabaseHandler.MessageClass;
import com.example.smsapp.DatabaseHandler.SentMessageDataBase;
import com.example.smsapp.RecyclerHandler.MessageRecyclerAdapter;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smsapp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int MY_PERMISSION_RESQUEST_SEND_SMS = 0;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private SentMessageDataBase sentMessageDataBase;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.SEND_SMS)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, MY_PERMISSION_RESQUEST_SEND_SMS);
            }
        }



        sentMessageDataBase = new SentMessageDataBase(MainActivity.this);
        ArrayList<MessageClass> messageClasses =sentMessageDataBase.returnAllMessages();

        recyclerView = findViewById(R.id.recyclerViewId);
        recyclerView.setAdapter(new MessageRecyclerAdapter(messageClasses));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this , SendToMessageActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {

        SentMessageDataBase sentMessageDataBase1 = new SentMessageDataBase(MainActivity.this);
        ArrayList<MessageClass> messageClasses =sentMessageDataBase1.returnAllMessages();
        recyclerView.setAdapter(new MessageRecyclerAdapter(messageClasses));
        super.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSION_RESQUEST_SEND_SMS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission given", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
        }
    }
}


