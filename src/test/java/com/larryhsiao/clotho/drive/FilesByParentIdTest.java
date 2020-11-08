package com.larryhsiao.clotho.drive;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FilesByParentIdTest {

    /**
     * Check if this class can get actual files.
     */
    @Test
    void normalCase() {
        Assertions.assertNotEquals(
            0,
            new FilesByParentId(
                new TestClient().value(),
                "root"
            ).value().size()
        );
    }
}