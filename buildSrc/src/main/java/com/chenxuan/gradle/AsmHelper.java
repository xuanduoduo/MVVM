package com.chenxuan.gradle;

import java.io.IOException;

public interface AsmHelper {
    byte[] modifyClass(byte[] srcClass) throws IOException;
}
