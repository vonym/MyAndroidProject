package com.example.vonym.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner1, spinner2, spinner3;
    private GridView gridView;
    private ListView listView;
    private ArrayAdapter arrayAdapter;
    //使用ArrayAdapter去制作ListView，简单，但是功能不强大，还是没有办法进行跟深层次的自定义。
    // 一般不会配合ListView使用，而是配合其他的AdapterView的派生类

    /*ArrayAdapter的三个参数。ListView中的每一个列表项——》item
    * 第一个：ContentText  上下文
    * 第二个：LayoutResources 给每一个item设置布局方式
    * 第三个：Adapter填充的数据。数组*/

    private SimpleAdapter simpleAdapter;//使用SimpleAdapter去制作ListView，
    private List<Map<String, Object>> list;
    private int[] images = {R.mipmap.ic_launcher};

    //城市级联
    private String[] sheng = {"江西省", "江苏省"};
    private String[][] chengshi = {{"南昌市", "赣州市"}, {"南京市", "苏州市"}};
    private String[][][] quxian = {{{"红谷滩", "新建区", "青山湖"}, {"章贡区", "赣县", "上犹县"}}, {{"玄武区", "中山陵"}, {"a", "b", "c"}}};
    private int chengshiPosition;
    private String[] names = {"盖伦", "赵信", "皇子"};
    private String[] dosc = {"德玛西亚", "一点寒芒先到，随后枪出如龙", "犯我德邦者，虽远必诛"};
    /*SimpleAdapter 的五个参数
    * 1。ContentText：上下文
    * 2.数据
    * 3.ListView的布局文件
    * 4.数据从哪来
    * 5.数据放在那里去*/

    /*通过BaseAdapter制作ListView
    * 1.由于BaseAdapter是一个抽象类，使用
    * 2.实现BaseAdapter中未实现的4个方法*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        listView = (ListView) findViewById(R.id.list);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, names);
        //listView.setAdapter(arrayAdapter);

        list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap();
            map.put("image", images[0]);
            map.put("name", names[i % dosc.length]);
            map.put("dosc", dosc[i % dosc.length]);
            list.add(map);
        }
        simpleAdapter = new SimpleAdapter(this, list, R.layout.simple_layout,
                new String[]{"image", "name", "dosc"}, new int[]{R.id.img, R.id.txt1, R.id.txt2});
        //listView.setAdapter(simpleAdapter);

        spinner1.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                R.layout.support_simple_spinner_dropdown_item, sheng));
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner2.setAdapter(new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, chengshi[i]));
                chengshiPosition = i;
            }

            //当数据为空时触发
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner3.setAdapter(new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, quxian[chengshiPosition][i]));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        gridView.setAdapter(new SimpleAdapter(this, list, R.layout.gridview_item,
                new String[]{"image", "name"}, new int[]{R.id.img, R.id.txt1}));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Toast.makeText(MainActivity.this, "L=" + l + "i+" + i, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        listView.setAdapter(new MyAdapter(this, names));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, names[i], Toast.LENGTH_SHORT).show();
            }
        });
    }
}