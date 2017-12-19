/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.model.equipment;

/**
 * PROTOTYPE - Prototype
 * (possible/appropriate to implement using COMPOSITE design pattern)
 * @author nino
 */
public abstract class Equipment implements Cloneable
{
    private String fileRecord = null;
    
    protected String id = null;
    protected String name = null;

    public Equipment(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public String getFileRecord() { return fileRecord; }
    public void setFileRecord(String fileRecord) { this.fileRecord = fileRecord; }
    
    public String getId() { return id; }
    //private void setId(String id) { this.id = id; }

    public String getName() { return name; }
    //public void setName(String name) { this.name = name; }

    @Override
    public abstract Equipment clone();
}