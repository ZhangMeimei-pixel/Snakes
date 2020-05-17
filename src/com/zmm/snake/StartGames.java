package com.zmm.snake;

        import javax.swing.*;

public class StartGames {
    public static void main(String[]args){
        //1、绘制一个静态窗口,JFrame
        JFrame frame=new JFrame("贪吃蛇小游戏");
        //设置界面的大小
        frame.setBounds(10,10,900,720);
        //设置窗口大小不可以改变
        frame.setResizable(false);
        //设置关闭事件,关闭游戏
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //2、绘制一个面板，在面板上画画
        frame.add(new GamePanel());




        //让窗口能够展示出来
        frame.setVisible(true);


    }

}
