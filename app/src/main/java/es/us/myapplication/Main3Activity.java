package es.us.myapplication;

import android.app.ListActivity;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main3Activity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //Set<BluetoothDevice> pairedDevices = Activity2DispositivosBT.mBluetoothAdapter.getBondedDevices();
        String [] listadisp;
        List<String> s = new ArrayList<>();
        /*for(BluetoothDevice bt : pairedDevices)
            s.add(bt.getName());*/

        setListAdapter(new ArrayAdapter<>(this, R.layout.activity_main3, s));
        setContentView(R.layout.activity_main3);
        //listadisp = getResources().getStringArray(R.id.listview);
    }

    public void clickMe(View view) {
    }
}
