package net.tomlins.backgroundserviceexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by jason on 15/11/13.
 */
public class SMSBroadcastReciever extends BroadcastReceiver {

    private static final String TAG = "SMS_BROADCAST_RECEIVER";
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String SMS_ORIGINATING_SOURCE_NUMBER = "9999";

    @Override
    public void onReceive(Context _context, Intent _intent) {

        Log.i(TAG, "SMS received");

        if (_intent.getAction().equals(SMS_RECEIVED)) {

            Bundle bundle = _intent.getExtras();
            if (bundle != null) {

                Object[] pdus = (Object[]) bundle.get("pdus");
                StringBuffer smsMessageText = new StringBuffer();
                SmsMessage message = null;

                for (int index = 0; index < pdus.length; index++) {
                    message = SmsMessage.createFromPdu((byte[]) pdus[index]);

                    // We must also ensure the application is expecting an SMS
                    // from specified source and not something requested manually outside of the app
//                    if(!message.getOriginatingAddress().contentEquals(SMS_ORIGINATING_SOURCE_NUMBER))
//                        continue;

                    smsMessageText.append(message.getMessageBody());
                }

                if (smsMessageText.length() > 0) {
                    this.abortBroadcast();
                    Toast.makeText(_context.getApplicationContext(),
                            "SMS Received from " + message.getOriginatingAddress() + " says " +
                                    smsMessageText.toString(), Toast.LENGTH_LONG).show();
                }
            }

            //here we start the service
//            Intent serviceIntent = new Intent(_context, ServiceActivity.class);
//            _context.startService(serviceIntent);

            /*
             * Creates a new Intent to start the RSSPullService
             * IntentService. Passes a URI in the
             * Intent's "data" field.
             */
            Intent serviceIntent = new Intent(_context, SMSServiceIntent.class);
            serviceIntent.setData(Uri.parse("http://www.tomlins.net"));
            Log.i(TAG, "Starting intent service...");
            _context.startService(serviceIntent);
            Log.i(TAG, "Completed " + TAG);


        }
    }

}

