package com.github.startzyp.sbanplugin;

import com.github.startzyp.sbanplugin.Listener.PlayerEvents;
import com.github.startzyp.sbanplugin.PluginChannel.SbanChannnel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.bukkit.Bukkit.getOnlinePlayers;

public class main extends JavaPlugin {
    public static JavaPlugin plugin;
    public static HashMap<String,String> playerrebootcode = new HashMap<>();
    public static List<String> bancode = new ArrayList<>();
    public static int CheckSpace = 20;
    public static int Online = 3;

    @Override
    public void onEnable() {
        File config = new File(getDataFolder() + File.separator + "config.yml");
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
        }
        saveDefaultConfig();
        plugin = this;
        bancode = getConfig().getStringList("BanCode");
        CheckSpace = getConfig().getInt("CheckSpace");
        Online = getConfig().getInt("Online");
        getLogger().info("§e§l[SBan]§4§l加载配置项CheckSpace:"+CheckSpace+"|Online:"+Online);
        for (String code:bancode){
            getLogger().info("§e§l[SBan]§4§lBanCode:"+code);
        }
        initevent();
        super.onEnable();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("sban")){
            if (args.length==1){
                if (getServer().getPlayer(args[0]).isOnline()&&main.playerrebootcode.containsKey(args[0])){
                    String rebootcode = main.playerrebootcode.get(args[0]);
                    bancode.add(rebootcode);
                    getConfig().set("BanCode",bancode);
                    saveConfig();
                    getServer().getPlayer(args[0]).kickPlayer("§e§l[SBan]§4§l您的机器已被Sban禁止入内");
                }else {
                    sender.sendMessage("§e§l[SBan]§4§l此玩家不在线或没有信息");
                }
            }else if (args.length==2){
                if (args[0].equalsIgnoreCase("remove")){
                    for (String code:bancode){
                        if(code.contains(args[1])){
                            bancode.remove(code);
                            getConfig().set("BanCode",bancode);
                            saveConfig();
                            sender.sendMessage("§e§l[SBan]§4§l成功移除:"+code);
                            return true;
                        }
                    }
                }else if (args[0].equalsIgnoreCase("account")){
                    if (getServer().getPlayer(args[1]).isOnline()&&main.playerrebootcode.containsKey(args[1])){
                        sender.sendMessage("§e§l[SBan]§4§l玩家同时在线如下：");
                        String rebootcode = main.playerrebootcode.get(args[1]);
                        for (Player player:getOnlinePlayers()){
                            String playername = player.getName();
                            String playerrebootcode = main.playerrebootcode.get(playername);
                            if (playerrebootcode.equals(rebootcode)){
                                sender.sendMessage("§e§l[SBan]§4§l玩家:"+playername+"§b§2机器码:"+playerrebootcode);
                            }
                        }
                    }else {
                        sender.sendMessage("§e§l[SBan]§4§l此玩家不在线或没有信息");
                    }
                }
            }
        }
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
