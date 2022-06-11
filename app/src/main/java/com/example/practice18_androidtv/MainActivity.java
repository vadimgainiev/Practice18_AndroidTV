package com.example.practice18_androidtv;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import java.util.Calendar;

public class MainActivity extends FragmentActivity {

    TextView dateTimeTitle;
    Calendar dateTime = Calendar.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateTimeTitle = findViewById(R.id.txtTitle);
        Button dateBtn = findViewById(R.id.btnChangeDate);
        Button timeBtn = findViewById(R.id.btnChangeTime);

        dateBtn.setOnClickListener(v -> new DatePickerDialog(this, d,
                dateTime.get(Calendar.YEAR),
                dateTime.get(Calendar.MONTH),
                dateTime.get(Calendar.DAY_OF_MONTH))
                .show());

        timeBtn.setOnClickListener(v -> new TimePickerDialog(this, t,
                dateTime.get(Calendar.HOUR_OF_DAY),
                dateTime.get(Calendar.MINUTE),
                true)
                .show());
    }

    private final DatePickerDialog.OnDateSetListener d =
            (view, year, month, dayOfMonth) -> {
                dateTime.set(Calendar.YEAR, year);
                dateTime.set(Calendar.MONTH, month);
                dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                setDateAndTime();
            };

    private final TimePickerDialog.OnTimeSetListener t =
            (view, hourOfDay, minute) -> {
                dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                dateTime.set(Calendar.MINUTE, minute);
                setDateAndTime();
            };

    private void setDateAndTime() {
        dateTimeTitle.setText(DateUtils.formatDateTime(this, dateTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_DATE
                        | DateUtils.FORMAT_SHOW_TIME));
    }
}