package me.chinmaya.togglecase;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void convert(View view) {
        TextView t = findViewById(R.id.convertText);
        String a = t.getText().toString();
        String b = "";
        double heuristic;
        boolean isUpperCase = true;
        for (char cur: a.toCharArray()) {
            double prob = Math.random();
            if (b.equals("")) {
                heuristic = 0.0;
            } else if (isUpperCase) {
                heuristic = 0.25;
            } else {
                heuristic = -0.25;
            }
            if (prob >= 0.5 + heuristic) {
                b += Character.toUpperCase(cur);
                isUpperCase = true;
            } else {
                b += Character.toLowerCase(cur);
                isUpperCase = false;
            }
        }
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("", b);
        clipboard.setPrimaryClip(clip);
        t = findViewById(R.id.convertedText);
        t.setText(b);
    }
}
