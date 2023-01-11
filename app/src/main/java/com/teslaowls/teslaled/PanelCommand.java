package com.teslaowls.teslaled;

import android.content.Context;
import android.content.res.AssetManager;

public abstract class PanelCommand {
    public PanelCommand() {}

    public abstract boolean sendCommand(BluetoothClient bluetoothClient, AssetManager assetManager);
}
