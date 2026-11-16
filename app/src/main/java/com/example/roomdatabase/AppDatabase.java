package com.example.roomdatabase;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Student.class} , version = 2 , exportSchema = false)
public abstract class AppDatabase  extends RoomDatabase {
    public abstract StudentDao studentDao() ;
    private static volatile AppDatabase instance ;
    private static final int Number_Of_Threads = 4 ;
    static final ExecutorService IO =
            Executors.newFixedThreadPool(Number_Of_Threads);

    public static synchronized AppDatabase getInstance(Context context){
    if (instance == null){
        instance = Room.databaseBuilder
                (context.getApplicationContext() , AppDatabase.class , "School.db").addCallback(roomcallback)
                .build();
    }


        return instance;
    }


    private static final RoomDatabase.Callback roomcallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            IO.execute(() ->{
                if (instance == null)return;
                StudentDao dao = instance.studentDao();
                dao.InsertData(new Student("Mohammed" , "medo@gmail.com" , "1050$&55"));


            });
        }
    };


}
