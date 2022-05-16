package com.example.stant_challenge.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(

    @SerializedName("id")
    val id: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("poster_path")
    val poster: String?,

    @SerializedName("release_date")
    var release: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("original_language")
    val language: String?,

    @SerializedName("vote_average")
    val rate: Float?,

    @SerializedName("genre_ids")
    val genres: Array<Int>?,

    var genreNames: String?,

    var firstGenreName: String?

) : Parcelable {
    constructor() : this("", "", "", "", "", "", 0F, arrayOf(), "", "")
}

