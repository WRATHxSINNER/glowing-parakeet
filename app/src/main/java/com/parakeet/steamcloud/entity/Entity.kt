package com.parakeet.steamcloud.entity

import androidx.room.*
import java.util.*

/**
 * Database entities for game storage
 */

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "game_name")
    val gameName: String,
    @ColumnInfo(name = "executable_path")
    val executablePath: String,
    @ColumnInfo(name = "install_path")
    val installPath: String,
    @ColumnInfo(name = "app_id")
    val appId: Int?,
    @ColumnInfo(name = "is_exe_file")
    val isExeFile: Boolean = false,
    @ColumnInfo(name = "file_size")
    val fileSize: Long,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "last_played")
    val lastPlayed: Long? = null,
    @ColumnInfo(name = "playtime_hours")
    val playtimeHours: Float = 0f,
    @ColumnInfo(name = "container_id")
    val containerId: String? = null,
    @ColumnInfo(name = "graphics_quality")
    val graphicsQuality: String = "medium"
)

@Entity(tableName = "save_data")
data class SaveDataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "game_id")
    val gameId: Int,
    @ColumnInfo(name = "save_path")
    val savePath: String,
    @ColumnInfo(name = "save_name")
    val saveName: String,
    @ColumnInfo(name = "file_size")
    val fileSize: Long,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "modified_at")
    val modifiedAt: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "is_cloud_synced")
    val isCloudSynced: Boolean = false,
    @ColumnInfo(name = "sync_timestamp")
    val syncTimestamp: Long? = null
)

@Entity(tableName = "downloads")
data class DownloadEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "game_id")
    val gameId: Int,
    @ColumnInfo(name = "download_url")
    val downloadUrl: String,
    @ColumnInfo(name = "destination_path")
    val destinationPath: String,
    @ColumnInfo(name = "total_size")
    val totalSize: Long,
    @ColumnInfo(name = "downloaded_size")
    val downloadedSize: Long = 0,
    @ColumnInfo(name = "status")
    val status: String = "pending",
    @ColumnInfo(name = "progress_percentage")
    val progressPercentage: Int = 0,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "completed_at")
    val completedAt: Long? = null
)

@Entity(tableName = "steam_games")
data class SteamGameEntity(
    @PrimaryKey
    @ColumnInfo(name = "app_id")
    val appId: Int,
    @ColumnInfo(name = "game_name")
    val gameName: String,
    @ColumnInfo(name = "playtime_forever")
    val playtimeForever: Int,
    @ColumnInfo(name = "icon_url")
    val iconUrl: String,
    @ColumnInfo(name = "logo_url")
    val logoUrl: String,
    @ColumnInfo(name = "is_downloaded")
    val isDownloaded: Boolean = false,
    @ColumnInfo(name = "local_path")
    val localPath: String? = null,
    @ColumnInfo(name = "sync_timestamp")
    val syncTimestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "containers")
data class ContainerEntity(
    @PrimaryKey
    @ColumnInfo(name = "container_id")
    val containerId: String,
    @ColumnInfo(name = "game_id")
    val gameId: Int?,
    @ColumnInfo(name = "container_type")
    val containerType: String,
    @ColumnInfo(name = "base_path")
    val basePath: String,
    @ColumnInfo(name = "status")
    val status: String = "stopped",
    @ColumnInfo(name = "memory_limit_mb")
    val memoryLimitMb: Int = 2048,
    @ColumnInfo(name = "cpu_cores")
    val cpuCores: Int = 2,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "last_used")
    val lastUsed: Long? = null
)