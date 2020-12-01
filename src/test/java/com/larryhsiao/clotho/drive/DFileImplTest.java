package com.larryhsiao.clotho.drive;

import com.google.api.services.drive.Drive;
import com.larryhsiao.clotho.Source;
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
        Source<String> parentId = new RootFileId();
        String fileName = "testFile.txt";
        String mimeType = "plain/text";
        Drive drive = new TestClient().value();
        String fileId = new CreatedFileId(drive,
            parentId,
            fileName,
            mimeType
        ).value();
        DFile file = new QueriedDFiles(
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
        Source<String> parentId = new RootFileId();
        String fileName = "testFile.txt";
        Drive drive = new TestClient().value();
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