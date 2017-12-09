package me.Xephi.InfinityDispenser;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dispensers
{
    private InfinityDispenser plugin;

    private final Set<Location> dispensers;

    public Dispensers( InfinityDispenser plugin )
    {
        this.plugin = plugin;
        this.dispensers = new HashSet<>();

        load();
    }

    public boolean isDispenser( Location location )
    {
        return dispensers.contains( location );
    }

    public void addDispenser( Location location )
    {
        dispensers.add( location );
        save();
    }

    public void removeDispenser( Location location )
    {
        dispensers.remove( location );
        save();
    }

    private void save()
    {
        List<String> stringLocations = new ArrayList<>();
        for( Location location : dispensers )
        {
            stringLocations.add( Util.getStringLocation( location ) );
        }

        plugin.getConfig().set( "dispensers", stringLocations );
        plugin.saveConfig();
    }

    private void load()
    {
        dispensers.clear();
        plugin.reloadConfig();

        for( String location : plugin.getConfig().getStringList( "dispensers" ) )
        {
            dispensers.add( Util.getLocationString( location ) );
        }
    }
}