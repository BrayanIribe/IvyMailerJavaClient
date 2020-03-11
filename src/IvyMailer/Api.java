/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IvyMailer;

import IvyMailer.Model.ClientMail;
import org.json.*;
import IvyMailer.Response.*;
import java.util.Vector;

/**
 *
 * @author Ivy
 */
public class Api {
    public SettingsResponse settings;
    
    public Api() throws Exception{
        Http.host = "https://api.ivysoftware.com.mx";
    }
    
    public void setToken(String token){
        Http.token = token;
    }
    
    public SettingsResponse getSettings() throws Exception{
        return new SettingsResponse(Http.request("/", null));
    }
    
    public TokenResponse checkToken() throws Exception{
        return new TokenResponse(Http.request("/auth/check", null));
    }
    
    public GetClientsResponse getClients(String keyword, int page) throws Exception{
        JSONObject request = new JSONObject();
        request.put("keyword", keyword == null ? "" : keyword);
        request.put("page", page);
        System.out.println(request.toString());
        return new GetClientsResponse(Http.request("/clients", request.toString()));
    }
    
    public int SaveClient(String id, String id_group, String name, String denomination, String phone, Vector<ClientMail> mails) throws Exception{
        JSONObject request = new JSONObject();
        request.put("id", id);
        request.put("id_group", id_group);
        request.put("name", name);
        request.put("denomination", denomination);
        request.put("phone", phone);
        request.put("mails", mails);
        System.out.println(request.toString());
        Base response = Http.request("/clients/create-update", request.toString());
        return Integer.valueOf((String)response.raw);
    }
    
    public Base SendMessageRaw(String id_group, String title, String altbody, String body) throws Exception{
        String request;
        request= "{\"id_clients\":[],\"id_group\":\"##ID_GROUP\",\"title\":\"##TITLE\",\"altbody\":\"##ALTBODY\",\"cells\":[{\"type\":\"row\",\"params\":{\"block\":true,\"color\":\"#007bff\",\"bgcolor\":\"#ffffff\"},\"cells\":[{\"type\":\"image\",\"params\":{\"url\":\"ivy://logo\",\"raw\":\"ivy://logo\",\"href\":null,\"alt\":null,\"align\":\"left\",\"bgcolor\":\"#ffffff\",\"full\":false,\"logo\":false}}]},{\"type\":\"row\",\"params\":{\"block\":true,\"color\":\"#007bff\",\"bgcolor\":\"#ffffff\"},\"cells\":[{\"type\":\"text\",\"params\":{\"body\":\"##BODY\"}}]}],\"variables\":[],\"attachments\":[],\"is_sms\":false}";
        request = request.replaceAll("##ID_GROUP", id_group);
        request = request.replaceAll("##TITLE", title);
        request = request.replaceAll("##ALTBODY", altbody);
        request = request.replaceAll("##BODY",body);
        System.out.println(request);
        return Http.request("/mailer/send", request);
    }
}
