/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.model.diver.specialty;

import ncerovec_zadaca_4.model.equipment.EquipmentCategory;

/**
 *
 * @author nino
 */
public class SpecialtyUnderwaterPhoto extends Specialty
{
    public static final String title = "Podvodni fotograf";
    public static final String requiredEquipmentCategory = EquipmentCategory.Id.CAMERA;
    
    public SpecialtyUnderwaterPhoto()
    {
        this.setTitle(SpecialtyUnderwaterPhoto.title);
        this.setRequiredEquipmentCategory(SpecialtyUnderwaterPhoto.requiredEquipmentCategory);
    }
    
    //methods specific for UnderwaterPhoto specialty
}