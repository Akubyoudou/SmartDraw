package SmartDraw.Vector;

import SmartDraw.CORE;
import processing.core.*;
import static processing.core.PApplet.*;

public class Vector4
{
    //プロパティ
    public float x=0;
    public float y=0;
    public float z=0;
    public float w=0;
    final public static Vector4 zero=new Vector4();
    //コンストラクタ
    public Vector4(float _x,float _y,float _z,float _w)
    {
        x=_x;
        y=_y;
        z=_z;
        w=_w;
    }
    public Vector4(Vector4 v)
    {
        x=v.x;
        y=v.y;
        z=v.z;
        w=v.w;
    }
    public Vector4(Vector2 pos,Vector2 sca)
    {
        x=pos.x;
        y=pos.y;
        z=sca.x;
        w=sca.y;
    }
    public Vector4(){}
    //複製
    public Vector4 Clone()
    {
        return new Vector4(x,y,z,w);
    }
    //足し算
    public Vector4 Add(float _x,float _y,float _z,float _w)
    {
        return new Vector4(x+_x,y+_y,z+_z,w+_w);
    }
    public Vector4 Add(Vector2 v)
    {
        return new Vector4(x+v.x,y+v.y,z,w);
    }
    public Vector4 Add(Vector3 v)
    {
        return new Vector4(x+v.x,y+v.y,z+v.z,w);
    }
    public Vector4 Add(Vector4 v)
    {
        return new Vector4(x+v.x,y+v.y,z+v.z,w+v.w);
    }
    //掛け算
    public Vector4 Scalar(float xs,float ys,float zs,float ws)
    {
        return new Vector4(x*xs,y*ys,z*zs,w*ws);
    }
    //セッター(簡易化)
    public void Set(float _x,float _y,float _z,float _w)
    {
        x=_x;
        y=_y;
        z=_z;
        w=_w;
    }
    public void Set(Vector2 pos,Vector2 sca)
    {
        x=pos.x;
        y=pos.y;
        z=sca.x;
        w=sca.y;
    }
    public  void Set(Vector4 v)
    {
        x=v.x;
        y=v.y;
        z=v.z;
        w=v.w;
    }
    public Vector4 Normalized()
    {
        float large=Large();
        if (large==0)return this;
        Vector4 v=new Vector4();
        v.x/=large;
        v.y/=large;
        v.z/=large;
        v.w/=large;
        return v;
    }

    public float Large()
    {
        return sqrt(x*x+y*y+z*z+w*w);
    }

}
