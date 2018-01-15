package comm.android.win.music.utils;

import java.io.File;

/**
 * 文件处理
 * Created by win on 2018/1/8.
 */

public class FileUtils {
    /**
     * 判断文件是否存在
     * @param path 文件的路径
     * @return
     */
    public static boolean isExists(String path) {
        File file = new File(path);
        return file.exists();
    }


}
