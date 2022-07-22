//package com.dbs.configurations;
//
//import lombok.NoArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.context.annotation.Configuration;
//
//@Aspect
//@Configuration
//@Slf4j
//@NoArgsConstructor
//public class LoggingAspectConfiguration {
//
//    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping) " +
//            "|| @annotation(org.springframework.web.bind.annotation.PutMapping) " +
//            "|| @annotation(org.springframework.web.bind.annotation.PostMapping) " +
//            "|| @annotation(org.springframework.web.bind.annotation.RequestMapping) " +
//            "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
//    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        log.info("Invoking {} with {} ", proceedingJoinPoint, proceedingJoinPoint.getArgs());
//        long startTime = System.currentTimeMillis();
//        Object result = null;
//
//        long timeTaken;
//        try {
//            result = proceedingJoinPoint.proceed();
//        } catch (Exception e) {
//            log.error("Exception occurred :: ", e);
//        } finally {
//            timeTaken = System.currentTimeMillis() - startTime;
//            log.info("Time Taken by {} is {}ms, return value {} ", proceedingJoinPoint, timeTaken, new Object[]{result});
//        }
//
//        return result;
//    }
//
//}
