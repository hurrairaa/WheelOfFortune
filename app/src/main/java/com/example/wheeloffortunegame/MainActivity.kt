package com.example.wheeloffortunegame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.wheeloffortunegame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        var view=binding.root
        setContentView(view)


        binding.submit.setOnClickListener {
            val checkedRadioId=binding.rgChoose.checkedRadioButtonId
            val rangeValue=findViewById<RadioButton>(checkedRadioId)
            var range=binding.sRange.getSelectedItem().toString();
            if(rangeValue.text.toString()!="Yes"){
                range = "1000";
            }
            var name=binding.etName.text.toString()
            if(name!=""){
                Intent(this,PlayWheel::class.java).also{
                    it.putExtra("name",name)
                    it.putExtra("range",range)
                    startActivity(it)
                }
            }else{
                Toast.makeText(this,"Gammer name is Required",Toast.LENGTH_SHORT).show()
            }

        }

        binding.rgChoose.setOnCheckedChangeListener { group, checkedId ->
            var rangeValue=findViewById<RadioButton>(checkedId)
            if(rangeValue.text.toString()=="Yes"){
                binding.sRange.setVisibility(View.VISIBLE)
            }else{
                binding.sRange.setVisibility(View.INVISIBLE)
            }
        }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu,menu)
        return true
    }

}