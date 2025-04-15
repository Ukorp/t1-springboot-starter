package org.log.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.log.CustomLoggingConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

@Aspect
public class LogAspect {

    private final CustomLoggingConfiguration customLoggingConfiguration;

    private final Logger log = LoggerFactory.getLogger(LogAspect.class);

    public LogAspect(CustomLoggingConfiguration customLoggingConfiguration) {
        this.customLoggingConfiguration = customLoggingConfiguration;
    }

    @Pointcut("@annotation(org.log.annotation.CustomLogging)")
    public void customLogPointcut() {
    }

    @Before("customLogPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        Level level = customLoggingConfiguration.getLevelBefore();
        log.atLevel(level).log("Метод {} вызван. Аргументы: {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "customLogPointcut()",
            returning = "result"
    )
    public void logAfterReturn(JoinPoint joinPoint, Object result) {
        Level level = customLoggingConfiguration.getLevelReturn();
        log.atLevel(level).log("Метод {} вызван вернул объект класса: {}", joinPoint.getSignature().getName(),
                result != null ?
                        result.getClass() :
                        null);
    }

    @AfterThrowing(pointcut = "customLogPointcut()",
            throwing = "exception"
    )
    public void logAfterThrow(JoinPoint joinPoint, Exception exception) {
        Level level = customLoggingConfiguration.getLevelReturn();
        log.atLevel(level).log("Метод {} выбросил исключение: {}", joinPoint.getSignature().getName(), exception.getMessage());
    }
}
