package me.Xephi.InfinityDispenser;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class InfinityDispenser extends JavaPlugin
{
    private static final String PREFIX = ChatColor.YELLOW + "[InfDisp]" + ChatColor.GREEN + " ";

    private Dispensers dispensers;

    public InfinityDispenser() {}

    public void onEnable()
    {
        saveDefaultConfig();

        this.dispensers = new Dispensers( this );

        PluginManager pluginmanager = this.getServer().getPluginManager();

        //Events
        pluginmanager.registerEvents( new InfinityDispenserListener( this ), this );

        //Commands
        getCommand( "infdisp" ).setExecutor( new Commands( this ) );
    }

    public Dispensers getDispensers()
    {
        return dispensers;
    }

    public static String getPrefix()
    {
        return PREFIX;
    }
}