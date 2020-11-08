package com.larryhsiao.clotho.drive;

/**
 * A file in Drive
 */
public interface DFile {
    String id();

    String name();

    String mimeType();

    void delete();
}
