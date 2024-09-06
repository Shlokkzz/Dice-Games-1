package androidsamples.java.dicegames;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class WalletTest {
  private Die mDie;

  @Before
  public void intialize(){
    mDie = new Die6();
  }
  @Test
  public void numRange(){
    mDie.roll();
    int val = mDie.value();
    assertTrue(val>=1 && val<=6);
  }

  @Test
  public void constantValueWhenNoRoll(){
    mDie.roll();
    int val1 = mDie.value();
    int val2 = mDie.value();
    assertTrue(val1 == val2);
  }

}