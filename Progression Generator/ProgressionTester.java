/** Test program for the progression classes */
public class ProgressionTester {
  public static void main(String[] args) {
    // test Progression
    System.out.println("Default progression");
    Progression prog = new Progression();
    prog.printProgression(10);

    // test ArithProgression
    System.out.println("Arithmetic progression with start value 2 and increment 5:");
    Progression Arithprog = new extArithProgression(2, 5);
    Arithprog.printProgression(10);

    // test FibonacciProgression
    System.out.println("Fibonacci progression with start values 4 and 6:");
    Progression Fibonacciprog = new extFibProgression(4,6);
    Fibonacciprog.printProgression(10);
  }
}
