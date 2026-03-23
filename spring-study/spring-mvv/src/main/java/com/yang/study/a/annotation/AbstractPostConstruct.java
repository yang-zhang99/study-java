package com.yang.study.annotation;

import jakarta.annotation.PostConstruct;

public abstract class AbstractPostConstruct {

    /**
     * 被 @PostConstruct 修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次。
     * PostConstruct 在构造函数之后执行，init() 方法之前执行。
     */

    @PostConstruct
    public void init() {
        System.out.println("AbstractPostConstruct  init");
    }

}
