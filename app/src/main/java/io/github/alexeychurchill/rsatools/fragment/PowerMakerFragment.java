package io.github.alexeychurchill.rsatools.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.github.alexeychurchill.rsatools.R;
import io.github.alexeychurchill.rsatools.rsa.MathUtils;
import io.github.alexeychurchill.rsatools.rsa.PrimeNumberGenerator;

/**
 * PowerMakerFragment class
 * Fragment which allows to use powByMod
 */

public class PowerMakerFragment extends Fragment implements View.OnClickListener {
    private PrimeNumberGenerator mGenerator;
    private EditText mETNumber;
    private EditText mETPower;
    private EditText mETMod;
    private TextView mTVOut;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mGenerator = new PrimeNumberGenerator(getContext().getApplicationContext());
        View fragmentView = inflater.inflate(R.layout.fragment_powermaker, container, false);
        if (fragmentView != null) {
            mETNumber = ((EditText) fragmentView.findViewById(R.id.etNumber));
            mETPower = ((EditText) fragmentView.findViewById(R.id.etPower));
            mTVOut = ((TextView) fragmentView.findViewById(R.id.tvOut));
            mETMod = ((EditText) fragmentView.findViewById(R.id.etMod));
            //Buttons events
            assignEventsToGenerateButtons(fragmentView);
            //Calculate
            Button btnCalculate = ((Button) fragmentView.findViewById(R.id.btnCalculate));
            if (btnCalculate != null) {
                btnCalculate.setOnClickListener(this);
            }
        }
        return fragmentView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCalculate:
                calculatePowerByMod();
                break;
        }
    }

    private void calculatePowerByMod() {
        Integer number = getNumber();
        Integer power = getPower();
        Integer mod = getMod();
        if ((number == null) || (power == null) || (mod == null)) {
            Toast.makeText(getContext(), R.string.text_incorrect_data, Toast.LENGTH_SHORT)
                    .show();
        }
        int result = MathUtils.powByMod(number, power, mod);
        mTVOut.setText(String.valueOf(result));
    }

    private Integer getNumber() {
        if (mETNumber == null) {
            return null;
        }
        Integer number;
        try {
            number = Integer.parseInt(mETNumber.getText().toString());
        } catch (NumberFormatException e) {
            number = null;
        }
        return number;
    }

    private Integer getPower() {
        if (mETPower == null) {
            return null;
        }
        Integer power;
        try {
            power = Integer.parseInt(mETPower.getText().toString());
        } catch (NumberFormatException e) {
            power = null;
        }
        return power;
    }

    private Integer getMod() {
        if (mETMod == null) {
            return null;
        }
        Integer mod;
        try {
            mod = Integer.parseInt(mETMod.getText().toString());
        } catch (NumberFormatException e) {
            mod = null;
        }
        return mod;
    }

    private void assignEventsToGenerateButtons(View fragmentView) {
        //First number
        //8-Bit generation
        Button btnGenerateNumber8Bit = ((Button) fragmentView.findViewById(R.id.btnNumberGenerate8Bit));
        if (btnGenerateNumber8Bit != null) {
            btnGenerateNumber8Bit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mETNumber.setText(String.valueOf(mGenerator.generate8Bit()));
                }
            });
        }
        //16-bit generation
        Button btnGenerateNumber16Bit = ((Button) fragmentView.findViewById(R.id.btnNumberGenerate16Bit));
        if (btnGenerateNumber16Bit != null) {
            btnGenerateNumber16Bit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mETNumber.setText(String.valueOf(mGenerator.generate16Bit()));
                }
            });
        }
        //Power
        //8-bit
        Button btnGeneratePower8Bit = ((Button) fragmentView.findViewById(R.id.btnPower8Bit));
        if (btnGeneratePower8Bit != null) {
            btnGeneratePower8Bit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mETPower.setText(String.valueOf(mGenerator.generate8Bit()));
                }
            });
        }
        //16-bit generation
        Button btnGeneratePower16Bit = ((Button) fragmentView.findViewById(R.id.btnPower16Bit));
        if (btnGeneratePower16Bit != null) {
            btnGeneratePower16Bit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mETPower.setText(String.valueOf(mGenerator.generate16Bit()));
                }
            });
        }
        //Mod
        //8-bit
        Button btnGenerateMod8Bit = ((Button) fragmentView.findViewById(R.id.btnMod8Bit));
        if (btnGenerateMod8Bit != null) {
            btnGenerateMod8Bit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mETMod.setText(String.valueOf(mGenerator.generate8Bit()));
                }
            });
        }
        //16-bit generation
        Button btnGenerateMod16Bit = ((Button) fragmentView.findViewById(R.id.btnMod16Bit));
        if (btnGenerateMod16Bit != null) {
            btnGenerateMod16Bit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mETMod.setText(String.valueOf(mGenerator.generate16Bit()));
                }
            });
        }
    }
}
