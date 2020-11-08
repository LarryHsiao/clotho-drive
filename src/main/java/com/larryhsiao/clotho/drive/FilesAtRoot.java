package com.larryhsiao.clotho.drive;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.FileList;
import com.silverhetch.clotho.Source;

/**
 * Source to build {@link FileList} for root of the Drive.
 */
public class FilesAtRoot implements Source<FileList> {
    private final Drive drive;

    public FilesAtRoot(Drive drive) {
        this.drive = drive;
    }

    @Override
    public FileList value() {
        return new FilesByParentId(drive, "root").value();
    }
}
