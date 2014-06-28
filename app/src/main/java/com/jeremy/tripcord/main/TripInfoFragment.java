package com.jeremy.tripcord.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremy.tripcord.app.R;
import com.jeremy.tripcord.common.database.DatabaseManager;
import com.jeremy.tripcord.common.database.domain.TripInfo;
import com.jeremy.tripcord.main.model.TripInfoModel;
import com.jeremy.tripcord.main.view.TripInfoListAdaptor;
import com.jeremy.tripcord.record.RecordActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asura1983 on 2014. 6. 22..
 */
public class TripInfoFragment extends Fragment {

//    DatabaseManager databaseManager;
    List<TripInfo> tripInfoList;

    ListView listViewTripInfos;

    public static TripInfoFragment newInstance() {

        TripInfoFragment fragment = new TripInfoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public TripInfoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        initModules();
        initViews(rootView);

        loadTripInfoList();

        return rootView;
    }

    private void initViews(View rootView) {

        listViewTripInfos = (ListView) rootView.findViewById(R.id.listView_trip_infos);

        TripInfoListAdaptor tripInfoListAdaptor = new TripInfoListAdaptor(getActivity(), R.layout.adapter_trip_info_list, tripInfoList);
        listViewTripInfos.setAdapter(tripInfoListAdaptor);

        Button buttonNewTrip = (Button) rootView.findViewById(R.id.button_new_trip);
        buttonNewTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRecord = new Intent(getActivity().getApplicationContext(), RecordActivity.class);
                getActivity().startActivity(intentRecord);
            }
        });
    }

    private void initModules() {

        tripInfoList = new ArrayList<TripInfo>();

//        databaseManager = new DatabaseManager(getActivity());
//        databaseManager.open();
    }

    private void loadTripInfoList() {

        tripInfoList = TripInfoModel.loadTripInfoList(getActivity().getApplicationContext());

        TripInfoListAdaptor tripInfoListAdaptor = (TripInfoListAdaptor) listViewTripInfos.getAdapter();
        tripInfoListAdaptor.clear();
        tripInfoListAdaptor.addAll(tripInfoList);
        tripInfoListAdaptor.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();

        loadTripInfoList();
    }

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
    }

}
