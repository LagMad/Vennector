package com.doaayahibu.venector

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.doaayahibu.venector.adapters.TimeSlotAdapter
import com.doaayahibu.venector.models.TimeSlot

class BookMentoringActivity : AppCompatActivity() {

    private lateinit var minusSessionButton: Button
    private lateinit var plusSessionButton: Button
    private lateinit var sessionCountDisplay: TextView
    private lateinit var findNewMentorButton: Button
    private lateinit var orderNowButton: Button
    private lateinit var timeSlotRecyclerView: RecyclerView

    private var sessionCount: Int = 1
    private val maxSessions = 10
    private val minSessions = 1

    private val timeSlots = mutableListOf<TimeSlot>()
    private val selectedTimeSlots = mutableListOf<TimeSlot>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_mentoring_layout)

        // Initialize Views
        minusSessionButton = findViewById(R.id.minus_session_button)
        plusSessionButton = findViewById(R.id.plus_session_button)
        sessionCountDisplay = findViewById(R.id.session_count_display)
        findNewMentorButton = findViewById(R.id.find_new_mentor_button)
        orderNowButton = findViewById(R.id.order_now_button)
        timeSlotRecyclerView = findViewById(R.id.time_slot_container_mentoring)

        // Set initial session count
        sessionCountDisplay.text = sessionCount.toString()

        // Set up button listeners
        minusSessionButton.setOnClickListener { decreaseSessionCount() }
        plusSessionButton.setOnClickListener { increaseSessionCount() }
        findNewMentorButton.setOnClickListener { navigateToMentorSelection() }
        orderNowButton.setOnClickListener { processOrder() }

        // Initialize time slots
        initializeTimeSlots()
        setupTimeSlotRecyclerView()
    }

    private fun decreaseSessionCount() {
        if (sessionCount > minSessions) {
            sessionCount--
            sessionCountDisplay.text = sessionCount.toString()
        } else {
            Toast.makeText(this, "Minimum session count is $minSessions", Toast.LENGTH_SHORT).show()
        }
    }

    private fun increaseSessionCount() {
        if (sessionCount < maxSessions) {
            sessionCount++
            sessionCountDisplay.text = sessionCount.toString()
        } else {
            Toast.makeText(this, "Maximum session count is $maxSessions", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToMentorSelection() {
        val intent = Intent(this, MentorConsultActivity::class.java)
        startActivity(intent)
    }

    private fun processOrder() {
        if (selectedTimeSlots.isEmpty()) {
            Toast.makeText(this, "Please select at least one time slot.", Toast.LENGTH_SHORT).show()
        } else {
            // TODO: Implement order processing and navigate to payment page
            Toast.makeText(this, "Proceeding to payment...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initializeTimeSlots() {
        val times = listOf("9:00 AM", "10:00 AM", "1:00 PM", "2:00 PM", "3:00 PM", "5:00 PM", "6:00 PM")
        val days = listOf("Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

        days.forEach { day ->
            times.forEach { time ->
                val isAvailable = !(day == "Tuesday" && (time == "9:00 AM" || time == "10:00 AM" || time == "3:00 PM"))
                timeSlots.add(TimeSlot(day, time, isAvailable))
            }
        }
    }

    private fun setupTimeSlotRecyclerView() {
        val adapter = TimeSlotAdapter(timeSlots) { timeSlot, isSelected ->
            if (isSelected) {
                selectedTimeSlots.add(timeSlot)
            } else {
                selectedTimeSlots.remove(timeSlot)
            }
            updateOrderButtonState()
        }

        timeSlotRecyclerView.layoutManager = GridLayoutManager(this, 3)
        timeSlotRecyclerView.adapter = adapter
    }

    private fun updateOrderButtonState() {
        orderNowButton.isEnabled = selectedTimeSlots.isNotEmpty()
    }
}
