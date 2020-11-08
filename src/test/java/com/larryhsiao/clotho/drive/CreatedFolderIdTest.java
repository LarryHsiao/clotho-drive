package com.larryhsiao.clotho.drive;

import com.silverhetch.clotho.source.ConstSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for {@link CreatedFolderId}
 */
class CreatedFolderIdTest {

    @Test
    void normalCase() {
        var fileName = "Test folder";
        var drive = new TestClient().value();
        new CreatedFolderId(
            drive,
            new ConstSource<>("root"),
            fileName
        ).value();
        Assertions.assertEquals(
            1,
            new FileByName(
                drive,
                fileName,
                "root"
            ).value().getFiles().size()
        );
    }
}