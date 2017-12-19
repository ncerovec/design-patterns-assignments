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
public class SpecialtyDrySuit extends Specialty
{
    public static final String title = "Suho odijelo";
    public static final String requiredEquipmentCategory = EquipmentCategory.Id.DRY_SUIT;
    
    public SpecialtyDrySuit()
    {
        this.setTitle(SpecialtyDrySuit.title);
        this.setRequiredEquipmentCategory(SpecialtyDrySuit.requiredEquipmentCategory);
    }
        
    //methods specific for DrySuit specialty
}