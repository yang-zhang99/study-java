package com.yang.study.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class RequestHandlerRegistry implements ApplicationListener<ContextRefreshedEvent> {



    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        System.out.println(event);
//        Map<String, RequestHandler> beansOfType = event.getApplicationContext().getBeansOfType(RequestHandler.class);
//        Collection<RequestHandler> values = beansOfType.values();
//        for (RequestHandler requestHandler : values) {
//
//            Class<?> clazz = requestHandler.getClass();
//            boolean skip = false;
//            while (!clazz.getSuperclass().equals(RequestHandler.class)) {
//                if (clazz.getSuperclass().equals(Object.class)) {
//                    skip = true;
//                    break;
//                }
//                clazz = clazz.getSuperclass();
//            }
//            if (skip) {
//                continue;
//            }
//
//            try {
//                Method method = clazz.getMethod("handle", Request.class, RequestMeta.class);
//                if (method.isAnnotationPresent(TpsControl.class) && TpsControlConfig.isTpsControlEnabled()) {
//                    TpsControl tpsControl = method.getAnnotation(TpsControl.class);
//                    String pointName = tpsControl.pointName();
//                    ControlManagerCenter.getInstance().getTpsControlManager().registerTpsPoint(pointName);
//                }
//            } catch (Exception e) {
//                //ignore.
//            }
//            Class tClass = (Class) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
//            registryHandlers.putIfAbsent(tClass.getSimpleName(), requestHandler);
//        }
    }
}
