package com.parakeet.steamcloud.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.parakeet.steamcloud.entity.*

/**
 * Room database for local game storage and metadata
 */
@Database(
    entities = [
        GameEntity::class,
        SaveDataEntity::class,
        DownloadEntity::class,
        SteamGameEntity::class,
        ContainerEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
    abstract fun saveDataDao(): SaveDataDao
    abstract fun downloadDao(): DownloadDao
    abstract fun steamGameDao(): SteamGameDao
    abstract fun containerDao(): ContainerDao
}