package com.example.project_tomorrowdiary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WriteDiary extends Activity {

    String title;
    EditText edttitle;
    EditText edtdiary;
    EditText edtlist_1;
    EditText edtlist_2;
    EditText edtlist_3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writedairy);

        TextView nextday = findViewById(R.id.nextday);
        Button btnsave = findViewById(R.id.savediary);
        edttitle=findViewById(R.id.diarytitle);
        edtdiary=findViewById(R.id.diarytext);
        edtlist_1=findViewById(R.id.diarylist_1);
        edtlist_2=findViewById(R.id.diarylist_2);
        edtlist_3=findViewById(R.id.diarylist_3);

        //내일날짜 호출
        Calendar today = Calendar.getInstance ( );
        today.add ( Calendar.DATE, 1 );
        SimpleDateFormat Format = new SimpleDateFormat("yyyy/MM/dd");
        Date tomorrow = today.getTime ( );
        String time = Format.format(tomorrow);
        nextday.setText(time);


        //저장 되는 일기 제목
        SimpleDateFormat format2 =new SimpleDateFormat("yyyyMMdd");
        String numtomorrow = format2.format(tomorrow);
        String txttitle = "_diary";
        title = numtomorrow+txttitle;


        //수정시 데이터 가져오기
        SharedPreferences spdiary = getSharedPreferences(title, Context.MODE_PRIVATE);
        String dbtitle = spdiary.getString("title", "");
        edttitle.setText(dbtitle);

        String dbdiary = spdiary.getString("diary", "");
        edtdiary.setText(dbdiary);

        String dblist1 = spdiary.getString("list1", "");
        edtlist_1.setText(dblist1);

        String dblist2 = spdiary.getString("list2", "");
        edtlist_2.setText(dblist2);

        String dblist3 = spdiary.getString("list3", "");
        edtlist_3.setText(dblist3);


        //저장버튼 누를 시
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //메인액티비티로 이동
                Intent intent=new Intent(WriteDiary.this,MainActivity.class);
                startActivity(intent);

                //제목 저장
                SharedPreferences sptitle = getSharedPreferences(title, MODE_PRIVATE);
                SharedPreferences.Editor edtitle = sptitle.edit();
                edtitle.putString("title", edttitle.getText().toString());
                edtitle.putString("diary", edtdiary.getText().toString());
                edtitle.putString("list1", edtlist_1.getText().toString());
                edtitle.putString("list2", edtlist_2.getText().toString());
                edtitle.putString("list3", edtlist_3.getText().toString());
                edtitle.apply();


            }
        });
    }
}
