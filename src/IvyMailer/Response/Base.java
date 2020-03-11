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
public class Base {
    
    public boolean status;
    public String message;
    public JSONObject data;
    public Object raw;
    private String response;
    
    public Base(){
     status = false;
     message = "";
     data = null;
     response = null;
    }
    
    public Base(String response) throws Exception{
        this.response = response;
        
        JSONObject json = new JSONObject(response);
        
        this.status = json.getBoolean("status");
        this.message = json.getString("message");
        try{
        this.data = this.status ? json.getJSONObject("data") : null;
        }catch(Exception e){}
        this.raw = json.get("data");
        
        if (!this.status){
            throw new Exception(this.message);
        }
    }

    @Override
    public String toString() {
        return response;
    }
    
    
}
