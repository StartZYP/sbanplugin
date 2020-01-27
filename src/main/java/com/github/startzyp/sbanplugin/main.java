package com.github.startzyp.sbanplugin;

import com.github.startzyp.sbanplugin.Listener.PlayerEvents;
import com.github.startzyp.sbanplugin.PluginChannel.SbanChannnel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;

public class main extends JavaPlugin {
    public static JavaPlugin plugin;
    public static HashMap<String,String> playerrebootcode = new HashMap<>();

    @Override
    public void onEnable() {
        File config = new File(getDataFolder() + File.separator + "config.yml");
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
        }
        saveDefaultConfig();
        plugin = this;
        initevent();
        super.onEnable();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        return super.onCommand(sender, command, label, args);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }


    private void initevent(){
        new PlayerEvents();
        new SbanChannnel();
    }
}
