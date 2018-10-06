package com.robomwm.nobedrock;

import me.ryanhamshire.GriefPrevention.DataStore;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created on 10/5/2018.
 *
 * @author RoboMWM
 */
public class NoBedrock extends JavaPlugin implements Listener
{
    private DataStore dataStore;

    public void onEnable()
    {
        GriefPrevention griefPrevention = (GriefPrevention)getServer().getPluginManager().getPlugin("GriefPrevention");
        dataStore = griefPrevention.dataStore;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private void onBlockPlace(BlockPlaceEvent event)
    {
        boolean cancel = event.getBlock().getType().equals(Material.BEDROCK) && dataStore.getClaimAt(event.getBlock().getLocation(), true, null) != null;
        event.setCancelled(cancel);
        event.setBuild(!cancel);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private void onBlockBuild(BlockCanBuildEvent event)
    {
        boolean cancel = event.getBlock().getType().equals(Material.BEDROCK) && dataStore.getClaimAt(event.getBlock().getLocation(), true, null) != null;
        event.setBuildable(!cancel);
    }
}
