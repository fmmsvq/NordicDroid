package es.us.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import static es.us.myapplication.Activity2DispositivosBT.mBluetoothAdapter;
import static es.us.myapplication.OnActivityResult.REQUEST_ENABLE_BT;

public class OnResume {
    private static final OnResume onResume = new OnResume();
    //static BluetoothAdapter mBluetoothAdapter;
    //static BluetoothLeScanner mLEScanner;
    //static ScanSettings settings;
    //static List<ScanFilter> filters;


    public static OnResume getInstance() {

        return onResume;
    }

    private OnResume(){
        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            Activity2DispositivosBT.fa.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);

        } else {
            Activity2DispositivosBT.mLEScanner = mBluetoothAdapter.getBluetoothLeScanner();
            Activity2DispositivosBT.settings = new ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY).build();
            Activity2DispositivosBT.filters = new ArrayList<>();
            new ScanLeDevice(true);
        }
    }

}
