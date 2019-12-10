package SmartDraw.Vector;

import SmartDraw.CORE;

import static processing.core.PApplet.sqrt;

public class Vector3
{
    public float x=0;
    public float y=0;
    public float z=0;
    final public static Vector3 zero=new Vector3();
    //コンストラクタ
    public Vector3(float _x,float _y,float _z)
    {
        x=_x;
        y=_y;
        z=_z;
    }
    public Vector3(Vector3 v)
    {
        x=v.x;
        y=v.y;
        z=v.z;
    }
    public Vector3(){}
    //複製
    public Vector3 Clone()
    {
        return new Vector3(x,y,z);
    }
    //足し算
    public Vector3 Add(float _x,float _y,float _z)
    {
        return new Vector3(x+_x,y+_y,z+_z);
    }
    public Vector3 Add(Vector2 v)
    {
        return new Vector3(x+v.x,y+v.y,z);
    }
    public Vector3 Add(Vector3 v)
    {
        return new Vector3(x+v.x,y+v.y,z+v.z);
    }
    public Vector3 Add(Vector4 v)
    {
        return new Vector3(x+v.x,y+v.y,z+v.z);
    }
    //掛け算
    public Vector3 Scalar(float xs,float ys,float zs)
    {
        return new Vector3(x*xs,y*ys,z*zs);
    }
    //セッター(簡易化)
    public void Set(float _x,float _y,float _z)
    {
        x=_x;
        y=_y;
        z=_z;
    }
    public  void Set(Vector3 v)
    {
        x=v.x;
        y=v.y;
        z=v.z;
    }
    public Vector3 Normalized()
    {
        float large=Large();
        if (large==0)return this;
        Vector3 v=new Vector3();
        v.x/=large;
        v.y/=large;
        v.z/=large;
        return v;
    }
    public float Large()
    {
        return sqrt(x*x+y*y+z*z);
    }
    public void Fill()
    {
        CORE.papplet.fill(x, y, z);
    }
}
