package com.duran.gyoung_tae_93.pj.easywine.data.model.type.style

import java.io.Serializable

data class StyleModel(
    var uid: String? = null,
    var wineTitle: String? = null,
    var aroma: String? = null,
    var ratingFlavor: Float = 0.0F,
    var ratingBody: Float = 0.0F,
    var ratingSweet: Float = 0.0F,
    var ratingAcidity: Float = 0.0F,
    var ratingAlcohol: Float = 0.0F,
    var saveTime: Long? = null,
    var isChecked: Boolean
): Serializable
