package com.wirVsVirus.shopping;


import java.io.Serializable;

public class Store implements Serializable{

    private final String zentrale;
    private final String region;
    private final String partnerfirma;
    private final String regie;
    private final String markttyp;
    private final String geschaeftsart;
    private final String vkf;
    private final String mitarbeiter;
    private final String inhaber;
    private final int plz;
    private final String ort;
    private final String stadtteil;
    private final String bundesland;
    private final String kreis;
    private final String nielsen;
    private final String strasse;
    private final String hausnr;
    private final String fon;
    private final String fax;
    private final String email;
    private final String url;
    private final String index;
    private final String closing;
    private final String anlage;
    private final String ehastra;
    private final String anzahl_von_verwaltung;
    private final double x;
    private final double y;

    private int activity = 0;
    private boolean claimed = false;
    private User owner;

    public Store(String string, String string2, String string3, String string4, String string5, String string6,
                 String string7, String string8, String string9, String string10, String string11, String string12,
                 String string13, String string14, String string15, String string16, String string17, String string18,
                 String string19, String string20, String string21, String string22, String string23, String string24,
                 String string25, String string26, String string27, String string28) {

        zentrale = string;
        region = string2;
        partnerfirma = string3;
        regie = string4;
        markttyp = string5;
        geschaeftsart = string6;
        vkf = string7;
        mitarbeiter = string8;
        inhaber = string9;
        plz = Integer.parseInt(string10);
        ort = string11;
        stadtteil = string12;
        bundesland = string13;
        kreis = string14;
        nielsen = string15;
        strasse = string16;
        hausnr = string17;
        fon = string18;
        fax = string19;
        email = string20;
        url = string21;
        index = string22;
        closing = string23;
        anlage = string24;
        ehastra = string25;
        anzahl_von_verwaltung = string26;
        x = Double.parseDouble(string27);
        y = Double.parseDouble(string28);

    }

    public int getPlz() {
        return plz;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean isClaimed() {
        return claimed;
    }

    public String getMarkttyp() {
        return markttyp;
    }

    public String getOrt() {
        return ort;
    }

    public String getStrasse() {
        return strasse;
    }

    public String getHausnr() {
        return hausnr;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int i) {
        this.activity = i;
    }

    public void claim(User user) {
        claimed = true;
    }
}



