package me.Xephi.InfinityDispenser;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.block.Dropper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.inventory.ItemStack;

public class InfinityDispenserListener implements Listener
{
    private InfinityDispenser plugin;

    public InfinityDispenserListener( InfinityDispenser plugin )
    {
        this.plugin = plugin;
    }

    @EventHandler( priority = EventPriority.HIGHEST )
    public void onDispenserUseItem( BlockDispenseEvent event )
    {
        if( event.isCancelled() )
        {
            return;
        }
        if( event.getBlock().getType() != Material.DISPENSER && event.getBlock().getType() != Material.DROPPER )
        {
            return;
        }

        Block block = event.getBlock();

        if( block.getType() != Material.DISPENSER )
        {
            return;
        }

        if( !plugin.getDispensers().isDispenser( block.getLocation() ) )
        {
            return;
        }

        plugin.getServer().getScheduler().scheduleSyncDelayedTask( plugin, new Runnable()
        {
            @Override
            public void run()
            {
                Dispenser disp = (Dispenser) block.getState();
                disp.getInventory().addItem( new ItemStack( event.getItem() ) );
            }
        }, 10L );
    }

    @EventHandler( priority = EventPriority.HIGHEST )
    public void onDispenserIgniteBlock( BlockIgniteEvent event )
    {
        if( event.isCancelled() || event.getIgnitingBlock() == null )
        {
            return;
        }

        if( event.getCause() != BlockIgniteEvent.IgniteCause.FLINT_AND_STEEL )
        {
            return;
        }

        if( !(event.getIgnitingBlock().getState() instanceof Dispenser) )
        {
            return;
        }

        Block block = event.getIgnitingBlock();

        if( block.getType() != Material.DISPENSER )
        {
            return;
        }

        if( !plugin.getDispensers().isDispenser( block.getLocation() ) )
        {
            return;
        }

        plugin.getServer().getScheduler().scheduleSyncDelayedTask( plugin, new Runnable()
        {
            @Override
            public void run()
            {
                Dispenser disp = (Dispenser) block.getState();
                disp.getInventory().remove( Material.FLINT_AND_STEEL );
                disp.getInventory().addItem( new ItemStack( Material.FLINT_AND_STEEL ) );
            }
        }, 10L );
    }

    @EventHandler( priority = EventPriority.HIGHEST )
    public void onDispenserBreak( BlockBreakEvent event )
    {
        if( event.isCancelled() || !(event.getBlock().getState() instanceof Dispenser) || !(event.getBlock().getState() instanceof Dropper) )
        {
            return;
        }

        if( plugin.getDispensers().isDispenser( event.getBlock().getLocation() ) )
        {
            plugin.getDispensers().removeDispenser( event.getBlock().getLocation() );
        }
    }
}