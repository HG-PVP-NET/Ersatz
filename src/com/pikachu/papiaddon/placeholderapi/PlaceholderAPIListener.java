package com.pikachu.papiaddon.placeholderapi;

import com.pikachu.papiaddon.Main;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlaceholderAPIListener extends PlaceholderExpansion {

    private final Main plugin;
    private final String prefix;

    public PlaceholderAPIListener(Main plugin, String prefix) {
        this.plugin = plugin;
        this.prefix = prefix;
    }

    @Override
    public @NotNull String getIdentifier() {
        return prefix;
    }

    @Override
    public @NotNull String getAuthor() {
        return String.join(", ", plugin.getPluginMeta().getAuthors());
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getPluginMeta().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer offlinePlayer, @NotNull String identifier) {
        Player player = offlinePlayer != null && offlinePlayer.isOnline() ? offlinePlayer.getPlayer() : null;
        PlaceholderAPIEvent event = new PlaceholderAPIEvent(identifier, player, prefix);
        Bukkit.getServer().getPluginManager().callEvent(event);
        return event.getResult();
    }
}
