package com.example.smsapp.DatabaseHandler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.File;
import java.util.ArrayList;

public class SentMessageDataBase extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "messagesSent";
    private static final int DATABASE_VERSION = 1;
    private static final String MESSAGE_TABLE = "messageHistory";
    private static final String ID_KEY = "id";
    private static final String PHONE_KEY = "phoneNo";
    private static final String MESSAGE_KEY = "message";

    public SentMessageDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDatabaseSQL = "create table " + MESSAGE_TABLE +
                "(" + ID_KEY + " integer primary key autoincrement, " + PHONE_KEY + " text" + ", " + MESSAGE_KEY + " text" + ")";
        db.execSQL(createDatabaseSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if EXISTS " + MESSAGE_TABLE);
        onCreate(db);
    }

    public void saveMessage(MessageClass messageClass) {
        SQLiteDatabase database = getWritableDatabase();
        String addSQLCommand = "insert into " + MESSAGE_TABLE +
                " values(null,'" + messageClass.getPhoneNo() + "', '" + messageClass.getSentMessage() + "')";
        database.execSQL(addSQLCommand);
        database.close();
    }


    public ArrayList<MessageClass> returnAllMessages() {
        SQLiteDatabase database = getWritableDatabase();
        String sqlQueryCommand = "Select * from " + MESSAGE_TABLE;
        Cursor cursor = database.rawQuery(sqlQueryCommand, null);

        ArrayList<MessageClass> messageClasses = new ArrayList<>();
        while (cursor.moveToNext()) {

            MessageClass currentMessageClass = new MessageClass(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2));
            messageClasses.add(currentMessageClass);
        }
        database.close();
        return messageClasses;
    }
}
