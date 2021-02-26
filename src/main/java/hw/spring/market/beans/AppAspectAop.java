package hw.spring.market.beans;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Aspect
@Component
public class AppAspectAop { // это для AOP
    /*public HashMap<String, Integer> count;
    public HashMap<String, Long> time;

    @PostConstruct
    public void init() {
        this.count = new HashMap<>();
        this.time = new HashMap<>();
    }
//    @Before("execution(public * hw.spring.market.*.*.*(..))")
//    public void beforeTestMethod() {
//        System.out.println("поймал");
//    }

    //Задание 1
    @Before("execution(public * hw.spring.market.*.*.*(..))")
    public void countNumberMethodCalls(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodsName = methodSignature.toShortString();
        Integer num = 1;
        if (count.containsKey(methodsName)) {
            num = count.get(methodsName) + 1;
        }
        count.put(methodsName, num);
        System.out.println(count);
    }

    //Задание 2
    @Around("execution(public * hw.spring.market.controller.*.*(..))")
    public Object timeOfControllersMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String methodsName = methodSignature.toShortString();

        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;

        long ms = duration;
        if (time.containsKey(methodsName)) {
            ms = time.get(methodsName) + duration;
        }
        time.put(methodsName, ms);
        System.out.println(time);
        return out;
    }

*/
}
