package com.example.footballstats.data.remote.models


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("away_team")
    val awayTeam: AwayTeam,
    @SerializedName("group")
    val group: Group,
    @SerializedName("home_team")
    val homeTeam: HomeTeam,
    @SerializedName("league_id")
    val leagueId: Int,
    @SerializedName("match_id")
    val matchId: Int,
    @SerializedName("match_start")
    val matchStart: String,
    @SerializedName("match_start_iso")
    val matchStartIso: String,
    @SerializedName("minute")
    val minute: Int,
    @SerializedName("referee_id")
    val refereeId: Int,
    @SerializedName("round")
    val round: Round,
    @SerializedName("season_id")
    val seasonId: Int,
    @SerializedName("stage")
    val stage: Stage,
    @SerializedName("stats")
    val stats: Stats,
    @SerializedName("status")
    val status: String,
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("venue")
    val venue: Venue
)