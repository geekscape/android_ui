package org.geekscape.android.androidui;

import org.geekscape.android.androidui.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class AndroidUIActivity extends Activity {
  public static String DEBUG_TAG = "AndroidUI";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.i(DEBUG_TAG, "Log() test");
    }
}