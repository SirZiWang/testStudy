package com.wangzi.test.demo;

public class EscapeAnalysis {
	private static class Foo {
        private int x;
        private static int counter;
 
        public Foo() {
            x = (++counter);
        }
    }
  /**
   *  -server -verbose:gc
   *  -server -verbose:gc -XX:+DoEscapeAnalysis
   * @param args
   */
    public static void main(String[] args) {
        long start = System.nanoTime();
        for (int i = 0; i < 1000 * 1000 * 10; ++i) {
            Foo foo = new Foo();
        }
        long end = System.nanoTime();
        System.out.println("Time cost is " + (end - start));
    }

}
