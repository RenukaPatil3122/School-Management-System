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

public class viewattendence extends AppCompatActivity {

    private Spinner spinnerClass;
    private DatePicker datePicker;
    private ListView listViewAttendance;
    private Button buttonViewAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewattendence); // Ensure this matches your XML file name

        spinnerClass = findViewById(R.id.spinnerClass);
        datePicker = findViewById(R.id.datePicker);
        listViewAttendance = findViewById(R.id.listViewAttendance);
        buttonViewAttendance = findViewById(R.id.buttonViewAttendance);

        populateClassSpinner();

        buttonViewAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    viewAttendance();
                } catch (Exception e) {
                    Toast.makeText(viewattendence.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace(); // Print stack trace for debugging
                }
            }
        });
    }

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

    private void viewAttendance() {
        // Validate selected class and date
        if (spinnerClass.getSelectedItem() == null) {
            Toast.makeText(this, "Please select a class", Toast.LENGTH_SHORT).show();
            return;
        }

        String selectedClass = spinnerClass.getSelectedItem().toString();
        String selectedDate = datePicker.getDayOfMonth() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getYear();

        DBattendance dbAttendance = new DBattendance(this);

        ArrayList<String[]> attendanceList = dbAttendance.getAttendance(selectedClass, selectedDate);

        if (attendanceList.isEmpty()) {
            Toast.makeText(this, "No attendance records found for this class on this date.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Prepare data for ListView
        ArrayList<String> formattedList = new ArrayList<>();
        for (String[] record : attendanceList) {
            formattedList.add(record[0] + ": " + record[1]); // student name + status
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, formattedList);
        listViewAttendance.setAdapter(adapter);
    }
}
