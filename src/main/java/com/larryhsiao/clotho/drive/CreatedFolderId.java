package com.larryhsiao.clotho.drive;

import com.google.api.services.drive.Drive;
import com.larryhsiao.clotho.Source;

/**
 * Source to build a File id that is a folder just created on Drive.
 */
public class CreatedFolderId implements Source<String> {
    private final Drive drive;
    private final Source<String> parentId;
    private final String folderName;

    public CreatedFolderId(
        Drive drive,
        Source<String> parentId,
        String folderName
    ) {
        this.drive = drive;
        this.parentId = parentId;
        this.folderName = folderName;
    }

    @Override
    public String value() {
        return new CreatedFileId(
            drive,
            parentId,
            folderName,
            "application/vnd.google-apps.folder"
        ).value();
    }
}