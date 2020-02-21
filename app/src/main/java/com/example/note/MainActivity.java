package com.example.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public FloatingActionButton button_add, Button_calendar;
    public ImageView imageview;
    public TextView textview_1;
    public ListView list_view;
    public Toolbar toolbar;
    public Dao dao;
    public Constants constants;

    ArrayAdapter<String> adapter;

    public List<Constants> list_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.display_menu, menu);
//        return true;
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode){
            case 1: if(resultCode == RESULT_OK){
                String content = intent.getStringExtra("content");
                int id = 0;

                if(list_main.size() != 0){
                    id = list_main.get(0).getId() + 1;
                }

                Constants constants = new Constants(id, content, "true");
                dao.insert(constants);
                showData();
            }
            break;
            case 2: if(resultCode == RESULT_OK){
                String content = intent.getStringExtra("content");

                int id = intent.getIntExtra("id", -1);
                if(id != -1){
                    Constants constants = new Constants(id, content, "true");
                    dao.updatecontent(constants);

                    showData();
                    break;
                }
                else{
                    if(list_main.size() != 0){
                        id = list_main.get(0).getId() + 1;
                    }
                    else {
                        id = 0;
                    }
                }
                constants = new Constants(id, content, "true");
                dao.insert(constants);
                showData();
            }
            break;

            default: break;
        }
    }

    private void initview(){
        initListview();
        initToolbar();
        initFloatingActionButton();
        initDao();
        initConstant();
        initImageView();
        initTextview();
        showData();
    }

    //true可见，false不可见
    private void display_control_view(boolean flag){
        if(flag)
        {
            imageview.setVisibility(View.VISIBLE);
            textview_1.setVisibility(View.VISIBLE);
        }
        else {
            imageview.setVisibility(View.INVISIBLE);
            textview_1.setVisibility(View.INVISIBLE);
        }
    }

    private void initImageView(){
        imageview = findViewById(R.id.dataisEmptyIcon);
    }

    private void initTextview(){
        textview_1 = findViewById(R.id.main_add);
    }

    private void initListview(){
        list_view = findViewById(R.id.list_view_main);
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1);
        list_view.setAdapter(adapter);
        //listview绑定设配器

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //int operate_id = list_main.get(position).getId();

//                Intent intent=new Intent(MainActivity.this, chooseActivity.class);
//                lvPosition = position;
                //intent中带入文本内容，便于分享
                //分享这里只设置分享文本内容，时间不分享
//                intent.putExtra("content",sch.get(lvPosition).content);
//                MainActivity.this.startActivityForResult(intent,2);
                //设置请求码 启动选择活动

                showBottomDialog(list_main.get(position));
            }
        });
    }

    private void initToolbar(){
        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
    }

    private void initFloatingActionButton(){
        button_add = findViewById(R.id.float_button);
        Button_calendar = findViewById(R.id.calendar_button);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addintent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(addintent, 1);
            }
        });

        Button_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calintent = new Intent(MainActivity.this, CalendarActivity.class);
                //calintent.putExtra("UPDATE",0);
                startActivity(calintent);
            }
        });
    }

    private void initDao()
    {
        dao = new Dao(getApplicationContext());
    }

    private void initConstant(){
        constants = new Constants();
    }

    private void showData(){
        int position = 0;

        String content, content_tmp;
        list_main = dao.querycontent();
        int len = list_main.size();
        adapter.clear();

        while (position < len){
            content = list_main.get(position).getContent();
            if (content.length() > 15) {
                content_tmp = content.substring(0, 15);
                adapter.add(content_tmp + "……");
            }
            else {
                adapter.add(content);
            }
            position++;
        }
        if(len == 0)
        {
            display_control_view(true);
        }
        else {
            display_control_view(false);
        }
    }

    private void showBottomDialog(final Constants constants){
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        View view = View.inflate(this, R.layout.activity_dialog_main, null);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);

        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        //删除事件
        dialog.findViewById(R.id.main_dialog_linear_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"点击了删除",Toast.LENGTH_SHORT).show();
                dao.delete(constants.getId());
                showData();
                dialog.dismiss();
            }
        });

        //修改事件
        dialog.findViewById(R.id.main_dialog_linear_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"点击了修改",Toast.LENGTH_SHORT).show();
                Intent addintent = new Intent(MainActivity.this, AddActivity.class);
                addintent.putExtra("id", constants.getId());
                addintent.putExtra("content", constants.getContent());
                addintent.putExtra("isimportant", constants.getIsimportant());
                startActivityForResult(addintent, 2);
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.main_dialog_linear_friendspace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"点击了分享",Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, constants.getContent());
                startActivity(Intent.createChooser(intent,"share"));

                dialog.dismiss();
            }
        });
        //弹窗中按钮事件
    }

}
