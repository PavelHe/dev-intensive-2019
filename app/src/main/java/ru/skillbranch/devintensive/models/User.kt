package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils.parseFullName
import java.util.*

data class User(
    val id: String,
    val firstName: String?,
    val lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    var lastVisit: Date? = null,
    var isOnline: Boolean = false
) {
    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    private constructor(userBuilder: Builder) : this(
        userBuilder.id!!,
        userBuilder.firstName,
        userBuilder.lastName,
        userBuilder.avatar,
        userBuilder.rating,
        userBuilder.respect,
        userBuilder.lastVisit,
        userBuilder.isOnline
    )

    constructor(id: String) : this(id = id, firstName = null, lastName = null)

    companion object Factory {
        private var lastId = -1

        fun makeUser(fullName: String?): User {
            lastId++
            val (firstName, lastName) = parseFullName(fullName)
            return User(
                id = "$lastId",
                firstName = firstName,
                lastName = lastName
            )
        }
    }

    class Builder(
        var id: String? = null,
        var firstName: String? = null,
        var lastName: String? = null,
        var avatar: String? = null,
        var rating: Int = 0,
        var respect: Int = 0,
        var lastVisit: Date? = Date(),
        var isOnline: Boolean = false
    ) {
        fun id(id: String) = apply { this.id = id }

        fun firstName(firstName: String) = apply { this.firstName = firstName }

        fun lastName(lastName: String) = apply { this.lastName = lastName }

        fun avatar(avatar: String) = apply { this.avatar = avatar }

        fun rating(rating: Int) = apply { this.rating = rating }

        fun respect(respect: Int) = apply { this.respect = respect }

        fun lastVisit(lastVisit: Date) = apply { this.lastVisit = lastVisit }

        fun isOnline(isOnline: Boolean) = apply { this.isOnline = isOnline }

        fun build(): User {
            id = id ?: UUID.randomUUID().toString()
            return User(this)
        }
    }
}