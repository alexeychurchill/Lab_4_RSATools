package io.github.alexeychurchill.rsatools.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import io.github.alexeychurchill.rsatools.R;
import io.github.alexeychurchill.rsatools.rsa.MathUtils;
import io.github.alexeychurchill.rsatools.rsa.PrimeNumberGenerator;

public class MainActivity extends AppCompatActivity {
    private PrimeNumberGenerator mPrimeNumberGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPrimeNumberGenerator = new PrimeNumberGenerator(this.getApplicationContext());
    }

    public void requestGenerate(View v) {
        Log.d("MILLER", "Prime: " + mPrimeNumberGenerator.generate16Bit() + " 8-bit: " + mPrimeNumberGenerator.generate8Bit());
    }
}
