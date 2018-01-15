package comm.android.win.music.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import comm.android.win.music.bean.Song;

/**
 * 本地音乐扫描工具类
 * Created by win on 2018/1/7.
 */

public class MusicUtils {


    /**
     * 定义一个方法用来格式化获取到的时间
     *//*
    public static String formatTime(int time) {
        if (time / 1000 % 60 < 10) {
            return time / 1000 / 60 + ":0" + time / 1000 % 60;

        } else {
            return time / 1000 / 60 + ":" + time / 1000 % 60;
        }
    }*/
    public static List<Song> getMusicData(Context mContext) {
        //创建ArryList
        List<Song> list = new ArrayList<Song>();

        //创建一个扫描游标
        Cursor cursor = mContext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        if (cursor != null) {

            //循环读取

            while (cursor.moveToNext()) {

                //实例化Song对象
                Song song = new Song();
                //扫描本地文件，得到歌曲的相关信息
                song.song = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                song.singer = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                song.path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                song.duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                song.size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
                song.album_id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));


                //将model值加入到数组中
                list.add(song);

            }
            //打印出数组的长度
            Log.i("list", "list.size:" + list.size());

        }

        //得到一个数组的返回值
        return list;

    }
    public static String getAlbumArt(Context mContext, String album_id) {
        String mUriAlbums = "content://media/external/audio/albums";
        String[] projection = new String[] { "album_art" };
        Cursor cur = mContext.getContentResolver().query(
                Uri.parse(mUriAlbums + "/" + (album_id)),
                projection, null, null, null);
        String album_art = null;
        if (cur.getCount() > 0 && cur.getColumnCount() > 0) {
            cur.moveToNext();
            album_art = cur.getString(0);
        }
        cur.close();
        cur = null;
        return album_art;
    }



    //读取歌词信息
//    public static ArrayList<LrcModel> redLrc(String path) {
//        ArrayList<LrcModel> alist = new ArrayList<LrcModel>();
//
//        File f = new File(path.replace(".mp3", ".lrc"));
//
//        try {
//            FileInputStream fs = new FileInputStream(f);
//            InputStreamReader inputStreamReader = new InputStreamReader(fs,
//                    "utf-8");
//            BufferedReader br = new BufferedReader(inputStreamReader);
//            String s = "";
//            while (null != (s = br.readLine())) {
//                if (!TextUtils.isEmpty(s)) {
//                    LrcModel lrcModle = new LrcModel();
//                    String lylrc = s.replace("[", "");
//                    String data_ly[] = lylrc.split("]");
//                    if (data_ly.length > 1) {
//                        String time = data_ly[0];
//                        lrcModle.setTime(LrcData(time));
//                        String lrc = data_ly[1];
//                        lrcModle.setLrc(lrc);
//                        alist.add(lrcModle);
//                    }
//
//                }
//
//            }
//
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return alist;
//
//    }

    public static int LrcData(String time) {
        time = time.replace(":", "#");
        time = time.replace(".", "#");
        String mTime[] = time.split("#");
        int mtime = Integer.parseInt(mTime[0]);
        int stime = Integer.parseInt(mTime[1]);
        int mitime = Integer.parseInt(mTime[2]);
        int ctime = (mtime * 60 + stime) * 1000 + mitime * 10;
        return ctime;
    }



}
