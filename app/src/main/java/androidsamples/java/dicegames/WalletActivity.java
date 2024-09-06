package androidsamples.java.dicegames;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class WalletActivity extends AppCompatActivity {

  private static final String TAG = "WalletActivity";

  private TextView mTxtCoins;
  private Button mBtnDie;
  private TextView mTxtPrevRoll;
  private TextView mTxtSingleSixes;
  private TextView mTxtDoubleSixes;
  private TextView mTxtDoubleOther;
  private TextView mTxtTotalRolls;

  private WalletViewModel mWalletVM;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
//    Log.d(TAG,"OnCreate");
    setContentView(R.layout.activity_wallet);

    // views
    mTxtCoins = findViewById(R.id.txt_coins);
    mBtnDie = findViewById(R.id.btn_die);
    mTxtPrevRoll = findViewById(R.id.txt_prev_roll);
    mTxtSingleSixes = findViewById(R.id.txt_single_sixes);
    mTxtDoubleSixes = findViewById(R.id.txt_double_sixes);
    mTxtDoubleOther = findViewById(R.id.txt_double_others);
    mTxtTotalRolls = findViewById(R.id.txt_total_rolls);

    mWalletVM = new ViewModelProvider(this).get(WalletViewModel.class);

    updateUI();

    // button click
    mBtnDie.setOnClickListener(v->{

      int prevCoins = mWalletVM.balance();
      mWalletVM.rollDie();
      int currCoins = mWalletVM.balance();

      // update UI
      updateUI();

      // earned coins
      if(currCoins > prevCoins){
        CharSequence text = "Congratulations!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this /* MyActivity */, text, duration);
        toast.show();
      }
    });
  }

  // UPDATE UI
  private void updateUI(){
    mTxtCoins.setText(Integer.toString(mWalletVM.balance()));
    mBtnDie.setText(Integer.toString(mWalletVM.dieValue()));
    mTxtPrevRoll.setText(Integer.toString(mWalletVM.previousRoll()));
    mTxtSingleSixes.setText(Integer.toString(mWalletVM.singleSixes()));
    mTxtDoubleSixes.setText(Integer.toString(mWalletVM.doubleSixes()));
    mTxtDoubleOther.setText(Integer.toString(mWalletVM.doubleOthers()));
    mTxtTotalRolls.setText(Integer.toString(mWalletVM.totalRolls()));
  }

}