package com.doaayahibu.venector

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent

class LandingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_layout)

        val elips = findViewById<View>(R.id.elips)
        val logoSmall = findViewById<View>(R.id.logo_small)
        val logoFull = findViewById<View>(R.id.logo_full)

        // Tampilkan elips dari kecil ke besar
        elips.visibility = View.VISIBLE
        val elipsScaleX = ObjectAnimator.ofFloat(elips, "scaleX", 0f, 3f)
        val elipsScaleY = ObjectAnimator.ofFloat(elips, "scaleY", 0f, 3f)
        val elipsAnimatorSet = AnimatorSet().apply {
            duration = 1000
            playTogether(elipsScaleX, elipsScaleY)
        }

        // Logo kecil muncul dari bawah ke atas dengan bounce
        logoSmall.visibility = View.VISIBLE
        val logoSmallTranslateY = ObjectAnimator.ofFloat(logoSmall, "translationY", 500f, 0f)
        val logoSmallBounce = ObjectAnimator.ofFloat(logoSmall, "translationY", 0f, -30f, 0f).apply {
            duration = 300
            startDelay = 1200
        }
        val logoSmallAnimatorSet = AnimatorSet().apply {
            playSequentially(logoSmallTranslateY, logoSmallBounce)
            duration = 1000
        }

        // Logo kecil mengecil dan bergerak ke kanan
        val logoSmallMoveRight = ObjectAnimator.ofFloat(logoSmall, "translationX", 0f, 300f)
        val logoSmallShrink = ObjectAnimator.ofFloat(logoSmall, "scaleX", 1f, 0f)
        val logoSmallShrinkY = ObjectAnimator.ofFloat(logoSmall, "scaleY", 1f, 0f)
        val logoSmallMoveAndShrink = AnimatorSet().apply {
            playTogether(logoSmallMoveRight, logoSmallShrink, logoSmallShrinkY)
            duration = 1000
        }

        // Fade In logo besar
        logoFull.visibility = View.VISIBLE
        val fadeInLogoFull = ObjectAnimator.ofFloat(logoFull, "alpha", 0f, 1f).apply {
            duration = 1000
            startDelay = 500
        }

        // Logo besar bergerak ke atas
        val logoFullMoveUp = ObjectAnimator.ofFloat(logoFull, "translationY", 0f, -300f).apply {
            duration = 1000
            startDelay = 1500
        }

        // Menyembunyikan logo kecil setelah animasi selesai
        val logoSmallDisappear = ObjectAnimator.ofFloat(logoSmall, "alpha", 1f, 0f).apply {
            duration = 500
            startDelay = 1500
        }

        // Menggabungkan semua animasi
        val fullAnimatorSet = AnimatorSet().apply {
            playSequentially(
                elipsAnimatorSet,
                logoSmallAnimatorSet,
                logoSmallMoveAndShrink,
                fadeInLogoFull,
                logoFullMoveUp,
                logoSmallDisappear
            )
            duration = 3500
        }

        // Mulai animasi
        fullAnimatorSet.start()

        // Setelah animasi selesai, pindah ke login layout
        fullAnimatorSet.addListener(object : android.animation.Animator.AnimatorListener {
            override fun onAnimationStart(animation: android.animation.Animator) {}

            override fun onAnimationEnd(animation: android.animation.Animator) {
                // Lakukan transisi ke login layout setelah animasi selesai
                val intent = Intent(this@LandingActivity, LoginActivity::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }

            override fun onAnimationCancel(animation: android.animation.Animator) {}

            override fun onAnimationRepeat(animation: android.animation.Animator) {}
        })
    }
}
