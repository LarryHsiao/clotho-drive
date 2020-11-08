package com.larryhsiao.clotho.drive;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

/**
 * File implementation.
 */
public class DFileImpl implements DFile {
    private final Drive drive;
    private final File file;

    public DFileImpl(Drive drive, File file) {
        this.drive = drive;
        this.file = file;
    }

    @Override
    public String id() {
        return file.getId();
    }

    @Override
    public String name() {
        return file.getName();
    }

    @Override
    public String mimeType() {
        return file.getMimeType();
    }

    @Override
    public void delete() {
        try {
            drive.files().delete(file.getId()).execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
