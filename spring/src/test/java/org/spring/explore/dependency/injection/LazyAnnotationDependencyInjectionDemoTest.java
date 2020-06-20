package org.spring.explore.dependency.injection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 64. {@link org.springframework.beans.factory.ObjectProvider} 延迟依赖注入
 *
 * @author mason
 */
@Slf4j
class LazyAnnotationDependencyInjectionDemoTest {

    private AnnotationConfigApplicationContext applicationContext;

    @BeforeEach
    void setUp() {
        // 创建 BeanFactory 容器
        applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动应用上下文
        applicationContext.refresh();
    }

    @Test
    void getUserTest() {

        LazyAnnotationDependencyInjectionDemo lazyAnnotationDependencyInjectionDemo
                = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);

        assertEquals(lazyAnnotationDependencyInjectionDemo.getUser(),
                lazyAnnotationDependencyInjectionDemo.getUserObjectProvider().getObject());

        log.info(lazyAnnotationDependencyInjectionDemo.getUserObjectFactory().getObject().toString());

        lazyAnnotationDependencyInjectionDemo
                .getUserObjectProvider()
                .stream()
                .forEach(t -> log.info(t.toString()));
    }

    @AfterEach
    void tearDown() {
        applicationContext.close();
    }
}