/**
* Arithmetic extension to progression.
*/
public class extArithProgression extends Progression {
  /** First value of the progression.  */
  private long first;

  /** Current value of the progression.  */
  private long cur;

  /** Increment. */
  private long inc;

  /** Default constructor.  */
  public extArithProgression(int inFirst, int increment) {
    cur = first = inFirst;
    inc = increment;
  }
  /** Default constructor(Without parameters).  */
  public extArithProgression() {
    cur = first = 0;
    inc = 1;
  }

  /** Resets the progression to the first value.
  *
  * @return first value
  */
  protected long firstValue() {
    cur = first;
    return cur;
  }

  /** Advances the progression by adding the increment to the current value.
  *
  * @return next value of the progression
  */
  protected long nextValue() {
    cur += inc;
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
