package com.example.wheeloffortunegame

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wheeloffortunegame.databinding.ActivityPlayWheelBinding
import com.example.wheeloffortunegame.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {
    private lateinit var binding:ActivityScoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityScoreBinding.inflate(layoutInflater)
        var view=binding.root
        setContentView(view)

        val sharedpref= getSharedPreferences("ScoreData", Context.MODE_PRIVATE)
        var name=sharedpref.getString("name",null)
        var wrongAnswers=sharedpref.getInt("wrongAnswers",0)
        var correctAnswer=sharedpref.getInt("correctAnswers",0)
        var total=correctAnswer+wrongAnswers;
        var score=sharedpref.getInt("score",0)
        binding.tvDescription.setText("$name has scrored $score points in the last game and has made $total attempts with correct answers $correctAnswer and incorrect answers $wrongAnswers")


    }
}