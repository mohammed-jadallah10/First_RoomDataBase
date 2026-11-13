package com.example.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao{
    @Insert
    void InsertData(Student student);

    @Update
    void UpdateData(Student student);


    @Delete
    void deleteStudent(Student student);

    @Query("Delete From students")
    void DeleteAllData();

    @Query("Select * From students Order by username ASC")
    LiveData<List<Student>> getAllStudents();
}
