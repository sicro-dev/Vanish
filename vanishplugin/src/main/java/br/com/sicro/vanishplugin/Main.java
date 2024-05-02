package br.com.sicro.vanishplugin;

import br.com.sicro.vanishplugin.commands.vanishcommand;
import br.com.sicro.vanishplugin.listeners.vanishlistener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("VanishPlugin ativado com sucesso!");
        this.getCommand("v").setExecutor(new vanishcommand(this)); // Passando a referência do plugin
        this.getServer().getPluginManager().registerEvents(new vanishlistener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("VanishPlugin desativado com sucesso!");
    }

}
