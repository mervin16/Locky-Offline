package com.th3pl4gu3.locky.repository.database

import android.app.Application
import com.th3pl4gu3.locky.core.main.User

class UserRepository private constructor(application: Application) {

    private val database = Database.getDatabase(application)
    private val userDao = database.userDao()

    companion object {

        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(application: Application) =
            instance ?: synchronized(this) {
                instance ?: UserRepository(application).also { instance = it }
            }
    }

    fun get(key: String) = userDao.get(key)

    suspend fun insert(user: User) = userDao.insert(user)

    suspend fun update(user: User) = userDao.update(user)

    suspend fun delete(key: String) = userDao.remove(key)
}