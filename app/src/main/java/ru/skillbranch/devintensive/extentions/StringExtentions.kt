package ru.skillbranch.devintensive.extentions

fun String.truncate(amount: Int = 16): String {
    val result = this.trimEnd()
    return when {
        result.length <= amount -> result
        else -> "${result.substring(0, amount).trimEnd()}..."
    }
}

fun String.stripHtml(): String = this.replace("\\s+".toRegex(), " ")
    .replace("<.+?>".toRegex(), "")
    .replace("&.+?;".toRegex(), "")