package org.log.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.log.CustomLoggingConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class TimeMonitoringAspect {

    private final Logger log = LoggerFactory.getLogger(TimeMonitoringAspect.class);

    private final CustomLoggingConfiguration customLoggingConfiguration;

    public TimeMonitoringAspect(CustomLoggingConfiguration customLoggingConfiguration) {
        this.customLoggingConfiguration = customLoggingConfiguration;
    }

    @Pointcut("@annotation(org.log.annotation.TimeMonitoring)")
    public void customTimeMonitoringPointcut() {}

    @Around("customTimeMonitoringPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        log.atLevel(customLoggingConfiguration.getLevelBefore()).log("Замер метода {} начался", joinPoint.getSignature().getName());
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            log.atLevel(customLoggingConfiguration.getLevelThrow()).log("Метод {} выбросил исключение на земере времени", joinPoint.getSignature().getName());
            throw throwable;
        }
        long end = System.currentTimeMillis();
        log.atLevel(customLoggingConfiguration.getLevelMonitoring()).log("Замер метода {} закончился", joinPoint.getSignature().getName());
        log.atLevel(customLoggingConfiguration.getLevelMonitoring()).log("Время выполнения метода {}: {} ms", joinPoint.getSignature().getName(), end - start);
        return result;
    }
}
