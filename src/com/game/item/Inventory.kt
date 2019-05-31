package com.game.item

import com.game.entity.Player
import java.lang.IndexOutOfBoundsException

class Inventory {
    val owner: Player
    val items: Array<Array<StackedItem?>> = arrayOf(emptyline(), emptyline(), emptyline(), emptyline())
    constructor(player: Player) {
        owner = player
    }
    fun getItemAt(row: Int, column: Int): StackedItem? {
        if(row > 3 || row < 0) throw IndexOutOfBoundsException("Row index out of range in Inventory(Player). Row index: $row.")
        else if(column > 10 || column < 0) throw IndexOutOfBoundsException("Column index out of range in Inventory(Player). Column index: $column.")
        return items[row][column]
    }
    fun setItemAt(row: Int, column: Int, stack: StackedItem): StackedItem {
        items[row][column] = stack
        return stack
    }
    @Deprecated("Use .add(stack)")
    fun push(stack: StackedItem?): Boolean {
        val slot = nextFreeSlot() ?: return false
        setItemAt(slot[0], slot[1], stack as StackedItem)
        return true
    }
    fun add(stack: StackedItem): StackedItem? {
        val leftAmount = stack.stack
        r@ for(y in 0..items.size) {
            var row = items[y]
            c@ for(x in 0..row.size) {
                val slotStack = row[x]
                val condition = slotStack?.item?.javaClass?.equals(stack.item.javaClass)
                if(condition != null && condition && slotStack.stack + leftAmount > stack.maxStack && slotStack.stack != stack.maxStack) {
                    val newAmount = slotStack.stack + leftAmount - stack.maxStack
                    slotStack.stack = slotStack.maxStack
                    return add(StackedItem(stack.item, newAmount))
                } else if(condition != null && condition && slotStack.stack + leftAmount <= stack.maxStack) {
                    slotStack.stack += leftAmount
                    return null
                }
            }
        }
        val nextSlot = nextFreeSlot()
        if(nextSlot != null) setItemAt(nextSlot[0], nextSlot[1], stack)
        return if(nextSlot != null) null else stack
    }
    fun nextFreeSlot(): Array<Int>? {
        var i = 0
        var e = 0
        var a = false
        r@ for(j in 0..items.size) {
            var row = items[j]
            c@ for(y in 0..row.size) {
                var column = row[y]
                if(column == StackedItem.EMPTY) {
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
        fun emptyline(): Array<StackedItem?> = arrayOf(
            StackedItem.EMPTY as? StackedItem, StackedItem.EMPTY as? StackedItem, StackedItem.EMPTY as? StackedItem,
            StackedItem.EMPTY as? StackedItem, StackedItem.EMPTY as? StackedItem, StackedItem.EMPTY as? StackedItem,
            StackedItem.EMPTY as? StackedItem, StackedItem.EMPTY as? StackedItem, StackedItem.EMPTY as? StackedItem,
            StackedItem.EMPTY as? StackedItem)
    }
}