package me.Xephi.InfinityDispenser;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor
{
    private InfinityDispenser plugin;

    public Commands( InfinityDispenser plugin )
    {
        this.plugin = plugin;
    }

    public boolean onCommand( CommandSender sender, Command cmd, String label, String[] args )
    {
        if( !sender.hasPermission( "infdispenser.command" ) )
        {
            return true;
        }

        if( sender instanceof ConsoleCommandSender )
        {
            sender.sendMessage( InfinityDispenser.getPrefix() + "You can't use /infdisp command out the game" );
            return true;
        }

        if( args.length == 0 )
        {
            sender.sendMessage( InfinityDispenser.getPrefix() + "Use /infdisp set - to set a infinitydispenser" );
            sender.sendMessage( InfinityDispenser.getPrefix() + "Use /infdisp remove - to remove a infinitydispenser" );
            return true;
        }

        Player player = (Player) sender;

        if( Util.getBlockTarget( player ).getType() != Material.DISPENSER && Util.getBlockTarget( player ).getType() != Material.DROPPER )
        {
            player.sendMessage( InfinityDispenser.getPrefix() + "You must target a dispenser or a dropper !" );
            return true;
        }

        Block dispenser = Util.getBlockTarget( player );

        if( args[0].equalsIgnoreCase( "set" ) )
        {
            if( !plugin.getDispensers().isDispenser( dispenser.getLocation() ) )
            {
                plugin.getDispensers().addDispenser( dispenser.getLocation() );
                player.sendMessage( InfinityDispenser.getPrefix() + "You created an infinity dispenser!" );
            }
            else
            {
                player.sendMessage( InfinityDispenser.getPrefix() + ChatColor.RED +  "This is already an infinity dispenser!" );
            }

            return true;
        }

        if( args[0].equalsIgnoreCase( "remove" ) )
        {
            if( plugin.getDispensers().isDispenser( dispenser.getLocation() ) )
            {
                plugin.getDispensers().removeDispenser( dispenser.getLocation() );
            }

            player.sendMessage( InfinityDispenser.getPrefix() + "Successfully removed the infinity dispenser!" );
            return true;
        }

        sender.sendMessage( InfinityDispenser.getPrefix() + ChatColor.RED + "Unknown sub-command!" );
        return true;
    }
}

