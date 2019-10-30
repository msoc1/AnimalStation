package com.fixed4fun.android.animalstation.objects;

public class NewUser {
    String useremail;
    String ID;

    public NewUser(String useremail, String ID) {
        this.useremail = useremail;
        this.ID = ID;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
