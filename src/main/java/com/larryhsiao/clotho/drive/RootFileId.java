package com.larryhsiao.clotho.drive;

import com.larryhsiao.clotho.Source;

/**
 * Source to build root file id.
 */
public class RootFileId implements Source<String> {
    @Override
    public String value() {
        return "root";
    }
}
