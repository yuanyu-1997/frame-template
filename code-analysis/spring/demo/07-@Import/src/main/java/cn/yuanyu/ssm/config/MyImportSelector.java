package cn.yuanyu.ssm.config;


import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

// 自定义逻辑返回需要导入的组件
public class MyImportSelector implements ImportSelector {
    /**
     * @param importingClassMetadata 当前标注@Import注解的类的所有注解信息
     * @return 导入到容器中的组件全类名
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // importingClassMetadata
        // 方法不要返回null值
        return new String[]{"cn.yuanyu.ssm.bean.Blue", "cn.yuanyu.ssm.bean.Yellow"};
    }
}