package courier;
public class CourierData {
    public static final Courier defaultCourier =  new Courier("TequilaSunset", "doloresdei", "Harrier");

    public static Courier sameLogin (){
        return new Courier("TequilaSunset","myfavoritecocktail","AnotherHarrier");
    }

    public static Courier withoutLogin (){
        return new Courier(null,"doloresdei","Harrier");
    }

    public static Courier incorrectCredentials(){
        return new Courier("TequilaSunrise", "doloresdei", "Harrier");
    }

    }

