package com.example.app;

import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private XmlResourceParser xmlResourceParser;
    private List<Book> list;
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv);
        xmlResourceParser = getResources().getXml(R.xml.books);
    }

    public void start(View view) throws Exception {
        //得到第一个事件类型
        int type = xmlResourceParser.getEventType();
        while (type != XmlResourceParser.END_DOCUMENT) {
            switch (type) {
                case XmlResourceParser.START_DOCUMENT:
                    Toast.makeText(this, "开始解析", Toast.LENGTH_SHORT).show();
                    list = new ArrayList<>();
                    break;
                case XmlPullParser.START_TAG:
                    if ("book".equals(xmlResourceParser.getName())) {
                        book = new Book();
                        book.setId(xmlResourceParser.getAttributeValue(null, "id"));
                    }
                    if (book != null) {
                        if ("name".equals(xmlResourceParser.getName())) {
                            book.setName(xmlResourceParser.nextText());
                        }
                        if ("author".equals(xmlResourceParser.getName())) {
                            book.setAuthor(xmlResourceParser.nextText());
                        }
                        if ("year".equals(xmlResourceParser.getName())) {
                            book.setYear(xmlResourceParser.nextText());
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("book".equals(xmlResourceParser.getName())) {
                        list.add(book);
                        book = null;
                    }
                    break;
            }

            type = xmlResourceParser.next();
        }
        Toast.makeText(this, "解析结束", Toast.LENGTH_SHORT).show();
        textView.setText(list.toString());
    }
}
