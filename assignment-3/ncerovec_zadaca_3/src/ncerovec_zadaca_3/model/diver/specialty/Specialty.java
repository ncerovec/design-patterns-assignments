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
public abstract class Specialty
{
    private String title = null;
    private String requiredEquipmentCategory = null;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getRequiredEquipmentCategory() { return requiredEquipmentCategory; }
    public void setRequiredEquipmentCategory(String requiredEquipmentCategory) { this.requiredEquipmentCategory = requiredEquipmentCategory; }
}
