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
public class SpecialtyNightDive extends Specialty
{
    public static final String title = "Noćno ronjenje";
    public static final String requiredEquipmentCategory = EquipmentCategory.Id.LIGHT;

    public SpecialtyNightDive()
    {
        this.setTitle(SpecialtyNightDive.title);
        this.setRequiredEquipmentCategory(SpecialtyNightDive.requiredEquipmentCategory);
    }
    
    //methods specific for NightDive specialty
}