package com.example.notefullappviewmodel

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities= [NoresTable::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    companion object{
        var instance:NotesDatabase?=null

        //this method will take context and return instance of this class
        fun getInstance(context: Context):NotesDatabase {

            //this if statement will be used (true) only after database was built using databaseBuilder
            //as we gonna get the instance of database form instance variable
            //because we have saved a reference of database build when it build in instance variable and we reuse it now
            if(instance!=null){
                return instance as NotesDatabase
            }

            /*Creates a RoomDatabase.Builder for a persistent database.
            Once a database is built, you should keep a reference to it and re-use it.
            (that is why we store it in a variable to use it again)
            take 3 parameter: context, klass: abstract class which is annotated with Database and extends RoomDatabase. (this class), name of database  */

            //this code will be execute for first time (create it) only once
            instance= Room.databaseBuilder(context,NotesDatabase::class.java, "data").run {allowMainThreadQueries()}.build();

            //return the instance
            return instance as NotesDatabase
        }
    }//end companion object

    //3) create fun that return Dao interface to get DAOs which access the entities
    // (the fun must be abstract since class is)
    abstract fun getNotesDao():NotesDao;
}