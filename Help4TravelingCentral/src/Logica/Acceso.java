/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author yaman
 */
public class Acceso {
    private int num;
    private String IP;
    private String URL;
    private String Browse;
    private String SO;

    public Acceso() {
    }

    public Acceso(int num, String IP, String URL, String Browse, String SO) {
        this.num = num;
        this.IP = IP;
        this.URL = URL;
        this.Browse = Browse;
        this.SO = SO;
    }

    public int getNum() {
        return num;
    }

    public String getIP() {
        return IP;
    }

    public String getURL() {
        return URL;
    }

    public String getBrowse() {
        return Browse;
    }

    public String getSO() {
        return SO;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setBrowse(String Browse) {
        this.Browse = Browse;
    }

    public void setSO(String SO) {
        this.SO = SO;
    }
    
    
}
