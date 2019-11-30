package ru.skillbranch.devintensive.models

import java.util.*

abstract class BaseMessage(
    open val id: String,
    open val from: User?,
    open val chat: Chat,
    open val isIncoming: Boolean = false,
    open val date: Date = Date()
) {
    abstract fun formatMessage(): String

    companion object AbstractFactory {
        var lastId = -1;
        fun makeMessage(
            from: User?,
            chat: Chat,
            date: Date = Date(),
            type: String = "text",
            payLoad: Any?
        ): BaseMessage {
            lastId++
            return when (type) {
                "image" -> ImageMessage("$lastId", from, chat, date = date, image = payLoad as String)
                else -> TextMessage("$lastId", from, chat, date = date, text = payLoad as String)
            }
        }
    }


}