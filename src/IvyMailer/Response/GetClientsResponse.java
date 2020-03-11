/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IvyMailer.Response;

import IvyMailer.Model.Client;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Ivy
 */
public class GetClientsResponse {

    public Client items[];
    public int first, before, previous, current, last, next, total_pages, total_items, limit;

    private final Base response;

    public GetClientsResponse(Base response) throws Exception {
        this.response = response;
        this.first = response.data.getInt("first");
        this.before = response.data.getInt("before");
        this.previous = response.data.getInt("previous");
        this.current = response.data.getInt("current");
        this.last = response.data.getInt("last");
        this.next = response.data.getInt("next");
        this.total_pages = response.data.getInt("total_pages");
        this.total_items = response.data.getInt("total_items");
        this.limit = response.data.getInt("limit");
        JSONArray rows = response.data.getJSONArray("items");
        this.items = new Client[rows.length()];
        for (int i = 0; i < rows.length(); i++) {
            // String id, String id_group, String name, String phone, String mail
            JSONObject row = rows.getJSONObject(i);
            String id = row.getString("id");
            String id_group = row.getString("id_group");
            String group_name = row.getString("group_name");
            String name = row.getString("name");
            String phone = row.optString("phone", null);
            String created_at = row.optString("created_at", null);
            JSONArray mails = row.getJSONArray("mails");
            String mail = mails.length() > 0 ? mails.getJSONObject(0).getString("data") : "";
            items[i] = new Client(id, id_group, group_name, name, phone, mail, created_at);
        }
    }

    @Override
    public String toString() {
        return "GetClientsResponse: " + response.toString();
    }
}
