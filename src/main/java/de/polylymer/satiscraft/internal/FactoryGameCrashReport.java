package de.polylymer.satiscraft.internal;

import de.polylymer.satiscraft.main.Satisfactory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FactoryGameCrashReport {

    public <E> FactoryGameCrashReport(Exception exception, E e, Class<?> clazz) {
        File dir = new File("./factorygame/crash-reports");
        if(!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File("./factorygame/crash-reports", "crash_" + new SimpleDateFormat("ss-mm-dd-MM-yyyy").format(new Date()));
        try {
            file.createNewFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("CRASH REPORT OF " + new SimpleDateFormat("ss-mm-dd-MM-yyyy").format(new Date()));
            writer.write("\nError occoured in element Type " + e.getClass().getSimpleName() + " in clazz " + clazz.getName() + ": " + clazz+ "\n");
            writer.write("What wrent wrong: " + exception.getMessage() + "\n \n ");
            for(StackTraceElement stackTraceElement : exception.getStackTrace()) {
                writer.write("\n" + stackTraceElement.getLineNumber() + " @ " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName());
                writer.write("\n" + stackTraceElement);
            }
            writer.write("\n" + System.getProperty("os.name") + " " + System.getProperty("os.version") + " withType<JavaJVM> @ " + System.getProperty("java.version"));
            writer.close();
            System.out.println("Crashreport saved under " + file.getAbsolutePath());
        } catch (IOException ioException) {
            System.out.println("Cannot save crashreport!");
            ioException.printStackTrace();
        }
        //TODO Create getAuthenticatedPlayers(); in FactoryGameServer.java
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.kickPlayer(ChatColor.DARK_RED + "" + ChatColor.BOLD + "AN ERROR OCCURRED" + ChatColor.GRAY + "\n \nAn internal error occurred in your connection.\n" + ChatColor.WHITE + " \n " + exception.getMessage() + "@" + exception.getCause());
        }
        System.exit(0);
    }

}
