package SmartDraw.Vector;

import SmartDraw.CORE;
import processing.core.*;

import static processing.core.PApplet.sqrt;

public class Vector2
{
    public float x=0;
    public float y=0;
    final public static Vector2 zero=new Vector2();
    public Vector2(float _x,float _y)
    {
        x=_x;
        y=_y;
    }
    public Vector2(Vector2 v)
    {
        x=v.x;
        y=v.y;
    }
    public Vector2(){}
    //複製
    public Vector2 Clone()
    {
        return new Vector2(x,y);
    }
    //足し算
    public Vector2 Add(float _x,float _y)
    {
        return new Vector2(x+_x,y+_y);
    }
    public Vector2 Add(Vector2 v)
    {
        return new Vector2(x+v.x,y+v.y);
    }
    public Vector2 Add(Vector3 v)
    {
        return new Vector2(x+v.x,y+v.y);
    }
    public Vector2 Add(Vector4 v)
    {
        return new Vector2(x+v.x,y+v.y);
    }
    //掛け算
    public Vector2 Scalar(float xs,float ys)
    {
        return new Vector2(x*xs,y*ys);
    }
    //セッター(簡易化)
    public void Set(float _x,float _y)
    {
        x=_x;
        y=_y;
    }
    public  void Set(Vector2 v)
    {
        x=v.x;
        y=v.y;
    }
    public Vector2 Normalized()
    {
        float large=Large();
        if (large==0)return this;
        Vector2 v=new Vector2(this);
        v.x/=large;
        v.y/=large;
        return v;
    }
    public float Large()
    {
        return sqrt(x*x+y*y);
    }
}
