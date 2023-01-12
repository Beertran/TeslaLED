package com.teslaowls.teslaled;

import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class ImageCommand extends PanelCommand {

    private String imageFile;

    public ImageCommand(String imageName) {
        super();

        this.imageFile = imageName;
    }

    public String getImageName() {
        return imageFile;
    }

    public void setImageName(String imageName) {
        this.imageFile = imageName;
    }

    public boolean sendCommand(BluetoothClient bluetoothClient, AssetManager assetManager) {
        if (bluetoothClient.sendMessage("image_command".getBytes())) {
            System.out.println("[+] Able to send image command.");
            try {
                InputStream inputStream = assetManager.open(this.imageFile);
                int size = inputStream.available();
                byte[] buffer = new byte[size];
                int lengthRead = inputStream.read(buffer);

                bluetoothClient.sendMessage(buffer);
                inputStream.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("[-] Couldn't use image command.");
            return false;
        }
        return false;
    }
}
