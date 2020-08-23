package cn.yuanyu.qrcode;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileTest {

    @Test
    public void test_() throws IOException {
        File file = new File("./test.jpg");
        if (!file.exists()) {
            file.createNewFile();
        }
    }
}
