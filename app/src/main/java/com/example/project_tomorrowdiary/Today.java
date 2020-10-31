package com.example.project_tomorrowdiary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Today#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Today extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Today() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Today.
     */
    // TODO: Rename and change types and number of parameters
    public static Today newInstance(String param1, String param2) {
        Today fragment = new Today();
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
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_today,container,false);

        TextView showtitle = (TextView) viewGroup.findViewById(R.id.title);
        TextView dday= (TextView) viewGroup.findViewById(R.id.dday);
        TextView showdiary = (TextView) viewGroup.findViewById(R.id.maintext);
        TextView showlist1 = (TextView) viewGroup.findViewById(R.id.list_01);
        TextView showlist2 = (TextView) viewGroup.findViewById(R.id.list_02);
        TextView showlist3 = (TextView) viewGroup.findViewById(R.id.list_03);

        //오늘 날짜 구하기
        Calendar today = Calendar.getInstance ( );
        SimpleDateFormat Format = new SimpleDateFormat("yyyy/MM/dd");
        Date date = today.getTime ( );
        String time = Format.format(date);
        dday.setText(time);


//        //내일날짜 호출
//        today.add ( Calendar.DATE, 1 );
//        Date tomorrow = today.getTime ( );

        //저장 된 제목 불러오기기
        SimpleDateFormat format2 =new SimpleDateFormat("yyyyMMdd");
        String today2 = format2.format(date);
        String txttitle = "_diary";
        String savetitle = today2+txttitle;

        //저장 데이터 불러오기
        SharedPreferences spdiary = this.getActivity().getSharedPreferences(savetitle, Context.MODE_PRIVATE);
        String dbtitle = spdiary.getString("title", "제목이 없습니다");
        showtitle.setText(dbtitle);

        String dbdiary = spdiary.getString("diary", "일기가 없습니다");
        showdiary.setText(dbdiary);

        String dblist1 = spdiary.getString("list1", "리스트가 없습니다");
        showlist1.setText(dblist1);

        String dblist2 = spdiary.getString("list2", "리스트가 없습니다");
        showlist2.setText(dblist2);

        String dblist3 = spdiary.getString("list3", "리스트가 없습니다");
        showlist3.setText(dblist3);



        return viewGroup;
    }
}