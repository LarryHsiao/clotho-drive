package com.larryhsiao.clotho.drive;

import com.google.api.services.drive.Drive;
import com.larryhsiao.clotho.Source;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

/**
 * Test for {@link Uploading}.
 */
class UploadingTest {

    /**
     * Check if text uploads.
     */
    @Test
    void normalCase() {
        Source<String> parentId = new RootFileId();
        String fileName = "testFile.txt";
        Drive drive = new TestClient().value();
        Source<String> fileId = new CreatedFileId(drive, parentId, fileName, "plain/text");
        new Uploading(
            drive,
            fileId,
            fileName,
            new ByteArrayInputStream("This is sample".getBytes()),
            "plain/text"
        ).fire();
        Assertions.assertEquals(
            1,
            new FileByName(
                drive,
                fileName,
                parentId.value()
            ).value().getFiles().size()
        );
        new FileDeletionById(
            drive,
            fileId.value()
        ).fire();
    }
}