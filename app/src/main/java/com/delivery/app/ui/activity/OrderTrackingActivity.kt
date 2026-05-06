package com.delivery.app.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.delivery.app.databinding.ActivityOrderTrackingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderTrackingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderTrackingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderTrackingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}