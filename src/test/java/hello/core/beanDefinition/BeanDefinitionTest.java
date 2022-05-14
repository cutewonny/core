package hello.core.beanDefinition;

import hello.core.Order.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinitionTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 설정 메타 정보 확인")
    void findApplicationBean(){
        String[] beanDefintionNames = ac.getBeanDefinitionNames();
        for(String name : beanDefintionNames){
            BeanDefinition beanDefinition = ac.getBeanDefinition(name);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinitionName = "+name+"beanDefintion = "+beanDefinition);
            }
        }
    }
}
