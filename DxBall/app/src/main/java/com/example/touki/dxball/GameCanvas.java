package com.example.touki.dxball;

/**
 * Created by touki on 8/27/2017.
 */
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

public class GameCanvas extends View{

    Paint paintbox;
    Paint paintball;
    Paint paintstick;
    Paint painthr;
    Paint painttext;
    Paint paintgameover;
    int score=0;
    int scoreplace=0;
    float x=0,y=0,X=x+36,Y=y+36,a=100,b=a+300,bas=36;
    float mid=(a+b)/2;
    boolean firstTime=true;
    boolean left=false;
    boolean right=true;
    boolean up=false;
    boolean down=false;
    boolean upward=false;
    boolean downward=true;
    boolean leftward=false;
    boolean rightward=false;
    boolean gameOver=false;
    int l=5,t=50,bo=150,r=205,type=0;
    Bricks [] bricks=new Bricks[10];
    Rect[] rect=new Rect[10];
    MainActivity mainActivity=new MainActivity();

    protected void onDraw(Canvas canvas)
    {

        if(firstTime)
        {

            firstTime=false;
            x=canvas.getWidth() / 2;
            y=canvas.getHeight() / 2;
            for(int i=1;i<5;i++) {
                rect[i]=new Rect();
                rect[i].set((i*179)+5-179,200,(i*179),300);

                bricks[i]=new Bricks((i*179)+5-179,200,(i*179),300,rect[i]);
            }

        }

        if(y==1140&&x>=a&&x<=b)
        {
          if (x<=mid)
            {
                leftward=true;
                rightward=false;
            }
            else if(x>mid)
            {
                leftward=false;
                rightward=true;
            }
            left=false;
            right=false;
            up=true;
            down=false;
            upward=true;
            downward=false;
        }
        else if(y>1300)
        {
            type=1;
            gameOver=true;
        }
        if(x>=canvas.getWidth()-38)
        {
            left=true;
            right=false;
            up=false;
            down=false;
            leftward=true;
            rightward=false;
        }
        else if(x<=38) {
            left=false;
            right=true;
            up=false;
            down=false;
            leftward=false;
            rightward=true;
        }

        if(y>=canvas.getHeight()-38)
        {
           /* left=false;
            right=false;
            up=true;
            down=false;
            upward=true;
            downward=false;*/
        }
        else if(y<=190) {
            left=false;
            right=false;
            up=false;
            down=true;
            upward=false;
            downward=true;
        }
       if(left)
       {
           if(downward) {
               x--;
               y++;
           }
           else if(upward)
           {
               x--;
               y--;
           }
       }
       if(right)
       {
           if(downward) {
               x++;
               y++;
           }
           else if(upward)
           {
               x++;
               y--;
           }
       }
       if(up)
       {
           if(leftward) {
               x--;
               y--;
           }
           else if(rightward)
           {
               x++;
               y--;
           }
           else
           {
               y--;
           }
       }
       if(down)
       {
           if(leftward) {
               x--;
               y++;
           }
           else if(rightward)
           {
               x++;
               y++;
           }
       }

      /* if(x>=100-bas&&x<=200+bas&&y<=200+bas&&y>=100-bas)
       {
           x++;
           y++;
           upward=false;
           downward=true;
       }*/
       // calNextPos();
        canvas.drawRGB(255, 255, 255);





        paintbox.setColor(Color.RED);
        paintbox.setStyle(Style.FILL);
        //canvas.drawRect(rect[0],paintbox);
        for(int i=1;i<5;i++)
        {
            if(bricks[i].stat) {
                canvas.drawRect(bricks[i].rect, paintbox);
            }
            else
            {
                continue;
            }
        }
        //canvas.drawRect(rect[1],paintbox);
       // canvas.drawRect(rect[2],paintbox);
        //canvas.drawRect(rect[3],paintbox);
      /*  Rect rect1=new Rect();
        Rect rect2=new Rect();
        rect1.set(l,t,r,bo);

        rect2.set(200,200,300,300);

        canvas.drawRect(rect1,paintbox);
        canvas.drawRect(rect2,paintbox);*/
        paintball.setColor(Color.GREEN);
        paintball.setStyle(Style.FILL);
        canvas.drawCircle(x,y, 40, paintball);
        paintstick.setColor(Color.MAGENTA);
        paintstick.setStyle(Style.FILL);
        paintstick.setStrokeWidth(40);
        canvas.drawLine(a, 1200, b, 1200, paintstick);
        painttext.setColor(Color.BLACK);
        painttext.setStyle(Style.FILL);
        painttext.setStrokeWidth(40);
        painttext.setTextSize(50);
        canvas.drawText("Score: "+score,50,100,painttext);
        scoreplace=score;
        painthr.setColor(Color.BLACK);
        painthr.setStyle(Style.FILL);
        painthr.setStrokeWidth(10);
        canvas.drawLine(0, 150, canvas.getWidth(), 150, painthr);
        for(int i=1;i<5;i++){
            if(bricks[i].stat)
            {
                if(x>=bricks[i].l-bas&&x<=bricks[i].r+bas&&y==bricks[i].b+bas)
                {
                    upward=false;
                    downward=true;
                    if(leftward)
                    {
                        y++;
                        x--;
                    }
                    else if(rightward)
                    {
                        y++;
                        x++;

                    }
                    bricks[i].setStat();
                    score++;
                }
                if(x>=bricks[i].l-bas&&x<=bricks[i].r+bas&&y==bricks[i].t-bas)
                {
                    upward=true;
                    downward=false;
                    if(leftward)
                    {
                        y--;
                        x--;
                    }
                    else if(rightward)
                    {
                        y--;
                        x++;

                    }
                    bricks[i].setStat();
                    score++;
                }

                if(y>=bricks[i].t-bas&&y<=bricks[i].b+bas&&x==bricks[i].l-bas)
                {
                    rightward=false;
                    leftward=true;
                    if(upward)
                    {
                        y--;
                        x--;
                    }
                    else if(downward)
                    {
                        y++;
                        x--;

                    }
                    bricks[i].setStat();
                    score++;
                }

                if(y>=bricks[i].t-bas&&y<=bricks[i].b+bas&&x==bricks[i].r-bas)
                {
                    rightward=true;
                    leftward=false;
                    if(upward)
                    {
                        y--;
                        x++;
                    }
                    else if(downward)
                    {
                        y++;
                        x++;

                    }
                    bricks[i].setStat();
                    score++;
                }

            }
        }
        if(scoreplace==4)
        {
            type=0;
            gameOver=true;


        }
           if(!gameOver) {

               invalidate();
           }
           else {
               if(type==1) {
                   paintgameover.setColor(Color.RED);
                   paintgameover.setStyle(Style.FILL);
                   paintgameover.setStrokeWidth(40);
                   paintgameover.setTextSize(100);
                   canvas.drawText("Game Over", 100, 700, paintgameover);
               }
               else if(type==0)
               {

                   paintgameover.setColor(Color.GREEN);
                   paintgameover.setStyle(Style.FILL);
                   paintgameover.setStrokeWidth(40);
                   paintgameover.setTextSize(100);
                   canvas.drawText("Win!!",250,700,paintgameover);

               }
           }
    }
    public GameCanvas(Context context)
    {
        super(context);
        paintbox=new Paint();
        paintball=new Paint();
        paintstick=new Paint();
        painthr=new Paint();
        painttext=new Paint();
        paintgameover=new Paint();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float X =  event.getX();

               if (X < 500 &&a>=0) {
                   a = a - 10;
                   b = a + 300;
               } else if(x>=500&&a<=400) {
                   a = a + 10;
                   b = a + 300;
               }


        return true;
    }
}
