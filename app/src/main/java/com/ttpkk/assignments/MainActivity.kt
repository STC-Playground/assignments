package com.ttpkk.assignments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.cardview.widget.CardView
import com.ttpkk.assignments.assignment2.SATOOneCheck
import java.util.zip.Inflater


class MainActivity : AppCompatActivity()  {

    lateinit var assign1Btn : CardView
    lateinit var assign2Btn : CardView
    lateinit var assign3Btn : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializing CardViews
        assign1Btn = findViewById(R.id.assign1Btn)
        assign2Btn = findViewById(R.id.assign2Btn)

        assign2Btn.setOnClickListener {
            val intent = Intent(this, SATOOneCheck::class.java)
            startActivity(intent)
        }

    }












}