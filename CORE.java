package SmartDraw;

import processing.core.PApplet;

import java.util.ArrayList;

public class CORE
{
    public static PApplet papplet=null;
    public static CORE core=null;
    public static ArrayList<Scene> scenelist=new ArrayList<Scene>();
    public static boolean backupdate=true;
    public static boolean isDebug=false;
    public static Scene defaultscene=new Scene();
    //コンストラクタ(setup内で呼び出し)
    public CORE(PApplet _papplet)
    {
        papplet = _papplet;
        core = this;
    }
    //これをプロセのdrawで呼び出し
    public static void CoreUpdate()
    {
        if(backupdate)papplet.background(0);
        for (Scene s : scenelist) s.Update();
    }
    public static void DoDebug(){isDebug=true;}
}
