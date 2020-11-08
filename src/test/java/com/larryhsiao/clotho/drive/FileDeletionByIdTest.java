package com.larryhsiao.clotho.drive;

import com.silverhetch.clotho.source.ConstSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FileDeletionByIdTest {

    @Test
    void normalCase() {
        var fileName = "Test folder";
        var drive = new TestClient().value();
        new FileDeletionById(
            drive,
            new CreatedFolderId(
                drive,
                new ConstSource<>("root"),
                fileName
            ).value()
        ).fire();
        Assertions.assertEquals(
            0,
            new FileByName(
                drive,
                fileName,
                "root"
            ).value().getFiles().size()
        );
    }
}