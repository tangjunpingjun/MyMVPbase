package com.example.mymvpbase.utils;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 字符串操作工具类
 * Created by 汤军平 on 2016/06/12.
 */
public class StringUtil {

    /**
     * 格式化时间：去除时间中 T 字母
     *
     * @param str
     * @return
     */
    public static String dateRemoveT(String str) {
        if (str != null && !TextUtils.isEmpty(str)) {
            return str.replace("T", " ");
        } else {
            return "";
        }
    }

    /**
     * 将 年月日T时间 转成 年月日
     *
     * @param str
     * @return
     */
    public static String YMDDtoYMD(String str) {
        if (str != null && !TextUtils.isEmpty(str)) {
            String[] ts = str.split("T");
            return ts[0];
        } else {
            return "";
        }
    }

    /**
     * double 双浮点型数据 保留斯位小数并格式化成汇率显示的格式 0.0000
     * @return
     */
    public static String numberForRate(double data){
        return new DecimalFormat("#,###,###,##0.0000").format(data);
    }
    public static String numberForRate1(double data){
        return new DecimalFormat("###########0.0000").format(data);
    }
    /**
     * double 双浮点型数据 保留两位小数并格式化成金钱显示的格式 000，000，000.00
     *
     * @param data
     * @return
     */
    public static String numberFormat(double data) {
        return new DecimalFormat("#,###,###,##0.00").format(data);
    }

    public static String numberFormat1(double data) {
        return new DecimalFormat("#########0.00").format(data);
    }


    /**
     * float 单浮点型数据 保留两位小数并格式化成金钱显示的格式 000，000，000.00
     *
     * @param data
     * @return
     */
    public static String numberFormat(float data) {
        return new DecimalFormat("#,###,###,##0.00").format(data);
    }

    /**
     * int 整型数据 格式化成金钱显示的格式 000，000，000
     *
     * @param data
     * @return
     */
    public static String numberFormat(int data) {
        return new DecimalFormat("#,###,###,###").format(data);
    }

    /**
     *
     */
    public static String ReplaceString(String str){
        String string= StringToNulls(str);
        return string.replaceAll("\r|\n","");
    }



    /**
     * 将传来的String 为空的时候 null 还有 “”
     */
    public static String StringToNull(String str){
        if(TextUtils.isEmpty(str)){
            return "无";
        }else{
            return str;
        }
    }
    public static String StringToNulls(String str){
        if(TextUtils.isEmpty(str)){
            return "";
        }else{
            return str;
        }
    }

    /**
     * 将 double数据变成 金钱
     * @param data
     * @return
     */
    public static String numberDecimal(double data){
        BigDecimal big=new BigDecimal(data).setScale(2, BigDecimal.ROUND_HALF_UP);
        return  new DecimalFormat("￥###,###,##0.00").format(big);
    }


    /**
     * 将 变成百分比￥
     * @param data
     * @return
     */
    public static String taxRateFormat(double data){
        return new DecimalFormat("#0%").format(data);
    }


    public static String URLEncoderString(@NonNull String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, "UTF-8");
    }


    public static String StringBuilderAndString(String left, String right){
        StringBuilder sb=new StringBuilder();
        sb.append(left);
        sb.append(" 天 --- ");
        sb.append(right);
        if(sb.length() >140) {
            sb.delete(140, sb.length());
        }
        return sb.toString();
    }


    public static byte[] getBytesFromFile(File file) throws IOException {
        // Get the size of the file
        long length = file.length();
        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            // File is too large
            throw new IOException("File is too large!");
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;

        InputStream is = new FileInputStream(file);
        try {
            while (offset < bytes.length
                    && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
                offset += numRead;
            }
        } finally {
            is.close();
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }
        return bytes;
    }

    /**
     * 从文件的绝对路径来获取它的名称
     * @param file
     * @return
     */
    public  static String FileTofileName(File file){
       return  file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("/") + 1);
    }

    public static String FilePathTOString(String s){
        return s.substring(s.lastIndexOf("/") + 1);
    }

    public static String getReadableFileSize(long size) {
        if (size <= 0) {
            return "0";
        }
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }


    ////将字符串分割成数组
    public static String[] StringSplit(String name, String type){
        String[] strs=name.split(type);
        return strs;
    }
    //将数组转换为list集合
    public static List<String> StringJoin(String[] name){
        List<String> strList = new ArrayList<String>();
        //把String数组输入list
        for(String str :name){
            strList.add(str);
        }
        return strList;
    }

    //当name不等于null 将字符串分割成数组，然后将数组转换为list集合
    public static List<String> StringSplit(String name){
        List<String> strList = new ArrayList<String>();
        if(! TextUtils.isEmpty(name)){
            String[] strs=name.split(",");
            //把String数组输入list
            for(String str :strs){
                strList.add(str);
            }
        }
        return strList;
    }

    /**
     * 将  list=集合 转为带 ，的string
     * @param join
     * @param strAry
     * @return
     */
    public static String join(String join, List<String> strAry){
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<strAry.size();i++){
            if(i==(strAry.size()-1)){
                sb.append(strAry.get(i));
            }else{
                sb.append(strAry.get(i)).append(join);
            }
        }
        return new String(sb);
    }


}
