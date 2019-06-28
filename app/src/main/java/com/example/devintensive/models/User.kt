package com.example.devintensive.models

import com.example.devintensive.utils.Utils.parseFullName
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

    private constructor(userBuilder: Factory) : this(
        userBuilder.id,
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
        private var id: String = "<EMPTY>"
        private var firstName: String? = null
        private var lastName: String? = null
        private var avatar: String? = null
        private var rating: Int = 0
        private var respect: Int = 0
        private var lastVisit: Date? = null
        private var isOnline: Boolean = false

        fun makeUser(fullName: String?): User {
            val (firstName, lastName) = parseFullName(fullName)
            return User(id = "${lastId++}", firstName = firstName, lastName = lastName)
        }

        fun id(id: String): Factory {
            Factory.id = id
            return this
        }

        fun firstName(firstName: String?): Factory {
            Factory.firstName = firstName
            return this
        }

        fun lastName(lastName: String?): Factory {
            Factory.lastName = lastName
            return this
        }

        fun avatar(avatar: String?): Factory {
            Factory.avatar = avatar
            return this
        }

        fun rating(rating: Int): Factory {
            Factory.rating = rating
            return this
        }

        fun respect(respect: Int): Factory {
            Factory.respect = respect
            return this
        }

        fun lastVisit(lastVisit: Date?): Factory {
            Factory.lastVisit = lastVisit
            return this
        }

        fun isOnline(isOnline: Boolean): Factory {
            Factory.isOnline = isOnline
            return this
        }

        fun build(): User {
            val user = User(this)
            toDefault()
            return user
        }

        private fun toDefault() {
            id = "<EMPTY>"
            firstName = null
            lastName = null
            avatar = null
            rating = 0
            respect = 0
            lastVisit = null
            isOnline = false
        }
    }
}