package com.credit_cure.creditcuremobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class AddCard extends AppCompatActivity {

    private EditText cardAmountText;
    private SeekBar monthBar;
    private TextView tvMonthValue;
    private Button submitButton;

    ArrayList<VirtualCard> vcList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        vcList =
                (ArrayList<VirtualCard>) getIntent().getExtras().getSerializable(MainActivity.CARD_PARCEL);

        cardAmountText = (EditText) findViewById(R.id.add_card_amount);
        monthBar = (SeekBar) findViewById(R.id.monthSeek);
        tvMonthValue = (TextView) findViewById(R.id.add_card_month_tv);
        submitButton = (Button) findViewById(R.id.submit_new_card);

        setupWidgets();
    }

    private void setupWidgets() {
        cardAmountText.setText("$");
        cardAmountText.setSelection(1);
        cardAmountText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                if (!input.startsWith("$"))
                    cardAmountText.setText("$" + input);
                cardAmountText.setSelection(cardAmountText.getText().toString().length());
            }
        });

        monthBar.setProgress(0);
        monthBar.incrementProgressBy(1);
        monthBar.setMax(11);
        tvMonthValue.setText(barValToMonth(0));
        monthBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvMonthValue.setText(barValToMonth(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random randNumGen = new Random();
                String cardNumber = "";
                for (int i = 0; i < 4; i++) {
                    if (i == 3)
                        cardNumber += checkNumber(randNumGen.nextInt(10000));
                    else
                        cardNumber += checkNumber(randNumGen.nextInt(10000)) + " ";
                }

                String cvv = "" + randNumGen.nextInt(1000);
                String date = barValToMonth(monthBar.getProgress());
                String balance = cardAmountText.getText().toString().trim();

                VirtualCard vc = new VirtualCard(cardNumber, balance, date, cvv);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra(MainActivity.CARD_PARCEL, vc);
                startActivity(intent);
            }
        });
    }

    private String checkNumber(int number) {
        String retNumber  = "" + number;
        while (retNumber.length() < 4) {
            retNumber = "0" + retNumber;
        }
        return retNumber;
    }
    private String barValToMonth(int value) {
        switch(value) {
            case 0:
                return "6/18";
            case 1:
                return "7/18";
            case 2:
                return "8/18";
            case 3:
                return "9/18";
            case 4:
                return "10/18";
            case 5:
                return "11/18";
            case 6:
                return "12/18";
            case 7:
                return "1/19";
            case 8:
                return "2/19";
            case 9:
                return "3/19";
            case 10:
                return "4/19";
            case 11:
                return "5/19";
            default:
                return "-1";
        }
    }

}
