package com.outfluent.specialsites.db

import android.provider.BaseColumns
import android.provider.BaseColumns._ID

object SpecialSitesDBContract {

    object SiteEntry : BaseColumns {
        const val TABLE_NAME = "site"
        const val COLUMN_ID = _ID
        const val COLUMN_SITEURL = "siteurl"
        const val COLUMN_SECRET = "secret"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_DATE = "created"

        const val SQL_CREATE_ENTRIES =
            "CREATE TABLE $TABLE_NAME (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_SITEURL TEXT NOT NULL, " +
                    "$COLUMN_SECRET TEXT NOT NULL, " +
                    "$COLUMN_DESCRIPTION TEXT NOT NULL, " +
                    "$COLUMN_DATE INTEGER NOT NULL)"

        const val SQL_DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
    }

}