package com.larryhsiao.clotho.drive;

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
        var parentId = new RootFileId();
        var fileName = "testFile.txt";
        var drive = new TestClient().value();
        var fileId = new CreatedFileId(drive, parentId, fileName, "plain/text");
        var content = "This is sample";
        var downloadStream = new ByteArrayOutputStream();
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