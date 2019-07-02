package ru.skillbranch.devintensive.utils

object Utils {

    private val transliterationMap = mapOf(
        'а' to "a", 'б' to "b", 'в' to "v", 'г' to "g", 'д' to "d", 'е' to "e", 'ё' to "e", 'ж' to "zh", 'з' to "z",
        'и' to "i", 'й' to "i", 'к' to "k", 'л' to "l", 'м' to "m", 'н' to "n", 'о' to "o", 'п' to "p", 'р' to "r",
        'с' to "s", 'т' to "t", 'у' to "u", 'ф' to "f", 'х' to "h", 'ц' to "c", 'ч' to "ch", 'ш' to "sh", 'щ' to "sh",
        'ъ' to "", 'ы' to "i", 'ь' to "", 'э' to "e", 'ю' to "yu", 'я' to "ya"
    )

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        if (fullName == null || fullName.trim().isEmpty()) return null to null
        val parts = fullName.split(" ")
        val firstName = parts.getOrNull(0)
        val lastName = parts.getOrNull(1)
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String = payload.map {
        val isUpper = it.isUpperCase()
        val letter = if (it == ' ') divider else transliterationMap[it.toLowerCase()] ?: it
        if (isUpper) letter.toString().capitalize() else letter
    }.joinToString("")

    fun toInitials(firstName: String?, lastName: String?): String? = when {
        firstName.isNullOrBlank() && lastName.isNullOrBlank() -> null
        !firstName.isNullOrBlank() && lastName.isNullOrBlank() -> firstName.first().toUpperCase().toString()
        firstName.isNullOrBlank() && !lastName.isNullOrBlank() -> lastName.first().toUpperCase().toString()
        !firstName.isNullOrBlank() && !lastName.isNullOrBlank() -> "${firstName.first().toUpperCase()}${lastName.first().toUpperCase()}"
        else -> throw IllegalArgumentException("Cat't parse value in toInitials")
    }
}