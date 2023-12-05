package com.example.appclasebasedatos.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.appclasebasedatos.model.Pendiente

@Dao
interface PendienteDao {
    @Insert
    fun insert(pendiente: Pendiente):Long
    @Delete
    fun delete(pendiente: Pendiente)
    @Update
    fun update(pendiente: Pendiente)
    @Query("select * from tblPendiente order by id")
    fun getPendientes():LiveData<List<Pendiente>>
    @Query("select * from tblPendiente where id=:idInput")
    fun getPendienteById(idInput:Int):Pendiente

}


