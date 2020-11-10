package com.larryhsiao.clotho.drive;

import com.google.api.services.drive.Drive;
import com.silverhetch.clotho.Source;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Test for {@link Downloading}.
 */
class DownloadingTest {

    /**
     * Check the downloaded content.
     */
    @Test
    void normalCase() {
        Source<String> parentId = new RootFileId();
        String fileName = "testFile.txt";
        Drive drive = new TestClient().value();
        Source<String> fileId = new CreatedFileId(drive, parentId, fileName, "plain/text");
        String content = "This is sample";
        ByteArrayOutputStream downloadStream = new ByteArrayOutputStream();
        new Uploading(
            drive,
            fileId,
            fileName,
            new ByteArrayInputStream(content.getBytes()),
            "plain/text"
        ).fire();
        new Downloading(
            drive,
            fileId,
            downloadStream
        ).fire();
        Assertions.assertEquals(
            content,
            new String(downloadStream.toByteArray())
        );
        new FileDeletionById(drive, fileId.value()).fire();
    }
}