package utils;

import models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UserUtils {
    public static boolean checkAuthUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        return user != null;
    }

    public static String buildHash(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest);
    }

    public static User findUserByIdOrNull(List<User> users, String userID) {
        for(User user : users){
            if (user.getId().toString().equals(userID))
                return user;
        }
        return null;
    }

    public static User findUserByLoginOrNull(List<User> users, String userLogin) {
        for(User user : users){
            if (user.getLogin().equals(userLogin))
                return user;
        }
        return null;
    }
}
