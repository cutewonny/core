package hello.core.beanFind;

import hello.core.Order.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextInfoExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameAppConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByParentTypeDuplicate() {
        //DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        //NoUniqueBeanDefinitionException
        //expected single matching bean but found 2: rateDiscountPolicy,fixDiscountPolicy

        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(DiscountPolicy.class));

    }

    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면, 빈 이름을 지정한다")
    void findBeanByParentTypeBeanName() {
        DiscountPolicy bean = ac.getBean("fixDiscountPolicy", DiscountPolicy.class);
        //NoUniqueBeanDefinitionException
        //expected single matching bean but found 2: rateDiscountPolicy,fixDiscountPolicy

        assertThat(bean).isInstanceOf(FixDiscountPolicy.class);

    }

    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면, 특정 하위 타입으로 조회")
    void findBeanBySubType() {
        DiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        //NoUniqueBeanDefinitionException
        //expected single matching bean but found 2: rateDiscountPolicy,fixDiscountPolicy

        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);

    }

    @Test
    @DisplayName("부모 타입으로 모두 조회")
    void findAllBeanByParentType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for(String key: beansOfType.keySet()){
            System.out.println("key ="+key+" value= "+beansOfType.get(key));
        }
        /*
        key =rateDiscountPolicy value= hello.core.discount.RateDiscountPolicy@59d2400d
        key =fixDiscountPolicy value= hello.core.discount.FixDiscountPolicy@75cd8043
         */
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    void findAllBeanByObjectType(){//스프링에 등록된 모든 객체가 출력된다
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for(String key: beansOfType.keySet()){
            System.out.println("key ="+key+" value= "+beansOfType.get(key));
        }

    }

    //AppConfig 만들기
    static class SameAppConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }

}
