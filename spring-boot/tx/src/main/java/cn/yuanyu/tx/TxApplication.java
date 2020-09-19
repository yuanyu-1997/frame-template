package cn.yuanyu.tx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yuanyu
 */
// https://www.cnblogs.com/zwgblogs/p/13404142.html
// https://blog.csdn.net/acingdreamer/article/details/91873745
// https://www.pianshen.com/article/2061695048/
// https://www.cnblogs.com/jpfss/p/9151797.html

@SpringBootApplication
@MapperScan("cn.yuanyu.tx.mapper")
public class TxApplication {
    public static void main(String[] args) {
        SpringApplication.run(TxApplication.class, args);
    }

}
