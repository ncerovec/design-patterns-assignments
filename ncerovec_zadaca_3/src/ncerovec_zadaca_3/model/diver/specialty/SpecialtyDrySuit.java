/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.model.diver.specialty;

/**
 *
 * @author nino
 */
public class SpecialtyDrySuit extends Specialty
{
    public static final String title = "Suho odijelo";
    public static final String requiredEquipmentCategory = "1.1.3";
    
    public SpecialtyDrySuit()
    {
        this.setTitle(SpecialtyDrySuit.title);
        this.setRequiredEquipmentCategory(SpecialtyDrySuit.requiredEquipmentCategory);
    }
        
    //methods specific for DrySuit specialty
}