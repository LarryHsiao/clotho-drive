package com.larryhsiao.clotho.drive;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.larryhsiao.clotho.Source;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Source to build a file id which is just created to Drive.
 */
public class CreatedFileId implements Source<String> {
    private final Drive drive;
    private final Source<String> parentIdSrc;
    private final String newFileName;
    private final String mimeType;

    public CreatedFileId(
        Drive drive,
        Source<String> parentIdSrc,
        String newFileName,
        String mimeType
    ) {
        this.drive = drive;
        this.parentIdSrc = parentIdSrc;
        this.newFileName = newFileName;
        this.mimeType = mimeType;
    }

    @Override
    public String value() {
        try {
            final String parentId = this.parentIdSrc.value();
            List<File> exist = drive.files().list()
                .setQ(queryString(parentId)).execute()
                .getFiles();
            if (exist.size() > 0) {
                return exist.get(0).getId();
            }
            File googleFile = drive.files()
                .create(
                    new File()
                        .setParents(Collections.singletonList(parentId))
                        .setMimeType(mimeType)
                        .setName(newFileName)
                ).execute();
            if (googleFile == null) {
                throw new IOException("Null result when requesting file creation.");
            }
            return googleFile.getId();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String queryString(String parentId) {
        String res = "name='" + newFileName +
            "' and '" + parentId + "' in parents and trashed = false";
        if (!mimeType.isEmpty()) {
            res = res + " and mimeType='" + mimeType + "'";
        }
        return res;
    }
}