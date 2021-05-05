package com.outfluent.specialsites.activity

import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.outfluent.specialsites.databinding.ActivityAddSiteBinding
import com.outfluent.specialsites.db.SpecialSitesDBContract
import com.outfluent.specialsites.util.DatabaseHelper
import java.text.SimpleDateFormat

import java.util.*


class AddSiteActivity : AppCompatActivity() {

    private val appCalender = Calendar.getInstance()
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var binding: ActivityAddSiteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_add_site)
        binding = ActivityAddSiteBinding.inflate(layoutInflater)
        val addSiteView = binding.root
        setContentView(addSiteView)

        databaseHelper = DatabaseHelper(this)

        // for clicking on the calendar dialog
        val date = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            appCalender.set(Calendar.YEAR, year)
            appCalender.set(Calendar.MONTH, month)
            appCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            binding.editTextDate.setText(getFormattedDate(appCalender.timeInMillis))
        }

        binding.editTextDate.setOnClickListener {
            setUpCalendar(date)
        }
        binding.saveButton.setOnClickListener {
            saveSite()
        }

        binding.cancelButton.setOnClickListener {
            finish()
        }


    } // on create

    private fun saveSite() {
        var isValid = true
        binding.editTextUrl.error = if(binding.editTextUrl?.text.toString().isEmpty()) {
            isValid = false
            "Required Field"
        } else null

        binding.editTextPassword.error = if(binding.editTextPassword?.text.toString().isEmpty()) {
            isValid = false
            "Required Field"
        } else null

        if(isValid) {
            val siteurl = binding.editTextUrl?.text.toString()
            val secret = binding.editTextPassword?.text.toString()
            val desc = binding.editTextDesc?.text.toString()
            val created = appCalender.timeInMillis

            val db = databaseHelper.writableDatabase

            val values = ContentValues()
            values.put(SpecialSitesDBContract.SiteEntry.COLUMN_SITEURL, siteurl)
            values.put(SpecialSitesDBContract.SiteEntry.COLUMN_SECRET, secret)
            values.put(SpecialSitesDBContract.SiteEntry.COLUMN_DESCRIPTION, desc)
            values.put(SpecialSitesDBContract.SiteEntry.COLUMN_DATE, created)

            val result = db.insert(
                SpecialSitesDBContract.SiteEntry.TABLE_NAME,
                null, values)

            setResult(RESULT_OK, Intent())
            Toast.makeText(
                applicationContext,
                "Site successfully added",
                Toast.LENGTH_SHORT)
                .show()
        }
        finish()
    } // save site

    private fun setUpCalendar(date: DatePickerDialog.OnDateSetListener) {
        DatePickerDialog(
            this, date,
            appCalender.get(Calendar.YEAR),
            appCalender.get(Calendar.MONTH),
            appCalender.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun getFormattedDate(createdDateInMillis: Long?): String {
        return createdDateInMillis?.let {
            val sdf = SimpleDateFormat("d MMM, yyyy", Locale.getDefault())
            sdf.format(createdDateInMillis)
        } ?: "Not Found"
    }



} // class