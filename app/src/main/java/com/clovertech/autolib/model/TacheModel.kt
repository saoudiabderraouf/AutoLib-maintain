package com.clovertech.autolib.model

data class TacheModel(
    var id: Int,
    var taskModelName: String,
    var steps: List<Step>
) {
}