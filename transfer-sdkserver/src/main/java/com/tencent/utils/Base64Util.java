package com.tencent.utils;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;


public class Base64Util {

	public final static String CHARSET_UTF8 = "UTF-8"; 
    public static String encode (String data , String charset) throws Exception{

        if(charset == null || charset.equals("")){
            charset = CHARSET_UTF8 ;
        }
        try {
            byte[] dataBytes = Base64.encodeBase64(data.getBytes(charset));

            String dataBase64 = new String(dataBytes, charset);
            if (dataBase64 != null) {
                dataBase64 = dataBase64.trim();
            }

            return dataBase64;
        } catch (UnsupportedEncodingException e) {
            System.out.println("Base64Util.encode UnsupportedEncodingException");
            throw new Exception("Base64Util.encode UnsupportedEncodingException:" + e.getMessage());
        }

    }

    public static String decode (String data , String charset) throws Exception {

        if(charset == null || charset.equals("")){
            charset = CHARSET_UTF8 ;
        }
        try {
            byte[] dataBytes = Base64.decodeBase64(data.getBytes(charset));

            String dataStr = new String(dataBytes, charset);

            return dataStr;
        } catch (UnsupportedEncodingException e) {
        	System.out.println("Base64Util.decode UnsupportedEncodingException");
            throw new Exception("Base64Util.decode UnsupportedEncodingException:" + e.getMessage());
        }
    }

    public static byte[] decodeToBytes (String data , String charset) throws Exception {

        if(charset == null || charset.equals("")){
            charset = CHARSET_UTF8 ;
        }
        try {
            byte[] dataBytes = Base64.decodeBase64(data.getBytes(charset));
            return dataBytes;
        } catch (UnsupportedEncodingException e) {
        	System.out.println("Base64Util.decode UnsupportedEncodingException");
            throw new Exception("Base64Util.decode UnsupportedEncodingException:" + e.getMessage());
        }
    }
}
