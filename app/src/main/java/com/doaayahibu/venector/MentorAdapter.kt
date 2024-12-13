package com.doaayahibu.venector

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MentorAdapter(
    private val mentors: List<Mentor>,
    private val onItemClick: (Mentor) -> Unit,
    private val onFavoriteClick: (Mentor) -> Unit
) : RecyclerView.Adapter<MentorAdapter.MentorViewHolder>() {

    inner class MentorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgPhoto: ImageView = itemView.findViewById(R.id.img_mentor)
        private val tvName: TextView = itemView.findViewById(R.id.tv_mentor_name)
        private val tvPosition: TextView = itemView.findViewById(R.id.tv_mentor_position)
        private val imgFavorite: ImageView = itemView.findViewById(R.id.img_favorite)

        fun bind(mentor: Mentor) {
            imgPhoto.setImageResource(mentor.photo)
            tvName.text = mentor.name
            tvPosition.text = mentor.position
            imgFavorite.setImageResource(
                if (mentor.isFavorite) R.drawable.favorite_after_click else R.drawable.favorite_before_click
            )

            itemView.setOnClickListener { onItemClick(mentor) }
            imgFavorite.setOnClickListener { onFavoriteClick(mentor) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mentor_card_item, parent, false)
        return MentorViewHolder(view)
    }

    override fun onBindViewHolder(holder: MentorViewHolder, position: Int) {
        holder.bind(mentors[position])
    }

    override fun getItemCount() = mentors.size
}
