package com.duran.gyoung_tae_93.pj.easywine.data.model

data class NoteInfoModel(
    var uid: String = "",
    var wineName: String = "",
    var wineType: String = "",
    var wineCountry: String = "",
    var wineArea: String = "",
    var wineVariety: String = "",
    var wineVintage: String = "",
    var wineAlcohol: String = "",
    var wineBuyDate: String = "",
    var wineDrinkDate: String = "",
    var winePrice: String = "",
    var wineNoteEtc: String = "",
    var wineSbSweetness: Int = 0,
    var wineSbAcidity: Int = 0,
    var wineSbTannin: Int = 0,
    var wineSbBody: Int = 0,
    var wineSbAlcohol: Int = 0,
    var wineSbAroma: Int = 0,
    var wineNoteAroma: String = "",
    var wineBalance: Int = 0,
    var wineLikable: Int = 0,
    var wineNoteTaste: String = "",
    var saveTime: String = "",
    var isChecked: Boolean = false
)
