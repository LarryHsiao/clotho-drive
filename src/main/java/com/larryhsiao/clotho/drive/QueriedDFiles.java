package com.larryhsiao.clotho.drive;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.FileList;
import com.larryhsiao.clotho.Source;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Source to build list of {@link DFile}.
 */
public class QueriedDFiles implements Source<List<DFile>> {
    private final Drive drive;
    private final Source<FileList> origin;

    public QueriedDFiles(
        Drive drive,
        Source<FileList> origin
    ) {
        this.drive = drive;
        this.origin = origin;
    }

    @Override
    public List<DFile> value() {
        return origin.value()
            .getFiles()
            .stream()
            .map(file -> new DFileImpl(drive, file))
            .collect(Collectors.toList());
    }
}
