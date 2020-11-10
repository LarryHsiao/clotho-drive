package com.larryhsiao.clotho.drive;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for {@link DFile}.
 */
class DFileImplTest {
    /**
     * Check some field values.
     */
    @Test
    void fields() {
        var parentId = new RootFileId();
        var fileName = "testFile.txt";
        var mimeType = "plain/text";
        var drive = new TestClient().value();
        var fileId = new CreatedFileId(drive,
            parentId,
            fileName,
            mimeType
        ).value();
        var file = new QueriedDFiles(
            drive,
            new FileByName(
                drive,
                fileName,
                parentId.value()
            )
        ).value().get(0);
        Assertions.assertEquals(fileName, file.name());
        Assertions.assertEquals(fileId, file.id());
        Assertions.assertEquals(mimeType, file.mimeType());
    }

    /**
     * Test the deletion action should work.
     */
    @Test
    void delete() {
        var parentId = new RootFileId();
        var fileName = "testFile.txt";
        var drive = new TestClient().value();
        new CreatedFileId(drive, parentId, fileName, "plain/text").value();
        new QueriedDFiles(
            drive,
            new FileByName(drive, fileName, parentId.value())
        ).value().forEach(DFile::delete);
        Assertions.assertEquals(
            0,
            new FileByName(
                drive,
                fileName,
                parentId.value()
            ).value().getFiles().size()
        );
    }
}