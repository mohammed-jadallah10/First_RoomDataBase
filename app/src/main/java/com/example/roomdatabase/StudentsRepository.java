package com.example.roomdatabase;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentsRepository {
    private StudentDao dao ;
      private  LiveData<List<Student>> getAllStudents;

      StudentsRepository(Application application){
          AppDatabase db = AppDatabase.getInstance(application);
          dao = db.studentDao();
          getAllStudents = dao.getAllStudents();
      }


      LiveData<List<Student>> AllStudents(){
          return getAllStudents;
      }

      void insert(Student student){
          AppDatabase.IO.execute(() ->{
              dao.InsertData(student);
          });
      }

      void update(Student student){
          AppDatabase.IO.execute(() ->{
              dao.UpdateData(student);
          });
      }


      void delete(Student student){
          AppDatabase.IO.execute(() ->{
              dao.deleteStudent(student);
          });
      }

      void deleteAllStudents(){
          AppDatabase.IO.execute(() ->{
              dao.DeleteAllData();
          });
      }
}
