package com.duran.gyoung_tae_93.pj.easywine.data.model.note

import java.io.Serializable
import java.text.SimpleDateFormat

data class NoteInfoModel(
    var uid: String? = null,
    var imageUrl: String? = null,
    var wineName: String? = null,
    var wineType: String? = null,
    var wineCountry: String? = null,
    var wineArea: String? = null,
    var wineVariety: String? = null,
    var wineVintage: String? = null,
    var wineAlcohol: String? = null,
    var wineBuyDate: String? = null,
    var wineDrinkDate: String? = null,
    var winePrice: String? = null,
    var wineNoteEtc: String? = null,
    var wineSbSweetness: Int = 0,
    var wineSbAcidity: Int = 0,
    var wineSbTannin: Int = 0,
    var wineSbBody: Int = 0,
    var wineSbAlcohol: Int = 0,
    var wineSbAroma: Int = 0,
    var wineNoteAroma: String? = null,
    var wineBalance: Int = 0,
    var wineLikable: Int = 0,
    var wineNoteTaste: String? = null,
    var saveTime: Long? = null,
    var isChecked: Int = 0
): Serializable
