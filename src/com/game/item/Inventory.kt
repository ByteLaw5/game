package com.game.item

import com.game.entity.Player
import java.lang.IndexOutOfBoundsException

class Inventory {
    val owner: Player
    val items: Array<Array<ItemStack?>> = arrayOf(emptyline(), emptyline(), emptyline(), emptyline())
    constructor(player: Player) {
        owner = player
    }
    fun getItemAt(row: Int, column: Int): ItemStack? {
        if(row > 3 || row < 0) throw IndexOutOfBoundsException("Row index out of range in Inventory(Player). Row index: $row.")
        else if(column > 10 || column < 0) throw IndexOutOfBoundsException("Column index out of range in Inventory(Player). Column index: $column.")
        return items[row][column]
    }
    fun setItemAt(row: Int, column: Int, stack: ItemStack): ItemStack {
        items[row][column] = stack
        return stack
    }
    fun push(stack: ItemStack?): Boolean {
        val slot = nextFreeSlot() ?: return false
        setItemAt(slot[0], slot[1], stack as ItemStack)
        return true
    }
    fun nextFreeSlot(): Array<Int>? {
        var i = 0
        var e = 0
        var a = false
        r@ for(j in 0..items.size) {
            var row = items[j]
            c@ for(y in 0..row.size) {
                var column = row[y]
                if(column == ItemStack.EMPTY) {
                    a = true
                    i = j
                    e = y
                    break@r
                }
            }
        }
        return if(a) arrayOf(i, e) else null
    }
    private companion object {
        fun emptyline(): Array<ItemStack?> = arrayOf(
            ItemStack.EMPTY as? ItemStack, ItemStack.EMPTY as? ItemStack, ItemStack.EMPTY as? ItemStack,
            ItemStack.EMPTY as? ItemStack, ItemStack.EMPTY as? ItemStack, ItemStack.EMPTY as? ItemStack,
            ItemStack.EMPTY as? ItemStack, ItemStack.EMPTY as? ItemStack, ItemStack.EMPTY as? ItemStack,
            ItemStack.EMPTY as? ItemStack)
    }
}