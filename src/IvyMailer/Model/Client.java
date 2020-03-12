/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IvyMailer.Model;

import java.util.Vector;
import org.json.JSONObject;

/**
 *
 * @author Ivy
 */
public class Client {
    public String id, id_group, group_name, name, denomination, phone, created_at;
    public Vector<ClientMail> mails;
    
    public Client(){
    this.mails = new Vector<ClientMail>();
    }
    
    public Client(String id, String id_group, String group_name, String name, String phone, String mail, String created_at){
        this.id = id;
        this.id_group = id_group;
        this.group_name = group_name;
        this.name = name;
        this.denomination = name;
        this.phone = phone;
        this.created_at = created_at;
        ClientMail e = new ClientMail("1", mail);
        this.mails = new Vector<ClientMail>();
        this.mails.add(e);
    }
}
