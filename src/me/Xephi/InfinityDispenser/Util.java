package me.Xephi.InfinityDispenser;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;

import java.util.HashSet;
import java.util.Set;

public class Util
{
    private static final Set<Material> STANDING_MATERIALS = new HashSet<>();

    static
    {
        STANDING_MATERIALS.add( Material.AIR );
        STANDING_MATERIALS.add( Material.SAPLING );
        STANDING_MATERIALS.add( Material.POWERED_RAIL );
        STANDING_MATERIALS.add( Material.DETECTOR_RAIL );
        STANDING_MATERIALS.add( Material.LONG_GRASS );
        STANDING_MATERIALS.add( Material.DEAD_BUSH );
        STANDING_MATERIALS.add( Material.YELLOW_FLOWER );
        STANDING_MATERIALS.add( Material.RED_ROSE);
        STANDING_MATERIALS.add( Material.BROWN_MUSHROOM );
        STANDING_MATERIALS.add( Material.RED_MUSHROOM );
        STANDING_MATERIALS.add( Material.TORCH );
        STANDING_MATERIALS.add( Material.REDSTONE_WIRE );
        STANDING_MATERIALS.add( Material.SEEDS );
        STANDING_MATERIALS.add( Material.SIGN_POST );
        STANDING_MATERIALS.add( Material.WOODEN_DOOR );
        STANDING_MATERIALS.add( Material.LADDER );
        STANDING_MATERIALS.add( Material.RAILS );
        STANDING_MATERIALS.add( Material.WALL_SIGN );
        STANDING_MATERIALS.add( Material.LEVER );
        STANDING_MATERIALS.add( Material.STONE_PLATE );
        STANDING_MATERIALS.add( Material.IRON_DOOR_BLOCK );
        STANDING_MATERIALS.add( Material.WOOD_PLATE );
        STANDING_MATERIALS.add( Material.REDSTONE_TORCH_OFF );
        STANDING_MATERIALS.add( Material.REDSTONE_TORCH_ON );
        STANDING_MATERIALS.add( Material.STONE_BUTTON );
        STANDING_MATERIALS.add( Material.SNOW );
        STANDING_MATERIALS.add( Material.SUGAR_CANE_BLOCK );
        STANDING_MATERIALS.add( Material.DIODE_BLOCK_OFF );
        STANDING_MATERIALS.add( Material.DIODE_BLOCK_ON );
        STANDING_MATERIALS.add( Material.TRAP_DOOR );
        STANDING_MATERIALS.add( Material.PUMPKIN_STEM );
        STANDING_MATERIALS.add( Material.MELON_STEM );
        STANDING_MATERIALS.add( Material.VINE );
        STANDING_MATERIALS.add( Material.FENCE_GATE );
        STANDING_MATERIALS.add( Material.WATER_LILY );
        STANDING_MATERIALS.add( Material.NETHER_FENCE );
        STANDING_MATERIALS.add( Material.NETHER_WARTS );
        STANDING_MATERIALS.add( Material.TRIPWIRE_HOOK );
        STANDING_MATERIALS.add( Material.TRIPWIRE );
        STANDING_MATERIALS.add( Material.WATER );
        STANDING_MATERIALS.add( Material.STATIONARY_WATER );
    }

    public static Block getBlockTarget( LivingEntity entity )
    {
        return entity.getTargetBlock( STANDING_MATERIALS, 300 );
    }

    public static String getStringLocation( final Location l )
    {
        if( l == null )
        {
            return "";
        }
        return l.getWorld().getName() + ":" + l.getBlockX() + ":" + l.getBlockY() + ":" + l.getBlockZ();
    }

    static public Location getLocationString( final String s )
    {
        if( s == null || s.trim().equals( "" ) )
        {
            return null;
        }

        final String[] parts = s.split(":");

        if (parts.length == 4)
        {
            final World w = Bukkit.getServer().getWorld(parts[0]);
            final int x = Integer.parseInt(parts[1]);
            final int y = Integer.parseInt(parts[2]);
            final int z = Integer.parseInt(parts[3]);

            return new Location(w, x, y, z);
        }

        return null;
    }
}

