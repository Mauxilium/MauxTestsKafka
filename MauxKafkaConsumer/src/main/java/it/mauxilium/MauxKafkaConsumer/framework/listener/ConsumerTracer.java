package it.mauxilium.MauxKafkaConsumer.framework.listener;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
public class ConsumerTracer {

    private static final String KIBANA_CONSUMING_LOG = "KIBANA RECEIVED: duration=%s  payload=%s";

    @Pointcut("execution(public void it.mauxilium.MauxKafkaConsumer.framework.listener.KafkaSimpleConsumer.consumeMessage(..))")
    public void simpleConsumerMessage() {
    }

    @Around("simpleConsumerMessage()")
    public void consumingLogging(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        pjp.proceed();
        long end = System.nanoTime();

        log.info(String.format(
                KIBANA_CONSUMING_LOG,
                TimeUnit.NANOSECONDS.toMillis(end - start),
                pjp.getArgs()[0]));
    }
}
