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

        }

        return false;
    }

}

