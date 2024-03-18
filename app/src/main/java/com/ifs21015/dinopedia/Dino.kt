package com.ifs21015.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Dino(
    var name: String,
    var icon: Int,
    var description: String,
    var characteristic: String,
    var habitat: String,
    var process: String,
    val food: String,
    val long: String,
    val weight: String,
    val weakness: String,
) : Parcelable