package com.example.roomdatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    public interface StudentAction{
        void run(Student student);

    } // هادي الدالة هتشغلي ال Action الخاص بال student
    private List<Student> getAllStudents = new ArrayList<>();
    private final StudentAction onClick ;
    private final StudentAction onlongClick ;

    public StudentAdapter(StudentAction onClick, StudentAction onlongClick) {
        this.onClick = onClick;
        this.onlongClick = onlongClick;
    }

    public void setStudent(List<Student> NewgetAllStudent) {
        getAllStudents = NewgetAllStudent;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentAdapter.StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_shape , parent , false );

        return new StudentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.StudentViewHolder holder, int position) {
        Student s = getAllStudents.get(position);
        holder.username.setText(s.getUsername());
        holder.email.setText(s.getEmail());
        holder.password.setText(s.getPassword());
        holder.itemView.setOnClickListener(v -> {
            onClick.run(s);
        });


        holder.itemView.setOnLongClickListener(v -> {
            onlongClick.run(s);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return getAllStudents.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView username , email , password ;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.fir_txt);
            email = itemView.findViewById(R.id.soc_txt);
            password = itemView.findViewById(R.id.thr_txt);


        }
    }
}
