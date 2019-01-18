package com.example.king.vinamobile.A3_Scan;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.king.vinamobile.R;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;

/**
 * A simple {@link Fragment} subclass.
 */
public class A3_Scan_Fragment extends Fragment implements BarcodeReader.BarcodeReaderListener {

    //region KHAI BÁO BIẾN TOÀN CỤC
    private static final String TAG = A3_Scan_Fragment.class.getSimpleName();
    private BarcodeReader barcodeReader;
    //endregion


    public A3_Scan_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a3__scan_, container, false);
        barcodeReader = (BarcodeReader) getChildFragmentManager().findFragmentById(R.id.a3_barcode_fragment);
        barcodeReader.setListener(this);

        return view;
    }

    @Override
    public void onScanned(final Barcode barcode) {
        try {
            Log.e(TAG, "onScanned: " + barcode.displayValue);
            barcodeReader.playBeep();

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), "Barcode: " + barcode.displayValue, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {

        }

    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {
        try {
            Log.e(TAG, "onScannedMultiple: " + barcodes.size());

            String codes = "";
            for (Barcode barcode : barcodes) {
                codes += barcode.displayValue + " , ";
            }

            final String finalCodes = codes;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), "Barcodes: " + finalCodes, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {
        Log.e(TAG, "onScanError: " + errorMessage);
    }

    @Override
    public void onCameraPermissionDenied() {
        Toast.makeText(getActivity(), "Camera permission denied!", Toast.LENGTH_LONG).show();
    }

}
