package my.decimal.converter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigInteger;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText numerator;
    EditText denominator;

    EditText decimal;

    Button DecimaltoFraction;
    Button FractiontoDecimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numerator = (EditText) findViewById(R.id.numerator);
        denominator = (EditText) findViewById(R.id.denominator);
        decimal = (EditText) findViewById(R.id.input);

        DecimaltoFraction = (Button) findViewById(R.id.ConvertFraction);
        FractiontoDecimal = (Button) findViewById(R.id.ConvertDecimal);
    }

    // Converts decimal to fraction
    // Called via 'Convert to fraction' decimal
    public void Decimaltofraction(View v) {
        String deci = decimal.getText().toString();
        try {
            double wholenum = Double.valueOf(deci);
            int decimalDigits = deci.length() - deci.indexOf('.') - 1;
            int denom = 1;

            // For loop to make the decimal not a decimal so we can divide it

            for (int i = 0; i < decimalDigits; i++) {
                wholenum *= 10;
                denom *= 10;
            }
            int intwholenum = (int)wholenum;
            int gc = gcd(intwholenum, denom);
            intwholenum = intwholenum/gc;
            denom = denom/gc;
            numerator.setText(String.valueOf(intwholenum));
            denominator.setText(String.valueOf(denom));

        } catch (Exception e) {
            AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
            myAlert.setMessage("Enter a number!").create();
            myAlert.show();
        }

    }
    public static int gcd(int num1, int num2) {
        BigInteger bnum1 = BigInteger.valueOf(num1);
        BigInteger bnum2 = BigInteger.valueOf(num2);
        BigInteger gcdenom = bnum1.gcd(bnum2);
        return gcdenom.intValue();
    }
    public void FractiontoDecimal(View v) {
        String num = numerator.getText().toString();
        String denom = denominator.getText().toString();
        double numValue = 0;
        double denomValue = 0;
        try {
            numValue = Double.parseDouble(num);
            denomValue = Double.parseDouble(denom);
            if(denomValue == 0) {
                throw new ArithmeticException();
            } else {
                double divide = numValue / denomValue;
                DecimalFormat df = new DecimalFormat("#.######");
                decimal.setText(df.format(divide));
            }
        } catch (Exception e) {
            AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
            myAlert.setMessage("Dividing by Zero!").create();
            myAlert.show();
            decimal.setText("error");
        }
    }

    public void Clear(View v) {
        numerator.setText("");
        denominator.setText("");
        decimal.setText("");
    }
}
