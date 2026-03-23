package com.yang.study.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class People {
    
    @JsonProperty("name")
    private String name2;
    
    private Body body;
    
    private class Body {
        private String hand;
        
        private String foot;
        
        public String getHand() {
            return hand;
        }
        
        public void setHand(String hand) {
            this.hand = hand;
        }
        
        public String getFoot() {
            return foot;
        }
        
        public void setFoot(String foot) {
            this.foot = foot;
        }
        
        @Override
        public String toString() {
            return "Body{" + "hand='" + hand + '\'' + ", foot='" + foot + '\'' + '}';
        }
    }
    
    public String getName2() {
        return name2;
    }
    
    public void setName2(String name2) {
        this.name2 = name2;
    }
    
    public Body getBody() {
        return body;
    }
    
    public void setBody(Body body) {
        this.body = body;
    }
    
    @Override
    public String toString() {
        return "People{" + "name2='" + name2 + '\'' + ", body=" + body + '}';
    }
}
