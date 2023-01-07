package com.teslaowls.teslaled;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<String> features = Arrays.asList("Bonjour", "Merci", "Carre", "Tesla", "Clock", "Desole");

    BluetoothClient bluetoothClient = new BluetoothClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout = findViewById(R.id.command_grid);
        for (final String feature : features) {
            Button button = new Button(this);
            button.setText(feature);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bluetoothClient.findDevice();
                    if (!bluetoothClient.isConnected()) {
                        bluetoothClient.connect();
                    }
                    bluetoothClient.sendMessage(feature.toLowerCase());
                    System.out.println("[+] Socket closed");
                }
            });
            gridLayout.addView(button);
        }
    }
}