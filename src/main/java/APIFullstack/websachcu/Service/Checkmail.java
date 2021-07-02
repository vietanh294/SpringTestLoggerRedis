package APIFullstack.websachcu.Service;

public class Checkmail {
    public static boolean checkmail(String email){
        Boolean result = email.endsWith("@gmail.com");
        return result;
    }
}
