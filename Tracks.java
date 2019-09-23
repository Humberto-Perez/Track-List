package ttester;

/**
 *
 * @author Humberto
 */
public class Tracks implements Comparable 
{
    String tname;
    
    public Tracks(String tname) 
    {
        this.tname =tname;
    }
    
    public int compareTo(Object hits)
    {
        Tracks otherhits = (Tracks)hits;
        return tname.compareTo(otherhits.tname);
    }
    
    public String toString()
    {
        return tname;
    }
}
