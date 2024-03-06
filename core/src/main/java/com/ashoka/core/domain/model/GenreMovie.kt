package com.ashoka.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenreMovie (
    val name: String,
    val id: Int
) : Parcelable