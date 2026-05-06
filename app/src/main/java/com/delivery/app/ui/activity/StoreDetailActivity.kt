package com.delivery.app.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.delivery.app.databinding.ActivityStoreDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStoreDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoreDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}