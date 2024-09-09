package androidsamples.java.dicegames;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class WalletTest {

  private final WalletViewModel mWalletVM = new WalletViewModel();
  private final Die mDie = mock(Die6.class);


  @Test
  public void winValueIncr(){

    int oldbalance = mWalletVM.balance();

    when(mDie.value()).thenReturn(6);

    mWalletVM.setDie(mDie);
    mWalletVM.rollDie();
    assertTrue(mWalletVM.balance() == oldbalance + 5);

  }

  @Test
  public void winValueDoubleIncr(){
    int oldbalance = mWalletVM.balance();

    when(mDie.value()).thenReturn(6);

    mWalletVM.setDie(mDie);
    //prev value was 6
    mWalletVM.setCurrRoll(6);

    mWalletVM.rollDie();
    // after rolling it will be 6

    assertEquals(mWalletVM.balance() , oldbalance + 10);
  }

  @Test
  public void doubleOtherDecrement(){
    mWalletVM.setBalance(100);
    int oldbalance = mWalletVM.balance();

    when(mDie.value()).thenReturn(3);

    mWalletVM.setDie(mDie);
    mWalletVM.setCurrRoll(3);

    mWalletVM.rollDie();

    assertEquals(mWalletVM.balance(),oldbalance - 5);

  }

  @Test
  public void noChange(){
    int oldBalance = mWalletVM.balance();
    when(mDie.value()).thenReturn(1);

    mWalletVM.setDie(mDie);
    mWalletVM.setCurrRoll(4);

    mWalletVM.rollDie();
    assertEquals(mWalletVM.balance(),oldBalance);
  }

  @Test
  public void totalStatRollIncr(){
    int oldBalance = mWalletVM.balance();
    int oldTotalRolls = mWalletVM.totalRolls();
    int oldSingleSixes = mWalletVM.singleSixes();
    int oldDoubleSixes = mWalletVM.doubleSixes();
    int oldDoubleOthers = mWalletVM.doubleOthers();


    when(mDie.value()).thenReturn(6);

    mWalletVM.setDie(mDie);
    mWalletVM.setCurrRoll(6);

    mWalletVM.rollDie();

    assertEquals(mWalletVM.balance(),oldBalance+10);
    assertEquals(mWalletVM.totalRolls(),oldTotalRolls+1);
    assertEquals(mWalletVM.singleSixes(),oldSingleSixes);
    assertEquals(mWalletVM.doubleSixes(),oldDoubleSixes+1);
    assertEquals(mWalletVM.doubleOthers(),oldDoubleOthers);

  }

  @Test
  public void totalStatRollIncr2(){

    mWalletVM.setBalance(100);

    int oldBalance = mWalletVM.balance();
    int oldTotalRolls = mWalletVM.totalRolls();
    int oldSingleSixes = mWalletVM.singleSixes();
    int oldDoubleSixes = mWalletVM.doubleSixes();
    int oldDoubleOthers = mWalletVM.doubleOthers();


    when(mDie.value()).thenReturn(4);

    mWalletVM.setDie(mDie);
    mWalletVM.setCurrRoll(4);

    mWalletVM.rollDie();

    assertEquals(mWalletVM.balance(),oldBalance-5);
    assertEquals(mWalletVM.totalRolls(),oldTotalRolls+1);
    assertEquals(mWalletVM.singleSixes(),oldSingleSixes);
    assertEquals(mWalletVM.doubleSixes(),oldDoubleSixes);
    assertEquals(mWalletVM.doubleOthers(),oldDoubleOthers+1);

  }
}