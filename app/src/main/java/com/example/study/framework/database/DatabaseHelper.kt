package com.example.study.framework.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri
import androidx.core.database.getIntOrNull
import com.example.study.core.entities.CocktailModels.Cocktail

// SQLite
class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "cocktails.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "cocktails"
        private const val COLUMN_ID = "_id" // Добавлено новое поле для индивидуального ID
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_DESCRIPTION = "description"
        private const val COLUMN_RECIPE = "recipe"
        private const val COLUMN_IMAGE = "image"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Создание таблицы
        val createTableQuery = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_TITLE TEXT," +
                "$COLUMN_DESCRIPTION TEXT," +
                "$COLUMN_RECIPE TEXT," +
                "$COLUMN_IMAGE TEXT)"
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Обновление базы данных (если необходимо)
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Функция для добавления записи в базу данных
    fun addCocktail(cocktail: Cocktail): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, cocktail._title)
            put(COLUMN_DESCRIPTION, cocktail._description)
            put(COLUMN_RECIPE, cocktail._recipe)
            put(COLUMN_IMAGE, cocktail._img?.toString())
        }
        val id = db.insert(TABLE_NAME, null, values) // Получение сгенерированного ID
        cocktail._id = id.toInt()
        db.close()

        return id.toInt()
    }
    fun getAllCocktails(): MutableList<Cocktail> {
        val cocktails = mutableListOf<Cocktail>()

        val db = readableDatabase
        val projection = arrayOf(
            COLUMN_ID,
            COLUMN_TITLE,
            COLUMN_DESCRIPTION,
            COLUMN_RECIPE,
            COLUMN_IMAGE
        )
        val cursor = db.query(
            TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )
        with(cursor) {
            while (moveToNext()) {
                val id = getIntOrNull(getColumnIndexOrThrow(COLUMN_ID))
                val title = getString(getColumnIndexOrThrow(COLUMN_TITLE))
                val description = getString(getColumnIndexOrThrow(COLUMN_DESCRIPTION))
                val recipe = getString(getColumnIndexOrThrow(COLUMN_RECIPE))
                val imageUri = getString(getColumnIndexOrThrow(COLUMN_IMAGE))?.let { Uri.parse(it) }
                val cocktail = Cocktail(id, title, description, recipe, imageUri)
                cocktails.add(cocktail)
            }
        }
        cursor.close()
        db.close()

        return cocktails
    }

    fun updateCocktail(cocktail: Cocktail) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, cocktail._title)
            put(COLUMN_DESCRIPTION, cocktail._description)
            put(COLUMN_RECIPE, cocktail._recipe)
            put(COLUMN_IMAGE, cocktail._img?.toString())
        }
        val selection = "$COLUMN_ID = ?" // Изменено условие поиска на основе ID
        val selectionArgs = arrayOf(cocktail._id.toString()) // Используется ID для поиска
        db.update(TABLE_NAME, values, selection, selectionArgs)
        db.close()
    }

    fun deleteCocktail(cocktailId: Int) {
        val db = writableDatabase
        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(cocktailId.toString())
        db.delete(TABLE_NAME, selection, selectionArgs)
        db.close()
    }


}


private fun saveToDatabase(context: Context, cocktails: MutableList<Cocktail>) {
    val dbHelper = DatabaseHelper(context)

    for (cocktail in cocktails) {
        dbHelper.addCocktail(cocktail)
    }
}
