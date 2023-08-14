package kyubii.de.fantasywar.utils;

import kyubii.de.fantasywar.configs.QuestConfig;

import java.util.UUID;

@SuppressWarnings("all")
public class QuestUtils {
    QuestConfig config = new QuestConfig();

    public void createPlayer(UUID uuid){
        if (!playerExists(uuid)){
            createQuests(uuid, "mining");
        }
    }
    public boolean playerExists(UUID uuid){
        return config.get().contains(uuid.toString());
    }


    private void createQuests(UUID uuid, String quest){
        config.get().set(uuid + "." + quest + ".level", 0);
        config.get().set(uuid + "." + quest + ".exp", 0);
        config.get().set(uuid + "." + quest + ".exptolvlup", 0);
        config.get().set(uuid + "." + quest + ".reward", 100);
        config.save();
        config.reload();
    }
    public int getLevel(UUID uuid, String quest){
        return config.get().getInt(uuid + "." + quest + ".level");
    }

    public int getExp(UUID uuid, String quest){
        return config.get().getInt(uuid + "." + quest + ".exp");
    }
    public int getExpToLvlUp(UUID uuid, String quest){
        return config.get().getInt(uuid + "." + quest + ".exptolvlup");
    }
    public int getReward(UUID uuid, String quest){
        return config.get().getInt(uuid + "." + quest + ".reward");
    }
    public void setLevel(UUID uuid, String quest, int level){
        config.get().set(uuid + "." + quest + ".level", level);
        config.save();
        config.reload();
    }
    public void setExp(UUID uuid, String quest, int exp){
        config.get().set(uuid + "." + quest + ".exp", exp);
        config.save();
        config.reload();
    }
    public void setExpToLevelUp(UUID uuid, String quest, int exp){
        config.get().set(uuid + "." + quest + ".exptolvlup", exp);
        config.save();
        config.reload();
    }
    public void setReward(UUID uuid, String quest, int reward){
        config.get().set(uuid + "." + quest + ".reward", reward);
        config.save();
        config.reload();
    }
}
