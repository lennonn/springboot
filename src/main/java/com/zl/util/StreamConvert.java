package com.zl.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/5/22.
 */
public class StreamConvert {
    //outputStream转inputStream
    public static ByteArrayInputStream parse(OutputStream out) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(1024);
        byte[] arr = baos.toByteArray();
        baos.writeTo(out);
        ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
        return swapStream;

    }
}
