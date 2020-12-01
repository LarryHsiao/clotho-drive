package com.larryhsiao.clotho.drive;

import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.larryhsiao.clotho.Action;
import com.larryhsiao.clotho.Source;

import java.io.InputStream;

/**
 * Action to upload file.
 */
public class Uploading implements Action {
    private final Drive drive;
    private final Source<String> fileId;
    private final String fileName;
    private final InputStream uploadStream;
    private final String mimeType;

    public Uploading(
        Drive drive,
        Source<String> fileId,
        String fileName,
        InputStream uploadStream,
        String mimeType
    ) {
        this.drive = drive;
        this.fileId = fileId;
        this.fileName = fileName;
        this.uploadStream = uploadStream;
        this.mimeType = mimeType;
    }

    @Override
    public void fire() {
        try {
            drive.files().update(
                fileId.value(),
                new File().setName(fileName),
                new InputStreamContent(mimeType, uploadStream)
            ).execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
