package com.capstone.hanzo.bluebsystem.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.capstone.hanzo.bluebsystem.BusNoList
import com.capstone.hanzo.bluebsystem.PlatformArvlInfoList
import com.capstone.hanzo.bluebsystem.UserInfoList


// TODO : Room의 데이터베이스 객체 작성해야함 (2019.04.04 진행중...)

@Database(entities = [BusNoList::class, PlatformArvlInfoList::class, UserInfoList::class], version = 2)
abstract class InfoDB : RoomDatabase() {
    abstract fun busDao(): BusDao
    abstract fun platformDao(): PlatformDao
    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: InfoDB? = null

        fun getInstance(context: Context): InfoDB? {
            if (INSTANCE == null) {
                synchronized(InfoDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        InfoDB::class.java, "info.db"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
