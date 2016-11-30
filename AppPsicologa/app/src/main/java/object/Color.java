package object;

/**
 * Created by Victor on 24/11/2016.
 */

public class Color {


    public String name;
    public int icon;

    public Color(){
        super();
    }
    public Color(String name,int icon) {
        super();
        this.name = name;
        this.icon = icon;
    }

    public String getName(){
        return name;
    }

}
