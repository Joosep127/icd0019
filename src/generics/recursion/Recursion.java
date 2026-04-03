package generics.recursion;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Recursion {

    public List<String> getParts(Path path) {

        Path temp = path.getParent();
        List<String> output = new ArrayList<String>();

        for (int i = 0; i < path.getNameCount(); i++) {
            output.add(path.getName(i).toString());
        }

        return output;
    }

    public List<String> getParts2(Path path) {
        List<String> result = new ArrayList<>();

        if (path == null) {
            return result;
        }

        if (path.getParent() != null) {
            result.addAll(getParts2(path.getParent()));
        }

        if (path.getFileName() != null) {
            result.add(path.getFileName().toString());
        }

        return result;

    }

    public List<String> getParts3(Path path, List<String> parts) {

        parts.addAll(getParts2(path));

        return parts;
    }

    public List<String> getParts4(Path path) {

        return getParts2(path);
    }
}
