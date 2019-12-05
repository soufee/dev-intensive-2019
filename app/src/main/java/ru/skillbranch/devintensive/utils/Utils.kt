package ru.skillbranch.devintensive.utils

import android.annotation.SuppressLint

object Utils {

    private val transliterationMap = mapOf(
        'а' to "a", 'б' to "b", 'в' to "v", 'г' to "g", 'д' to "d",
        'е' to "e", 'ё' to "e", 'ж' to "zh", 'з' to "z", 'и' to "i",
        'й' to "i", 'к' to "k", 'л' to "l", 'м' to "m", 'н' to "n",
        'о' to "o", 'п' to "p", 'р' to "r", 'с' to "s", 'т' to "t",
        'у' to "u", 'ф' to "f", 'х' to "h", 'ц' to "c", 'ч' to "ch",
        'ш' to "sh", 'щ' to "sh'", 'ъ' to "", 'ы' to "i", 'ь' to "",
        'э' to "e", 'ю' to "yu", 'я' to "ya"
    )

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        if (fullName == null || "".equals(fullName) || " ".equals(fullName)) return null to null else {
            val parts: List<String>? = fullName.split(" ")
            val firstName = parts?.getOrNull(0)
            val lastName = parts?.getOrNull(1)
            return firstName to lastName
        }

    }

    fun transliteration(payload: String, divider: String = " "): String {
        val builder = StringBuilder()

        for (char in payload.trim())
            builder.append(getTranslChar(char))

        return builder.toString().replace(" ", divider)
    }

    @SuppressLint("DefaultLocale")
    private fun getTranslChar(char: Char): String {
        val transl  = transliterationMap[char.toLowerCase()] ?: char.toString()

        return if (char.isUpperCase() && transl.isNotEmpty())
            transl.capitalize()
        else transl

    }


        fun toInitials(firstName: String?, lastName: String?): String? {
        val name = firstName.orEmpty().trim().getOrNull(0)?.toUpperCase()
        val surname = lastName.orEmpty().trim().getOrNull(0)?.toUpperCase()
        val firstInit = name?.toString() ?: ""
        val secondInit = surname?.toString() ?: ""
        return "$firstInit$secondInit".ifEmpty { null }
    }
}