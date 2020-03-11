/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IvyMailer.Response;

import org.json.JSONObject;

/**
 *
 * @author Ivy
 */
public class SettingsResponse {
    
    public boolean enable_sms;
    public boolean enable_email;
    public String patch_notes;
    public boolean in_maintenance;
    public int maintenance_unix;
    public String maintenance;
    private Base response;
    
    public SettingsResponse(Base response){
        this.response = response;
        try{
        this.enable_sms = response.data.getBoolean("enable_sms");
        this.enable_email = response.data.getBoolean("enable_email");
        this.patch_notes = response.data.getString("patch_notes");
        this.in_maintenance = response.data.getBoolean("in_maintenance");
        this.maintenance_unix = response.data.getInt("maintenance_unix");
        this.maintenance = response.data.getString("maintenance");
        }catch(Exception e){}
    }

    @Override
    public String toString() {
        return "IndexResponse: " + response.toString();
    }
    
    
}
