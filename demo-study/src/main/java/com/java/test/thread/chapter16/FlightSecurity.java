package com.yang.thread.chapter16;

public class FlightSecurity {

    private int count = 0;

    private String boardingPass = "null";

    private String idCard = "null";

    public synchronized void pass(String boardingPass, String idCard) {
        this.boardingPass = boardingPass;
        this.idCard = idCard;
        this.count++;
        this.check();

    }

    private void check(){
        if (boardingPass.charAt(0) != idCard.charAt(0)) {
            throw new RuntimeException("=== Exception ===" + this.boardingPass + " " + this.idCard);
        }
    }

    @Override
    public String toString() {
        return "FlightSecurity{" +
                "count=" + count +
                ", boardingPass='" + boardingPass + '\'' +
                ", idCard='" + idCard + '\'' +
                '}';
    }
}
