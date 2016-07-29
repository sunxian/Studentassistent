package com.example.sun.studentassistent;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TimetableFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TimetableFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimetableFragment extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TimeTableView mTimeTableView;
    private List<TimeTableModel> mList;


    private OnFragmentInteractionListener mListener;

    public TimetableFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TimetableFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TimetableFragment newInstance(String param1, String param2) {
        TimetableFragment fragment = new TimetableFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_timetable, container, false);
        mList = new ArrayList<TimeTableModel>();
        mTimeTableView = (TimeTableView) v.findViewById(R.id.main_timetable_ly);
        TimetableAdapter timetableAdapter=new TimetableAdapter(mList,getContext());
        mList=timetableAdapter.selectDB();
        mTimeTableView.setTimeTable(mList);
        return v;
    }
//    private void addList() {
//        mList.add(new TimeTableModel(0, 1, 2, 1, "8:20", "10:10", "财务报表分析",
//                "王老师", "1", "2-13"));
//        mList.add(new TimeTableModel(0, 3, 4, 1, "8:20", "10:10", "审计实务",
//                "李老师", "2", "2-13"));
//        mList.add(new TimeTableModel(0, 6, 7, 1, "8:20", "10:10", "市场营销实务",
//                "王", "3", "2-13"));
//        mList.add(new TimeTableModel(0, 6, 7, 2, "8:20", "10:10", "财务管理实务",
//                "老师1", "4", "2-13"));
//        mList.add(new TimeTableModel(0, 8, 9, 2, "8:20", "10:10", "财务报表分析",
//                "老师2", "5", "2-13"));
//        mList.add(new TimeTableModel(0, 1, 2, 3, "8:20", "10:10", "审计实务",
//                "老师3", "6", "2-13"));
//        mList.add(new TimeTableModel(0, 6, 7, 3, "8:20", "10:10", "管理会计实务",
//                "老师4", "7", "2-13"));
//        mList.add(new TimeTableModel(0, 3, 5, 4, "8:20", "10:10", "财务管理实务",
//                "老师4", "8", "2-13"));
//        mList.add(new TimeTableModel(0, 8, 9, 4, "8:20", "10:10", "管理会计实务",
//                "老师5", "9", "2-13"));
//        mList.add(new TimeTableModel(0, 3, 5, 5, "8:20", "10:10", "税务筹划",
//                "老师6", "10", "2-13"));
//        mList.add(new TimeTableModel(0, 6, 8, 5, "8:20", "10:10", "证券投资分析",
//                "老师7", "11", "2-13"));
//    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
