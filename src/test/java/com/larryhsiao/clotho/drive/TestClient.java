package com.larryhsiao.clotho.drive;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.larryhsiao.clotho.Source;

/**
 * Source to build {@link Drive} for unit tests.
 */
public class TestClient implements Source<Drive> {
    @Override
    public Drive value() {
        return new DesktopClient(
            "Clotho-drive unit test",
            getClass().getResourceAsStream("/credentials.json"),
            "build/token",
            DriveScopes.DRIVE_FILE,
            DriveScopes.DRIVE_METADATA_READONLY
        ).value();
    }
}
