package ttester;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 *@author Humberto
 *@version 1.0
 * This program is designed to read, sort and print a file of albums with
 * The albums names, artist names and track names
 * Concepts outside of simple for loops, this program uses arraylist, collections.sort
 * Lastly this program uses numerous file application such as hasnext, nextLine and exists
 */
public class TTester 
{

    /**
     * @param args the command line arguments
     */
    private File afile = new File("catalog2.txt");
    private String albumname, artistname, trackname;
    private ArrayList<Tracks>thetrack = new ArrayList<>();
    private ArrayList<Albums>catalogue = new ArrayList<>();

    public static void main(String[] args) throws IOException 
    {
        TTester runit = new TTester();
        try
        {
        runit.checkfile();
        runit.sortitout();
        runit.createmenu();
        runit.printing();
        }
        catch(java.lang.ArrayIndexOutOfBoundsException e)
        {
            JOptionPane.showMessageDialog(null, "Please check spelling or if your inputting the right parameter");
        }
        catch(java.util.NoSuchElementException o)
        {
            JOptionPane.showMessageDialog(null, "Check the file a line could have been skipped and is causing issues");
        }
    }
/**
    * This method is meant to check if the file is valid and exists 
    *@param  
    *@return nothing since it is a void method
    *@throws no exception
    */
public void checkfile()
{
   if(!afile.exists())
        {
            System.out.println("Houston we have a problem");
            System.exit(25);
        }
}
/**
    * This method is meant to read the file add the two arraylists of type tracks and albums
    *@param  
    *@return nothing since it is a void method
    *@throws the FileNotFoundException which is caught in the checkfile method
    */
public void sortitout() throws FileNotFoundException
{
        Scanner myfile = new Scanner(afile); 
            String line;
            
            while(myfile.hasNext())
            {
                line = myfile.nextLine();
                Scanner myline = new Scanner(line);
                albumname = myline.next();
                artistname = myline.next();
                thetrack = new ArrayList<>(); 
                while(myline.hasNext())
                {
                    trackname = myline.next();
                    Tracks thetracks = new Tracks(trackname);
                    thetrack.add(thetracks);
                    
  
                }
                
                Albums listing = new Albums(albumname, artistname, thetrack);
                catalogue.add(listing);
            }
        myfile.close();
    }

    public void createmenu() throws IOException
    {
        boolean quit = false;
        
        while(!quit)
        {
        
        String input = JOptionPane.showInputDialog("Select one of the following numbers: \n"
                            +"1. Search by Album Title\n"
                            +"2. Search by Artist\n"
                            +"3. Add album to catalog\n"
                            +"4. Quit");
        int option = 0;
        
        try
        {
            option = Integer.parseInt(input);
        }
        catch(java.lang.NumberFormatException e)
        {
            System.out.println("Please enter a number");
            System.out.println("If you wish to cancel type four please");
        }
        
        switch(option)
        {
            case 1: searchingalbum();
                break;
            
            case 2: searchingartist();
                break;
            
            case 3: addto();
                break;
            
            case 4: quit = true;
                break;
            
            default: 
                break;
        }
        
        }
    }
   /**
    * This method is meant to search through the arraylist for the user desired album
    * Using binary search and sort features of collections
    *@param  
    *@return nothing since it is a void method
    *@throws no exception
    */
    public void searchingalbum()
    {
        Collections.sort(catalogue);
           String asker = JOptionPane.showInputDialog("Enter album name");
           String ask = asker.replaceAll(" ", "_");
           
           Albums searchalbum = new Albums(null, ask, null);
           int index = Collections.binarySearch(catalogue, searchalbum);
           
           System.out.println(catalogue.get(index));
    }
    /**
    * This method is meant to search through the arraylist for the user desired artist
    * Using binary search and sort features of collections 
    * As well as a while loop to show all albums under desired artist
    *@param  
    *@return nothing since it is a void method
    *@throws no exception
    */ 
    public void searchingartist()
    {
        Collections.sort(catalogue, new ArtistComparator());
                String asker2 = JOptionPane.showInputDialog("Enter the artist name");
                String ask2 = asker2.replaceAll(" ", "_");
                Albums searchartist = new Albums(ask2, null, null);
                int index2 = Collections.binarySearch(catalogue, searchartist, new ArtistComparator());
                
                int checker = 0;
                int counter = 0;
                while(checker  < catalogue.size())
                {
                    if(ask2.compareToIgnoreCase(catalogue.get(checker).getartistname()) == 0)
                    {
                        System.out.println(catalogue.get(checker) + " ");
                        counter++;
                    }
                    checker++;
                }
                System.out.println("Found: " + counter + " albums under your artist");
    }
    /**
    * This method is meant to allow the user the ability to add a whole album to the arraylist and the file
    *@param  
    *@return nothing since it is a void method
    *@throws IOException
    */
    public void addto() throws IOException
    {
        String inputalbum, inputartist, inputtracksongs, tracktitle;
        int index3;
        
        inputalbum = JOptionPane.showInputDialog("Enter an album name");
        String albumaddition = inputalbum.replaceAll(" ", "_");
        
        Collections.sort(catalogue);
        Albums addedalbum = new Albums(null,albumaddition,null);
        index3 = Collections.binarySearch(catalogue, addedalbum);
        
        if(index3 >= 0)
        {
            System.out.println("Album "+ inputalbum +" already on the list");
        }
        
        else
        {
            inputartist = JOptionPane.showInputDialog("Enter the artist name");
            String artistaddition = inputartist.replaceAll(" ", "_");
            Albums addedartist = new Albums(artistaddition,albumaddition,null);
            
            FileWriter writes = new FileWriter(afile, true);
            PrintWriter prints = new PrintWriter(writes);
            prints.print(artistaddition+" "+albumaddition);
            
            do
            {
                inputtracksongs = JOptionPane.showInputDialog("Enter songs of track\n"
                                                        +"When your done don't type anything and press Enter");
                tracktitle = inputtracksongs.replaceAll(" ", "_");
                if(tracktitle.length()>0)
                {
                    prints.print(" " + tracktitle);
                }
            }
            while(tracktitle.length()>0);
           
            Albums addedup = new Albums(artistaddition, albumaddition, thetrack);
            catalogue.add(addedup);
            Collections.sort(catalogue);
            prints.println();
            prints.close();
        }
    }
    /**
    * This method is meant to sort the arraylist of albums and printing them alphabetically by album
    * Then prints a new sorted arraylist of type albums but to print in order alphabetically by artist
    *@param  
    *@return nothing since it is a void method
    *@throws no exception
    */
    public void printing()
    {
        JOptionPane.showMessageDialog(null, "Whole catalogue  about to be printed, brace yourself");
        Collections.sort(catalogue);
        System.out.println("Sorted by album: ");
            for (int i = 0; i < catalogue.size(); i++) 
            {
                System.out.println(catalogue.get(i));
            }
        
        System.out.println("*****************************");
            
        Collections.sort(catalogue, new ArtistComparator());
        System.out.println("\nSorted by artist: ");
            for (int i = 0; i < catalogue.size(); i++)
            {
                System.out.println(catalogue.get(i));
            }
    }
    
}


