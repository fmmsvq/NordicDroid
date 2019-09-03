package es.us.myapplication;

import android.bluetooth.le.ScanResult;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;


public class OnBatchScanResults {
    static List<ScanResult> results1;
    static ScanResult scanResult;
    private static final OnBatchScanResults ourInstance = new OnBatchScanResults(results1);

    public static OnBatchScanResults getInstance() {
        return ourInstance;
    }

    private OnBatchScanResults(List< ScanResult > results) {
        results1=results;

            for (ScanResult sr : results) {
                Log.i("ScanResult - Results", sr.toString());
            }
            //scanResults(results);
        }

}
