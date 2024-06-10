package fr.gaspezia.colorchoice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton imageButton = findViewById(R.id.color_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView temperature = findViewById(R.id.temperature);
                int currentColor = temperature.getCurrentTextColor();
                Intent intent = new Intent(MainActivity.this, ColorActivity.class);
                intent.putExtra("currentColor", currentColor);
                startActivityForResult(intent, 1); // 1 est le requestCode
            }
        });

        ImageButton imageButtonPressure = findViewById(R.id.color_button_pressure);
        imageButtonPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText pressure = findViewById(R.id.pression);
                int currentColor = pressure.getCurrentTextColor();
                Intent intent = new Intent(MainActivity.this, ColorActivity.class);
                intent.putExtra("currentColor", currentColor);
                startActivityForResult(intent, 2); // 2 est le requestCode pour la pression
            }
        });

        ImageButton imageButtonHumidity = findViewById(R.id.color_button_hygrometrie);
        imageButtonHumidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText humidity = findViewById(R.id.hygrometrie);
                int currentColor = humidity.getCurrentTextColor();
                Intent intent = new Intent(MainActivity.this, ColorActivity.class);
                intent.putExtra("currentColor", currentColor);
                startActivityForResult(intent, 3); // 3 est le requestCode pour l'hygrométrie
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            int selectedColor = data.getIntExtra("selectedColor", -1);
            if (selectedColor != -1) {
                if (requestCode == 1) {
                    TextView temperature = findViewById(R.id.temperature);
                    temperature.setTextColor(selectedColor);
                } else if (requestCode == 2) {
                    EditText pressure = findViewById(R.id.pression);
                    pressure.setTextColor(selectedColor);
                } else if (requestCode == 3) {
                    EditText humidity = findViewById(R.id.hygrometrie);
                    humidity.setTextColor(selectedColor);
                }
            }
        }
    }
}