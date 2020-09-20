package cn.yuanyu.tx.init;


import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;
import java.util.Set;

/**
 * @author yuanyu
 */
public class ServletInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        addFilter(ctx);
    }

    public void addFilter(ServletContext ctx) {
        // 1.字符编码过滤器（放在所有过滤器之前）
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceRequestEncoding(true);
        characterEncodingFilter.setForceRequestEncoding(true);
        ctx.addFilter("characterEncodingFilter", characterEncodingFilter)
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
        // 2.使用Rest风格的URI
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        ctx.addFilter("hiddenHttpMethodFilter", hiddenHttpMethodFilter)
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
        //3.
        //FormContentFilter formContentFilter = new FormContentFilter();
        //ctx.addFilter("formContentFilter", formContentFilter)
        //        .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
    }

}
