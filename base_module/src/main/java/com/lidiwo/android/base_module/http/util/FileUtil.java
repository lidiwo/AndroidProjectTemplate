package com.lidiwo.android.base_module.http.util;

import android.text.TextUtils;

import com.lidiwo.android.base_module.http.RetrofitConfigure;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/24 15:22
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class FileUtil {

    private static final SimpleDateFormat DEFAULT_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    public static String getDownloadDir(String downloadDir) {
        if (TextUtils.isEmpty(downloadDir)) {
            String path = RetrofitConfigure.getInstance().getContext().getExternalCacheDir().getAbsolutePath();
            return path;
        } else {
            return downloadDir;
        }
    }

    public static String getFileName(String fileName) {
        if (TextUtils.isEmpty(fileName)) {
            return "lidiwo-".concat(getCurrentDate());
        } else {
            return fileName.concat("-").concat(getCurrentDate());
        }
    }

    public static String getExtensionName(String extensionName) {
        if (TextUtils.isEmpty(extensionName)) {
            return "app";
        } else {
            return extensionName;
        }
    }

    public static File createFile(String downloadDir, String fileName, String extensionName) {
        String fullName = fileName.concat(".").concat(extensionName);
        File file = new File(downloadDir, fullName);
        if (file.exists()) {
            file.delete();
        }
        return file;
    }


    private static String getCurrentDate() {
        long currentTimeMillis = System.currentTimeMillis();
        return DEFAULT_SDF.format(new Date(currentTimeMillis));
    }

}
