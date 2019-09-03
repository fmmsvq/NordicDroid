package es.us.myapplication;

import static es.us.myapplication.ConectToDevice.mGatt;

public class OnDestroy {
    private static final OnDestroy onDestroy = new OnDestroy();

    public static OnDestroy getInstance() {
        return onDestroy;
    }

    private OnDestroy() {
        if (mGatt != null) {
            mGatt.close();
            mGatt = null;
        }
        //super.onDestroy();
    }
}
