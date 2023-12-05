package com.example.appclasebasedatos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appclasebasedatos.model.Pendiente

@Database(entities = [Pendiente::class], version = 1, exportSchema = false)
abstract class AppDataBase:RoomDatabase() {
    abstract fun pendienteDao():PendienteDao
    companion object{
        var instancedb:AppDataBase ?= null
        fun getInstance(context:Context):AppDataBase{
            if(instancedb==null){
                instancedb = Room.databaseBuilder(context, AppDataBase::class.java,"dbPendientesf").build()
            }
                return instancedb as AppDataBase
        }
    }

}