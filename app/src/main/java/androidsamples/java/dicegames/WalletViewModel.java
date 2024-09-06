package androidsamples.java.dicegames;

import androidx.lifecycle.ViewModel;

public class WalletViewModel extends ViewModel {
  private static final String TAG = "WalletViewModel";
  private final int WIN_VALUE = 6;
  private final int INCREMENT = 5;
  private final int BONUS_INCREMENT = 10;
  private final int DECREMENT = 5;

  private int mBalance;
  private Die mDie;

  private int totalRolls;
  private int prevRoll;
  private int currRoll;
  private int singleSixes;
  private int doubleSixes;
  private int doubleOthers;

  /**
   * The no argument constructor.
   */
  public WalletViewModel() {
    // TODO implement method
    mBalance = 0;
    mDie = new Die6();
    totalRolls = 0;
    prevRoll = 0;
    currRoll = 0;
    singleSixes = 0;
    doubleSixes = 0;
    doubleOthers = 0;
  }

  /**
   * Reports the current balance.
   *
   */
  public int balance() {
    // TODO implement method
    return mBalance;
  }

  /**
   * Rolls the {@link Die} in the wallet and implements the changes accordingly.
   */
  public void rollDie() {
    // TODO implement method
    // set prevRoll to last CurrRoll
    prevRoll = currRoll;

    mDie.roll();
    // Log.d(TAG,"Die Roll = " + mDie.value());

    // + totalRolls
    totalRolls++;

    int value=mDie.value();

    // set currRoll
    currRoll = value;

    // + coins
    if(value==WIN_VALUE){
      if(value == prevRoll){
        // + doubleSixes
        doubleSixes ++;
        mBalance += BONUS_INCREMENT;
      }
      else {
        // + singleSixes
        singleSixes ++;
        mBalance += INCREMENT;
      }
    }

    // - coins
    if(value != WIN_VALUE && value == prevRoll){
      // + doubleOthers
      doubleOthers ++;
      mBalance -= DECREMENT;
      if(mBalance < 0){
        mBalance = 0;
      }
    }
  }

  /**
   * Reports the current value of the {@link Die}.
   *
   */
  public int dieValue() {
    // TODO implement method
    return mDie.value();
  }

  /**
   * Reports the number of single (or first) sixes rolled so far.
   *
   */
  public int singleSixes() {
    // TODO implement method
    return singleSixes;
  }

  /**
   * Reports the total number of dice rolls so far.
   *
   */
  public int totalRolls() {
    // TODO implement method
    return totalRolls;
  }

  /**
   * Reports the number of times two sixes were rolled in a row.
   *
   */
  public int doubleSixes() {
    // TODO implement method
    return doubleSixes;
  }

  /**
   * Reports the number of times any other number was rolled twice in a row.
   *
   */
  public int doubleOthers() {
    // TODO implement method
    return doubleOthers;
  }

  /**
   * Reports the value of the die on the previous roll.
   *
   */
  public int previousRoll() {
    // TODO implement method
    return prevRoll;
  }
}
