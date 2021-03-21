package de.polylymer.satiscraft.commands;

import de.polylymer.satiscraft.config.Localization;
import de.polylymer.satiscraft.main.Satisfactory;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public abstract class AbstractCommand implements CommandExecutor {

    private String permissionNode = "";
    private final String name;

    public AbstractCommand(String name) {
        Satisfactory.getFactory().getCommand(name).setExecutor(this);
        this.name = name;
    }

    public AbstractCommand(String name, String... aliases) {
        Satisfactory.getFactory().getCommand(name).setExecutor(this);
        Satisfactory.getFactory().getCommand(name).setAliases(Arrays.asList(aliases.clone()));
        this.name = name;
    }

    public AbstractCommand(String name, String permissionNode) {
        Bukkit.getPluginManager().addPermission(new Permission(permissionNode));
        Satisfactory.getFactory().getCommand(name).setExecutor(this);
        this.permissionNode = permissionNode;
        this.name = name;
    }

    public AbstractCommand(String name, String permissionNode, String... aliases) {
        Bukkit.getPluginManager().addPermission(new Permission(permissionNode));
        Satisfactory.getFactory().getCommand(name).setExecutor(this);
        Satisfactory.getFactory().getCommand(name).setAliases(Arrays.asList(aliases.clone()));
        this.permissionNode = permissionNode;
        this.name = name;
    }

    public abstract void handle(CommandSender sender, String[] args);

    public String getName() {
        return name;
    }

    public String getPermissionNode() {
        return permissionNode;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(this.permissionNode.isEmpty()) {
            handle(sender,args);
        } else {
            if(sender.hasPermission(permissionNode)) {
                handle(sender,args);
            } else {
                sender.sendMessage(Localization.getMessage("basic.noPermissions", "en_en"));
            }
        }
        return false;
    }
}
