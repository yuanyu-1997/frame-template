import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class AppTest {
    @Test
    public void test() throws UnknownHostException {
        System.out.println(InetAddress.getLocalHost().getHostAddress());
    }
}
