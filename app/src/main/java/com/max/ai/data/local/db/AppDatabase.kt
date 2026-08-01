package com.max.ai.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.max.ai.data.local.entity.MemoryEntity
import com.max.ai.data.local.entity.NoteEntity
import com.max.ai.data.local.entity.CommandLogEntity

@Database(entities = [MemoryEntity::class, NoteEntity::class, CommandLogEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memoryDao(): com.max.ai.data.local.dao.MemoryDao
    abstract fun notesDao(): com.max.ai.data.local.dao.NotesDao
    abstract fun commandLogDao(): com.max.ai.data.local.dao.CommandLogDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null
        fun getInstance(ctx: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            Room.databaseBuilder(ctx.applicationContext, AppDatabase::class.java, "max_ai_db")
                .fallbackToDestructiveMigration().build().also { INSTANCE = it }
        }
    }
}
