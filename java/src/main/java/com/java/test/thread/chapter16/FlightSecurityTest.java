package com.yang.thread.chapter16;

public class FlightSecurityTest extends Thread {

    static class Passengers extends Thread {
        private final FlightSecurity flightSecurity;

        private final String idCard;

        private final String boardingPass;

        public Passengers(FlightSecurity flightSecurity, String boardingPass, String idCard) {
            this.boardingPass = boardingPass;
            this.flightSecurity = flightSecurity;
            this.idCard = idCard;
        }

        @Override
        public void run() {
            while (true) {
                flightSecurity.pass(boardingPass, idCard);
            }
        }
    }

    public static void main(String[] args) {
        final FlightSecurity flightSecurity = new FlightSecurity();
        new Passengers(flightSecurity, "A123456", "A123456").start();
        new Passengers(flightSecurity, "B123456", "B123456").start();
        new Passengers(flightSecurity, "C123456", "C123456").start();
    }
}
