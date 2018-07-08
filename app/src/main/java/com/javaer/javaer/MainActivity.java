package com.javaer.javaer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "感谢使用Javaer模块!", Toast.LENGTH_LONG).show();
        TextView tv = findViewById(R.id.javaer_tip);
        tv.setText(this.getString(R.string.tip)
                + "\n\n" + this.getString(R.string.tip_1));
    }
}
