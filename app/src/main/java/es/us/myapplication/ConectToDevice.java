package es.us.myapplication;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothProfile;
import android.util.Log;


public class ConectToDevice {

    static BluetoothDevice device1;
    static BluetoothGatt mGatt;
    static final ConectToDevice conectToDevice = new ConectToDevice(device1);


    public static ConectToDevice getInstance() {
        return conectToDevice;
    }

    ConectToDevice(BluetoothDevice device) {
        device1=device;

        if (mGatt == null) {
            mGatt = device.connectGatt(GlobalApp.getAppContext(), false, gattCallback);
            new ScanLeDevice(false);// will stop after first device detection
        }
    }
    private final BluetoothGattCallback gattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            Log.i("onConnectionStateChange", "Status: " + status);
            switch (newState) {
                case BluetoothProfile.STATE_CONNECTED:
                    Log.i("gattCallback", "STATE_CONNECTED");
                    gatt.discoverServices();
                    break;
                case BluetoothProfile.STATE_DISCONNECTED:
                    Log.e("gattCallback", "STATE_DISCONNECTED");
                    break;
                default:
                    Log.e("gattCallback", "STATE_OTHER");
            }

        }
    };
}
