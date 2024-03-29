package com.game.item

class StackedItem {
    var item: Item
    var stack: Int
    val maxStack: Int get() = item.maxStack
    constructor(item: Item, stack: Int) {
        this.item = item
        this.stack = stack
    }

    override fun toString(): String {
        return item.registryName + ":" + stack
    }

    fun equals(other: StackedItem): Boolean {
        return this == other || this.item == other.item && this.stack == other.stack && this.maxStack == other.maxStack
    }

    fun isEmpty(): Boolean {
        return this.stack == 0 || this.item is EmptyItem || this.equals(EMPTY)
    }

    companion object {
        val EMPTY: StackedItem = StackedItem(EmptyItem(), 0)
    }
}
