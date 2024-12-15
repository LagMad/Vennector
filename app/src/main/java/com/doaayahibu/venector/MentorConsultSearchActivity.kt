package com.doaayahibu.venector

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SearchPageActivity : AppCompatActivity() {

    private lateinit var mentorList: MutableList<Mentor>
    private lateinit var adapter: MentorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mentor_consult_search_layout)

        mentorList = mutableListOf(
            Mentor("Haga Surbakti", "CEO Vennector", R.drawable.haga_foto, 4.5f, listOf("Leadership"), false),
            Mentor("Hizkia Jeremmy", "Developer", R.drawable.jeremmy_foto, 4.0f, listOf("Coding"), false),
            Mentor("Aracel Nestova", "Designer", R.drawable.aracel_foto, 4.8f, listOf("Design"), false)
        )

        val rvMentor = findViewById<RecyclerView>(R.id.rv_mentor_list)
        rvMentor.layoutManager = LinearLayoutManager(this)
        adapter = MentorAdapter(mentorList, ::onMentorClick, ::onFavoriteClick)
        rvMentor.adapter = adapter

        findViewById<EditText>(R.id.search_mentor).setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                filterMentorList()
                true
            } else {
                false
            }
        }

        findViewById<ImageView>(R.id.backButton).setOnClickListener {
            finish()
        }

        updateProductFoundLabel(mentorList.size) // Default count
    }

    private fun filterMentorList() {
        val query = findViewById<EditText>(R.id.search_mentor).text.toString()
        val filteredList = mentorList.filter { it.name.contains(query, true) }

        // Update RecyclerView
        adapter = MentorAdapter(filteredList, ::onMentorClick, ::onFavoriteClick)
        findViewById<RecyclerView>(R.id.rv_mentor_list).adapter = adapter

        // Update Product Found Label
        updateProductFoundLabel(filteredList.size)
    }

    private fun updateProductFoundLabel(count: Int) {
        val tvProductFound = findViewById<TextView>(R.id.tv_product_found)
        if (count > 0) {
            tvProductFound.text = "$count Product Found"
            tvProductFound.visibility = View.VISIBLE
        } else {
            tvProductFound.text = "0 Product Found"
            tvProductFound.visibility = View.GONE
        }
    }

    private fun onMentorClick(mentor: Mentor) {
        val intent = when (mentor.name) {
            "Haga Surbakti" -> Intent(this, MentorHagaDescriptionActivity::class.java)
            "Hizkia Jeremmy" -> Intent(this, MentorJeremmyDescriptionActivity::class.java)
            "Aracel Nestova" -> Intent(this, MentorAracelDescriptionActivity::class.java)
            else -> null
        }
        startActivity(intent)
    }

    private fun onFavoriteClick(mentor: Mentor) {
        mentor.isFavorite = !mentor.isFavorite
        adapter.notifyDataSetChanged()
    }
}