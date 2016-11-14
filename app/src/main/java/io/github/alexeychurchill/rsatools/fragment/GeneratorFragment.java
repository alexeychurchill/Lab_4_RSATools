package io.github.alexeychurchill.rsatools.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import io.github.alexeychurchill.rsatools.R;
import io.github.alexeychurchill.rsatools.rsa.PrimeNumberGenerator;

/**
 * GeneratorFragment class
 * Implements fragment, which allows to use prime generator
 */

public class GeneratorFragment extends Fragment implements View.OnClickListener {
    private PrimeNumberGenerator mGenerator;
    private TextView mTVOut;
    private int mNumber;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mGenerator = new PrimeNumberGenerator(getContext().getApplicationContext());
        //View
        View fragmentView = inflater.inflate(R.layout.fragment_generator, container, false);
        if (fragmentView != null) {
            mTVOut = ((TextView) fragmentView.findViewById(R.id.tvOut));
            Button btnGenerate8Bit = ((Button) fragmentView.findViewById(R.id.btnGenerate8Bit));
            if (btnGenerate8Bit != null) {
                btnGenerate8Bit.setOnClickListener(this);
            }
            Button btnGenerate16Bit = ((Button) fragmentView.findViewById(R.id.btnGenerate16Bit));
            if (btnGenerate16Bit != null) {
                btnGenerate16Bit.setOnClickListener(this);
            }
        }
        return fragmentView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGenerate8Bit:
                generateAndOut8Bit();
                break;
            case R.id.btnGenerate16Bit:
                generateAndOut16Bit();
                break;
        }
    }

    private void generateAndOut8Bit() {
        mNumber = mGenerator.generate8Bit();
        updateView();
    }

    private void generateAndOut16Bit() {
        mNumber = mGenerator.generate16Bit();
        updateView();
    }

    private void updateView() {
        if (mTVOut != null) {
            mTVOut.setText(String.valueOf(mNumber));
        }
    }
}
