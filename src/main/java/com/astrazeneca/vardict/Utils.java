package com.astrazeneca.vardict;

import java.util.*;

public final class Utils {

    private Utils() {}

    /**
     * Method creates string from elements of specified collection (by appending them with space delimiter)
     * @param collection any collection
     * @param <E> generic type of collection elements
     * @return generated string
     */
    public static <E> String toString(Collection<E> collection) {
        Iterator<E> it = collection.iterator();
        if (! it.hasNext())
            return "";

        StringBuilder sb = new StringBuilder();
        for (;;) {
            E e = it.next();
            sb.append(e == collection ? "(this Collection)" : e);
            if (! it.hasNext())
                return sb.toString();
            sb.append(' ');
        }
    }

    /**
     * Method creates string from arguments by appending them with specified delimiter
     * @param delim specified delimiter
     * @param args array of arguments
     * @return generated string
     */
    public static String join(String delim, Object... args) {
        if (args.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]);
            if (i + 1 != args.length) {
                sb.append(delim);
            }
        }
        return sb.toString();
    }

    /**
     * Method creates string from arguments by appending them with specified delimiter.
     * If element from args is null, it replaced by delimiter.
     * @param delim specified delimiter
     * @param args array of arguments
     * @return generated string
     */
    public static String joinNotNull(String delim, Object... args) {
        if (args.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            if (args[i] == null) {
                if (i + 1 != args.length && args[i + 1] != null) {
                    sb.append(delim);
                }
                continue;
            }
            sb.append(args[i]);
            if (i + 1 != args.length && args[i + 1] != null) {
                sb.append(delim);
            }
        }
        return sb.toString();
    }

    public static <K, V> V getOrElse(Map<K, V> map, K key, V or) {
        V v = map.get(key);
        if (v == null) {
            v = or;
            map.put(key, v);
        }
        return v;
    }

    /**
     * Method rounds value with the specified precision
     * @param value value to round
     * @param dp precision
     * @return rounded value
     */
    public static double round(double value, int dp) {
        double mf = Math.pow(10, dp);
        double d = value * mf;
        return Math.round(d) / mf;

    }

    public static int toInt(String intStr) {
        return Integer.parseInt(intStr);
    }

    /**
     * Method creates substring of string begin from specified idx.
     * If idx is negative, it returns substring, counted from the right end of string.
     * @param string sequence to substring
     * @param idx begin index of substring
     * @return generated substring
     */
    public static String substr(String string, int idx) {
        if (idx >= 0) {
            return string.substring(Math.min(string.length(), idx));
        } else {
            return string.substring(Math.max(0, string.length() + idx));
        }
    }

    /**
     * Method creates substring of string begin from specified idx and of specified length.
     * If begin or len is negative, it returns substring, counted from the right end of string.
     * @param string sequence to substring
     * @param begin begin index of substring
     * @param len length of substring
     * @return generated substring
     */
    public static String substr(String string, int begin, int len) {
        if (begin < 0) {
            begin = string.length() + begin;
        }
        if (len > 0) {
            return string.substring(begin, Math.min(begin + len, string.length()));
        } else if (len == 0) {
            return "";
        } else {
            int end = string.length() + len;
            if (end < begin) {
                return "";
            }
            return string.substring(begin, end);
        }
    }

    /**
     * Method finds character on specified index in String. If index is negative, it counts index from right end of string.
     * @param str String where to search character
     * @param index position in sequence
     * @return founded character on specified position
     */
    public static char charAt(String str, int index) {
        if (index < 0) {
            int i = str.length() + index;
            if (i < 0)
                return (char)-1; //
            return str.charAt(i);
        }
        return str.charAt(index);
    }

    /**
     * Method finds character on specified index in StringBuilder. If index is negative, it counts index from right end of string.
     * @param str StringBuilder where to search character
     * @param index position in sequence
     * @return founded character on specified position
     */
    public static char charAt(StringBuilder str, int index) {
        if (index < 0) {
            int i = str.length() + index;
            if (i < 0)
                return (char)-1;//
            return str.charAt(str.length() + index);
        }
        return str.charAt(index);
    }

    /**
     * Method calculates sum from integer values of Objects in collection.
     * @param list any collection
     * @return sum of values from collection
     */
    public static int sum(Collection<?> list) {
        int result = 0;
        for (Object object : list) {
            result += toInt(String.valueOf(object));
        }
        return result;
    }

    /**
     * Method founds all results of matching Pattern in the string
     * @param alignedLength pattern to apply to string
     * @param string string to find specified pattern
     * @return List of strings (founded parts, that matches pattern)
     */
    public static List<String> globalFind(jregex.Pattern alignedLength, String string) {
        List<String> result = new LinkedList<>();
        jregex.Matcher matcher = alignedLength.matcher(string);
        while (matcher.find()) {
            result.add(matcher.group(1));
        }
        return result;

    }


}
