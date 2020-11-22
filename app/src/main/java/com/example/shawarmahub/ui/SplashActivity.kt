package com.example.shawarmahub.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.shawarmahub.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val view = layoutInflater.inflate(R.layout.activity_splash, null, false)
//        // we want to keep the logo of the app centered and at the same position the theme sets the logo to.
//        // since the theme positions the logo with respect to the entire window, we need to add top margin
//        // to our image view which should be equal to the height of the status bar
//        view.viewTreeObserver.addOnPreDrawListener(object: ViewTreeObserver.OnPreDrawListener {
//            override fun onPreDraw(): Boolean {
//                view.viewTreeObserver.removeOnPreDrawListener(this)
//                // set the margin to the image
//                val imageView = view.findViewById<ImageView>(R.id.splash_image_iv)
//                val layoutParams = imageView.layoutParams as ConstraintLayout.LayoutParams
//                val statusBarHeight = resources.displayMetrics.heightPixels - view.measuredHeight
//                layoutParams.setMargins(0, statusBarHeight, 0, 0)
//                imageView.layoutParams = layoutParams
//                return true
//            }
//        })
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)
    }
}