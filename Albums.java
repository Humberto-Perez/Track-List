package ttester;

import java.util.ArrayList;




/**
 *
 * @author Humberto
 */
public class Albums implements Comparable<Albums>
{
    private String albumname;
    private String artistname;
    private ArrayList<Tracks>thetrack;
    
    public Albums(String artistname, String albumname, ArrayList thetrack)
    {
        this.albumname = albumname;
        this.artistname = artistname;
        this.thetrack = thetrack;        
    }

     Albums(String albumname) 
    {
       this.albumname = albumname;
    }
    
   public int compareTo(Albums album)
   {
       return albumname.compareToIgnoreCase(album.albumname);
   }
    
    public String getartistname()
    {
       return artistname;
    }
    
    public String toString()
    {
        return albumname +" "+ artistname + " " + thetrack;
    }

   
}
