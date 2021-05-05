package com.outfluent.specialsites.util

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.outfluent.specialsites.db.SpecialSitesDBContract

class DatabaseHelper(
    context: Context?,
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SpecialSitesDBContract.SiteEntry.SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SpecialSitesDBContract.SiteEntry.SQL_DROP_TABLE)
        onCreate(db)
    }

//    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//        super.onDowngrade(db, oldVersion, newVersion)
//    }
//
//    override fun onOpen(db: SQLiteDatabase?) {
//        super.onOpen(db)
//    }

    companion object {
        const val DATABASE_NAME = "specialsites.db"
        const val DATABASE_VERSION = 1
    }



} // class