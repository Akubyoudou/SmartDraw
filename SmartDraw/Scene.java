package SmartDraw;

import java.util.ArrayList;

public class Scene
{
    public static ArrayList<DrawObject> drawlayer[]=new ArrayList[5];
    public static ArrayList<DrawObject> removelist=new ArrayList<DrawObject>();
    //コンストラクタ
    public Scene()
    {
        CORE.scenelist.add(this);
        for (int i=0; i<drawlayer.length; i++)drawlayer[i]=new ArrayList<DrawObject>();
    }
    public void Update()
    {
        SceneDraw();
        SceneUpdate();
        SceneHitCheck();
        SceneLateUpdate();
        RemoveSceneObject();
    }


    public void SceneDraw()
    {
        for(int i=0;i<drawlayer.length;i++)for(DrawObject d:drawlayer[i])
            d.Draw();
    }
    public void SceneUpdate()
    {
        for(int i=0;i<drawlayer.length;i++)for(DrawObject d:drawlayer[i])
            d.Update();
    }
    public void SceneHitCheck()
    {
        for(int i=0;i<drawlayer.length;i++)for(DrawObject d:drawlayer[i])
            d.HitCheck();
    }
    public void SceneLateUpdate()
    {
        for(int i=0;i<drawlayer.length;i++)for(DrawObject d:drawlayer[i])
            d.LateUpdate();
    }
    void RemoveSceneObject()
    {
        if(removelist.size()>0)
        {
            for(DrawObject d : removelist)for(ArrayList<DrawObject> list:drawlayer)
                list.remove(d);
            removelist.clear();
        }
    }

}
