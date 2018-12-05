package com.mymovieapp.room

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context


abstract class RoomDb : RoomDatabase() {
    abstract fun movieDao() : MovieDao
    companion object {
        private var INSTANCE : RoomDb? = null
        fun getInstance(context : Context) : MovieDao?{
            if(INSTANCE == null){
                synchronized(RoomDb::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            RoomDb::class.java,"movie").addCallback(sRoomDatabaseCallback).build()
                }
            }
            return INSTANCE!!.movieDao()
        }


        private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
            }
        }
        fun destroyInstance() {
            INSTANCE = null
        }

    }
}