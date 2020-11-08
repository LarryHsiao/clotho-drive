package com.larryhsiao.clotho.drive;

import com.google.api.services.drive.Drive;
import com.silverhetch.clotho.Action;

/**
 * Action to delete drive by file id.
 */
public class FileDeletionById implements Action {
    private final Drive drive;
    private final String fileId;

    public FileDeletionById(Drive drive, String fileId) {
        this.drive = drive;
        this.fileId = fileId;
    }

    @Override
    public void fire() {
        try {
            drive.files().delete(fileId).execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
