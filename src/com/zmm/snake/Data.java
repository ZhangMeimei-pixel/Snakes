package com.zmm.snake;

import javax.swing.*;
import java.net.URL;

public class Data {
    //头部的图片， 利用URL定位图片地址，ImageIcon：图片
    public static URL headerURL=Data.class.getResource("/statics/header.png");
    public static ImageIcon header = new ImageIcon(headerURL);

    //up
    public static URL upURL=Data.class.getResource("/statics/up.jpeg");
    public static ImageIcon up=new ImageIcon(upURL);
    //left
    public static URL leftURL=Data.class.getResource("/statics/left.jpeg");
    public static ImageIcon left=new ImageIcon(leftURL);
    //right
    public static URL rightURL=Data.class.getResource("/statics/right.jpeg");
    public static ImageIcon right=new ImageIcon(rightURL);
    //down
    public static URL downURL=Data.class.getResource("/statics/down.jpeg");
    public static ImageIcon down=new ImageIcon(downURL);
    //body
    public static URL bodyURL=Data.class.getResource("/statics/body.jpeg");
    public static ImageIcon body=new ImageIcon(bodyURL);
    //food
    public static URL foodURL=Data.class.getResource("/statics/food.jpeg");
    public static ImageIcon food=new ImageIcon(foodURL);
}
