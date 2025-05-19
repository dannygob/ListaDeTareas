package com.example.listadetareas.utils

import androidx.recyclerview.widget.DiffUtil

class TaskDiffUtil(val oldList: List<Task>, val newList: List<Task>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
}

override fun getNewListSize(): Int = newList.size
}

override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    return {
        oldList[oldItemPosition].id !== newList[newItemPosition].id->false
        oldList[oldItemPosition].title !== newList[newItemPosition].id->false
        oldList[oldItemPosition].id !== newList[newItemPosition].id->false
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}


class TaskDiffUtils