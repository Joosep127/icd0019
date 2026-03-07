package exceptions.basic;

import java.io.IOException;

/**
 * This is a class for simulating file manipulation.
 * No need to change this.
 */
public class FakeFile {

    private boolean isOpen = false;
    private boolean throwOnRead = false;
    private String data;

    public void open() {
        isOpen = true;
    }

    public String read() throws IOException {
        if (!isOpen) {
            throw new IOException("resource is not open");
        }

        if (throwOnRead) {
            throw new IOException("error on reading info");
        }

        return data;
    }

    public void close() {
        isOpen = false;
    }

    public boolean isClosed() {
        return !isOpen;
    }


    public FakeFile throwOnRead() {
        throwOnRead = true;
        return this;
    }

    public FakeFile setData(String data) {
        this.data = data;
        return this;
    }
}
