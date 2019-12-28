package SmartDraw;

import java.util.ArrayList;

public class Scene
{
    public static ArrayList<DrawObject> drawlist=new ArrayList<DrawObject>();
    public static ArrayList<DrawObject> removelist=new ArrayList<DrawObject>();
    //コンストラクタ
    public Scene()
    {
        CORE.scenelist.add(this);
    }
    public void Update()
    {
        Zsort();
        SceneDraw();
        SceneUpdate();
        SceneHitCheck();
        SceneLateUpdate();
        RemoveSceneObject();
    }
    public void Zsort()
    {
        drawlist.sort((z1,z2)-> (int)(z1.pos.z-z2.pos.z));
    }
    public void SceneDraw()
    {
        for(DrawObject d:drawlist)
            d.Draw();
    }
    public void SceneUpdate()
    {
        for(DrawObject d:drawlist)
            d.Update();
    }
    public void SceneHitCheck()
    {
        for(DrawObject d:drawlist)
            d.HitCheck();
    }
    public void SceneLateUpdate()
    {
        for(DrawObject d:drawlist)
            d.LateUpdate();
    }
    void RemoveSceneObject()
    {
        if(removelist.size()>0)
        {
            for(DrawObject d : removelist)
                drawlist.remove(d);
            removelist.clear();
        }
    }

}
