package mathieu.lahet.mareu.ui.meeting_list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import mathieu.lahet.mareu.R;
import mathieu.lahet.mareu.di.DI;
import mathieu.lahet.mareu.events.DeleteMeetingEvent;
import mathieu.lahet.mareu.events.FilterMeetingsByDate;
import mathieu.lahet.mareu.events.FilterMeetingsByRoom;
import mathieu.lahet.mareu.model.Meeting;
import mathieu.lahet.mareu.service.MeetingApiService;

public class MeetingFragment extends Fragment {

    public MeetingApiService mApiService;
    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getMeetingApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meeting_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view; }

    /**
     * Init the meeting's list
     */
    private void initList(){
        List<Meeting> meetings = mApiService.getMeetings();
        mRecyclerView.setAdapter(new MyMeetingRecyclerViewAdapter(meetings));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        initList();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on delete button
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteMeetingEvent event) {
        mApiService.deleteMeeting(event.meeting);
        initList();
    }

    @Subscribe
    public void onFilterByDate(FilterMeetingsByDate event){
        mRecyclerView.setAdapter(new MyMeetingRecyclerViewAdapter(mApiService.getFilteredMeetingsByDate(event.date)));
    }

    @Subscribe
    public void onFilterByRoom(FilterMeetingsByRoom event){
        mRecyclerView.setAdapter(new MyMeetingRecyclerViewAdapter(mApiService.getFilteredMeetingsByDate(event.room)));
    }

}
