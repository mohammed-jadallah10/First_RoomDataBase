package com.example.roomdatabase;

import static android.app.ProgressDialog.show;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private StudentViewModel viewmodel ;
     EditText username , userEmail , password ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.first_ed);
        userEmail = findViewById(R.id.soc_ed);
        password = findViewById(R.id.thr_ed);
        StudentAdapter adapter = new StudentAdapter(this::editEmail , this::deleteStudent);
        RecyclerView rv = findViewById(R.id.Rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        viewmodel = new ViewModelProvider(this).get(StudentViewModel.class);


        viewmodel.getAllStudents().observe(this , adapter::setStudent);


        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(v -> {addStudent();});

        Button delete_btn = findViewById(R.id.button2);
        delete_btn.setOnClickListener(v -> DeleteAllStudent());




    }

    private void addStudent (){
        String name = username.getText().toString().trim();
        if (name.isEmpty()){
            username.setError("Username is required");
            return;
        }
        String email = userEmail.getText().toString().trim();
        String u_password = password.getText().toString().trim();
        if (u_password.isEmpty()){
            password.setError("Password is required");
            return;
        }

//        if (!email.contains("@")){
//            userEmail.setError("@ is required");
//            return;

//        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            userEmail.setError("Invalid email");
            return;
        }

         Student s  = new Student( name , email , u_password);
             viewmodel.insert(s);
          username.setText("");
          password.setText("");
          userEmail.setText("");
    }

    private void editEmail(Student s){
        EditText input = new EditText(this);
        input.setText(s.getEmail());

        new AlertDialog.Builder(this).setTitle("Email Edit  ")
                .setView(input).setPositiveButton("Save" , ((dialog, which) -> {
                    String i = input.getText().toString().trim();
                    if (!Patterns.EMAIL_ADDRESS.matcher(i).matches()){
                        Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (i.isEmpty()) return;
                    s.setEmail(i);
                    viewmodel.update(s);
                }))
                .setNegativeButton("Cancel" , null)
                .show();
        }

        private void deleteStudent(Student s){
        new AlertDialog.Builder(this).setTitle("Delete Student")
                .setPositiveButton("Delete" , ((dialog, which) ->
                        viewmodel.delete(s)))
                .setNegativeButton("Cancel" , null)
                .show();
        }


        private void DeleteAllStudent(){
        new AlertDialog.Builder(this).setTitle("Delete All Student").setMessage("Are you sure about confirm delete ?").setPositiveButton("Yes" ,
                ((dialog, which) -> {viewmodel.deleteAllStudents();}))
                .setNegativeButton("Cancel" , null).show();
        }
}
