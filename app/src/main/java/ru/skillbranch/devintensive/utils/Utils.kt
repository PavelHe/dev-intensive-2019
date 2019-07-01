package ru.skillbranch.devintensive.utils

import java.lang.IllegalArgumentException

object Utils {

    private val transliterationMap = mapOf(
        "а" to "a",
        "б" to "b",
        "в" to "v",
        "г" to "g",
        "д" to "d",
        "е" to "e",
        "ё" to "e",
        "ж" to "zh",
        "з" to "z",
        "и" to "i",
        "й" to "i",
        "к" to "k",
        "л" to "l",
        "м" to "m",
        "н" to "n",
        "о" to "o",
        "п" to "p",
        "р" to "r",
        "с" to "s",
        "т" to "t",
        "у" to "u",
        "ф" to "f",
        "х" to "h",
        "ц" to "c",
        "ч" to "ch",
        "ш" to "sh",
        "щ" to "sh'",
        "ъ" to "",
        "ы" to "i",
        "ь" to "",
        "э" to "e",
        "ю" to "yu",
        "я" to "ya"
    )

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        if (fullName == null || fullName.trim().isEmpty()) return null to null
        val parts = fullName.split(" ")
        val firstName = parts.getOrNull(0)
        val lastName = parts.getOrNull(1)
        return firstName to lastName
    }

    fun transliteration(fullName: String, divider: String = " "): String {
        fun getLetter(str: String) = transliterationMap[str] ?: str

        val fullUserName = fullName.split(" ")
        val first = fullUserName.getOrNull(0)?.map { getLetter(it.toString().toLowerCase()) }
            ?.joinToString(separator = "", prefix = "", postfix = "")
        val second = fullUserName.getOrNull(1)?.map { getLetter(it.toString().toLowerCase()) }
            ?.joinToString(separator = "", prefix = "", postfix = "")

        return "${first?.capitalize()}$divider${second?.capitalize()}"
    }

    fun toInitials(firstName: String?, lastName: String?): String {
        if (firstName == null && lastName == null) return "null"
        if (firstName?.trim().isNullOrEmpty() && lastName?.trim().isNullOrEmpty()) return "null null"
        if (firstName == null) throw IllegalArgumentException("First Name can't be null")

        val first = firstName.trim().first().toUpperCase()
        if (lastName?.trim().isNullOrEmpty()) return "$first"
        val second = lastName?.first()?.toUpperCase()

        return "$first$second".trim()
    }

}