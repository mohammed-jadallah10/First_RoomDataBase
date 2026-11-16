package com.example.roomdatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    private final StudentsRepository repository ;
    private final LiveData<List<Student>> getAllStudents ;
    public StudentViewModel(@NonNull Application application) {
        super(application);
        repository = new StudentsRepository(application);
        getAllStudents = repository.AllStudents();
    }

    LiveData<List<Student>> getAllStudents(){
        return getAllStudents;
    }

    void insert(Student student){
        repository.insert(student);
    }
    void update(Student student){
       repository.update(student);
    }
    void delete(Student student){
    repository.delete(student);
    }

    void deleteAllStudents(){
        repository.deleteAllStudents();
    }
}
