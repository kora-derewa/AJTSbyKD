package com.example.autoconnect;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.network.ClientConnection;

public class AutoConnectMod implements ClientModInitializer {
    // Set your server's address and port here
    private static final String SERVER_IP = "ip";
    private static final int SERVER_PORT = port; // default Minecraft server port

    @Override
    public void onInitializeClient() {
        // Register a game loop tick event to detect when the title screen is shown
        net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.currentScreen instanceof TitleScreen) {
                // Replace this condition with a check to avoid infinite loops
                if (shouldAutoConnect(client)) {
                    autoConnectToServer(client);
                }
            }
        });
    }

    private boolean shouldAutoConnect(MinecraftClient client) {
        return true;  // Auto-connect on every title screen display
    }

    private void autoConnectToServer(MinecraftClient client) {
        ServerInfo serverInfo = new ServerInfo("AutoConnect Server", SERVER_IP, false);
        ConnectScreen.connect(new TitleScreen(), client, ServerAddress.parse(SERVER_IP + ":" + SERVER_PORT), serverInfo);
    }
}
