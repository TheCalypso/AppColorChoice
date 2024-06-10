package fr.gaspezia.colorchoice;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ColorActivity extends AppCompatActivity {

    private TextView viewColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        viewColor = findViewById(R.id.viewColor);

        int currentColor = getIntent().getIntExtra("currentColor", -1);
        if (currentColor != -1) {
            int red = Color.red(currentColor);
            int green = Color.green(currentColor);
            int blue = Color.blue(currentColor);
            ((EditText) findViewById(R.id.editTextRouge)).setText(String.valueOf(red));
            ((SeekBar) findViewById(R.id.seekBarRouge)).setProgress(red);
            ((EditText) findViewById(R.id.editTextVert)).setText(String.valueOf(green));
            ((SeekBar) findViewById(R.id.seekBarVert)).setProgress(green);
            ((EditText) findViewById(R.id.editTextBleu)).setText(String.valueOf(blue));
            ((SeekBar) findViewById(R.id.seekBarBleu)).setProgress(blue);
            viewColor.setBackgroundColor(currentColor);
        }

        setupColorSync(R.id.editTextRouge, R.id.seekBarRouge);
        setupColorSync(R.id.editTextBleu, R.id.seekBarBleu);
        setupColorSync(R.id.editTextVert, R.id.seekBarVert);

        Button buttonValider = findViewById(R.id.buttonValider);
        buttonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                int color = ((ColorDrawable) viewColor.getBackground()).getColor();
                resultIntent.putExtra("selectedColor", color);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    private void setupColorSync(int editTextId, int seekBarId) {
        EditText editText = findViewById(editTextId);
        SeekBar seekBar = findViewById(seekBarId);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty()) {
                    int value = Integer.parseInt(s.toString());
                    if(value <= 255) {
                        seekBar.setProgress(value);
                    }
                }
                updateButtonColor();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    editText.setText(String.valueOf(progress));
                }
                updateButtonColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editText.setText(String.valueOf(seekBar.getProgress()));
            }
        });
    }

    private void updateButtonColor() {
        EditText editTextRouge = findViewById(R.id.editTextRouge);
        EditText editTextBleu = findViewById(R.id.editTextBleu);
        EditText editTextVert = findViewById(R.id.editTextVert);

        String rougeStr = editTextRouge.getText().toString();
        String bleuStr = editTextBleu.getText().toString();
        String vertStr = editTextVert.getText().toString();

        if (rougeStr.isEmpty() || bleuStr.isEmpty() || vertStr.isEmpty()) {
            viewColor.setBackgroundColor(Color.TRANSPARENT);
        } else {
            int rouge = Integer.parseInt(rougeStr);
            int bleu = Integer.parseInt(bleuStr);
            int vert = Integer.parseInt(vertStr);
            viewColor.setBackgroundColor(Color.rgb(rouge, bleu, vert));
        }
    }
}