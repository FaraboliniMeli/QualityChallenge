package br.com.marcello.QualityChallenge.dto;

public class HomePriceResponse {

    private HomeDto home;
    private DistrictDto district;
    private Double homePrice;

    public HomeDto getHome() {
        return home;
    }

    public void setHome(HomeDto home) {
        this.home = home;
    }

    public DistrictDto getDistrict() {
        return district;
    }

    public void setDistrict(DistrictDto district) {
        this.district = district;
    }

    public Double getHomePrice() {
        return homePrice;
    }

    public void setHomePrice(Double homePrice) {
        this.homePrice = homePrice;
    }
}
