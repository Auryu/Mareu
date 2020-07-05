package mathieu.lahet.mareu.ui.meeting_list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mathieu.lahet.mareu.R;
import mathieu.lahet.mareu.di.DI;
import mathieu.lahet.mareu.model.Meeting;
import mathieu.lahet.mareu.service.MeetingApiService;

public class AddMeetingActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.create)
    TextView createMeeting;
    @BindView(R.id.btn_date)
    ImageButton btnDatePicker;
    @BindView(R.id.btn_time)
    ImageButton btnTimePicker;
    @BindView(R.id.in_date)
    EditText txtDate;
    @BindView(R.id.in_time)
    EditText txtTime;
    @BindView(R.id.name)
    EditText workerName;
    @BindView(R.id.room)
    Spinner mSpinner;
    @BindView(R.id.subject)
    EditText meetingSubject;
    @BindView(R.id.participants)
    EditText participantsName;
    @BindView(R.id.book)
    Button bookRoom;

    String[] mRooms = {"Salle A", "Salle B", "Salle C", "Salle D", "Salle E", "Salle F", "Salle G", "Salle H", "Salle I", "Salle J"};

    private int mYear, mMonth, mDay, mHour, mMinute;

    private MeetingApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        ButterKnife.bind(this);
        mApiService = DI.getMeetingApiService();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, mRooms);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        init();

    }

    @Override
    public void onClick(View v) {if (v == btnDatePicker) {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void init(){
        participantsName.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) { bookRoom.setEnabled(s.length() > 0); }
        });
    }

    @OnClick(R.id.book)
    void addMeeting() {
        Meeting meeting = new Meeting(
                System.currentTimeMillis(),
                txtDate.getText().toString(),
                txtTime.getText().toString(),
                workerName.getText().toString(),
                mSpinner.getResources().toString(),
                meetingSubject.getText().toString(),
                participantsName.getText().toString()
        );
        mApiService.createMeeting(meeting);
        finish();
    }

    /**
     * Used to navigate to this activity
     * @param activity
     */
    public static void navigate(FragmentActivity activity) {
        Intent addMeetingActivityIntent = new Intent(activity, AddMeetingActivity.class);
        ActivityCompat.startActivity(activity, addMeetingActivityIntent, null);
    }
}
