package hello.advanced.aop.v2;

import hello.advanced.hellotrace.HelloTraceV1;
import hello.advanced.hellotrace.HelloTraceV2;
import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final HelloTraceV2 trace;

    public void save(TraceId traceId, String itemId){

        TraceStatus status = null;

        try{
            status = trace.beginSync(traceId, "OrderRepository.save()");

            //
            if (itemId.equals("ex")){
                throw new IllegalStateException("예외 발생");
            }
            sleep(1000);
            //

            trace.end(status);
        }catch (Exception e){
            trace.exception(status, e);
            throw e; //예외를 반드시 다시 던져줘야 함.
        }


    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
