package es.us.myapplication;

import android.app.Activity;
import android.content.Intent;

public class OnActivityResult {
    static int requestCode1;
    static int resultCode1;
    static Intent data1;
    static int REQUEST_ENABLE_BT = 1;

    private static final OnActivityResult onActivityResult = new OnActivityResult(requestCode1, resultCode1, data1);

    public static OnActivityResult getInstance() {
        return onActivityResult;
    }

    private OnActivityResult(int requestCode, int resultCode, Intent data) {
        requestCode1 = requestCode;
        resultCode1 = resultCode;
        data1 = data;

        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_CANCELED) {
                //Bluetooth not enabled.
                Activity2DispositivosBT.fa.finish();
                return;
            }
        }
        new OnActivityResult(requestCode, resultCode, data);
    }
}
