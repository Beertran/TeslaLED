package com.teslaowls.teslaled;

import android.content.res.AssetManager;

public class LegacyCommand extends PanelCommand {

    private String scriptName;

    public LegacyCommand(String scriptName, int sleepDurationMs) {
        super(sleepDurationMs);

        this.scriptName = scriptName;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    @Override
    public boolean sendCommand(BluetoothClient bluetoothClient, AssetManager assetManager) {
        if (bluetoothClient.sendMessage("legacy_command".getBytes())) {
            System.out.println("[+] Able to send legacy command.");
            if (bluetoothClient.sendMessage(this.scriptName.getBytes())) {
                return true;
            } else {
                System.out.println("[-] Couldn't send legacy command.");
                return false;
            }
        } else {
            System.out.println("[-] Couldn't use legacy command.");
            return false;
        }
    }
}
