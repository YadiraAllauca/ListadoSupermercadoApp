package com.example.appclasebasedatos.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity(tableName = "tblPendiente")
data class Pendiente(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Int,
    @ColumnInfo(name = "articulo")
    val articulo:String,
    @ColumnInfo(name = "cantidad")
    val cantidad:String,
    @ColumnInfo(name = "limite")
    val limite:String,
    @ColumnInfo(name = "supermercado")
    val supermercado:String,
    @ColumnInfo(name = "precio")
    val precio:String
):Serializable
