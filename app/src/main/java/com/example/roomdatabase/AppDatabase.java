package com.example.roomdatabase;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Student.class} , version = 1 , exportSchema = false)
public abstract class AppDatabase  extends RoomDatabase {
    public abstract StudentDao studentDao() ;
    private static volatile AppDatabase instance ;
    private static final int Number_Of_Threads = 4 ;
    static final ExecutorService IO =
            Executors.newFixedThreadPool(Number_Of_Threads);

    public static synchronized AppDatabase getInstance(Context context){
    if (instance == null){
        instance = Room.databaseBuilder
                (context.getApplicationContext() , AppDatabase.class , "School.db")
                .build();
    }


        return instance;
    }


}
