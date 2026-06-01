package com.parakeet.steamcloud.steam

import retrofit2.http.*
import com.google.gson.annotations.SerializedName

/**
 * Retrofit interface for Steam Web API
 */
interface SteamWebAPI {

    @GET("ISteamUser/GetPlayerSummaries/v0002/")
    suspend fun getPlayerSummaries(
        @Query("steamids") steamids: String,
        @Query("key") key: String
    ): SteamResponse<List<PlayerSummary>>

    @GET("IPlayerService/GetOwnedGames/v1/")
    suspend fun getOwnedGames(
        @Query("steamid") steamid: String,
        @Query("key") key: String,
        @Query("include_appinfo") include_appinfo: Boolean
    ): SteamOwnedGamesResponse

    @GET("ISteamUser/ResolveVanityURL/v1/")
    suspend fun resolveVanityUrl(
        @Query("vanityurl") vanityurl: String,
        @Query("key") key: String
    ): SteamVanityUrlResponse
}

// Response Models
data class SteamResponse<T>(
    @SerializedName("response")
    val response: SteamResponseData<T>?
)

data class SteamResponseData<T>(
    @SerializedName("players")
    val players: T?
)

data class PlayerSummary(
    @SerializedName("steamid")
    val steamid: String,
    @SerializedName("personaname")
    val personaname: String,
    @SerializedName("profileurl")
    val profileurl: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("avatarmedium")
    val avatarmedium: String,
    @SerializedName("avatarfull")
    val avatarfull: String,
    @SerializedName("personastate")
    val personastate: Int,
    @SerializedName("communityvisibilitystate")
    val communityvisibilitystate: Int,
    @SerializedName("profilestate")
    val profilestate: Int?,
    @SerializedName("lastlogoff")
    val lastlogoff: Long?,
    @SerializedName("commentpermission")
    val commentpermission: Int?,
    @SerializedName("realname")
    val realname: String?,
    @SerializedName("primaryclanid")
    val primaryclanid: String?,
    @SerializedName("timecreated")
    val timecreated: Long?,
    @SerializedName("personastateflags")
    val personastateflags: Int?
)

data class SteamOwnedGamesResponse(
    @SerializedName("response")
    val response: OwnedGamesData?
)

data class OwnedGamesData(
    @SerializedName("game_count")
    val game_count: Int,
    @SerializedName("games")
    val games: List<OwnedGame>?
)

data class OwnedGame(
    @SerializedName("appid")
    val appid: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("playtime_forever")
    val playtime_forever: Int,
    @SerializedName("img_icon_url")
    val img_icon_url: String,
    @SerializedName("img_logo_url")
    val img_logo_url: String,
    @SerializedName("has_community_visible_stats")
    val has_community_visible_stats: Boolean?,
    @SerializedName("playtime_windows_forever")
    val playtime_windows_forever: Int?,
    @SerializedName("playtime_mac_forever")
    val playtime_mac_forever: Int?,
    @SerializedName("playtime_linux_forever")
    val playtime_linux_forever: Int?
)

data class SteamVanityUrlResponse(
    @SerializedName("response")
    val response: VanityUrlData?
)

data class VanityUrlData(
    @SerializedName("steamid")
    val steamid: String,
    @SerializedName("success")
    val success: Int
)