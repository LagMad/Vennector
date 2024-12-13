package com.doaayahibu.venector

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CommentReviewActivity : AppCompatActivity() {

    private lateinit var backButton: ImageView
    private lateinit var allCommentsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_comment_review)

        // Set up RecyclerView
        allCommentsRecyclerView.layoutManager = LinearLayoutManager(this)
        val reviews = generateReviews()
        val adapter = ReviewAdapter(reviews)
        allCommentsRecyclerView.adapter = adapter
    }

    private fun generateReviews(): List<Review> {
        return listOf(
            Review("Budi", "Awww, kakaknya ganteng banget jadi pengen.", "14 Mar 2024", R.drawable.comment_1),
            Review("Siti", "Lagi istirahat main ML, bukannya pick tank malah support roam.", "14 Mar 2024", R.drawable.comment_2),
            Review("Andi", "Mentornya sangat membantu, terima kasih kak!", "13 Mar 2024", R.drawable.comment_3),
            Review("Lina", "Belajar jadi lebih mudah dengan mentor hebat.", "12 Mar 2024", R.drawable.comment_1),
            Review("Rina", "Mentoring yang sangat insightful!", "11 Mar 2024", R.drawable.comment_2),
            Review("Dedi", "Thanks kak, ilmunya sangat bermanfaat.", "10 Mar 2024", R.drawable.comment_3),
            Review("Tono", "Banyak belajar strategi bisnis digital.", "9 Mar 2024", R.drawable.comment_1),
            Review("Fina", "Materinya mudah dipahami dan aplikatif.", "8 Mar 2024", R.drawable.comment_2),
            Review("Wawan", "Mentornya asik banget, ga bosen belajarnya.", "7 Mar 2024", R.drawable.comment_3),
            Review("Susi", "Terima kasih kak atas mentoringnya.", "6 Mar 2024", R.drawable.comment_1)
        )
    }
}
