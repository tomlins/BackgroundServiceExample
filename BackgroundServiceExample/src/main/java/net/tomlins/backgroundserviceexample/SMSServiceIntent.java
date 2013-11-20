package net.tomlins.backgroundserviceexample;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by jason on 15/11/13.
 */
public class SMSServiceIntent extends IntentService {

    private static final String TAG = "SMSServiceIntent";

    // Default constructor required
    public SMSServiceIntent() {
        super("SMSServiceIntent");
    }

    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //super.onStartCommand(intent, flags, startId);
        //Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        // Do work here, based on the contents of dataString
        String dataString = workIntent.getDataString();
        Log.i(TAG, dataString);
//        Toast.makeText(getBaseContext(), dataString, Toast.LENGTH_SHORT).show();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "I've finished sleeping");
    }

}
