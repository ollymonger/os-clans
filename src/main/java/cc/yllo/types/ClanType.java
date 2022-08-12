package cc.yllo.types;

import java.util.HashMap;

import cc.yllo.main;


public class ClanType {
    public String name;
    public String uuid;
    public String tag;
    // Interger: type, 0 - leader, 1 - member, String: uuid
    public HashMap<String, Integer> members;

    public ClanType(String name, String uuid, String tag, HashMap<String, Integer> members) {
        this.name = name;
        this.uuid = uuid;
        this.tag = tag;
        this.members = members;
    }
}
