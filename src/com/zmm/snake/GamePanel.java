//package com.zmm.snake;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.util.Random;
//
//
//public class GamePanel extends JPanel {
//
//    //画板
//    //Graphics，画笔
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);//清理屏幕
//        this.setBackground(Color.BLACK);//设置背景颜色
//
//        //绘制游戏框的头部
//        Data.header.paintIcon(this, g, 25, 11);
//
//        //绘制游戏区域
//        g.fillRect(25, 75, 850, 600);
//    }
//}
package com.zmm.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener ,ActionListener{

    int length; //蛇的长度
    int[] snakeX = new int[600];//蛇的坐标x
    int[] snakeY = new int[500];//蛇的坐标y
    String fx;//蛇的方向，R代表右，L代表作，U代表上，D代表下

    boolean isStart=false;//游戏是否开始


    Timer timer=new Timer(100, this);//定时器


    //定义一个食物
    int foodx;
    int foody;
    Random random =new Random();

    //死亡判断
    boolean isFail=false;


    //游戏的积分系统
    int score;

    //构造器调用初始化方法
    public GamePanel(){
        init();

        //获取键盘的监听事件
        this.setFocusable(true);//获取焦点
        this.addKeyListener(this);//进行监听
        timer.start();//让时间动起来
    }

    //初始化
    public void init(){
        length=3;//蛇的初始长度
        snakeX[0]=100;snakeY[0]=100;//头部坐标
        snakeX[1]=75;snakeY[1]=100;//第一个身体坐标
        snakeX[2]=50;snakeY[2]=100;//第二个身体坐标

        fx="R";//方向朝右

        //食物的随机坐标
        foodx=25+25*random.nextInt(34);
        foody=75+25*random.nextInt(24);

        score=0;

    }



    //画板
    //Graphics，画笔
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);//清理屏幕
        this.setBackground(Color.BLACK);//设置背景颜色

        //绘制游戏框的头部

        Data.header.paintIcon(this,g,25,11);

        //绘制游戏区域
        g.fillRect(25,75,850,600);


        if(fx.equals("R")){
            Data.right.paintIcon(this,g,snakeX[0],snakeY[0]);//画蛇的头部,朝右
        }else if(fx.equals("L")){
            Data.left.paintIcon(this,g,snakeX[0],snakeY[0]);//画蛇的头部，朝左
        }else if(fx.equals("U")){
            Data.up.paintIcon(this,g,snakeX[0],snakeY[0]);//画蛇的头部，朝上
        }else if(fx.equals("D")){
            Data.down.paintIcon(this,g,snakeX[0],snakeY[0]);//画蛇的头部，朝下
        }


        //画蛇的第i个身体，蛇的身体长度通过length来控制
        for(int i=1;i<length;i++)
        {
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }

        //画积分
        g.setColor(Color.WHITE);
        g.setFont(new Font("微软雅黑",Font.BOLD,18));
        g.drawString("长度:"+length,750,35);
        g.drawString("分数:"+score,750,50);

        //画食物
        Data.food.paintIcon(this,g,foodx,foody);

        //游戏提示：是否开始
        if(isStart==false){//如果isStart是false
            g.setColor(Color.WHITE);//设置画笔的颜色
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格开始游戏",300,300);

        }
        //失败提醒

        if(isFail==true){
            g.setColor(Color.RED);//设置画笔的颜色
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("游戏失败，按下空格重新开始",200,300);
        }


    }







    //键盘监听
    @Override
    public void keyPressed(KeyEvent e) {
        //键盘按下，未释放
        //获取键盘的键盘是哪一个键
        int keycode=e.getKeyCode();
        if(keycode==KeyEvent.VK_SPACE){//如果按下的是空格键

            if(isFail){//失败游戏，再来一遍
                isFail=false;
                init();//重新初始化游戏
            }else{
                isStart=!isStart;

            }
            repaint();//刷新界面
        }

        //键盘控制走向
        if(keycode==KeyEvent.VK_LEFT){
            fx="L";
        }if(keycode==KeyEvent.VK_RIGHT){
            fx="R";
        } if(keycode==KeyEvent.VK_UP){
            fx="U";
        } if(keycode==KeyEvent.VK_DOWN){
            fx="D";
        }


    }

    //定时器，监听时间流动


    //执行定时操作
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果游戏处于开始状态,并且游戏没有结束
        if(isStart && isFail==false){
            //右移

            //除了脑袋，身体都往前移动
            for(int i=length-1;i>0;i--){
                snakeX[i]=snakeX[i-1];
                snakeY[i]=snakeY[i-1];
            }

            //通过控制方向让头部移动
            if(fx.equals("R")){
                //头部向右移动
                snakeX[0]=snakeX[0]+25;
                //边界判断
                if(snakeX[0]>850){snakeX[0]=25; }
            }else if(fx.equals("L")){
                //头部向左移动
                snakeX[0]=snakeX[0]-25;
                //边界判断
                if(snakeX[0]<25){snakeX[0]=850; }
            }else if(fx.equals("U")){
                //头部向上移动
                snakeY[0]=snakeY[0]-25;
                //边界判断
                if(snakeY[0]<75){snakeY[0]=650; }
            }else if(fx.equals("D")){
                //头部向下移动
                snakeY[0]=snakeY[0]+25;
                //边界判断
                if(snakeY[0]>650){snakeY[0]=75; }
            }

            //如果蛇的头部和食物的坐标重合了，那就吃掉食物
            if(snakeX[0]==foodx && snakeY[0]==foody){
                //蛇的长度+1
                length++;
                score=score+10;

                //重新生成食物
                foodx=25+25*random.nextInt(34);
                foody=75+25*random.nextInt(24);
            }

            //失败判断
            for(int i=1;i<length;i++){
                if (snakeX[0]==snakeX[i] && snakeY[0]==snakeY[i]){  //如果蛇的头部和身体的某一个部分重合了之后，就判断失败。
                    isFail=true;
                }
            }


            //刷新界面
            repaint();

        }
        timer.start();//让时间动起来

    }




    @Override
    public void keyTyped(KeyEvent e) {
        //键盘按下去，弹起：敲击
    }
    @Override
    public void keyReleased(KeyEvent e) {
        //释放某个键的时候
    }


}
