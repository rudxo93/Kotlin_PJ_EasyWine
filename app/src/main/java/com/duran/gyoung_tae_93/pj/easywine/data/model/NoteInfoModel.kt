package com.duran.gyoung_tae_93.pj.easywine.data.model

data class NoteInfoModel(
    val uid: String? = null,
    val wineName: String? = null,
    val wineType: String? = null,
    val wineCountry: String? = null,
    val wineArea: String? = null,
    val wineVariety: String? = null,
    val wineVintage: String? = null,
    val wineAlcohol: String? = null,
    val wineBuyDate: String? = null,
    val wineDrinkDate: String? = null,
    val winePrice: String? = null,
    val wineNoteEtc: String? = null,
    val wineSbSweetness: Int? = 0,
    val wineSbAcidity: Int? = 0,
    val wineSbTannin: Int? = 0,
    val wineSbBody: Int? = 0,
    val wineSbAlcohol: Int? = 0,
    val wineSbAroma: Int? = 0,
    val wineNoteAroma: String? = null,
    val wineBalance: Int? = 0,
    val wineLikable: Int? = 0,
    val wineNoteTaste: String? = null,
    val saveTime: String? = null,
    var isChecked: Boolean
)
