package es.us.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/*** 1-Check if Bluetooth is available or not.
 * 2-Turn On/Off Bluetooth.
 * 3-Make Bluetooth Discoverable.
 * 4-Display Paired/Bounded devices.
 * Note: The getBoundedDevices() method of BluetoothAdapter class provides a set containing list of all paired or bounded bluetooth devices.
 * Permissions Required: BLUETOOTH, BLUETOOTH_ADMIN**/
public class MainActivity extends AppCompatActivity {

    private Button btn;
    private static final int ACCESS_FINE_LOCATION  = 456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, ACCESS_FINE_LOCATION);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToActivity2();
            }
        });


    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case ACCESS_FINE_LOCATION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted, yay! Start the Bluetooth device scan.
                } else {
                    // Alert the user that this application requires the location permission to perform the scan.
                }
            }
        }
    }
    private void moveToActivity2(){
        Intent intent = new Intent(this, Activity2DispositivosBT.class);
        startActivity(intent);
    }
}
