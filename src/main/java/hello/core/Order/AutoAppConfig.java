package hello.core.Order;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/*
AppCofig.class 와 다르게 여기선
@ComponentScan: 자동으로 @Bean 이라는 애들을 빈으로 등록한다.
 */
@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    // 텅텅 비었다.. @Bean 으로 등록한 메소드가 없다..
}
