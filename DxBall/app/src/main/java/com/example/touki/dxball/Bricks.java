package com.example.touki.dxball;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by touki on 8/27/2017.
 */

public class Bricks {
    public int l,t,b,r;
    Rect rect;
    public boolean stat=true;

    public Bricks(int left,int top, int right,int bottom,Rect re)
    {
        l=left;
        t=top;
        b=bottom;
        r=right;
        rect=re;
    }
    public void setStat()
    {
        stat=false;
    }
}
