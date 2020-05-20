package com.th3pl4gu3.locky.repository.database

import androidx.lifecycle.LiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot

interface IFirebaseRepository<T> {

    fun save(obj: T): Task<Void>

    fun update(obj: T): Task<Void>

    fun remove(key: String): Task<Void>

    fun getAll(key: String): LiveData<DataSnapshot>
}