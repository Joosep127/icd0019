package fp.examples.optional;

import java.util.Optional;

import static java.util.Optional.ofNullable;

public class MainLonger {

    public static void main(String[] args) {
        MainLonger main = new MainLonger();

        System.out.println(main.getLanguageUsingFlatmap(3L));
    }

    public String getLanguageUsingFlatmap(Long id) {
        return getUserById(id)
                .flatMap(user -> ofNullable(user.profile))
                .flatMap(profile -> ofNullable(profile.preferences))
                .flatMap(pref -> ofNullable(pref.language))
                .orElse("en");
    }

    public String getLanguageUsingIfs(Long id) {
        Optional<User> optionalUser = getUserById(id);

        return optionalUser.isPresent()
                && optionalUser.get().profile != null
                && optionalUser.get().profile.preferences != null
                && optionalUser.get().profile.preferences.language != null

                ? optionalUser.get().profile.preferences.language
                : "en";
    }

    private Optional<User> getUserById(Long id) {
        if (Long.valueOf(1L).equals(id)) { // sample user
            Preferences preferences = new Preferences();
            preferences.language = "et";
            Profile profile = new Profile();
            profile.preferences = preferences;
            User user = new User();
            user.profile = profile;

            return Optional.of(user);
        } else if (Long.valueOf(2L).equals(id)) { // sample user
            Profile profile = new Profile();
            User user = new User();
            user.profile = profile;

            return Optional.of(user);
        }

        return Optional.empty();
    }


}
