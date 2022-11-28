import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

/**
* 
* @author: Amir Armion 
* 
* @version: V.01
* 
*/
public class MaxTemperature 
{

    public CSVRecord hottestInfo(CSVParser parser)
    {
        CSVRecord hottest            = null;

        for(CSVRecord record: parser)
        {
            if(hottest == null)
            {
                hottest = record;
            }
            else
            {
                double tempRecord = Double.parseDouble(record.get("TemperatureF"));

                if(tempRecord > Double.parseDouble(hottest.get("TemperatureF")))
                {
                    hottest = record;
                }
            }
        }

        return hottest;
    }

    public CSVRecord hottestInfoInManyDays()
    {
        CSVRecord         hottest = null;
        DirectoryResource dr      = new DirectoryResource();

        for(File f: dr.selectedFiles())
        {
            FileResource file   = new FileResource(f);
            CSVParser    parser = file.getCSVParser();
            CSVRecord    record = hottestInfo(parser);

            if(hottest == null)
            {
                hottest = record;
            }
            else
            {
                double tempRecord = Double.parseDouble(record.get("TemperatureF"));

                if(tempRecord > Double.parseDouble(hottest.get("TemperatureF")))
                {
                    hottest = record;
                }
            }
        }

        return hottest;
    }

    public void testHottestInfo()
    {
        FileResource file   = new FileResource();
        CSVParser    parser = file.getCSVParser();

        CSVRecord    record = hottestInfo(parser);

        System.out.println("The hottest temperature was " + record.get("TemperatureF") + " at " + record.get("TimeEST") + ".");
    }

    public void testHottestInfoInManyDays()
    {
        System.out.println("The hottest temperature was " + hottestInfoInManyDays().get("TemperatureF") + " at " + hottestInfoInManyDays().get("DateUTC") + ".");
    }
}
