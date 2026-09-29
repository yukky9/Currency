package com.example.currency;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    // Функция для вывода сообщения пользователю
    public void AlertDialogs(String title, String message) {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);

        builder
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton("OK",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();
                            }

                        });

        AlertDialog alter = builder.create();
        alter.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void Consider(View view) {
        EditText tbRate = findViewById(R.id.tbRate);

        EditText tbCount = findViewById(R.id.tbSum);

        TextView tvResult = findViewById(R.id.tvResult);

        Switch dollar = findViewById(R.id.switch1);


        // Проверяем, введён ли курс валюты
        if (tbRate.getText().length() == 0) {

            AlertDialogs(
                    "Уведомление",
                    "Введите курс доллара"
            );

            return;
        }


        // Проверяем, введено ли количество валюты
        if (tbCount.getText().length() == 0) {

            AlertDialogs(
                    "Уведомление",
                    "Введите количество валюты"
            );

            return;
        }


        // Получаем значения из полей
        float f_rate =
                Float.parseFloat(
                        String.valueOf(tbRate.getText())
                );

        float f_count =
                Float.parseFloat(
                        String.valueOf(tbCount.getText())
                );


        String composition = "";


        // Если Switch включён
        if (dollar.isChecked()) {

            // Перевод долларов в рубли
            composition =
                    f_rate * f_count + " р.";

        } else {

            // Перевод рублей в доллары
            composition =
                    f_count / f_rate + " $";
        }


        // Выводим результат
        tvResult.setText(composition);
    }


    // Функция открытия ссылки в браузере
    public void URL(View view) {

        Intent intent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse(
                        "https://www.sberbank.ru/ru/quotes/currencies"
                )
        );

        startActivity(intent);

    }
}