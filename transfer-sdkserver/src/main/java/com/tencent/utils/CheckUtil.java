package com.tencent.utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;


/**
 * @author jinhetech
 */
public class CheckUtil {


    public static boolean isEmpty(Object... param) {
        if (null == param || "".equals(param)) {
            return true;
        }
        for (Object obj : param) {
            if (!validatorParamNotEmpty(obj)) {
                return true;
            }
        }
        return false;
    }


    private static boolean validatorParamNotEmpty(Object obj) {
        if (null == obj) {
            return false;
        }
        if (String.class.isInstance(obj)) {
            if ("undefined".equals(obj) || 0 == ((String) obj).length()) {
                return false;
            }

        }
        // validate Collection
        if (Collection.class.isInstance(obj)) {
            if (0 == ((Collection<?>) obj).size()) {
                return false;
            }
        }
        // validate Map
        if (Map.class.isInstance(obj)) {
            if (0 == ((Map<?, ?>) obj).size()) {
                return false;
            }
        }
        // validate Arrays
        if (Arrays.class.isInstance(obj)) {
            if (0 == Array.getLength(obj)) {
                return false;
            }
        }
        return true;
    }
}