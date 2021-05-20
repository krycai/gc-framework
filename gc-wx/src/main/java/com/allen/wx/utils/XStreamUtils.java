package com.allen.wx.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.HashMap;
import java.util.Map;

/**
 *  复用 XStream 的创建过程
 * @author xuguocai 2020/10/30 8:49
 */
public class XStreamUtils {

    private static Map<Class,XStream> map = new HashMap<>();

    public static XStream create(Class<?> classe){
        if (!map.containsKey(classe)) {
            Class<?>[] list = new Class[] { classe};

            XStream xstream = new XStream(new DomDriver());
            XStream.setupDefaultSecurity(xstream);
            xstream.allowTypes(list);
            xstream.ignoreUnknownElements();
            xstream.processAnnotations(classe);

            map.put(classe, xstream);
            return xstream;
        }
        return map.get(classe);
    }
}
