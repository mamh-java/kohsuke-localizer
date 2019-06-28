package org.jvnet.localizer;

import junit.framework.TestCase;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizableTest extends TestCase {

    public void testLocalizableTest() {
        ResourceBundleHolder holder = ResourceBundleHolder.get(LocalizableTest.class);

        Localizable localizable = new Localizable(holder, "key1", (Object) "my args");
        String s = localizable.toString(Locale.ENGLISH);
        assertEquals("en_default, param is my args", s);
    }


    public void testLocalizableTest1() {
        ResourceBundleHolder holder = ResourceBundleHolder.get(LocalizableTest.class);


        ResourceBundle resourceBundle = holder.get(Locale.SIMPLIFIED_CHINESE);
        Enumeration<String> keys = resourceBundle.getKeys();
        while (keys.hasMoreElements()) {
            String s = keys.nextElement();
            System.out.println("key = " + s);
        }
        String key1 = resourceBundle.getString("key1"); //先获取到对于的key的值
        System.out.println("先获取到对于的key的值 key1 = " + key1);
        String val1 = MessageFormat.format(key1, "格式化占位符"); //然后格式化其中的占位符
        System.out.println("然后格式化其中的占位符 val1 = " + val1);

        Localizable localizable = new Localizable(holder, "key1", (Object) "my args");
        String zhcn = localizable.toString(Locale.SIMPLIFIED_CHINESE);
        System.out.println(zhcn);
        assertEquals("zh_CN_default, param is my args 中文中国", zhcn);

        String zh = localizable.toString(Locale.CHINESE);
        System.out.println(zh);
        assertEquals("zh, param is my args 中文", zh);

    }
}
