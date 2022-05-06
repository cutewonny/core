package hello.core.beanFind;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextDuplicateBeanFindTest {
    //SameAppConfig를 가지고 스프링 컨테이너 생성
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameAppConfig.class);
    
    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByTypeDupicate(){
        //MemberRepository bean = ac.getBean(MemberRepository.class);//타입으로 조회

        // NoUniqueBeanDefinitionException
        // available: expected single matching bean but found 2: memberRepository1,memberRepository2

        //에러 예상 테스트 하기
        assertThrows(NoUniqueBeanDefinitionException.class,
                ()->ac.getBean(MemberRepository.class));
    }
    
    
    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정한다")
    void findBeanByName(){
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);

        //then
        org.assertj.core.api.Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);

    }
    
    @Test
    @DisplayName("특정 타입 모두 조회하기")
    void findAllBeanByType(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);

        for(String key : beansOfType.keySet()){
            System.out.println("key ="+key+"value= "+beansOfType.get(key));
        }
        System.out.println("BeanOfType = "+beansOfType);
        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);
    }


    //AppConfig 만들기
    static class SameAppConfig{
        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }

}
