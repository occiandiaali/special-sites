package com.outfluent.specialsites

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.outfluent.specialsites.activity.AddSiteActivity
import com.outfluent.specialsites.adapter.SiteListAdapter
import com.outfluent.specialsites.databinding.ActivityMainBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.outfluent.specialsites.db.DataManager
import com.outfluent.specialsites.util.DatabaseHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var databaseHelper: DatabaseHelper
    private val siteListAdapter = SiteListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        databaseHelper = DatabaseHelper(this)

        binding.rcView.adapter = siteListAdapter
        binding.rcView.layoutManager = LinearLayoutManager(this)
        siteListAdapter.setSites(DataManager.fetchAllSites(databaseHelper))


        binding.fab.setOnClickListener {
            val addSite = Intent(this, AddSiteActivity::class.java)
            startActivityForResult(addSite, 1)
        }
    } // on create

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            siteListAdapter.setSites(DataManager.fetchAllSites(databaseHelper))
        }
    }


} // class