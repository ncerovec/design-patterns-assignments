/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.context;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ncerovec_zadaca_4.Diving;

/**
 *
 * @author nino
 */
public class SpecialtiesContext
{
    /**
     * Read specialties file and return list of specialty assignments
     * @param specialtiesFile
     * @return 
     */
    public List<String> readSpecialtiesList(String specialtiesFile)
    {
        List<String> specialtiesAssignmentsList = null;
        
        try
        {
            specialtiesAssignmentsList = Files.readAllLines(new File(specialtiesFile).toPath(), Charset.defaultCharset());
        }
        catch (IOException ex) { Logger.getLogger(Diving.class.getName()).log(Level.SEVERE, null, ex); }

        return specialtiesAssignmentsList;
    }
}
