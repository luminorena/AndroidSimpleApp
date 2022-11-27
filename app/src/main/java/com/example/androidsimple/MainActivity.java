package com.example.androidsimple;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    // Обработка нажатия кнопки
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sendMessage(View view) {
        TextView randomListView = findViewById(R.id.textView);
        TextView primesView = findViewById(R.id.textView1);
        TextView resultView = findViewById(R.id.textView2);

        List<Integer> randomList = Stream.generate(() -> new Random()
                        .nextInt(100))
                .limit(10)
                .collect(Collectors.toList());
        randomListView.setText("Рандомный массив:  " + '\n' + randomList.toString());
        List<Integer> primes = randomList.stream()
                .filter(MainActivity::isPrime)
                .collect(Collectors.toList());
        primesView.setText("Простые числа:  " + '\n' + primes.toString());

        Integer primeResult = primes.stream()
                .reduce(Integer::sum).get();

        resultView.setText("Сумма простых чисел:  " + '\n' + primeResult.toString());


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private static boolean isPrime(int number) {
        return number > 1 && IntStream
                .range(2, number)
                .noneMatch(i -> number % i == 0);
    }
}