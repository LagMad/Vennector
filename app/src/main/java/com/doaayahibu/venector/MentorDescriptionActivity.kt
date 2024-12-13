package com.doaayahibu.venector

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MentorDescriptionActivity : AppCompatActivity() {

    private lateinit var favoriteButton: ImageView
    private lateinit var reviewRecyclerView: RecyclerView
    private lateinit var viewAllReviewsButton: TextView
    private lateinit var bookSessionButton: Button

    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mentor_description_layout)

        // Initialize views
        favoriteButton = findViewById(R.id.btnFavorite)
        reviewRecyclerView = findViewById(R.id.reviewRecycleView)
        viewAllReviewsButton = findViewById(R.id.tvSeeAllReviews)
        bookSessionButton = findViewById(R.id.btnBookSession)

        // Set up RecyclerView
        reviewRecyclerView.layoutManager = LinearLayoutManager(this)
        val reviews = generateReviews()
        val adapter = ReviewAdapter(reviews)
        reviewRecyclerView.adapter = adapter

        // Favorite button click listener
        favoriteButton.setOnClickListener {
            isFavorite = !isFavorite
            updateFavoriteButton()
            if (isFavorite) {
                Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show()
            }
        }

        // View all reviews button click listener
        viewAllReviewsButton.setOnClickListener {
            val intent = Intent(this, CommentReviewActivity::class.java)
            startActivity(intent)
        }

        // Book session button click listener
        bookSessionButton.setOnClickListener {
            val intent = Intent(this, BookSessionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun generateReviews(): List<Review> {
        return listOf(
            Review("Budi", "Awww, kakaknya ganteng banget jadi pengen.", "14 Mar 2024", R.drawable.comment_1),
            Review("Siti", "Lagi istirahat terus main ML, bukannya pick tank malah support roam.", "14 Mar 2024", R.drawable.comment_2),
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

    private fun updateFavoriteButton() {
        if (isFavorite) {
            favoriteButton.setImageResource(R.drawable.favorite_after_click)
        } else {
            favoriteButton.setImageResource(R.drawable.favorite_before_click)
        }
    }
}

// Data class for reviews
data class Review(
    val username: String,
    val content: String,
    val date: String,
    val imageResId: Int
)

// Adapter for RecyclerView
class ReviewAdapter(private val reviews: List<Review>) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment_review, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviews[position]
        holder.bind(review)
    }

    override fun getItemCount(): Int = reviews.size

    inner class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val username: TextView = itemView.findViewById(R.id.tvCommentUsername)
        private val content: TextView = itemView.findViewById(R.id.tvCommentContent)
        private val date: TextView = itemView.findViewById(R.id.tvCommentDate)
        private val userImage: ImageView = itemView.findViewById(R.id.imgCommentUser)

        fun bind(review: Review) {
            username.text = review.username
            content.text = review.content
            date.text = review.date
            userImage.setImageResource(review.imageResId)
        }
    }
}