package com.yang.study.applicationContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MyComponent implements ApplicationContextAware {
    
    private final ApplicationContext context1;
    
    private static ApplicationContext context2;
    
    @Autowired
    public MyComponent(ApplicationContext context) {
        this.context1 = context;
    }
    
    public void doSomething() {
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(this);
        
        System.out.println("1" + context1.getApplicationName());
        System.out.println("2" + context2.getApplicationName());
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context2 = applicationContext;
    }
    
//    @GetMapping
//    public void demo() throws InterruptedException {
//        myComponent.doSomething();
//            final ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 10, 120, TimeUnit.SECONDS,
    //            new ArrayBlockingQueue<>(2000), new ThreadPoolExecutor.DiscardPolicy());
    //
//        //
//        //        System.out.println("Hello World");
//        //
//        //        System.out.println(LocalDateTime.now() + " --- " + Thread.currentThread().getName() + "Start");
//        //        //        TimeUnit.SECONDS.sleep((long) (Math.random() * 100));
//        //
//        //        List<Future<String>> list = new ArrayList<>();
//        //
//        //        for (int i = 0; i < 10; i++) {
//        //            Future<String> f = executor.submit(new Task<>());
//        //        }
//        //
//        //        for (Future<String> f : list) {
//        //            try {
//        //                System.out.println(f.get());
//        //            } catch (ExecutionException e) {
//        //                System.out.println(e);
//        //                throw new RuntimeException(e);
//        //            }
//        //        }
//        //
//        //        System.out.println(LocalDateTime.now() + " --- " + Thread.currentThread().getName() + "End");
//        //        return "It Work";
//    }
}
