package fr.kappacite.customentity;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomEntity extends JavaPlugin {

    @Override
    public void onLoad() {
        CustomEntityType.registerEntities();
    }

    public void onEnable(){
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
    }

    public void onDisable(){
        CustomEntityType.unregisterEntities();
    }

}
