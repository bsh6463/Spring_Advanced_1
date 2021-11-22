package hello.advanced.trace.template;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

    @Test
    void templateMethodV0(){
        logic1();
        logic2();
    }

    /**
     * 시간을 찍는 로직과, 비즈니스 로직이 섞여있음.
     */
    private void logic1(){
        long startTime = System.currentTimeMillis();

        //비즈니스 로직 시행
        log.info("비즈니스 로직1 실행");

        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);

    }

    /**
     * 시간을 찍는 로직과, 비즈니스 로직이 섞여있음.
     */
    private void logic2(){
        long startTime = System.currentTimeMillis();

        //비즈니스 로직 시행
        log.info("비즈니스 로직2 실행");

        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);

    }

    /**
     * 템플릿 메서드 패턴 적용
     */
    @Test
    void templateMethodV1(){

        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();

        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();
    }

    @Test
    void templateMethodV2(){

        //상속 받으면서 구현 바로 함.
        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                 log.info("비즈니스 로직 1 실행");
            }
        };
        log.info("클래스 이름1 = {}", template1.getClass());
        template1.execute();

        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                 log.info("비즈니스 로직 2 실행");
            }
        };
        template2.execute();
        log.info("클래스 이름2 = {}", template2.getClass());
    }


}
