package com.example.project_tomorrowdiary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Alreadydiary#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Alreadydiary extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Alreadydiary() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Alreadydiary.
     */
    // TODO: Rename and change types and number of parameters
    public static Alreadydiary newInstance(String param1, String param2) {
        Alreadydiary fragment = new Alreadydiary();
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

//    public void checkdiary(){
//        Intent intent = new Intent(getFragmentManager(), WriteDiary.class);
//        startActivity(intent);
//    }

    String title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_alreadydiary,container,false);

        TextView showtitle = (TextView) viewGroup.findViewById(R.id.tomtitle);
        TextView nextday2= (TextView) viewGroup.findViewById(R.id.nextday2);
        TextView showdiary = (TextView) viewGroup.findViewById(R.id.tommaintext);
        TextView showlist1 = (TextView) viewGroup.findViewById(R.id.tomlist_01);
        TextView showlist2 = (TextView) viewGroup.findViewById(R.id.tomlist_02);
        TextView showlist3 = (TextView) viewGroup.findViewById(R.id.tomlist_03);
        Button btn = (Button) viewGroup.findViewById(R.id.rewrite);

        //내일날짜 호출
        Calendar today = Calendar.getInstance ( );
        today.add ( Calendar.DATE, 1 );
        SimpleDateFormat Format = new SimpleDateFormat("yyyy/MM/dd");
        Date tomorrow = today.getTime ( );
        String time = Format.format(tomorrow);
        nextday2.setText(time);


        //저장 되는 일기 제목
        SimpleDateFormat format2 =new SimpleDateFormat("yyyyMMdd");
        String numtomorrow = format2.format(tomorrow);
        String txttitle = "_diary";
        title = numtomorrow+txttitle;

        //저장 데이터 불러오기
        SharedPreferences spdiary = this.getActivity().getSharedPreferences(title, Context.MODE_PRIVATE);
        String dbtitle = spdiary.getString("title", "nodata");
        showtitle.setText(dbtitle);

        String dbdiary = spdiary.getString("diary", "일기가 없습니다");
        showdiary.setText(dbdiary);

        String dblist1 = spdiary.getString("list1", "리스트가 없습니다");
        showlist1.setText(dblist1);

        String dblist2 = spdiary.getString("list2", "리스트가 없습니다");
        showlist2.setText(dblist2);

        String dblist3 = spdiary.getString("list3", "리스트가 없습니다");
        showlist3.setText(dblist3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WriteDiary.class);
                startActivity(intent);
            }
        });



        return viewGroup;
    }
}