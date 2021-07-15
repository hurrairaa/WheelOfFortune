package com.example.wheeloffortunegame

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.wheeloffortunegame.databinding.ActivityMainBinding
import com.example.wheeloffortunegame.databinding.ActivityPlayWheelBinding

class PlayWheel : AppCompatActivity() {
    private lateinit var binding: ActivityPlayWheelBinding
    private var mulitplier:Int=0
    private var score:Int=0;
    private var name:String=""
    private var range:String=""
    private var list = mutableListOf<Int>()
    private var correctAnswer:Int=0
    private var wrongAnswers:Int=0
    private lateinit var myAdapter: ArrayAdapter<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPlayWheelBinding.inflate(layoutInflater)
        var view=binding.root
        setContentView(view)

        var mulitplier=rand(2,10);
        name= intent.getStringExtra("name").toString()
        range =intent.getStringExtra("range").toString()

        binding.tvName.setText("Hello "+name)
        binding.tvMultiple.setText("Select Multipliers of "+mulitplier);
        binding.tvRange.setText("Crash From "+range+" random numbers")


        generateListItems(100)
        myAdapter = ArrayAdapter<Int>(this,android.R.layout.simple_list_item_1,list)

        binding.WheelList.adapter=myAdapter


        binding.WheelList.setOnItemClickListener { _, _, index, id ->
//            Toast.makeText(this,"you Clicked "+list.get(index).toString(), Toast.LENGTH_SHORT).show()
            changeScore(index)
        }

        binding.btnDone.setOnClickListener{
            Toast.makeText(this,"Your Score is "+score , Toast.LENGTH_SHORT).show()
            
        }

        binding.btnQuit.setOnClickListener{
            storeScore()
            Intent(this,ScoreActivity::class.java).also{
                it.putExtra("score",score)
                it.putExtra("correctAnswered",correctAnswer)
                it.putExtra("wrongAnswers",wrongAnswers)
                it.putExtra("name",name)
                startActivity(it)
            }
        }

    }

    private fun generateListItems(size:Int){
        for(i in 1..100){
            list.add(rand(10,range.toInt()))
        }
    }
    private fun storeScore(){
        val sharedpref= getSharedPreferences("ScoreData", Context.MODE_PRIVATE)
        val editor =sharedpref.edit()

        editor.apply{
            putInt("score",score)
            putInt("correctAnswers",correctAnswer)
            putInt("wrongAnswers",wrongAnswers)
            putString("name",name)
            apply()
        }


    }
    private fun changeScore(index:Int){
        var answer=list.get(index).toInt()
        if(answer % mulitplier==0){
            score+=10;
            correctAnswer++
        }else{
            score-=10;
            wrongAnswers++
        }
        list.remove(index)
        myAdapter.notifyDataSetChanged()
        Toast.makeText(this,"your Score is $score" , Toast.LENGTH_SHORT).show()
    }

    fun rand(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return (start..end).random()
    }
}