package com.yang.study.json;

import com.alibaba.fastjson.JSONObject;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class main {
    public static void main(String[] args) throws Exception {
        // JSON 字符串
        String jsonString = "{\"Class\":\"John\", \"age\":30}";
        
//        // 创建 ObjectMapper 实例
//        ObjectMapper mapper = new ObjectMapper();
//
//        // 执行反序列化
//        Person person = mapper.readValue(jsonString, Person.class);
        
        Person person = JSONObject.parseObject(jsonString, Person.class);
        
        // 输出反序列化后的对象
        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());
    }
    
    static class Person {
        @JsonProperty("Class")
        @JSONField(name="Class")
        private String name;
        
        private int age;
        
        // Getters and setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
    }
}
