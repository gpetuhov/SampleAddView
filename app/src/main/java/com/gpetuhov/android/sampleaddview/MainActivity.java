package com.gpetuhov.android.sampleaddview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Key for storing entered texts in a Bundle
    public static final String ENTERED_TEXTS_KEY = "entered_texts_key";

    // References to LinearLayout, Button
    private LinearLayout mLinearLayout;
    private Button mButton;

    // References to added EditTexts
    private List<EditText> mEditTexts;

    // Keeps text entered by user in EditTexts
    private ArrayList<String> mEnteredTexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLinearLayout = (LinearLayout) findViewById(R.id.layout);
        mButton = (Button) findViewById(R.id.button);

        mEditTexts = new ArrayList<>();

        // Check if there is saved instance state
        if (savedInstanceState != null) {
            // Get entered texts from the Bundle
            mEnteredTexts = savedInstanceState.getStringArrayList(ENTERED_TEXTS_KEY);

            // If there are entered texts
            if (mEnteredTexts != null) {
                for (String enteredText : mEnteredTexts) {
                    // Add EditTexts to layout with texts from the Bundle
                    addEditTextToLayout(enteredText);
                }
            }
        }

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add EditText to layout with no text
                addEditTextToLayout("");
            }
        });
    }

    // Add EditText to layout and set its text
    private void addEditTextToLayout(String enteredText) {
        // Create parameters for width and height
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);

        // Create new EditText, set params and text
        EditText editText = new EditText(this);
        editText.setLayoutParams(params);
        editText.setText(enteredText);

        // Save reference to EditText in a list
        mEditTexts.add(editText);

        // Add EditText to layout
        mLinearLayout.addView(editText);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        mEnteredTexts = new ArrayList<>();

        // Save text entered in EditTexts in a String list
        for (EditText editText : mEditTexts) {
            mEnteredTexts.add(editText.getText().toString());
        }

        // Put String list into Bundle
        outState.putStringArrayList(ENTERED_TEXTS_KEY, mEnteredTexts);
    }
}
