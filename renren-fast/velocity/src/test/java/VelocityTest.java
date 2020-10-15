import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Test;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

// https://www.jianshu.com/p/5913903324ff
public class VelocityTest {

    @Test
    public void test_() {
        // 初始化模板引擎
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();

        // 获取模板文件
        Template template = velocityEngine.getTemplate("template/Dao.java.vm", "UTF-8");

        // 设置变量
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("package", "io.renren.modules");
        velocityContext.put("moduleName", "generator");
        velocityContext.put("mainPath", "io.renren");

        // 表名 => Java类名
        velocityContext.put("className", "User"); //

        velocityContext.put("author", "yuanyu");
        velocityContext.put("email", "yuanyu.nobug@qq.com");
        velocityContext.put("datetime", new SimpleDateFormat("yyyy/MM/dd").format(new Date()));


        // 输出
        StringWriter sw = new StringWriter();
        template.merge(velocityContext, sw);
        System.out.println(sw.toString());

    }

}
