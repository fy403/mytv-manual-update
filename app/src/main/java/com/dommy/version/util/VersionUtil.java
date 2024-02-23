package com.dommy.version.util;

import android.content.Context;
import android.content.pm.PackageManager;

import com.dommy.version.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class VersionUtil {

    /**
     * 获取当前App的versionCode值
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        int versionCode = 1;
        try {
            versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public static String getVersionName(Context context) {
        String versionName = "v1.0.0";

        // 获取当前类的Class对象
        Class<?> clazz = VersionUtil.class;

        try {
            // 创建JSONObject对象
            InputStream inputStream = context.getResources().openRawResource(R.raw.version);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            JSONObject jsonObject = new JSONObject(jsonContent.toString());
            String version = jsonObject.getString("versionName");
            System.out.println("versionName: " + version);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public static void setVersionName(String newVersionName){
        Class<?> clazz = VersionUtil.class;
        try {
            String output = String.format("{\"versionName\":\"%s\"}", newVersionName);
            JSONObject jsonObject = new JSONObject(output);
            // 将 JSON 数据转换为字符串
            String jsonContent = jsonObject.toString();
            // 获取 JSON 文件的输出流
            File file = new File(clazz.getResource("/raw/version.json").getFile());
            FileOutputStream outputStream = new FileOutputStream(file);
            // 将 JSON 字符串写入文件
            outputStream.write(jsonContent.getBytes());
            outputStream.close();
            System.out.println(newVersionName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

}
