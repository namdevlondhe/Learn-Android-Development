package com.learn.androiddevelopment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.learn.androiddevelopment.adapter.SubCategoryAdapter
import com.learn.androiddevelopment.model.SubTopic
import com.learn.androiddevelopment.model.Topic
import java.util.*

class SubjectListActivity : AppCompatActivity(), SubCategoryAdapter.ItemClickListener {

    var adapter: SubCategoryAdapter? = null
    private var topicItem: Topic? = null
    private var subTopicList: List<SubTopic>? = null
    private var mAdView: AdView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject_listing)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = intent.extras!!.getString("title")

        topicItem = intent.extras!!.getSerializable("subTopicItem") as Topic
        subTopicList = ArrayList<SubTopic>()
        subTopicList = topicItem!!.getSubTopicList()

        Log.d("HomeFragment", "Data: " + intent.extras!!.getString("chapterName"))
        // set up the RecyclerView
        // set up the RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewHome)

        val layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(
            this,
            layoutManager.orientation
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = SubCategoryAdapter(this, subTopicList)
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

    override fun onItemClick(view: View?, position: Int) {
        //Toast.makeText(getActivity(), "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        val intent = Intent(this, BrowserActivity::class.java)
        intent.putExtra("url", subTopicList!![position].url)
        intent.putExtra("title", subTopicList!![position].name)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}