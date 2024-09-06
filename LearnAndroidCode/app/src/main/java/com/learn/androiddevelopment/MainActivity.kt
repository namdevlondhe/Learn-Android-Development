package com.learn.androiddevelopment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.learn.androiddevelopment.adapter.HomeSubjectListAdapter
import com.learn.androiddevelopment.databinding.ActivityMainBinding
import com.learn.androiddevelopment.model.SubTopic
import com.learn.androiddevelopment.model.Topic
import com.learn.androiddevelopment.util.AppFunctions
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*


class MainActivity : AppCompatActivity(), HomeSubjectListAdapter.ItemClickListener {

    var adapter: HomeSubjectListAdapter? = null
    var doubleBackToExitPressedOnce = false
    private var topicList: MutableList<Topic>? = mutableListOf()
    private val TAG = "MainActivity"
    private var mAdView: AdView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)

        createTopicList()

        // set up the RecyclerView
        // set up the RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewHome)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = HomeSubjectListAdapter(this, topicList!!)
        adapter!!.setClickListener(this)
        recyclerView.adapter = adapter

        mAdView = findViewById<AdView>(R.id.adViewHome)
        MobileAds.initialize(
            this
        ) { }
        val adRequest =
            AdRequest.Builder().build()
        mAdView!!.loadAd(adRequest)
    }

    private fun createTopicList() {
        topicList = mutableListOf()
        try {
            if (AppFunctions.loadJSONFromAsset(this@MainActivity) != null) {
                val obj = JSONObject(AppFunctions.loadJSONFromAsset(this@MainActivity))
                val topicsArray: JSONArray = obj.getJSONArray("topics")
                for (i in 0 until topicsArray.length()) {
                    val topicObject = topicsArray.getJSONObject(i)
                    val topicItem = Topic()
                    topicItem.id = topicObject.optInt("id")
                    topicItem.name = topicObject.optString("name")
                    topicItem.category = topicObject.optString("category")
                    val subTopicList: MutableList<SubTopic> =
                        java.util.ArrayList<SubTopic>()
                    val subTopicArray = topicObject.optJSONArray("subtopics")
                    if (subTopicArray != null && subTopicArray.length() > 0) {
                        for (j in 0 until subTopicArray.length()) {
                            val subTopicObject = subTopicArray.getJSONObject(j)
                            val subTopicItem = SubTopic()
                            subTopicItem.id = j
                            subTopicItem.name = subTopicObject.optString("name")
                            subTopicItem.description = subTopicObject.optString("description")
                            subTopicItem.url = subTopicObject.optString("url")
                            subTopicList.add(subTopicItem)
                        }
                    }
                    topicItem.subTopicList = subTopicList
                    topicList!!.add(topicItem)
                }

                if (topicList!!.size > 0) {
                    Collections.sort(topicList!!)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            Log.d(TAG, "Exception: " + e.message)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_share) {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                resources.getString(R.string.hint_app_share) +
                        "" + "com.learn.androiddevelopment"
            )
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
            return true
        } else if (id == R.id.action_feedback) {
            val emailIntent = Intent(
                Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", resources.getString(R.string.hint_mail_id), null
                )
            )
            emailIntent.putExtra(
                Intent.EXTRA_SUBJECT,
                resources.getString(R.string.hint_subject_feedback)
            )
            emailIntent.putExtra(Intent.EXTRA_TEXT, " ")
            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        } else if (id == R.id.action_help) {
            val helpIntent = Intent(this, HelpActivity::class.java)
            startActivity(helpIntent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(view: View?, position: Int) {
        //Toast.makeText(getActivity(), "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        val intent = Intent(this, SubjectListActivity::class.java)
        intent.putExtra("subTopicItem", topicList!!.get(position))
        intent.putExtra("title", topicList!!.get(position).name)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, getString(R.string.str_hint_exit_again), Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}
