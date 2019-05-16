package com.example.countsmsandcalls;

import android.database.Cursor;
import android.provider.CallLog;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView txtUnreadSms;
    private TextView txtMissedCalls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUnreadSms = (TextView) findViewById(R.id.txtUnreadSms);
        txtMissedCalls = (TextView) findViewById(R.id.txtMissedCalls);
    }

    public void unreadSms(View view) {
        String where = Telephony.Sms.READ+"=0";
        Cursor c = this.getContentResolver().query(Telephony.Sms.CONTENT_URI, null,where, null, null);
        c.moveToFirst();

        String unreadSms = String.valueOf(c.getCount());
        txtUnreadSms.setText(unreadSms);

        Log.d(TAG, "Unread Sms: " + unreadSms);
    }

    public void missedCalls(View view) {
        String[] projection = { CallLog.Calls.CACHED_NAME, CallLog.Calls.CACHED_NUMBER_LABEL, CallLog.Calls.TYPE };
        String where = CallLog.Calls.TYPE+"="+CallLog.Calls.MISSED_TYPE;
        Cursor c = this.getContentResolver().query(CallLog.Calls.CONTENT_URI, projection, where, null, null);
        c.moveToFirst();

        String missedCalls = String.valueOf(c.getCount());
        txtMissedCalls.setText(missedCalls);

        Log.d(TAG, "Missed Calls: " + missedCalls);
    }
}
