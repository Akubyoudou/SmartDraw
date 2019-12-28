package SmartDraw;
import static SmartDraw.ImageLoader.loadImage;
import static processing.core.PApplet.*;
import SmartDraw.Vector.*;
import processing.core.PImage;

import java.util.ArrayList;

public class DrawObject
{
    //レイヤ列挙
    public enum DrawLayer
    {
        BACK,
        DEEP,
        MIDDLE,
        SHALLOW,
        SURFACE
    }
    //方向列挙
    public enum Direction
    {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }
    //描写モード列挙
    public enum DrawMode
    {
        Default,
        Text,
        Image,
        Anime,
        Rect,
        Ellipse
    }
    //所属シーン
    Scene s=null;
    //アクティブ
    public boolean isactive=true;
    //当たり判定可否
    public boolean nohit=false;
    //非表示フラグ
    public boolean hide=false;
    //描写モード
    public DrawMode drawmode=DrawMode.Default;
    //位置ベクトル
    Vector3 pos=new Vector3();
    Vector3 lastpos=new Vector3();
    //大きさ
    Vector2 size=new Vector2();
    //ヒットボックス
    private Vector4 hitbox=new Vector4();
    //ヒットボックスの修正割合
    private Vector4 hitadjust=null;
    //フレーム内で当たったものリスト
    public ArrayList<DrawObject> hitlist=new ArrayList<DrawObject>();
    //スピード
    public float speed = 0;
    //向き
    public Vector2 direction=new Vector2();
    //文字サイズ
    private float textsize=1;
    //文字データ
    private String text="";
    //色
    public Vector3 color = new Vector3();
    //画像のパス
    private String imagepath=null;

    //アニメ関係の変数
    private String animefolderpath=null;
    private PImage animeimage=null;
    private int animeframecount=0;
    public boolean animeroop=true;
    private   int animesize=0;

    //コンストラクタ
    public DrawObject()
    {
        s=CORE.defaultscene;
        s.drawlist.add(this);
    }
    public DrawObject(Scene _s)
    {
        s=_s;
        s.drawlist.add(this);
    }
    public DrawObject(DrawLayer layer)
    {
        s=CORE.defaultscene;
        s.drawlist.add(this);
    }
    public DrawObject(Scene _s,DrawLayer layer)
    {
        s=_s;
        s.drawlist.add(this);
    }

    //デストラクタ
    public void Remove()
    {
        s.removelist.add(this);
    }

    //描写
    public void Draw()
    {
        if(!isactive)return;
        if(hide)return;
        color.Fill();
        switch (drawmode)
        {
            case Image:
                Image();
                break;
            case Text:
                Text();
                break;
            case Rect:
                Rect();
                break;
            case Ellipse:
                Ellipse();
                break;
            case Anime:
                Anime();
                break;
        }
        Vector3.zero.Fill();
    }

    //アップデート
    public void Update()
    {
        if(!isactive)return;
        direction=direction.Normalized();
        pos=pos.Add(direction.Scalar(speed,speed));
    }

    //当たり判定(このオブジェクトがレイヤ内の全てに対し実行、シーンにおいてレイヤ内全てのオブジェクトから呼ばれる)
    public void HitCheck()
    {
        if(!isactive)return;
        if(nohit)return;
        for(DrawObject obj:s.drawlist)
        {
            if (obj.nohit) return;
            OverlapCheck(obj);
        }
    }

    //レイトアップデート
    public void LateUpdate()
    {
        if(!isactive)return;
        lastpos.Set(pos);
    }

    //当たった時のイベント
    public void OnHit(DrawObject obj,Direction hitpoint)
    {
        Back();
    }

    //重なり判定
    final private boolean XCheck(DrawObject o) {Vector4 box=GetHitbox();Vector4 obox=o.GetHitbox(); return (box.x < obox.x && obox.x < box.x + box.z) || (box.x < obox.x + obox.z && obox.x + obox.z < box.x + box.z); }
    final private boolean YCheck(DrawObject o) {Vector4 box=GetHitbox();Vector4 obox=o.GetHitbox(); return (box.y < obox.y && obox.y < box.y + box.w) || (box.y < obox.y + obox.w && obox.y + obox.w < box.y + box.w); }
    final private void OverlapCheck(DrawObject other)
    {
        if ((XCheck(other))&&YCheck(other))
        {
            for (DrawObject d : hitlist){if (d==other)return;}
            Direction this_lappoint=LapPointCheck(other);
            Direction other_lappoint=other.LapPointCheck(this);
            hitlist.add(other);
            other.hitlist.add(this);
            other.OnHit(this, other_lappoint);
            OnHit(other, this_lappoint);
            println("hit");
        }
    }
    //ヒットボックス取得
    public  Vector4 GetHitbox()
    {
        if(hitadjust==null)return hitbox.Add(pos);
        return new Vector4(((hitbox.x+hitadjust.x)+(hitbox.z-hitadjust.z)/2),((hitbox.y+hitadjust.y)+(hitbox.w-hitadjust.w)/2),(hitadjust.z),(hitadjust.w)).Add(pos);
    }
    //ヒットボックス調整
    public void HitboxAdjust(float originX,float originY,float sizeX,float sizeY)
    {
        if(sizeX==0)sizeX=hitbox.z;
        if(sizeY==0)sizeX=hitbox.w;
        hitadjust=new Vector4(originX,originY,sizeX,sizeY);
    }
    //当たった場所の判定(雑)
    final private Direction LapPointCheck(DrawObject o)
    {
        Direction result=Direction.UP;
        float leftlap=1023;
        float rightlap=1023;
        float downlap=1023;
        float uplap=1023;
        Vector4 box=GetHitbox();
        Vector4 obox=o.GetHitbox();
        if (box.x<obox.x+obox.z&&obox.x<box.x+box.z)
        {
            leftlap=abs(box.x-(obox.x+obox.z));
            rightlap=abs(obox.x-(box.x+box.z));
        }

        if (box.y<obox.y+obox.w&&obox.y<box.y+box.w)
        {
            uplap=abs(box.y-(obox.y+obox.w));
            downlap=abs(obox.y-(box.y+box.w));
        }
        float[] laplist={leftlap, rightlap, uplap, downlap};
        float min=min(laplist);

        if (min==1023){println("laperror");}
        else if (min==leftlap) { result=Direction.LEFT;}
        else if (min==rightlap){ result=Direction.RIGHT;}
        else if (min==uplap)   { result=Direction.UP;}
        else if (min==downlap) { result=Direction.DOWN;}
        return result;
    }
    public void Image(String path,Vector2 _pos,Vector2 _size)
    {
        drawmode=DrawMode.Image;
        imagepath=path;
        pos.Set(_pos);
        size=_size;
        hitbox=new Vector4(pos.x,pos.y,size.x,size.y);
    }
    public void Text(String _text,float _textsize,Vector2 _pos)
    {
        drawmode=DrawMode.Text;
        text=_text;
        textsize=_textsize;
        pos.Set(_pos);
        lastpos.Set(_pos);
    }
    public void Rect(Vector4 v)
    {
        drawmode=DrawMode.Rect;
        pos.Set(v.x,v.y,0);
        size.Set(v.z,v.w);
    }
    public void Rect(Vector2 _pos,Vector2 _size)
    {
        drawmode= DrawMode.Rect;
        pos.Set(_pos);
        size.Set(_size);
    }
    public void Ellipse(Vector4 v)
    {
        drawmode=DrawMode.Ellipse;
        pos.Set(v.x,v.y,0);
        size.Set(v.z,v.w);
    }
    public void Ellipse(Vector2 _pos,Vector2 _size)
    {
        drawmode= DrawMode.Ellipse;
        pos.Set(_pos);
        size.Set(_size);
    }
    public void Anime(String AnimePath,Vector4 v)
    {
        animefolderpath=AnimePath;
        drawmode=DrawMode.Anime;
    }
    private void Image()
    {
        CORE.papplet.image(loadImage(imagepath),pos.x,pos.y,size.x,size.y);
    }
    private void Image(PImage image){CORE.papplet.image(image,pos.x,pos.y,size.x,size.y);}
    private void Text()
    {
        CORE.papplet.textSize(textsize);
        CORE.papplet.text(text,pos.x,pos.y);
    }
    private void Rect()
    {
        CORE.papplet.rect(pos.x,pos.y,size.x,size.y);
    }
    private void Ellipse()
    {
        CORE.papplet.ellipse(pos.x,pos.y,size.x,size.y);
        hitadjust=new Vector4(0,0,size.x*0.9f,size.y*0.9f);
    }
    private void Anime()
    {
        String animepath =animefolderpath+"/"+animeframecount+".bmp";
        if(CORE.papplet.dataFile(animepath).exists())animeimage=loadImage(animepath);
        if(animeimage!=null)Image(animeimage);
        if(animesize==animeframecount)
        {
            OnAnimeFinish(animefolderpath);
            if(animeroop)animeframecount=0;
            else Remove();
        }
        animeframecount+=1;
    }

    public void OnAnimeFinish(String animename){}
    public void Back()
    {
        pos.Set(lastpos);
    }
}
