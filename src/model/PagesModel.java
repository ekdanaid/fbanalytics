package model;

public class PagesModel {
    private String pageID;
    private String name;

    public PagesModel(String pageID, String name) {
        this.pageID = pageID;
        this.name = name;
    }

    public String getPageID() {
        return pageID;
    }

    public void setPageID(String pageID) {
        this.pageID = pageID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
