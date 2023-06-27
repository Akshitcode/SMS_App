package com.example.smsapp.RecyclerHandler;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smsapp.R;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    private TextView txtPhonenNo;
    private TextView txtMessage;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);

        txtPhonenNo = itemView.findViewById(R.id.txtPhoneNo);
        txtMessage = itemView.findViewById(R.id.txtMessage);
    }

    public TextView getTxtPhonenNo() {
        return txtPhonenNo;
    }

    public TextView getTxtMessage() {
        return txtMessage;
    }
}
