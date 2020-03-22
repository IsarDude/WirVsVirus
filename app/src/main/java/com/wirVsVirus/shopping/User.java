package com.wirVsVirus.shopping;



public class User {

    private Store store;
    private String email;
    private String name;
    private String phone;

    public User() {

    }

    public boolean claimStore(Integer plz, String str) {

        if(!store.isClaimed()) {
            store.setOwner(this);
        }

        return false;
    }

    public void setActivity(int i) {
        store.setActivity(i);
    }


}

