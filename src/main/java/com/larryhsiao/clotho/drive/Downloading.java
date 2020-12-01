package com.larryhsiao.clotho.drive;

import com.google.api.services.drive.Drive;
import com.larryhsiao.clotho.Action;
import com.larryhsiao.clotho.Source;

import java.io.OutputStream;

/**
 * Action to download file to given target.
 */
public class Downloading implements Action {
    private final Drive drive;
    private final Source<String> fileId;
    private final OutputStream target;

    public Downloading(Drive drive, Source<String> fileId, OutputStream target) {
        this.drive = drive;
        this.fileId = fileId;
        this.target = target;
    }

    @Override
    public void fire() {
        try {
            drive.files().get(fileId.value()).executeMediaAndDownloadTo(target);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
