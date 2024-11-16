package com.java.test.json;

public class People {
    
    private String name;
    
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
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
