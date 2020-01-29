package com.github.startzyp.sbanplugin.PluginChannel;

import com.github.startzyp.sbanplugin.Entity.PlayerData;
import com.github.startzyp.sbanplugin.main;
import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.nio.charset.StandardCharsets;

import static org.bukkit.Bukkit.getServer;

public class SbanChannnel implements PluginMessageListener {
    public SbanChannnel() {
        getServer().getMessenger().registerIncomingPluginChannel(main.plugin,"sban", this);
        Bukkit.getMessenger().registerOutgoingPluginChannel(main.plugin, "sban");
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        System.out.println(channel);
        if (channel.equalsIgnoreCase("sban")){
            Gson gson = new Gson();
            PlayerData playerData = gson.fromJson(new String(message, StandardCharsets.UTF_8), PlayerData.class);
            System.out.println(playerData.toString());
            main.playerrebootcode.put(playerData.getPlayername(),playerData.getRebootCode());
        }
    }
}
