/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IvyMailer.Response;

/**
 *
 * @author Ivy
 */
public class TokenResponse {
    
    public String uid, customer_id, id, name, type;
    public int vigency;
    public boolean requires_tfa, sudo;
    private Base response;
    
    public TokenResponse(Base response) throws Exception{
        this.response = response;
        if (!response.status){
            throw new Exception("TOKEN INVALIDO.");
        }
        this.uid = response.data.getString("uid");
        this.customer_id = response.data.getString("customer_id");
        this.id = response.data.getString("id");
        this.name = response.data.getString("name");
        this.type = response.data.getString("type");
        this.vigency = response.data.getInt("vigency");
        this.requires_tfa = response.data.getBoolean("requires_tfa");
        this.sudo = response.data.getBoolean("sudo");
    }

    @Override
    public String toString() {
        return "TokenResponse: " + response.toString();
    }
}
