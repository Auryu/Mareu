package mathieu.lahet.mareu.ui.meeting_list;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mathieu.lahet.mareu.R;
import mathieu.lahet.mareu.di.DI;
import mathieu.lahet.mareu.events.FilterMeetingsByDate;
import mathieu.lahet.mareu.events.FilterMeetingsByRoom;
import mathieu.lahet.mareu.model.Meeting;
import mathieu.lahet.mareu.service.MeetingApiService;

public class ListMeetingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    String[] room = new String[] {"Réunion A", "Réunion B", "Réunion C", "Réunion D", "Réunion F", "Réunion G", "Réunion H", "Réunion I", "Réunion J"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int mYear, mMonth, mDay;

        switch (item.getItemId()) {
            case R.id.date_filter:
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
                                String dateFilterSelected = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                                EventBus.getDefault().post(new FilterMeetingsByDate(dateFilterSelected));
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                return true;

            case R.id.room_filter:
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ListMeetingActivity.this);
                mBuilder.setTitle("")
                        .setSingleChoiceItems(room, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNeutralButton("Annuler", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
                return true;

            case R.id.no_filter:
                Toast.makeText(this, "Filtre supprimé", Toast.LENGTH_SHORT).show();
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.add_meeting)
    void addMeeting() { AddMeetingActivity.navigate(this); }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
