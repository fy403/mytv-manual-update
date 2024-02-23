package com.dommy.version;

import org.junit.Test;

import static org.junit.Assert.*;

import com.dommy.version.util.VersionUtil;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void set_versionCode() {
        String newVersionName = "v2.0.0";
        // 调用方法进行测试
        VersionUtil.setVersionName(newVersionName);
    }
}