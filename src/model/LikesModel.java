package model;

import java.util.ArrayList;
import java.util.Arrays;

public class LikesModel {

	private String pageID;
    private ArrayList<String> userID;

    public LikesModel(String pageID, ArrayList<String> userID) {
        this.pageID = pageID;
        this.userID = userID;
    }

    public String getPageID() {
        return pageID;
    }

    public void setPageID(String pageID) {
        this.pageID = pageID;
    }

    public ArrayList<String> getUserID() {
        return userID;
    }

    public void setUserID(ArrayList<String> userID) {
        this.userID = userID;
    }
    
}
