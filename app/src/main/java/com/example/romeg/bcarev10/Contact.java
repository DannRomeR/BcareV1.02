package com.example.romeg.bcarev10;

/**
 * Created by romeg on 25/09/2017.
 */

public class Contact {

    int tel, cont1, cont2, edad, presu, punt, risk;
    String name, email, uname, pass, genero, fum, med, colt,colh;


    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getEmail()
    {
        return this.email;
    }

    public void setUname(String uname)
    {
        this.uname = uname;
    }
    public String getUname()
    {
        return this.uname;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }
    public String getPass()
    {
        return this.pass;
    }

    public void setTel(int tel)
    {
        this.tel = tel;
    }
    public int getTel()
    {
        return this.tel;
    }

    public void setEdad(int edad)
    {
        this.edad = edad;
    }
    public int getEdad()
    {
        return this.edad;
    }

    public void setCont1(int cont1)
    {
        this.cont1 = cont1;
    }
    public int getCont1()
    {
        return this.cont1;
    }

    public void setCont2(int cont2)
    {
        this.cont2 = cont2;
    }
    public int getCont2()
    {
        return this.cont2;
    }

    public void setGenero(String genero)
    {
        this.genero = genero;
    }
    public String getGenero()
    {
        return this.genero;
    }

    public void setFum(String fum){
        this.fum = fum;
    }
    public String getFum(){
        return this.fum;
    }

    public void setMed(String med){
        this.med = med;
    }
    public String getMed(){return this.med;}

    public void setColt(String colt){
        this.colt = colt;
    }
    public String getColt(){
        return this.colt;
    }

    public void setColh(String colh){
        this.colh = colh;
    }
    public String getColh(){
        return this.colh;
    }

    public void setPresu(int presu){
        this.presu = presu;
    }
    public int getPresu(){
        return this.presu;
    }

    public void setPunt(int punt){
        this.punt = punt;
    }
    public int getPunt(){
        return this.punt;
    }

    public void setRisk(int risk){
        this.risk = risk;
    }
    public int getRisk(){
        return  this.risk;
    }
}
