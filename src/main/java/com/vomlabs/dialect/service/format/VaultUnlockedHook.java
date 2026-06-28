package com.vomlabs.dialect.service.format;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class VaultUnlockedHook {

    private final Logger logger;
    private boolean vaultUnlockedAvailable;
    private Object economy;
    private Object permission;
    private Object chat;

    public VaultUnlockedHook(Logger logger) {
        this.logger = logger;
        detect();
    }

    private void detect() {
        try {
            if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
                RegisteredServiceProvider<?> econProvider = Bukkit.getServicesManager()
                    .getRegistration(Class.forName("net.milkbowl.vault2.economy.Economy"));
                if (econProvider != null) {
                    economy = econProvider.getProvider();
                }

                RegisteredServiceProvider<?> permProvider = Bukkit.getServicesManager()
                    .getRegistration(Class.forName("net.milkbowl.vault2.permission.PermissionUnlocked"));
                if (permProvider != null) {
                    permission = permProvider.getProvider();
                }

                RegisteredServiceProvider<?> chatProvider = Bukkit.getServicesManager()
                    .getRegistration(Class.forName("net.milkbowl.vault2.chat.ChatUnlocked"));
                if (chatProvider != null) {
                    chat = chatProvider.getProvider();
                }

                vaultUnlockedAvailable = (economy != null || permission != null || chat != null);
                if (vaultUnlockedAvailable) {
                    logger.info("VaultUnlocked hook: economy=" + (economy != null)
                        + " permission=" + (permission != null)
                        + " chat=" + (chat != null));
                }
            }
        } catch (Exception e) {
            vaultUnlockedAvailable = false;
        }
    }

    public boolean isAvailable() {
        return vaultUnlockedAvailable;
    }

    public Optional<BigDecimal> getBalance(Player player) {
        if (!vaultUnlockedAvailable || economy == null) {
            return Optional.empty();
        }
        try {
            Object result = economy.getClass()
                .getMethod("balance", String.class, UUID.class)
                .invoke(economy, "LazyDialect", player.getUniqueId());
            return Optional.of((BigDecimal) result);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public boolean hasPermission(Player player, String permissionNode) {
        if (!vaultUnlockedAvailable || permission == null) {
            return false;
        }
        try {
            Class<?> contextClass = Class.forName("net.milkbowl.vault2.helper.context.Context");
            Object globalContext = contextClass.getMethod("global").invoke(null);
            Object result = permission.getClass()
                .getMethod("playerHas", contextClass, UUID.class, String.class)
                .invoke(permission, globalContext, player.getUniqueId(), permissionNode);
            Class<?> triState = Class.forName("net.milkbowl.vault2.helper.TriState");
            Object triStateTrue = triState.getField("TRUE").get(null);
            return triStateTrue.equals(result);
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<String> getPrefix(Player player) {
        if (!vaultUnlockedAvailable || chat == null) {
            return Optional.empty();
        }
        try {
            Class<?> infoKeyClass = Class.forName("net.milkbowl.vault2.chat.InfoKey");
            Object prefixKey = infoKeyClass.getField("PREFIX").get(null);
            Object result = chat.getClass()
                .getMethod("playerInfo", UUID.class, infoKeyClass)
                .invoke(chat, player.getUniqueId(), prefixKey);
            return Optional.ofNullable(result != null ? result.toString() : null);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<String> getSuffix(Player player) {
        if (!vaultUnlockedAvailable || chat == null) {
            return Optional.empty();
        }
        try {
            Class<?> infoKeyClass = Class.forName("net.milkbowl.vault2.chat.InfoKey");
            Object suffixKey = infoKeyClass.getField("SUFFIX").get(null);
            Object result = chat.getClass()
                .getMethod("playerInfo", UUID.class, infoKeyClass)
                .invoke(chat, player.getUniqueId(), suffixKey);
            return Optional.ofNullable(result != null ? result.toString() : null);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
