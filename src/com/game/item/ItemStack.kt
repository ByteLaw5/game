package com.game.item

class ItemStack {
    val item: Item
    val stack: Int
    val maxStack: Int get() = item.maxStack
    constructor(item: Item, stack: Int) {
        this.item = item
        this.stack = stack
    }
}
