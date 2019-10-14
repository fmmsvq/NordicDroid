package es.us.myapplication;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import static es.us.myapplication.ScanLeDevice.SCAN_PERIOD;
import static es.us.myapplication.ScanLeDevice.scanResults;

/**
 * Activity for scanning and displaying available Bluetooth LE devices.
 */
public class Activity2DispositivosBT extends AppCompatActivity {

    public static Activity fa;
    static Handler mHandler;
    static BluetoothAdapter mBluetoothAdapter;

    static BluetoothLeScanner mLEScanner;
    static ScanSettings settings;
    static List<ScanFilter> filters;


/*
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeScanner mLEScanner;
    private int REQUEST_ENABLE_BT = 1;
    private ScanSettings settings;
    private List<ScanFilter> filters;
    private Handler mHandler;
    private  List<String> scanResults;
    private ListView listaConexiones;
    private static final long SCAN_PERIOD = 10000;

        private BluetoothGatt mGatt;
*/

        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);

            fa = this;
            setContentView(R.layout.activity_activity2_dispositivos_bt);
            //ScanLeDevice.listaConexiones = findViewById(R.id.listaConexiones);

            mHandler = new Handler();
            //BluetoothManager
            if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
                Toast.makeText(this, "BLE Not Supported", Toast.LENGTH_SHORT).show();
                finish();
            }
            final BluetoothManager bluetoothManager =
                    (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);

            mBluetoothAdapter = bluetoothManager.getAdapter();
            mLEScanner = mBluetoothAdapter.getBluetoothLeScanner();
            settings = new ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY).build();
            filters = new ArrayList<>();
            mLEScanner.startScan(filters, settings, mScanCallback);

        }
    private ScanCallback mScanCallback;
    {
        mScanCallback = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                Log.i("FATIMA", String.valueOf(result.getRssi()));
                //AÑADIENDO DISPOSITIVOS A LA LISTA PARA MOSTRAR
                //scanResults.add(callbackType,result.toString());
                //Context mContext = GlobalApp.getAppContext();
                //ArrayAdapter<String> adapter =  new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, scanResults);
                //System.out.println(result.getDevice().getName() + result.getRssi());
                BluetoothDevice btDevice = result.getDevice();
                new ConectToDevice(btDevice);
            }

            @Override
            public void onBatchScanResults(List<ScanResult> results) {
                for (ScanResult sr : results) {
                    Log.i("ScanResult - Results", sr.toString());
                }
             }

            @Override
            public void onScanFailed(int errorCode) {
                Log.e("Scan Failed", "Error Code: " + errorCode);
            }
        };
    }

/*
        @Override
        protected void onResume() {
            super.onResume();
            if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            } else {
                mLEScanner = mBluetoothAdapter.getBluetoothLeScanner();
                settings = new ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY).build();
                filters = new ArrayList<>();
                scanLeDevice(true);
            }
        }

        @Override
        protected void onPause() {
            super.onPause();
            if (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()) {
                scanLeDevice(false);
            }
        }

        @Override
        protected void onDestroy(){
            if (mGatt != null) {
                mGatt.close();
                mGatt = null;
            }
            super.onDestroy();
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == REQUEST_ENABLE_BT) {
                if (resultCode == Activity.RESULT_CANCELED) {
                    //Bluetooth not enabled.
                    finish();
                    return;
                }
            }
            super.onActivityResult(requestCode, resultCode, data);

        }

    private void scanLeDevice(final boolean enable) {
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
        private ScanCallback mScanCallback;
        {
        mScanCallback = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                //Log.i("FATIMA", String.valueOf(callbackType));
                //Log.i("FATIMA", result.toString());
                //listaConexiones.add(result.getDevice().getName());
                Log.i("FATIMA", String.valueOf(result.getRssi()));
                //AÑADIENDO DISPOSITIVOS A LA LISTA PARA MOSTRAR
                scanResults.add(callbackType,result.toString());
                ArrayAdapter<String> adapter =  new ArrayAdapter<>(Activity2DispositivosBT.this, android.R.layout.simple_list_item_1, scanResults);
                listaConexiones.setAdapter(adapter);
                //System.out.println(result.getDevice().getName() + result.getRssi());
                BluetoothDevice btDevice = result.getDevice();
                connectToDevice(btDevice);
            }

            @Override
            public void onBatchScanResults(List<ScanResult> results) {
                for (ScanResult sr : results) {
                    Log.i("ScanResult - Results", sr.toString());
                }
                //scanResults(results);
            }



            @Override
            public void onScanFailed(int errorCode) {
                Log.e("Scan Failed", "Error Code: " + errorCode);
            }
        };
    }

        public void connectToDevice(BluetoothDevice device) {
            if (mGatt == null) {
                mGatt = device.connectGatt(this, false, gattCallback);
                scanLeDevice(false);// will stop after first device detection
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

            @Override
            public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                List<BluetoothGattService> services = gatt.getServices();
                Log.i("onServicesDiscovered", services.toString());
                gatt.readCharacteristic(services.get(1).getCharacteristics().get(0));
            }

            @Override
            public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                Log.i("onCharacteristicRead", characteristic.toString());
                gatt.disconnect();
            }
        };*/
    }