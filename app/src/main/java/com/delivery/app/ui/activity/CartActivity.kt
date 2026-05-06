package com.delivery.app.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.delivery.app.databinding.ActivityCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}