package com.larryhsiao.clotho.drive;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for {@link FilesAtRoot}.
 */
class FilesAtRootTest {
    /**
     * Check if root folder have files.
     */
    @Test
    void normalCase() {
        Assertions.assertNotEquals(
            0,
            new FilesAtRoot(
                new TestClient().value()
            ).value().size()
        );
    }
}