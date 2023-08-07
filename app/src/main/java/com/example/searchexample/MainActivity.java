package com.example.searchexample;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mEdtAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdtAddress = findViewById(R.id.et_address);
        mEdtAddress.setFocusable(false);
        mEdtAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //주소검색 웹뷰 화면으로 이동
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                getSearchResult.launch(intent);
            }
        });

    }

    private final ActivityResultLauncher<Intent> getSearchResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                // Search Activity로부터의 결과값이 이곳으로 전달된다.(setResult에 의해)
                if(result.getResultCode() == RESULT_OK){
                    if(result.getData() != null){
                        String data = result.getData().getStringExtra("data");
                        mEdtAddress.setText(data);
                    }
                }
            }
    );

}