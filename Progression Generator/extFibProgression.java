/**
* Fibonacci extension to progression.
*/
public class extFibProgression extends Progression {
	/** First value of the progression.  */
  private long first;

  /** Current value of the progression.  */
  private long cur;

  /** Increment. */
  //private long inc;

  /** Previous value. */
  private long prev;

  /** Parametric constructor providing the first and second values.
   *
   * @param value1 first value.
   * @param value2 second value.
   */
  extFibProgression(long value1, long value2) {
    first = value1;
    prev = value2 - value1; // fictitious value preceding the first
  }
    
  /** Default constructor(Without parameters).  */
  extFibProgression() {
    first = 0;
    prev = 1 - first; // fictitious value preceding the first
  }

  /** Advances the progression by adding the previous value to the current value.
  *
  * @return next value of the progression
  */
  protected long nextValue() {
    long temp = prev;
    prev = cur;
    cur += temp;
    return cur;
  }

  /** Resets the progression to the first value.
  *
  * @return first value
  */
  protected long firstValue() {
    cur = first;
    return cur;
  }

  /** Prints the first n values of the progression.
  *
  * @param n number of values to print
  */
  public void printProgression(int n) {
    System.out.print(firstValue());
    for (int i = 2; i <= n; i++)
      System.out.print(" " + nextValue());
    System.out.println(); // ends the line
  }
}
