package kyubii.de.fantasywar.utils;

import kyubii.de.fantasywar.FantasyWar;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("all")
public class PlayerWarpUtils {
    public List<String> warpList;
    WarpsConfig warpsConfig = new WarpsConfig();
    FantasyWar instance = FantasyWar.getInstance();

    public void addWarp(UUID uuid, String warp, Location loc){
        if (!warpExists(warp)) {
            warpList = getWarpList();
            warpList.add(warp);
            warpsConfig.get().set(warp + ".Owner", uuid.toString());
            warpsConfig.get().set(warp + ".X", loc.getX());
            warpsConfig.get().set(warp + ".Y", loc.getY());
            warpsConfig.get().set(warp + ".Z", loc.getZ());
            warpsConfig.get().set(warp + ".YAW", loc.getYaw());
            warpsConfig.get().set(warp + ".PITCH", loc.getPitch());
            warpsConfig.get().set(warp + ".World", loc.getWorld().getName());
            warpsConfig.get().set(uuid + ".Count", getWarpCount(uuid) + 1);
            warpsConfig.get().set("warps", warpList);
            warpsConfig.save();
            warpsConfig.reload();

        }
    }
    public void removeWarp(String warp){
        warpList = getWarpList();
        warpList.remove(warp);
        String uuid = warpsConfig.get().getString(warp + ".Owner");
        warpsConfig.get().set(uuid + ".Count", getWarpCount(UUID.fromString(uuid)) - 1);
        warpsConfig.get().set(warp, null);
        warpsConfig.get().set("warps", warpList);
        warpsConfig.save();
        warpsConfig.reload();

    }
    public Location getWarpLocation(String warp){
        if (warpExists(warp)){
            World world = Bukkit.getWorld(warpsConfig.get().getString(warp + ".World"));
            float yaw = (float) warpsConfig.get().getDouble(warp + ".YAW");
            float pitch = (float) warpsConfig.get().getDouble(warp + ".PITCH");
            Location location = new Location(world, warpsConfig.get().getDouble(warp + ".X"), warpsConfig.get().getDouble(warp + ".Y"), warpsConfig.get().getDouble(warp + ".Z"), yaw, pitch);
            return location;
        }
        return null;
    }
    public Boolean warpExists(String warp){
        return warpsConfig.get().contains(warp) == true;
    }
    public Integer getWarpCount(UUID uuid){
        int count = 0;
        if (warpsConfig.get().contains(uuid.toString())){
            count = warpsConfig.get().getInt(uuid + ".Count");
        }
        return count;
    }
    public Boolean isOwnerOfWarp(UUID uuid, String warp) {
        if (warpExists(warp)) {
            String owner = warpsConfig.get().getString(warp + ".Owner");
            if (uuid.toString().equalsIgnoreCase(owner)){
                return true;
            }
        }
        return false;
    }
    public String getWarpOwner(String warp){
        String owner = warpsConfig.get().getString(warp + ".Owner");
        OfflinePlayer player = Bukkit.getServer().getOfflinePlayer(UUID.fromString(owner));
        return player.getName();
    }
    public List<String> getWarpList() {
        if (warpsConfig.get().getList("warps") !=null){
            return (List<String>) warpsConfig.get().getList("warps");
        }
        return new ArrayList<>();
    }

}
