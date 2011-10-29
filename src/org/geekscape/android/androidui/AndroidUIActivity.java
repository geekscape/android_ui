package org.geekscape.android.androidui;

import org.geekscape.android.androidui.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class AndroidUIActivity extends Activity {
  public static String LOG_TAG = AndroidUIActivity.class.getSimpleName();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "onCreate()");
        setContentView(R.layout.main);
    }
}