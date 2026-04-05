package fp.examples.optional;

import java.util.Optional;

public class MainSimple {

    public static void main(String[] args) {

        String lang = findUser(1)
                .flatMap(user -> getPreferredLanguage(user))
                .orElse("en");

        System.out.println(lang);

    }

    private static Optional<String> getPreferredLanguage(User user) {
        // find out what user's language.
        // return Optional.empty() if missing

        return Optional.of("et");
    }

    private static Optional<User> findUser(Integer id) {
        // read user corresponding to id from database.
        // return Optional.empty() is missing

        return Optional.of(new User());
    }

    private static class User  {

    }

}
