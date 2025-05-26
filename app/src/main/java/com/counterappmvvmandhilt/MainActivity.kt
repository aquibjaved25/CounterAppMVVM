package com.counterappmvvmandhilt

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.counterappmvvmandhilt.databinding.ActivityMainBinding
import com.counterappmvvmandhilt.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val mainVewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())

        binding.tvCounter.text = getString(R.string.click_count,mainVewModel.getCount())
        setAllClickListeners()
    }

    private fun setAllClickListeners(){
        binding.btnClickCounter.setOnClickListener {
            mainVewModel.updateCount()
            binding.tvCounter.text = getString(R.string.click_count,mainVewModel.getCount())
        }

        binding.btnStartService.setOnClickListener {
            val intentService = Intent(this, MyService::class.java)
            intentService.putExtra(Constants.COUNT,mainVewModel.getCount())
            startService(intentService)
        }
    }

    override fun onStop() {
        super.onStop()
        mainVewModel.resetCount()
        binding.tvCounter.text = getString(R.string.click_count,mainVewModel.getCount())
    }
}