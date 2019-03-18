/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dto;

/**
 *
 * @author Simon Bojesen
 */
public class ZipDTO {
    private String zip;
    private String cityname;

    public ZipDTO(String zip, String cityname) {
        this.zip = zip;
        this.cityname = cityname;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }
}
