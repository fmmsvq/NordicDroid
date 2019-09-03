package es.us.myapplication;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


import static es.us.myapplication.OnResume.filters;
import static es.us.myapplication.OnResume.mLEScanner;
import static es.us.myapplication.OnResume.settings;

public class ScanLeDevice {

    static boolean enable1;
    static Handler mHandler;
    static List<String> scanResults;
    static ListView listaConexiones;
    static final long SCAN_PERIOD = 10000;
    private static final ScanLeDevice scanLeDevice = new ScanLeDevice(enable1);


    public ScanLeDevice(boolean enable) {
        enable1=enable;
        if (enable) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mLEScanner.stopScan(mScanCallback);
                }
            }, SCAN_PERIOD);
            mLEScanner.startScan(filters, settings, mScanCallback);
        } else {
            mLEScanner.stopScan(mScanCallback);
        }
    }

    static ScanLeDevice getInstance() {
        return scanLeDevice;
    }

    private ScanCallback mScanCallback;
    {
        mScanCallback = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                Log.i("FATIMA", String.valueOf(result.getRssi()));
                //AÑADIENDO DISPOSITIVOS A LA LISTA PARA MOSTRAR
                scanResults.add(callbackType,result.toString());
                Context mContext = GlobalApp.getAppContext();
                ArrayAdapter<String> adapter =  new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, scanResults);

                listaConexiones.setAdapter(adapter);
                //System.out.println(result.getDevice().getName() + result.getRssi());
                BluetoothDevice btDevice = result.getDevice();
                new ConectToDevice(btDevice);
            }

            @Override
            public void onBatchScanResults(List<ScanResult> results) {
                for (ScanResult sr : results) {
                    Log.i("ScanResult - Results", sr.toString());
                }
                //scanResults(results);
            }

            /*public List<String> scanResults(List<ScanResult> results) {
                int i=0;
                for (ScanResult sr : results) {
                    scanResults.add(i,sr.getDevice().toString());
                    i++;
                }
                return scanResults;
            }*/

            @Override
            public void onScanFailed(int errorCode) {
                Log.e("Scan Failed", "Error Code: " + errorCode);
            }
        };
    }
}
