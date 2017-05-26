package com.example.musicapp;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FloatingActionButton floatingActionButton;


    private MediaPlayer player;
    private AudioManager manager;
    private ListView listView;
    private List<File> musics;
    private SeekBar position_Sb, volume_Sb;
    private TextView position_Tv, totalTime_Tv;
    private int mPosition = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                setTvtext(position_Tv, player.getCurrentPosition());
                setTvtext(totalTime_Tv, player.getDuration());
                position_Sb.setMax(player.getDuration());
                position_Sb.setProgress(player.getCurrentPosition());
                volume_Sb.setMax(manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
                volume_Sb.setProgress(manager.getStreamVolume(AudioManager.STREAM_MUSIC));
                handler.sendEmptyMessageDelayed(0x123, 1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.more);
        }
        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                return false;
            }
        });


        //简单，如果播放多首歌曲就不适用
        //player = MediaPlayer.create(this, R.raw.music);

        player = new MediaPlayer();
//        try {
//            //player.setDataSource(this, Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.music));
//            player.prepare();
//            initView();
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        initView();
        intListView();
        eventOnclick();
    }

    private void initView() {
        floatingActionButton = (FloatingActionButton) findViewById(R.id.flaotbtn);

        listView = (ListView) findViewById(R.id.listview);
        manager = (AudioManager) getSystemService(AUDIO_SERVICE);
        musics = new ArrayList<>();
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Music/");
        isMusic(file);
        intListView();
        position_Sb = (SeekBar) findViewById(R.id.position_sb);
        volume_Sb = (SeekBar) findViewById(R.id.volume_sb);
        volume_Sb.setProgress(manager.getStreamVolume(AudioManager.STREAM_MUSIC));
        position_Tv = (TextView) findViewById(R.id.position_tv);
        totalTime_Tv = (TextView) findViewById(R.id.totaltime_tv);
//        eventOnclick();
    }

    private void isMusic(File file) {
        File[] files = file.listFiles();
        for (File file1 : files) {
            if (file1.isFile()) {
                if (file1.getName().endsWith(".mp3")) {
                    musics.add(file1);
                }
            } else {
                isMusic(file1);
            }
        }
    }

    private void eventOnclick() {
        position_Sb.setOnSeekBarChangeListener(this);
        volume_Sb.setOnSeekBarChangeListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    player.reset();
                    player.setDataSource(MainActivity.this, Uri.parse(musics.get(position).getAbsolutePath()));
                    Log.v("music", musics.get(position).getAbsolutePath());
                    player.prepare();
                    handler.sendEmptyMessage(0x123);
                    player.start();
                    if ((position + 1) < musics.size()) {
                        mPosition = position + 1;
                    } else {
                        mPosition = 0;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                player.reset();
                try {
                    player.setDataSource(MainActivity.this, Uri.parse(musics.get(mPosition).getAbsolutePath()));
                    Log.v("music", musics.get(mPosition).getAbsolutePath());
                    player.prepare();
                    handler.sendEmptyMessage(0x123);
                    player.start();
                    if ((mPosition + 1) < musics.size()) {
                        mPosition = mPosition + 1;
                    } else {
                        mPosition = 0;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "float", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setTvtext(TextView textView, int millSecond) {
        int second = millSecond / 1000;
        int hh = second / 3600;
        int mm = second % 3600 / 60;
        int ss = second % 60;
        String time = null;
        if (hh != 0) {
            time = String.format("02d:%02d:%02d", hh, mm, ss);
        } else {
            time = String.format("%02d:%02d", mm, ss);
        }
        textView.setText(time);
    }


    public void start(View view) {
        //player.getDuration();//获取歌曲时长
        handler.sendEmptyMessage(0x123);
        player.start();
    }

    public void stop(View view) {
        player.pause();
        handler.removeMessages(0x123);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.position_sb:
                setTvtext(position_Tv, player.getCurrentPosition());
                break;
            case R.id.volume_sb:
                manager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_SHOW_UI);
                handler.sendEmptyMessage(0x123);
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        handler.removeMessages(0x123);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar.getId() == R.id.position_sb) {
            player.seekTo(seekBar.getProgress());
            handler.sendEmptyMessage(0x123);
        }
    }

    public void intListView() {
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return musics.size();
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder = null;
                if (convertView == null) {
                    holder = new ViewHolder();
                    convertView = View.inflate(MainActivity.this, R.layout.listview_item, null);
                    holder.textView_name = (TextView) convertView.findViewById(R.id.list_item_tv_name);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                holder.textView_name.setText(musics.get(position).getName());
                return convertView;
            }
        });
    }

    public class ViewHolder {
        private TextView textView_name;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player.isPlaying()) {
            player.pause();
            player.reset();
            player = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(MainActivity.this, "backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(MainActivity.this, "delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(MainActivity.this, "setting", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
