package com.learn.androiddevelopment

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_help.*

class HelpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setTitle(getString(R.string.str_help))
        val strHelp: String = getString(R.string.help_note1) + "\n\n" +
                getString(R.string.help_note2) + "\n\n" +
                getString(R.string.help_note3) + "\n\n" +
                getString(R.string.help_note4) + "\n\n" +
                getString(R.string.help_note5) + "\n\n"
        textViewHelp.setText(strHelp)

        MobileAds.initialize(this) { }
        val mAdView: AdView = findViewById(R.id.adViewHome)
        val adRequest =
            AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}