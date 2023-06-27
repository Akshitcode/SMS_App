package com.example.smsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smsapp.DatabaseHandler.MessageClass;
import com.example.smsapp.DatabaseHandler.SentMessageDataBase;

public class SendToMessageActivity extends AppCompatActivity {

    private EditText edtPhone;
    private EditText edtMessage;
    private Button btnSend;

    private SentMessageDataBase sentMessageDataBase ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_to_message);

        sentMessageDataBase = new SentMessageDataBase(SendToMessageActivity.this);

        edtPhone = findViewById(R.id.edtPhone);
        edtMessage = findViewById(R.id.edtMessage);
        btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNo = edtPhone.getText().toString();
                String message = edtMessage.getText().toString();

                MessageClass messageClass = new MessageClass(0, phoneNo, message);
                sentMessageDataBase.saveMessage(messageClass);

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNo,null, message,null,null);
                Toast.makeText(SendToMessageActivity.this, "Message sent!", Toast.LENGTH_SHORT).show();

                finish();
            }
        });


    }
}