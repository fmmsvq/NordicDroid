package es.us.myapplication;

import static es.us.myapplication.Activity2DispositivosBT.mBluetoothAdapter;
//import static es.us.myapplication.OnResume.mBluetoothAdapter;

public class Onpause {
    private static final Onpause onPause = new Onpause();

    public static Onpause getInstance() {
        return onPause;
    }

    private Onpause() {

        if (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()) {
            new ScanLeDevice(false);
        }
    }
}
