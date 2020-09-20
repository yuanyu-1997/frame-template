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
// https://blog.csdn.net/yulin_ganbo/article/details/78566835
// https://blog.csdn.net/v123411739/article/details/50803934
// https://blog.csdn.net/qq_37651267/article/details/92425172

@SpringBootApplication
@MapperScan("cn.yuanyu.tx.mapper")
public class TxApplication {
    public static void main(String[] args) {
        SpringApplication.run(TxApplication.class, args);
    }

}
