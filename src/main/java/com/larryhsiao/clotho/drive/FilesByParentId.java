package com.larryhsiao.clotho.drive;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.FileList;
import com.larryhsiao.clotho.Source;

/**
 * Source to Build {@link FileList} by given parent id.
 */
public class FilesByParentId implements Source<FileList> {
    private final Drive drive;
    private final String parentId;

    public FilesByParentId(Drive drive, String parentId) {
        this.drive = drive;
        this.parentId = parentId;
    }

    @Override
    public FileList value() {
        try {
            return drive.files().list()
                .setQ("'" + parentId + "' in parents and trashed = false")
                .execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
