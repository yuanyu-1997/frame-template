package cn.yuanyu.crud.config;


import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

// 自定义过滤规则
public class MyTypeFilter implements TypeFilter {
    /**
     * 返回true添加到容器中，返回false不添加到容器中
     *
     * @param metadataReader        读取到的当前正在扫描的类的信息
     * @param metadataReaderFactory 可以获取到其他任何类信息的
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        // 获取当前类注解的信息，
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        // 获取当前正在扫描的类，的类信息，类型是什么实现了什么接口等
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        // 获取当前类资源信息（类的路径等）
        Resource resource = metadataReader.getResource();

        // 根据业务决定是否加入容器中
        String className = annotationMetadata.getClassName();
        System.out.println("className => " + className);
        return false;
    }
}
