package com.ttpkk.assignments.assignment2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ttpkk.assignments.R;
import com.ttpkk.assignments.assignment2.DB.AppDatabase;
import com.ttpkk.assignments.assignment2.adapter.MyCustomAdapter;
import com.ttpkk.assignments.assignment2.dao.ItemDao;
import com.ttpkk.assignments.assignment2.entity.Item;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class SATOOneCheck extends AppCompatActivity {

    EditText scan1,scan2;
    ImageView resultImg;
    Button clearBtn, resultBtn;

    MyCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satoone_check);

        scan1 = findViewById(R.id.scan1EditText);
        scan2 = findViewById(R.id.scan2EditText);

        clearBtn = findViewById(R.id.clearBtn);
        resultBtn = findViewById(R.id.resultBtn);

        resultImg = findViewById(R.id.resultImg);

        scan1.setShowSoftInputOnFocus(false);
        scan2.setShowSoftInputOnFocus(false);
        scan2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text1 = scan1.getText().toString().trim();
                String text2 = s.toString().trim();

                if (text1.equals(text2)) {
                    resultImg.setImageResource(R.drawable.ic_check_144);
                } else {
                    resultImg.setImageResource(R.drawable.ic_cancel_144);
                }
                new bgThread().start();
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setClearBtn();
            }
        });

        resultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ResultOneCheckActivity.class));
            }
        });
    }
    public void setClearBtn() {
        scan1.setText("");
        scan2.setText("");
    }


    class bgThread extends Thread {
        public void run() {
            super.run();
            AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                   AppDatabase.class,
                    "room_db").build();
            String text1 = scan1.getText().toString().trim();
            String text2 = scan2.getText().toString().trim();
            ItemDao itemDao  = db.itemDao();
            if (!text1.equals("") && !text2.equals("")) {
                itemDao.insertItem(new Item(
                        text1,
                        text2,
                        System.currentTimeMillis(),
                        text1.equals(text2)));
            }

        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}