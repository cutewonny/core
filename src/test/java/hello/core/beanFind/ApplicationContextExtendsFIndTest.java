package hello.core.beanFind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFIndTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(testConfig.class);

    //테스트 코드
    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 2개 이상 있으면, 중복 오류 발생")
    void findBeanByParentTypeDuplicate(){
        DiscountPolicy bean = ac.getBean(DiscountPolicy.class);//ctrl alt V
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);//오류 발생
        //NoUniqueBeanDefinitionException 에러 발생
        //expected single matching bean but found 2: rateDiscountPolicy,fixDiscountPolicy
    }

    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 2개 이상 있으면, 중복 오류 테스트 코드 작성")
    void findBeanByParentTypeDuplicate2(){
        assertThrows(NoUniqueBeanDefinitionException.class, ()->ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 2개 이상 있으면, 빈 이름을 지정")
    void findBeanByParentType(){
        DiscountPolicy bean = ac.getBean("rateDiscountPolicy",DiscountPolicy.class);//ctrl alt V
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }


    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanbySubType(){
        DiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);//ctrl alt V
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    //설정 파일 만들기
    @Configuration
    static class testConfig{
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }

    }
}
