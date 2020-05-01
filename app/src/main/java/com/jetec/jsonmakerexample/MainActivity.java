package com.jetec.jsonmakerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText edIn01,edIn02,edIn03;
        Button btTrans,btClear;

        edIn01 = findViewById(R.id.editText_In01);
        edIn02 = findViewById(R.id.editText2_In02);
        edIn03 = findViewById(R.id.editText3_In03);
        btTrans = findViewById(R.id.button_Trans);
        btClear = findViewById(R.id.button_Clear);

        ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();//先建立一個ArrayList包HashMap

        btClear.setOnClickListener((v)->{
            arrayList.clear();
        });

        btTrans.setOnClickListener((v -> {
            String first,second,third;
            first = edIn01.getText().toString();
            second = edIn02.getText().toString();
            third = edIn03.getText().toString();
            if (first.length() == 0||second.length() == 0||third.length() == 0) return;
            HashMap<String,String> hashMap = new HashMap<>();//新增一個HashMap
            hashMap.put("MyFirstHashIndex",first);
            hashMap.put("MySecondHashIndex",second);
            hashMap.put("MyThirdHashIndex",third);
            arrayList.add(hashMap);//包裹進內容後，將HashMap放進ArrayList

            String gson = new Gson().toJson(arrayList);//將此ArrayList轉為json字串
            TextView tvShow = findViewById(R.id.textView_Show);
            tvShow.setMovementMethod(ScrollingMovementMethod.getInstance());//使TextView可以捲動
            gson = gson.replace("[","[\n");
            gson = gson.replace("]","]\n");
            gson = gson.replace("{","\t{\n\t");
            gson = gson.replace("}","\n\t}\n");
            gson = gson.replace(",",",\n\t");

            tvShow.setText(gson);//show出Json字串
        }));
    }//onCreate
}
