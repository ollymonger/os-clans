package cc.yllo.types;

import java.util.HashMap;


public class ClanType {
    public String name;
    public String uuid;
    public String tag;
    // Interger: type, 0 - leader, 1 - member, String: uuid
    public HashMap<Integer, String> members;

    public ClanType(String name, String uuid, String tag) {
        this.name = name;
        this.uuid = uuid;
        this.tag = tag;
        this.members = new HashMap<Integer, String>();
    }
}
