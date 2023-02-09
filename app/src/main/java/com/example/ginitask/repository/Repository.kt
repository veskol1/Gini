package com.example.ginitask.repository

import com.example.ginitask.api.Api
import com.example.ginitask.model.GiniNumber
import com.example.ginitask.ui.GiniNumberViewItem
import javax.inject.Inject
import kotlin.math.abs

class Repository @Inject constructor(private val api: Api) {

    suspend fun getGiniNumbers(): ArrayList<GiniNumberViewItem>? {
        val response = api.getGiniNumbers()
        return if (!response.isSuccessful || response.body() == null) {
            null
        } else {
            getGoodList(response.body()!!.list)
        }
    }
    private fun getGoodList(list: List<GiniNumber>): ArrayList<GiniNumberViewItem> {
        val listOfNumber = arrayListOf<GiniNumberViewItem>()
        val hashMap = hashMapOf<Int,Int>()


        list.forEach { number ->
            val x = abs(number.value)
            if (hashMap[x] == null) {
                hashMap[x] = ORANGE
            } else {
                hashMap[x] = RED
            }
        }

        list.forEach { number ->
            if (hashMap[abs(number.value)] == RED) {
                listOfNumber.add(GiniNumberViewItem.RedItemView(value = number.value))
            } else {
                listOfNumber.add(GiniNumberViewItem.OrangeItemView(value = number.value))
            }
        }

        return listOfNumber
    }

    companion object {
        private const val ORANGE = 1
        private const val RED = 2
    }
}