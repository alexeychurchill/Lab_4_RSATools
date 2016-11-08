package io.github.alexeychurchill.rsatools.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import io.github.alexeychurchill.rsatools.R;
import io.github.alexeychurchill.rsatools.rsa.MathUtils;
import io.github.alexeychurchill.rsatools.rsa.PrimeNumberGenerator;

public class MainActivity extends AppCompatActivity {
    private TextView tvOut;
    private PrimeNumberGenerator mPrimeNumberGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPrimeNumberGenerator = new PrimeNumberGenerator(this.getApplication());
        tvOut = ((TextView) findViewById(R.id.tvOut));
    }

    public void requestGenerate8(View view) {
        int generated = mPrimeNumberGenerator.generate8Bit();
        tvOut.setText(String.valueOf(generated));
    }

    public void requestGenerate16(View view) {
        int generated = mPrimeNumberGenerator.generate16Bit();
        tvOut.setText(String.valueOf(generated));
    }
}
