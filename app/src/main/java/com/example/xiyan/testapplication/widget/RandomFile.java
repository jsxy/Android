package com.example.xiyan.testapplication.widget;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Created by xiyan on 16-4-22.
 */
public class RandomFile {
    public static void main(String[] args) throws Exception
    {
        String str = "012345678vasdjhklsadfqwiurewopt_dasjd自己补全字母和数字,这个字符数是作为随机取值的源"; //自己补全字母和数字,这个字符数是作为随机取值的源
        PrintWriter pw = new PrintWriter(new FileWriter("/home/xiyan/temp/test1GB.txt"));
        int len = str.length();
        //每次写入10K,写入2048次就是 20M
        for (int i = 0; i < 57000; i++)
        {

            StringBuilder s = new StringBuilder();
            for (int j = 0; j < 10240; j++)
            {
                s.append(str.charAt((int)(Math.random()*len)));
            }
            Thread.sleep(1000);
            pw.println(s.toString());
        }
        pw.close();
    }


}
