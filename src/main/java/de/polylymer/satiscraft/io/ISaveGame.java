package de.polylymer.satiscraft.io;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ISaveGame {

    private UUID owner;
    private Date lastModified;
    private File file;

    public ISaveGame(File file) {
        this.file = file;
        this.lastModified = getReader().read("last-modify");
    }

    public File getFile() {
        return file;
    }

    public ISaveGameReader getReader() {
        return new ISaveGameReader(this);
    }

    public ISaveGameWriter getWriter() {
        return new ISaveGameWriter(this);
    }

}
