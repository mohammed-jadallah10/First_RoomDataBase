package com.example.roomdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jspecify.annotations.NonNull;

@Entity(tableName = "students")
public class Student {


    @PrimaryKey(autoGenerate = true)
    private int id ;
    @NonNull
    private String username ;
    @NonNull
    @ColumnInfo(name = "userEmail")
    private String email ;
    private String password ;

    public Student(@NonNull String username, @NonNull String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public @NonNull String getUsername() {
        return username;
    }

    public @NonNull String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
