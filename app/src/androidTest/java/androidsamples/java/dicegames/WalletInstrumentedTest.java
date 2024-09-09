package androidsamples.java.dicegames;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.content.Context;

import androidx.lifecycle.ViewModelProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class WalletInstrumentedTest {
  @Rule
  public ActivityScenarioRule<WalletActivity> activityRule = new ActivityScenarioRule<>(WalletActivity.class);

  private  WalletViewModel mWalletVM;

  private final Die mDie = mock(Die6.class);

  @Test
  public void DieRollIs6(){
    when(mDie.value()).thenReturn(6);

    activityRule.getScenario().onActivity(activity -> {
      mWalletVM = new ViewModelProvider(activity).get(WalletViewModel.class);
      mWalletVM.setDie(mDie);
    });

    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).check(matches(withText("6")));
  }

  @Test
  public void BalanceIncr(){
    when(mDie.value()).thenReturn(6);

    activityRule.getScenario().onActivity(activity -> {
      mWalletVM = new ViewModelProvider(activity).get(WalletViewModel.class);
      mWalletVM.setDie(mDie);
    });

    mWalletVM.setBalance(100);
    mWalletVM.setCurrRoll(4);
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.txt_coins)).check(matches(withText("105")));
  }

  @Test
  public void BalanceDecr(){
    when(mDie.value()).thenReturn(2);

    activityRule.getScenario().onActivity(activity -> {
      mWalletVM = new ViewModelProvider(activity).get(WalletViewModel.class);
      mWalletVM.setDie(mDie);
    });

    mWalletVM.setBalance(100);
    mWalletVM.setCurrRoll(2);
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.txt_coins)).check(matches(withText("95")));
  }

  @Test
  public void BalanceDoubleIncr(){
    when(mDie.value()).thenReturn(6);

    activityRule.getScenario().onActivity(activity -> {
      mWalletVM = new ViewModelProvider(activity).get(WalletViewModel.class);
      mWalletVM.setDie(mDie);
    });

    mWalletVM.setBalance(100);
    mWalletVM.setCurrRoll(6);
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.txt_coins)).check(matches(withText("110")));
  }

  @Test
  public void TotalRollStats1(){
    when(mDie.value()).thenReturn(6);

    activityRule.getScenario().onActivity(activity -> {
      mWalletVM = new ViewModelProvider(activity).get(WalletViewModel.class);
      mWalletVM.setDie(mDie);
    });
    mWalletVM.setBalance(100);
    // double sixes ++
    mWalletVM.setCurrRoll(6);

    int oldBalance = mWalletVM.balance();
    int oldTotalRolls = mWalletVM.totalRolls();
    int oldSingleSixes = mWalletVM.singleSixes();
    int oldDoubleSixes = mWalletVM.doubleSixes();
    int oldDoubleOthers = mWalletVM.doubleOthers();

    onView(withId(R.id.btn_die)).perform(click());

    onView(withId(R.id.txt_total_rolls)).check(matches(withText(Integer.toString(oldTotalRolls+1))));
    onView(withId(R.id.txt_single_sixes)).check(matches(withText(Integer.toString(oldSingleSixes))));
    onView(withId(R.id.txt_double_sixes)).check(matches(withText(Integer.toString(oldDoubleSixes+1))));
    onView(withId(R.id.txt_coins)).check(matches(withText(Integer.toString(oldBalance+10))));
    onView(withId(R.id.txt_double_others)).check(matches(withText(Integer.toString(oldDoubleOthers))));
  }

  @Test
  public void TotalRollStats2(){
    when(mDie.value()).thenReturn(6);

    activityRule.getScenario().onActivity(activity -> {
      mWalletVM = new ViewModelProvider(activity).get(WalletViewModel.class);
      mWalletVM.setDie(mDie);
    });
    mWalletVM.setBalance(100);
    // single sixes ++
    mWalletVM.setCurrRoll(2);

    int oldBalance = mWalletVM.balance();
    int oldTotalRolls = mWalletVM.totalRolls();
    int oldSingleSixes = mWalletVM.singleSixes();
    int oldDoubleSixes = mWalletVM.doubleSixes();
    int oldDoubleOthers = mWalletVM.doubleOthers();

    onView(withId(R.id.btn_die)).perform(click());

    onView(withId(R.id.txt_total_rolls)).check(matches(withText(Integer.toString(oldTotalRolls+1))));
    onView(withId(R.id.txt_single_sixes)).check(matches(withText(Integer.toString(oldSingleSixes+1))));
    onView(withId(R.id.txt_double_sixes)).check(matches(withText(Integer.toString(oldDoubleSixes))));
    onView(withId(R.id.txt_coins)).check(matches(withText(Integer.toString(oldBalance+5))));
    onView(withId(R.id.txt_double_others)).check(matches(withText(Integer.toString(oldDoubleOthers))));
  }

  @Test
  public void TotalRollStats3(){
    when(mDie.value()).thenReturn(4);

    activityRule.getScenario().onActivity(activity -> {
      mWalletVM = new ViewModelProvider(activity).get(WalletViewModel.class);
      mWalletVM.setDie(mDie);
    });
    mWalletVM.setBalance(100);
    // double others ++
    mWalletVM.setCurrRoll(4);

    int oldBalance = mWalletVM.balance();
    int oldTotalRolls = mWalletVM.totalRolls();
    int oldSingleSixes = mWalletVM.singleSixes();
    int oldDoubleSixes = mWalletVM.doubleSixes();
    int oldDoubleOthers = mWalletVM.doubleOthers();

    onView(withId(R.id.btn_die)).perform(click());

    onView(withId(R.id.txt_total_rolls)).check(matches(withText(Integer.toString(oldTotalRolls+1))));
    onView(withId(R.id.txt_single_sixes)).check(matches(withText(Integer.toString(oldSingleSixes))));
    onView(withId(R.id.txt_double_sixes)).check(matches(withText(Integer.toString(oldDoubleSixes))));
    onView(withId(R.id.txt_coins)).check(matches(withText(Integer.toString(oldBalance-5))));
    onView(withId(R.id.txt_double_others)).check(matches(withText(Integer.toString(oldDoubleOthers+1))));
  }

  @Test
  public void TotalRollStats4(){
    when(mDie.value()).thenReturn(1);

    activityRule.getScenario().onActivity(activity -> {
      mWalletVM = new ViewModelProvider(activity).get(WalletViewModel.class);
      mWalletVM.setDie(mDie);
    });
    mWalletVM.setBalance(100);
    // double others ++
    mWalletVM.setCurrRoll(5);

    int oldBalance = mWalletVM.balance();
    int oldTotalRolls = mWalletVM.totalRolls();
    int oldSingleSixes = mWalletVM.singleSixes();
    int oldDoubleSixes = mWalletVM.doubleSixes();
    int oldDoubleOthers = mWalletVM.doubleOthers();

    onView(withId(R.id.btn_die)).perform(click());

    onView(withId(R.id.txt_total_rolls)).check(matches(withText(Integer.toString(oldTotalRolls+1))));
    onView(withId(R.id.txt_single_sixes)).check(matches(withText(Integer.toString(oldSingleSixes))));
    onView(withId(R.id.txt_double_sixes)).check(matches(withText(Integer.toString(oldDoubleSixes))));
    onView(withId(R.id.txt_coins)).check(matches(withText(Integer.toString(oldBalance))));
    onView(withId(R.id.txt_double_others)).check(matches(withText(Integer.toString(oldDoubleOthers))));
  }
}