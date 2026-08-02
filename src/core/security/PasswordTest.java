package core.security;

public class PasswordTest {

    public static void main(String[] args) {

        String password = "admin123";

        String hash = PasswordUtils.hash(password);

        System.out.println(hash);

        System.out.println(
                PasswordUtils.verify(password, hash)
        );

    }

}