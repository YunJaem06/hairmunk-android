package com.hairmunk.app.model

data class ResultSearchKeyword(
    var documents : List<Place>
)

data class Place(
    var place_name: String,
    var address_name: String,
    var road_address_name: String,
    var x: String,
    var y: String
)
