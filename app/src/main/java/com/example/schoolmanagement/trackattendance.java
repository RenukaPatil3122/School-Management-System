package com.example.schoolmanagement;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class trackattendance extends AppCompatActivity {

    private Spinner spinnerClass;
    private DatePicker datePicker;
    private ListView listViewStudents;
    private Button buttonSave;
    private ArrayList<String> studentList; // List of all students
    private HashMap<String, Boolean> attendanceMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trackattendance); // Ensure this is the correct layout name

        // Initialize views
        spinnerClass = findViewById(R.id.spinnerClass);
        datePicker = findViewById(R.id.datePicker);
        listViewStudents = findViewById(R.id.listViewStudents);
        buttonSave = findViewById(R.id.buttonSave);

        // Populate the class spinner
        populateClassSpinner();

        // Sample student list (you can replace this with a database call to fetch actual students)
        studentList = new ArrayList<>();
        studentList.add("Sneha");
        studentList.add("Renuka");
        studentList.add("Pranav");
        studentList.add("Rohini");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, studentList);
        listViewStudents.setAdapter(adapter);
        listViewStudents.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // Set onClickListener for the Save button
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAttendance();
            }
        });
    }

    // Populate class spinner with class names
    private void populateClassSpinner() {
        ArrayList<String> classList = new ArrayList<>();
        classList.add("Class 1");
        classList.add("Class 2");
        classList.add("Class 3");
        classList.add("Class 4");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, classList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClass.setAdapter(adapter);
    }

    private void saveAttendance() {
        // Check if a class is selected
        if (spinnerClass.getSelectedItem() == null) {
            Toast.makeText(this, "Please select a class.", Toast.LENGTH_SHORT).show();
            return;
        }

        String selectedClass = spinnerClass.getSelectedItem().toString(); // Get selected class
        String selectedDate = datePicker.getDayOfMonth() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getYear();

        attendanceMap = new HashMap<>();
        for (int i = 0; i < listViewStudents.getCount(); i++) {
            attendanceMap.put(studentList.get(i), listViewStudents.isItemChecked(i)); // true if present
        }

        // Check if no attendance is marked
        if (attendanceMap.isEmpty()) {
            Toast.makeText(this, "No attendance marked. Please select students.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save attendance in the database
        DBattendance dbAttendance = new DBattendance(this);
        dbAttendance.insertAttendance(selectedClass, selectedDate, attendanceMap);
        Toast.makeText(this, "Attendance saved successfully.", Toast.LENGTH_SHORT).show();
    }
}
