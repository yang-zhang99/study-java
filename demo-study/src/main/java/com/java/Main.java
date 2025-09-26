package com.yang;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    
    public static void main(String[] args) {
        //        BigDecimal a = BigDecimal.valueOf(29.823);
        
        //        BigDecimal a1 = BigDecimal.valueOf(41.411);
        
        //        BigDecimal a3 = BigDecimal.valueOf(1035);
        
        //        System.out.println(a.multiply(a1).subtract(a3).divideToIntegralValue(BigDecimal.valueOf(100)));
        //        System.out.println(a.multiply(a1).subtract(a3).divide(BigDecimal.valueOf(100),0 ,BigDecimal.ROUND_DOWN));
        
        //        BigDecimal taxOil = (rtu.multiply(number).subtract(od.getPay())).divide(
        //                BigDecimal.valueOf(100), 0, ROUND_DOWN);
        
        Main.a(3);
        
        
    }
    
    private static void a(int a) {
        System.out.println(a + "A");
        if (a == 3) {
            StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
            // 跳过前3个元素（0: getStackTrace, 1: printCallerStack, 2: methodB）
            for (int i = 0; i < stackTraceElements.length; i++) {
                System.out.println(stackTraceElements[i]);
            }
            
            System.out.println(new Throwable().getStackTrace());
        }
    }
    
    private static void b(int i) {
        System.out.println(i + "B");
    }
}