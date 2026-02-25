package com.example.m203

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_NAME = "persons.db"
        private const val DB_VERSION = 1

        const val TABLE = "Person"
        const val COL_ID = "id"
        const val COL_NOM = "nom"
        const val COL_PRENOM = "prenom"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE $TABLE (" +
                    "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COL_NOM TEXT NOT NULL, " +
                    "$COL_PRENOM TEXT NOT NULL" +
                    ")"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE")
        onCreate(db)
    }


    // ── CREATE ──────────────────────────────────────────────────────────────
    fun insert(person: Person): Long {
        val cv = ContentValues().apply {
            put(COL_NOM,    person.nom)
            put(COL_PRENOM, person.prenom)
        }
        return writableDatabase.insert(TABLE, null, cv)
    }

    // ── READ ─────────────────────────────────────────────────────────────────
    fun getAll(): List<Person> {
        val list = mutableListOf<Person>()
        val cursor = readableDatabase.query(
            TABLE, null, null, null, null, null, "$COL_ID ASC"
        )
        cursor.use {
            while (it.moveToNext()) {
                list.add(
                    Person(
                        id     = it.getInt(it.getColumnIndexOrThrow(COL_ID)),
                        nom    = it.getString(it.getColumnIndexOrThrow(COL_NOM)),
                        prenom = it.getString(it.getColumnIndexOrThrow(COL_PRENOM))
                    )
                )
            }
        }
        return list
    }

    // ── UPDATE ───────────────────────────────────────────────────────────────
    fun update(person: Person): Int {
        val cv = ContentValues().apply {
            put(COL_NOM,    person.nom)
            put(COL_PRENOM, person.prenom)
        }
        return writableDatabase.update(TABLE, cv, "$COL_ID=?", arrayOf(person.id.toString()))
    }

    // ── DELETE ───────────────────────────────────────────────────────────────
    fun delete(id: Int): Int =
        writableDatabase.delete(TABLE, "$COL_ID=?", arrayOf(id.toString()))
}

