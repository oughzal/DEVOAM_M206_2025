package com.example.m203

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Entity(tableName = "User")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @ColumnInfo(name = "prenom")
    val firstName : String,
    @ColumnInfo(name = "nom")
    val lastName : String
)

@Dao //Data Access Object : interface qui définit les méthodes pour accéder à la base de données
interface UserDAO {
    @Query("SELECT * from User")
    suspend fun getUsers():List<User>

    @Query("SELECT * from User where id=:id")
    suspend fun getUser(id : Int):User

    @Insert
    suspend fun insertUser(user:User)

    @Update
    suspend fun updateUser(user:User)

    @Delete
    suspend fun deleteUser(user: User)
}

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDB : RoomDatabase() {
    abstract  fun userDao(): UserDAO
    companion object{
        @Volatile
        var INSTANCE : UserDB? = null
        fun getInstance(context: Context): UserDB? {
            if(INSTANCE != null) return INSTANCE as UserDB
            synchronized(this){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    UserDB::class.java,"user.db")
                    .build()
            }
            return INSTANCE
        }
    }
}
class UserViewModel(application: Application) : AndroidViewModel(application) {
    init {
        getUsers()
    }
    val userDao by lazy {  UserDB.getInstance(application.applicationContext)?.userDao()}
    var userList = MutableLiveData<List<User>>()

    fun getUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            val users =  userDao?.getUsers() ?: emptyList()

            userList.postValue(users)
        }
    }

}


