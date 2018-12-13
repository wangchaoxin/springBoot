package com.wcx.springboot.demo.midware.json.gson.excludefield;

public class MySubClass {
    private long id;
    /**
     * 1 transient
     * While this is very fast, it also comes with a severe downside: every serialization tool will take transient into account,
     * not only Gson.
     */
    private transient String description;
    private String otherVerboseInfo;

    public MySubClass(long id, String description, String otherVerboseInfo) {
        this.id = id;
        this.description = description;
        this.otherVerboseInfo = otherVerboseInfo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOtherVerboseInfo() {
        return otherVerboseInfo;
    }

    public void setOtherVerboseInfo(String otherVerboseInfo) {
        this.otherVerboseInfo = otherVerboseInfo;
    }
}
