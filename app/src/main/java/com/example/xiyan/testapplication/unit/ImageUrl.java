package com.example.xiyan.testapplication.unit;

import android.os.Environment;

/**
 * Created by xiyan on 16-4-29.
 */
public class ImageUrl {
    public static String[] imageNetUrl = new String[]{"http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE%E7%89%87&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&cs=2546610023,3120506294&os=3557275076,4187442601&simid=0,0&pn=0&rn=1&di=23857166990&ln=1000&fr=&fmq=1461907346204_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=&istype=0&ist=&jit=&bdtype=11&gsm=0&objurl=http%3A%2F%2Fbanbao.chazidian.com%2Fuploadfile%2F2016-01-25%2Fs145368924044608.jpg&rpstart=0&rpnum=0", "http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE%E7%89%87&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&cs=2609450077,4178219781&os=2092010548,3618729096&simid=3539262538,221039368&pn=26&rn=1&di=68377545940&ln=1000&fr=&fmq=1461907346204_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=&istype=0&ist=&jit=&bdtype=0&gsm=0&objurl=http%3A%2F%2Fimg01.taopic.com%2F141116%2F318764-14111610353113.jpg&rpstart=0&rpnum=0", "http://img2.imgtn.bdimg.com/it/u=3179881374,3105935508&fm=15&gp=0.jpg", "http://img6.baixing.net/e563baea0119fde1e0d8b8ec43e0467a.jpg_180x180", "http://fdfs.xmcdn.com/group7/M0B/4B/CA/wKgDWlWvCDzj1nXuAADoSBScAu0998_web_large.jpg", "http://img6.baixing.net/202160d4e03d20620d03fc6f964432dc.jpg_180x180", "http://img2.imgtn.bdimg.com/it/u=3797855178,3480629334&fm=11&gp=0.jpg", "http://ww2.sinaimg.cn/thumb180/94b23293tw1elooyszmnqj2050050t8q.jpg", "http://awb.img.xmtbang.com/img/uploadnew/201510/23/e84eaaee6719491cb9ab159ae9630c29.jpg", "http://img2.imgtn.bdimg.com/it/u=738878696,3319213926&fm=15&gp=0.jpg", "http://img4.imgtn.bdimg.com/it/u=2619159266,858976268&fm=21&gp=0.jpg", "http://img3.imgtn.bdimg.com/it/u=2517272913,886013688&fm=21&gp=0.jpg", "http://img4.imgtn.bdimg.com/it/u=2510939683,4234033765&fm=21&gp=0.jpg", "http://img008.hc360.cn/g8/M09/E5/40/wKhQtlQGrbeEIsNyAAAAAL4jbHI166.jpg..180x180.jpg", "http://p3.music.126.net/prtL9DkmxPubM7wheASF-w==/7962663210377677.jpg?param=180y180", "http://img4.imgtn.bdimg.com/it/u=470319400,4181718460&fm=15&gp=0.jpg", "http://portrait2.sinaimg.cn/3270074041/blog/180", "http://ww2.sinaimg.cn/thumb180/9380d3ebjw1f0oods9r7aj205k05k0st.jpg", "http://img0.imgtn.bdimg.com/it/u=2263238649,3508981465&fm=21&gp=0.jpg", "http://img.my.csdn.net/uploads/201407/26/1406383214_7794.jpg", "http://img.my.csdn.net/uploads/201407/26/1406383213_4418.jpg", "http://img.my.csdn.net/uploads/201407/26/1406383213_3557.jpg", "http://img.my.csdn.net/uploads/201407/26/1406383210_8779.jpg", "http://img.my.csdn.net/uploads/201407/26/1406383172_4577.jpg", "http://img.my.csdn.net/uploads/201407/26/1406383166_3407.jpg", "http://img.my.csdn.net/uploads/201407/26/1406383166_2224.jpg", "http://img.my.csdn.net/uploads/201407/26/1406383166_7301.jpg"};
    private static String filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static String[] imageFileUrl;

    public ImageUrl() {
    }

    static {
        imageFileUrl = new String[]{filePath + "/DCIM/yidian_3010.jpg", filePath + "/DCIM/yidian_3010.jpg", filePath + "/DCIM/yidian_3010.jpg", filePath + "/DCIM/yidian_3010.jpg", filePath + "/DCIM/yidian_3010.jpg"};
    }
}
