package com.parakeet.steamcloud.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.LifecycleService
import kotlinx.coroutines.*
import java.io.File

/**
 * File coordination service for managing game files and directories
 * Handles copying, organizing, and maintaining file structures for containers and games
 */
class FileCoordinationService : LifecycleService() {

    private val binder = FileCoordinationBinder()
    private val serviceScope = CoroutineScope(Dispatchers.IO + Job())

    inner class FileCoordinationBinder : Binder() {
        fun getService(): FileCoordinationService = this@FileCoordinationService
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("FileCoordinationService", "Service created")
    }

    override fun onBind(intent: Intent?): IBinder = binder

    /**
     * Copy game files to container
     */
    fun copyGameFilesToContainer(sourceDir: String, containerDir: String): Job = serviceScope.launch {
        try {
            val source = File(sourceDir)
            val destination = File(containerDir)
            
            if (!source.exists()) {
                throw IllegalArgumentException("Source directory not found: $sourceDir")
            }

            destination.mkdirs()
            copyRecursively(source, destination)
            
            Log.d("FileCoordinationService", "Files copied to container: $containerDir")
        } catch (e: Exception) {
            Log.e("FileCoordinationService", "Error copying files", e)
        }
    }

    /**
     * Organize game files in proper structure
     */
    fun organizeGameFiles(gameDir: String): Job = serviceScope.launch {
        try {
            val baseDir = File(gameDir)
            baseDir.mkdirs()

            // Create subdirectories
            val subdirs = listOf("bin", "data", "saves", "config", "logs", "temp", "backups")
            subdirs.forEach { subdir ->
                File(baseDir, subdir).mkdirs()
            }

            // Organize existing files
            baseDir.listFiles()?.forEach { file ->
                if (file.isFile) {
                    val subdir = when {
                        file.extension in listOf("exe", "dll", "so") -> "bin"
                        file.extension in listOf("sav", "dat") -> "saves"
                        file.extension in listOf("cfg", "ini", "conf") -> "config"
                        file.extension in listOf("log") -> "logs"
                        else -> "data"
                    }
                    
                    file.renameTo(File(baseDir, "$subdir/${file.name}"))
                }
            }

            Log.d("FileCoordinationService", "Game files organized in: $gameDir")
        } catch (e: Exception) {
            Log.e("FileCoordinationService", "Error organizing files", e)
        }
    }

    /**
     * Setup Wine prefix structure
     */
    fun setupWinePrefix(prefixPath: String): Job = serviceScope.launch {
        try {
            val prefix = File(prefixPath)
            prefix.mkdirs()

            // Create Wine directory structure
            val wineDirs = listOf(
                "drive_c/windows",
                "drive_c/windows/system32",
                "drive_c/windows/syswow64",
                "drive_c/Program Files",
                "drive_c/Program Files (x86)",
                "drive_c/users/user",
                "drive_c/users/user/Desktop",
                "drive_c/users/user/Documents",
                "drive_c/users/user/Downloads"
            )

            wineDirs.forEach { dir ->
                File(prefix, dir).mkdirs()
            }

            Log.d("FileCoordinationService", "Wine prefix created: $prefixPath")
        } catch (e: Exception) {
            Log.e("FileCoordinationService", "Error setting up Wine prefix", e)
        }
    }

    /**
     * Copy executable to Wine directory
     */
    fun copyExecutableToWine(exePath: String, wineDir: String): Job = serviceScope.launch {
        try {
            val exeFile = File(exePath)
            if (!exeFile.exists()) {
                throw IllegalArgumentException("Executable not found: $exePath")
            }

            val destination = File(wineDir, "drive_c/Program Files/${exeFile.name}")
            destination.parentFile?.mkdirs()
            
            exeFile.copyTo(destination, overwrite = true)
            
            // Copy supporting files
            exeFile.parentFile?.listFiles()?.forEach { file ->
                if (file.isFile && (file.extension in listOf("dll", "ini", "cfg", "dat"))) {
                    val destFile = File(destination.parentFile, file.name)
                    file.copyTo(destFile, overwrite = true)
                }
            }

            Log.d("FileCoordinationService", "Executable copied to Wine: $destination")
        } catch (e: Exception) {
            Log.e("FileCoordinationService", "Error copying executable", e)
        }
    }

    /**
     * Backup game saves
     */
    fun backupGameSaves(savePath: String, backupPath: String): Job = serviceScope.launch {
        try {
            val saves = File(savePath)
            val backup = File(backupPath)

            if (!saves.exists()) {
                throw IllegalArgumentException("Save directory not found: $savePath")
            }

            backup.mkdirs()
            copyRecursively(saves, backup)

            Log.d("FileCoordinationService", "Saves backed up to: $backupPath")
        } catch (e: Exception) {
            Log.e("FileCoordinationService", "Error backing up saves", e)
        }
    }

    /**
     * Restore game saves from backup
     */
    fun restoreGameSaves(backupPath: String, savePath: String): Job = serviceScope.launch {
        try {
            val backup = File(backupPath)
            val saves = File(savePath)

            if (!backup.exists()) {
                throw IllegalArgumentException("Backup not found: $backupPath")
            }

            saves.mkdirs()
            copyRecursively(backup, saves)

            Log.d("FileCoordinationService", "Saves restored from: $backupPath")
        } catch (e: Exception) {
            Log.e("FileCoordinationService", "Error restoring saves", e)
        }
    }

    /**
     * Clean up temporary files
     */
    fun cleanupTempFiles(gameDir: String): Job = serviceScope.launch {
        try {
            val tempDir = File(gameDir, "temp")
            if (tempDir.exists()) {
                tempDir.deleteRecursively()
                tempDir.mkdirs()
            }

            Log.d("FileCoordinationService", "Temporary files cleaned up: $gameDir")
        } catch (e: Exception) {
            Log.e("FileCoordinationService", "Error cleaning up temp files", e)
        }
    }

    /**
     * Recursively copy directory
     */
    private fun copyRecursively(source: File, destination: File) {
        if (source.isDirectory) {
            destination.mkdirs()
            source.listFiles()?.forEach { file ->
                val newDest = File(destination, file.name)
                if (file.isDirectory) {
                    copyRecursively(file, newDest)
                } else {
                    file.copyTo(newDest, overwrite = true)
                }
            }
        } else {
            source.copyTo(destination, overwrite = true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }
}