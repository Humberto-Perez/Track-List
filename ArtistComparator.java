/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ttester;

import java.util.Comparator;

/**
 *
 * @author Humberto
 */
public class ArtistComparator implements Comparator<Albums> 
{
    public int compare(Albums a,Albums b)
    {
        return (a.getartistname().compareToIgnoreCase(b.getartistname()));
    }
}
