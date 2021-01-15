package com.example.campusmate.contentUI.branch

import com.example.campusmate.R

class Loader {
    fun getDataSet() : List<Item> {
        return listOf<Item>(
            Item(R.string.CSE),
            Item(R.string.CIVIL),
            Item(R.string.IT),
            Item(R.string.MECH),
            Item(R.string.ECE),
            Item(R.string.EEE)
        )
    }
}