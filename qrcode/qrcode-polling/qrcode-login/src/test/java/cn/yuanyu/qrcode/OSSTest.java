package cn.yuanyu.qrcode;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OSSTest {
    @Autowired
    private OSS ossClient;
    @Value("${ali.oss.bucketName}")
    private String bucketName;
    @Test
    public void test_() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("F:/test/test.png");

        ossClient.putObject(bucketName, "hehe/test.png", inputStream);
    }

    @Test
    public void test_12() throws FileNotFoundException {
        test1();
        test2();
    }

    public void test1() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("F:/test/test.png");
        PutObjectResult res = ossClient.putObject(bucketName, "test.png", inputStream);
        System.out.println("res => " + res.getETag());
        System.out.println("res => " + res.getVersionId());
    }

    public void test2() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("F:/test/test.png");
        ossClient.putObject(bucketName, "test2.png", inputStream);
    }
}
