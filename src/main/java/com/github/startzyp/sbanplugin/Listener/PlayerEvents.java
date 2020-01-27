package com.github.startzyp.sbanplugin.Listener;

import com.github.startzyp.sbanplugin.main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

import static org.bukkit.Bukkit.getOnlinePlayers;
import static org.bukkit.Bukkit.getServer;

public class PlayerEvents implements Listener {
    public PlayerEvents() {
        getServer().getPluginManager().registerEvents(this, main.plugin);
    }

    @EventHandler
    public void PlayerQuitGame(PlayerQuitEvent event){
        String name = event.getPlayer().getName();
        main.playerrebootcode.remove(name);
    }

    @EventHandler
    public void PlayerJoinGame(PlayerJoinEvent event){
        UUID uniqueId = event.getPlayer().getUniqueId();
        String name = event.getPlayer().getName();
        Bukkit.getServer().getScheduler().runTaskLater(main.plugin, new BukkitRunnable() {
            @Override
            public void run()
            {
                if (getServer().getPlayer(uniqueId).isOnline()){
                    if (main.playerrebootcode.containsKey(name)){
                        int number =0;
                        String rebootcode = main.playerrebootcode.get(name);
                        for (Player player:getOnlinePlayers()){
                            String playername = player.getName();
                            if (playername.equals(name)){
                                continue;
                            }
                            String playerrebootcode = main.playerrebootcode.get(playername);
                            if (playerrebootcode.equals(rebootcode)){
                                number+=1;
                            }
                        }
                        if (number>=main.Online){
                            Bukkit.getServer().getPlayer(uniqueId).kickPlayer("§e§l[SBan]§4§l你所在的机器已经开满三个客户端，所以被禁止");
                        }
                    }else {
                        Bukkit.getServer().getPlayer(uniqueId).kickPlayer("§e§l[SBan]§4§l你未安装SbanMod");
                    }
                }else {
                    main.playerrebootcode.remove(name);
                }

            }
        }, 20L *main.CheckSpace);
    }
}
