package com.parakeet.steamcloud.database

import androidx.room.*
import com.parakeet.steamcloud.entity.*

/**
 * Database Access Objects (DAOs)
 */

@Dao
interface GameDao {
    @Query("SELECT * FROM games")
    suspend fun getAllGames(): List<GameEntity>

    @Query("SELECT * FROM games WHERE id = :gameId")
    suspend fun getGameById(gameId: Int): GameEntity?

    @Query("SELECT * FROM games WHERE game_name LIKE :gameName")
    suspend fun searchGames(gameName: String): List<GameEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: GameEntity): Long

    @Update
    suspend fun updateGame(game: GameEntity)

    @Delete
    suspend fun deleteGame(game: GameEntity)

    @Query("DELETE FROM games WHERE id = :gameId")
    suspend fun deleteGameById(gameId: Int)

    @Query("UPDATE games SET last_played = :timestamp WHERE id = :gameId")
    suspend fun updateLastPlayed(gameId: Int, timestamp: Long)
}

@Dao
interface SaveDataDao {
    @Query("SELECT * FROM save_data WHERE game_id = :gameId")
    suspend fun getSavesByGameId(gameId: Int): List<SaveDataEntity>

    @Query("SELECT * FROM save_data WHERE id = :saveId")
    suspend fun getSaveById(saveId: Int): SaveDataEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSave(save: SaveDataEntity): Long

    @Update
    suspend fun updateSave(save: SaveDataEntity)

    @Delete
    suspend fun deleteSave(save: SaveDataEntity)

    @Query("DELETE FROM save_data WHERE id = :saveId")
    suspend fun deleteSaveById(saveId: Int)

    @Query("UPDATE save_data SET is_cloud_synced = :isSynced, sync_timestamp = :timestamp WHERE id = :saveId")
    suspend fun updateSyncStatus(saveId: Int, isSynced: Boolean, timestamp: Long)
}

@Dao
interface DownloadDao {
    @Query("SELECT * FROM downloads WHERE status != 'completed'")
    suspend fun getActiveDownloads(): List<DownloadEntity>

    @Query("SELECT * FROM downloads WHERE game_id = :gameId")
    suspend fun getDownloadsByGameId(gameId: Int): List<DownloadEntity>

    @Query("SELECT * FROM downloads WHERE id = :downloadId")
    suspend fun getDownloadById(downloadId: Int): DownloadEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDownload(download: DownloadEntity): Long

    @Update
    suspend fun updateDownload(download: DownloadEntity)

    @Delete
    suspend fun deleteDownload(download: DownloadEntity)

    @Query("UPDATE downloads SET downloaded_size = :size, progress_percentage = :percentage WHERE id = :downloadId")
    suspend fun updateProgress(downloadId: Int, size: Long, percentage: Int)

    @Query("UPDATE downloads SET status = :status WHERE id = :downloadId")
    suspend fun updateDownloadStatus(downloadId: Int, status: String)
}

@Dao
interface SteamGameDao {
    @Query("SELECT * FROM steam_games")
    suspend fun getAllSteamGames(): List<SteamGameEntity>

    @Query("SELECT * FROM steam_games WHERE app_id = :appId")
    suspend fun getSteamGameByAppId(appId: Int): SteamGameEntity?

    @Query("SELECT * FROM steam_games WHERE is_downloaded = 1")
    suspend fun getDownloadedSteamGames(): List<SteamGameEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSteamGame(game: SteamGameEntity): Long

    @Update
    suspend fun updateSteamGame(game: SteamGameEntity)

    @Delete
    suspend fun deleteSteamGame(game: SteamGameEntity)

    @Query("UPDATE steam_games SET is_downloaded = :isDownloaded, local_path = :localPath WHERE app_id = :appId")
    suspend fun updateDownloadStatus(appId: Int, isDownloaded: Boolean, localPath: String?)
}

@Dao
interface ContainerDao {
    @Query("SELECT * FROM containers")
    suspend fun getAllContainers(): List<ContainerEntity>

    @Query("SELECT * FROM containers WHERE container_id = :containerId")
    suspend fun getContainerById(containerId: String): ContainerEntity?

    @Query("SELECT * FROM containers WHERE game_id = :gameId")
    suspend fun getContainerByGameId(gameId: Int): ContainerEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContainer(container: ContainerEntity): Long

    @Update
    suspend fun updateContainer(container: ContainerEntity)

    @Delete
    suspend fun deleteContainer(container: ContainerEntity)

    @Query("UPDATE containers SET status = :status WHERE container_id = :containerId")
    suspend fun updateContainerStatus(containerId: String, status: String)
}