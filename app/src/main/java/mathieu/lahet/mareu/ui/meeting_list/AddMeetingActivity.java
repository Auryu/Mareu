package mathieu.lahet.mareu.ui.meeting_list;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;


import java.util.Calendar;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mathieu.lahet.mareu.R;
import mathieu.lahet.mareu.di.DI;
import mathieu.lahet.mareu.model.Meeting;
import mathieu.lahet.mareu.service.MeetingApiService;

public class AddMeetingActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.create)
    TextView mCreateMeeting;
    @BindView(R.id.arrow_back)
    ImageButton mBack;
    @BindView(R.id.btn_date)
    ImageButton mBtnDatePicker;
    @BindView(R.id.btn_time)
    ImageButton mBtnTimePicker;
    @BindView(R.id.in_date)
    EditText mTxtDate;
    @BindView(R.id.in_time)
    EditText mTxtTime;
    @BindView(R.id.name)
    EditText mCreatorName;
    @BindView(R.id.room)
    Spinner mSpinner;
    @BindView(R.id.subject)
    EditText mMeetingSubject;
    @BindView(R.id.participants)
    EditText mParticipantsName;
    @BindView(R.id.book)
    Button mBookRoom;

    String[] room = new String[] {"Réunion A", "Réunion B", "Réunion C", "Réunion D", "Réunion F", "Réunion G", "Réunion H", "Réunion I", "Réunion J"};

    private int mYear, mMonth, mDay, mHour, mMinute;

    private MeetingApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        ButterKnife.bind(this);
        mApiService = DI.getMeetingApiService();

        mBtnDatePicker.setOnClickListener(this);
        mBtnTimePicker.setOnClickListener(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, room);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {

        if (v == mBtnDatePicker) {

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

                        mTxtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if (v == mBtnTimePicker) {

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

                            mTxtTime.setText(hourOfDay + "h" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }

    private void init(){
        mParticipantsName.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) { mBookRoom.setEnabled(s.length() > 0); }
        });
    }

    @OnClick(R.id.book)
    void addMeeting() {
        Meeting meeting = new Meeting(
                System.currentTimeMillis(),
                mTxtDate.getText().toString(),
                mTxtTime.getText().toString(),
                mCreatorName.getText().toString(),
                mMeetingSubject.getText().toString(),
                mSpinner.getSelectedItem().toString(),
                mParticipantsName.getText().toString()
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
