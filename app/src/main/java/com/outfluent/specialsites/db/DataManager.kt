package com.outfluent.specialsites.db

import com.outfluent.specialsites.db.SpecialSitesDBContract.SiteEntry
import com.outfluent.specialsites.model.Site
import com.outfluent.specialsites.util.DatabaseHelper

object DataManager {

    fun fetchAllSites(databaseHelper: DatabaseHelper): ArrayList<Site> {
        val sites = ArrayList<Site>()
        val db = databaseHelper.readableDatabase

        val columns = arrayOf(
            SiteEntry.COLUMN_ID,
            SiteEntry.COLUMN_SITEURL,
            SiteEntry.COLUMN_SECRET,
            SiteEntry.COLUMN_DESCRIPTION,
            SiteEntry.COLUMN_DATE
        )

        val cursor = db.query(
            SiteEntry.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            null)

        val idPos = cursor.getColumnIndex(SiteEntry.COLUMN_ID)
        val siteurlPos = cursor.getColumnIndex(SiteEntry.COLUMN_SITEURL)
        val secretPos = cursor.getColumnIndex(SiteEntry.COLUMN_SECRET)
        val descPos = cursor.getColumnIndex(SiteEntry.COLUMN_DESCRIPTION)
        val createdPos = cursor.getColumnIndex(SiteEntry.COLUMN_DATE)

        while (cursor.moveToNext()) {
            val id = cursor.getString(idPos)
            val siteUrl = cursor.getString(siteurlPos)
            val secret = cursor.getString(secretPos)
            val desc = cursor.getString(descPos)
            val created = cursor.getLong(createdPos)

            sites.add(Site(id, siteUrl, secret, desc, created))
        }

        cursor.close()

        return sites
    }



}