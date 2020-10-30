import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflexTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ReflexService reflexService = new ReflexService();
        reflexService.name = "rush...";
        //reflexService.show();
        Method method = reflexService.getClass().getDeclaredMethod("show", String.class);
        method.invoke(reflexService, "six");

    }
}
