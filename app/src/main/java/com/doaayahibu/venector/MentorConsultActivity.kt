package com.doaayahibu.venector

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.doaayahibu.venector.databinding.MentoringConsultationLayoutBinding

class MentorConsultActivity : AppCompatActivity() {

    private lateinit var binding: MentoringConsultationLayoutBinding
    private val searchHistory = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MentoringConsultationLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Back button functionality
        binding.backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Add search input listener
        binding.searchMentor.setOnEditorActionListener { _, _, _ ->
            val query = binding.searchMentor.text.toString().trim()
            if (query.isNotEmpty() && !searchHistory.contains(query)) {
                searchHistory.add(query)
                updateSearchHistory()
            }
            true
        }

        // Update initial search history (if needed)
        updateSearchHistory()
    }

    private fun setupBannerViewPager() {
        // Data for ViewPager2 (replace with your drawable resources)
        val bannerImages = listOf(
            R.drawable.banner_haga_30k,
            R.drawable.banner_haga_30k,
            R.drawable.banner_haga_30k
        )

        val bannerAdapter = BannerAdapter(bannerImages)
        binding.bannerViewPager.adapter = bannerAdapter

        // Optional: Add page change listener or auto-scroll functionality here
    }

    private fun updateSearchHistory() {
        binding.historyContainer.removeAllViews()
        for (history in searchHistory) {
            val chip = TextView(this).apply {
                text = history
                setPadding(16, 8, 16, 8)
                setBackgroundResource(R.drawable.chip_background)
                setTextColor(resources.getColor(android.R.color.black))
                setOnClickListener {
                    binding.searchMentor.setText(history)
                }
            }
            binding.historyContainer.addView(chip)
        }
    }
}
