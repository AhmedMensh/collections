package com.android.dev.ahmed.collections.ui.activties

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.dev.ahmed.collections.R
import com.android.dev.ahmed.collections.helpers.Constants
import com.android.dev.ahmed.collections.helpers.SharedPreferencesManager
import com.android.dev.ahmed.collections.ui.activties.home.HomeActivity
import com.android.dev.ahmed.collections.ui.activties.start.StartActivity
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val language = SharedPreferencesManager.getStringValue(this, Constants.LANGUAGE)

        if (language == Constants.ENGLISH) setLanguage(Constants.ENGLISH)
        else if(language == Constants.ARABIC) setLanguage(Constants.ARABIC)
        else {
            SharedPreferencesManager.setStringValue(this,Constants.LANGUAGE,"en")
            setLanguage(Constants.ENGLISH)
        }


        jump()

        startVideo()

    }

    fun setLanguage(language: String?) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val res = resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.setLocale(locale)
        res.updateConfiguration(conf, dm)
    }

    private fun startVideo() {
        val videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.video)
        videoView.setVideoURI(videoUri)
        videoView.setZOrderOnTop(true)
        videoView.setOnCompletionListener { jump() }
        videoView.setOnErrorListener { _, _, _ ->
            jump()
            false
        }
        videoView.start()
    }

    private fun jump(){

        if (SharedPreferencesManager.getIntValue(this, Constants.USER_ID) != 0) {
            startActivity(Intent(this, HomeActivity::class.java))
        }else {
            startActivity(Intent(this, StartActivity::class.java))

        }
        finish()
    }
}