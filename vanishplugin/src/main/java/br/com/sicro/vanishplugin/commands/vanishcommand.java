package br.com.sicro.vanishplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

public class vanishcommand implements CommandExecutor {
    private final Plugin plugin;

    public vanishcommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String aliases, String[] strings) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("v")) {
            // Check if the player is already vanished
            if (player.hasMetadata("vanished")) {
                player.removeMetadata("vanished", plugin);
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    onlinePlayer.showPlayer(player); // Mostra o jogador para todos os outros jogadores
                }
                player.sendMessage(ChatColor.GREEN + "Você não está mais invisível.");
                player.setGameMode(GameMode.CREATIVE); // Altera o modo de jogo para Criativo
            } else {
                player.setMetadata("vanished", new FixedMetadataValue(plugin, true));
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    onlinePlayer.hidePlayer(player); // Esconde o jogador de todos os outros jogadores
                }
                player.sendMessage(ChatColor.GREEN + "Você está invisível.");
                player.setGameMode(GameMode.CREATIVE); // Altera o modo de jogo para Criativo
            }
            return true;
        }
        return false;
    }
}
