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
public class SpecialtyUnderwaterPhoto extends Specialty
{
    public static final String title = "Podvodni fotograf";
    public static final String requiredEquipmentCategory = "6.1";
    
    public SpecialtyUnderwaterPhoto()
    {
        this.setTitle(SpecialtyUnderwaterPhoto.title);
        this.setRequiredEquipmentCategory(SpecialtyUnderwaterPhoto.requiredEquipmentCategory);
    }
    
    //methods specific for UnderwaterPhoto specialty
}