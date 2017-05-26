package com.example.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;

/**
 * Created by vonym on 17-3-11.
 */

public class GsonActivity extends AppCompatActivity {
    private String jsonData = "{\n" +
            "    \"resultcode\": \"200\",\n" +
            "    \"reason\": \"查询成功!\",\n" +
            "    \"result\": {\n" +
            "        \"sk\": {\t/*当前实况天气*/\n" +
            "            \"temp\": \"21\",\t/*当前温度*/\n" +
            "            \"wind_direction\": \"西风\",\t/*当前风向*/\n" +
            "            \"wind_strength\": \"2级\",\t/*当前风力*/\t\n" +
            "            \"humidity\": \"4%\",\t/*当前湿度*/\n" +
            "            \"time\": \"14:25\"\t/*更新时间*/\n" +
            "        },\n" +
            "        \"today\": {\n" +
            "            \"city\": \"天津\",\n" +
            "            \"date_y\": \"2014年03月21日\",\n" +
            "            \"week\": \"星期五\",\n" +
            "            \"temperature\": \"8℃~20℃\",\t/*今日温度*/\n" +
            "            \"weather\": \"晴转霾\",\t/*今日天气*/\n" +
            "            \"weather_id\": {\t/*天气唯一标识*/\n" +
            "                \"fa\": \"00\",\t/*天气标识00：晴*/\n" +
            "                \"fb\": \"53\"\t/*天气标识53：霾 如果fa不等于fb，说明是组合天气*/\n" +
            "            },\n" +
            "            \"wind\": \"西南风微风\",\n" +
            "            \"dressing_index\": \"较冷\", /*穿衣指数*/\n" +
            "            \"dressing_advice\": \"建议着大衣、呢外套加毛衣、卫衣等服装。\",\t/*穿衣建议*/\n" +
            "            \"uv_index\": \"中等\",\t/*紫外线强度*/\n" +
            "            \"comfort_index\": \"\",/*舒适度指数*/\n" +
            "            \"wash_index\": \"较适宜\",\t/*洗车指数*/\n" +
            "            \"travel_index\": \"适宜\",\t/*旅游指数*/\n" +
            "            \"exercise_index\": \"较适宜\",\t/*晨练指数*/\n" +
            "            \"drying_index\": \"\"/*干燥指数*/\n" +
            "        },\n" +
            "        \"future\": [\t/*未来几天天气*/\n" +
            "            {\n" +
            "                \"temperature\": \"28℃~36℃\",\n" +
            "                \"weather\": \"晴转多云\",\n" +
            "                \"weather_id\": {\n" +
            "                    \"fa\": \"00\",\n" +
            "                    \"fb\": \"01\"\n" +
            "                },\n" +
            "                \"wind\": \"南风3-4级\",\n" +
            "                \"week\": \"星期一\",\n" +
            "                \"date\": \"20140804\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"temperature\": \"28℃~36℃\",\n" +
            "                \"weather\": \"晴转多云\",\n" +
            "                \"weather_id\": {\n" +
            "                    \"fa\": \"00\",\n" +
            "                    \"fb\": \"01\"\n" +
            "                },\n" +
            "                \"wind\": \"东南风3-4级\",\n" +
            "                \"week\": \"星期二\",\n" +
            "                \"date\": \"20140805\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"temperature\": \"27℃~35℃\",\n" +
            "                \"weather\": \"晴转多云\",\n" +
            "                \"weather_id\": {\n" +
            "                    \"fa\": \"00\",\n" +
            "                    \"fb\": \"01\"\n" +
            "                },\n" +
            "                \"wind\": \"东南风3-4级\",\n" +
            "                \"week\": \"星期三\",\n" +
            "                \"date\": \"20140806\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"temperature\": \"27℃~34℃\",\n" +
            "                \"weather\": \"多云\",\n" +
            "                \"weather_id\": {\n" +
            "                    \"fa\": \"01\",\n" +
            "                    \"fb\": \"01\"\n" +
            "                },\n" +
            "                \"wind\": \"东南风3-4级\",\n" +
            "                \"week\": \"星期四\",\n" +
            "                \"date\": \"20140807\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"temperature\": \"27℃~33℃\",\n" +
            "                \"weather\": \"多云\",\n" +
            "                \"weather_id\": {\n" +
            "                    \"fa\": \"01\",\n" +
            "                    \"fb\": \"01\"\n" +
            "                },\n" +
            "                \"wind\": \"东北风4-5级\",\n" +
            "                \"week\": \"星期五\",\n" +
            "                \"date\": \"20140808\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"temperature\": \"26℃~33℃\",\n" +
            "                \"weather\": \"多云\",\n" +
            "                \"weather_id\": {\n" +
            "                    \"fa\": \"01\",\n" +
            "                    \"fb\": \"01\"\n" +
            "                },\n" +
            "                \"wind\": \"北风4-5级\",\n" +
            "                \"week\": \"星期六\",\n" +
            "                \"date\": \"20140809\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"temperature\": \"26℃~33℃\",\n" +
            "                \"weather\": \"多云\",\n" +
            "                \"weather_id\": {\n" +
            "                    \"fa\": \"01\",\n" +
            "                    \"fb\": \"01\"\n" +
            "                },\n" +
            "                \"wind\": \"北风4-5级\",\n" +
            "                \"week\": \"星期日\",\n" +
            "                \"date\": \"20140810\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    \"error_code\": 0\n" +
            "}";
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gson_main);
        textView = (TextView) findViewById(R.id.txt);

        Gson gson = new Gson();
        //讲json转换成bean--客户端
        //必须与json中key保持一致
        News news = gson.fromJson(jsonData, News.class);
        textView.setText(news.getResult().getFuture().toString());

        //讲bean装换成json--服务器
        //gson.toJson(news);

    }
}
