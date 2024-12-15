package com.doaayahibu.venector.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.doaayahibu.venector.R
import com.doaayahibu.venector.models.TimeSlot

class TimeSlotAdapter(
    private val timeSlots: List<TimeSlot>,
    private val onTimeSlotSelected: (TimeSlot, Boolean) -> Unit
) : RecyclerView.Adapter<TimeSlotAdapter.TimeSlotViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeSlotViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_time_slot, parent, false)
        return TimeSlotViewHolder(view)
    }

    override fun onBindViewHolder(holder: TimeSlotViewHolder, position: Int) {
        val timeSlot = timeSlots[position]
        holder.bind(timeSlot)

        holder.itemView.setOnClickListener {
            if (timeSlot.isAvailable) {
                timeSlot.isSelected = !timeSlot.isSelected
                onTimeSlotSelected(timeSlot, timeSlot.isSelected)
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int = timeSlots.size

    class TimeSlotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val timeText: TextView = itemView.findViewById(R.id.timeText)
        private val cardView: CardView = itemView.findViewById(R.id.timeCard)

        fun bind(timeSlot: TimeSlot) {
            timeText.text = "${timeSlot.time}\n${timeSlot.day}"

            if (timeSlot.isAvailable) {
                if (timeSlot.isSelected) {
                    cardView.setCardBackgroundColor(itemView.context.getColor(R.color.dark_blue))
                    timeText.setTextColor(itemView.context.getColor(R.color.white))
                } else {
                    cardView.setCardBackgroundColor(itemView.context.getColor(R.color.white))
                    timeText.setTextColor(itemView.context.getColor(R.color.light_blue))
                }
            } else {
                cardView.setCardBackgroundColor(itemView.context.getColor(R.color.dark_blue_20))
                timeText.setTextColor(itemView.context.getColor(R.color.dark_blue_30))
            }
        }
    }
}
