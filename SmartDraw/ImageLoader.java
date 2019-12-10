package SmartDraw;

import processing.core.PImage;
import java.util.HashMap;

public class ImageLoader
{
    public static HashMap<String,PImage> imagemap=new HashMap<String,PImage>();
    public static PImage loadImage(String path)
    {
        if(imagemap.get(path)!=null)return imagemap.get(path);
        if(!CORE.papplet.dataFile(path).exists()){CORE.papplet.println(path+"is null");return null;}
        PImage data=CORE.papplet.loadImage(path);
        imagemap.put(path,data);
        return data;
    }
}
