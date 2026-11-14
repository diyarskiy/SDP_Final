package adapter;

public class LegacyFlightService {
    public String[] loadOffers() {
        // id, Название, Направление, Цена, длительность полета
        return new String[] {
                "TB101;Air Astana;Almaty;Astana;25000;105",
                "TB205;Fly Arystan;Almaty;Astana;32000;90",
                "TB1488;SCAT;Astana;Almaty;35000;120",
                "TB012;Air Astana;Astana;Almaty;45000;80"
        };
    }
}
