package ru.skillbranch.devintensive.extensions

import kotlin.String

object StringExtension {

    fun String.truncate(truncateValue: Int = 16): String {
        if (this.trim().length < truncateValue)
            return this
        return this.trim().take(truncateValue).trim().plus("...")
    }

    fun String.stripHtml() = this.replace("<.*?>".toRegex(), "").replace("[\\s]+".toRegex(), " ")
}