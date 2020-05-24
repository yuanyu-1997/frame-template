package cn.yuanyu.tx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yuanyu
 */
@SpringBootApplication
@MapperScan("cn.yuanyu.tx.mapper")
public class TxApplication {
    public static void main(String[] args) {
        SpringApplication.run(TxApplication.class, args);
    }

}
