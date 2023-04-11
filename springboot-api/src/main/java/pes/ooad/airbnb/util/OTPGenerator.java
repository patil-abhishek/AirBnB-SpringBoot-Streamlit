package pes.ooad.airbnb.util;
import java.util.Random;
public class OTPGenerator {
    static public Integer generateOTP(){
        Random rand = new Random();
        Integer g = rand.nextInt(899999) + 100000;
        return g;
    }
}
