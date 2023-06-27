package com.example.smsapp.RecyclerHandler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smsapp.DatabaseHandler.MessageClass;
import com.example.smsapp.R;

import java.util.ArrayList;

public class MessageRecyclerAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    ArrayList<MessageClass> messageClasses;

    public MessageRecyclerAdapter(ArrayList<MessageClass> messageClasses) {
        this.messageClasses = messageClasses;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.message_view, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        holder.getTxtPhonenNo().setText(messageClasses.get(position).getPhoneNo());
        holder.getTxtMessage().setText(messageClasses.get(position).getSentMessage());
    }

    @Override
    public int getItemCount() {
        return messageClasses.size();
    }
}
