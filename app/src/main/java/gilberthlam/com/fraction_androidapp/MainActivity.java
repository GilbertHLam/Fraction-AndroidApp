package gilberthlam.com.fraction_androidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DecimalFormat df = new DecimalFormat("###.#");

        final EditText numeratorBox=(EditText)findViewById(R.id.numerBox);

        final EditText denominatorBox=(EditText)findViewById(R.id.denomBox);

        final Button button = (Button) findViewById(R.id.simplifyButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean pos = true;
                TextView status = (TextView) findViewById(R.id.statusLabel);
                double comFac;
                double numer = Double.parseDouble(numeratorBox.getText().toString());
                double denom = Double.parseDouble(denominatorBox.getText().toString());
                double startnumer = numer;
                double startdenom = denom;
                if (denom == 0) {
                    status.setText("INVALID FRACTION");
                }
                else {
                    if (status.getText().toString().equals("CALCULATING...")) {

                    }
                    else {
                        status.setText("CALCULATING...");
                        if (denom < 0 && numer > 0) {
                            denom = denom * -1;
                            pos = false;
                        } else if (numer < 0 && denom > 0) {
                            numer = numer * -1;
                            pos = false;
                        } else if (numer < 0 && denom < 0) {
                            denom = denom * -1;
                            numer = numer * -1;
                        }

                        if (denom > numer) {
                            for (double i = numer; i > 0; i--) {
                                if (numer % i == 0 && denom % i == 0) {
                                    numer = numer / i;
                                    denom = denom / i;
                                    comFac = i;
                                    break;
                                }
                            }
                        }
                        if (numer > denom) {
                            for (double i = denom; i > 0; i--) {
                                if (numer % i == 0 && denom % i == 0) {
                                    numer = numer / i;
                                    denom = denom / i;
                                    comFac = i;
                                    break;
                                }
                            }
                        }

                        if (pos == false) {
                            numeratorBox.setText("-" + df.format(numer));
                            denominatorBox.setText("" + df.format(denom));
                        } else {
                            numeratorBox.setText("" + (df.format(numer)));
                            denominatorBox.setText("" + df.format(denom));
                        }
                        if (startdenom == denom && startnumer == numer) {
                            status.setText("ALREADY SIMPLIFIED");
                        } else {
                            status.setText("FRACTION SIMPLIFIED!");
                        }

                    }

                }
            }
        });
    }
}
