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
public class SpecialtyNightDive extends Specialty
{
    public static final String title = "Noćno ronjenje";
    public static final String requiredEquipmentCategory = "5.1";

    public SpecialtyNightDive()
    {
        this.setTitle(SpecialtyNightDive.title);
        this.setRequiredEquipmentCategory(SpecialtyNightDive.requiredEquipmentCategory);
    }
    
    //methods specific for NightDive specialty
}