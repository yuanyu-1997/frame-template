package cn.yuanyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * https://www.jb51.net/article/132881.htm
 * https://blog.csdn.net/ldsh304/article/details/94590828
 * https://jingyan.baidu.com/article/0a52e3f4fc53aabf62ed72b5.html
 * https://blog.csdn.net/chancein007/article/details/54344730
 * <p>
 * http://localhost:6969/h2-console
 *
 * @author yuanyu
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        new SpringApplication(App.class).run(args);
    }
}
