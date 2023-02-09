package com.example.ginitask.ui

sealed class GiniNumberViewItem {

    class RedItemView(
        val value: Int
    ): GiniNumberViewItem()

    class OrangeItemView(
        val value: Int
    ): GiniNumberViewItem()
}