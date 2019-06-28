package org.jvnet.localizer;

import junit.framework.TestCase;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Kohsuke Kawaguchi
 */
public class ResourceBundleHolderTest extends TestCase {
    public void test1() {
        Locale.setDefault(Locale.CHINESE);
        ResourceBundleHolder h = new ResourceBundleHolder(LocalizableTest.class);
        assertEquals("en_default, param is {0}", h.get(Locale.ENGLISH).getString("key1"));
        assertEquals("zh, param is {0} 中文", h.get(Locale.CHINESE).getString("key1"));
        assertEquals("zh, param is {0} 中文", h.get(LocaleProvider.getLocale()).getString("key1"));
        assertEquals("zh_CN_default, param is {0} 中文中国", h.get(Locale.SIMPLIFIED_CHINESE).getString("key1"));
    }

    public void test2() {
        Locale.setDefault(Locale.ENGLISH);
        ResourceBundleHolder h = new ResourceBundleHolder(LocalizableTest.class);
        assertEquals("en_defaultkey2", h.get(Locale.ENGLISH).getString("key2"));
        assertEquals("zh中文中国key2", h.get(Locale.CHINESE).getString("key2"));
        assertEquals("en_defaultkey2", h.get(LocaleProvider.getLocale()).getString("key2"));
        assertEquals("zh_CN_default中文中国key2", h.get(Locale.SIMPLIFIED_CHINESE).getString("key2"));
    }


    public void testResourceBundleHolder() {
        ResourceBundleHolder h = new ResourceBundleHolder(LocalizableTest.class);
        ResourceBundle rb = h.get(Locale.SIMPLIFIED_CHINESE);
        Enumeration<String> keys = rb.getKeys();

        while (keys.hasMoreElements()) {
            String s = keys.nextElement();
            System.out.println(s);
        }
    }
}
