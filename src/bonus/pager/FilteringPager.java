package bonus.pager;

import java.util.ArrayList;
import java.util.List;

public class FilteringPager {

    private final SimplePager dataSource;
    private final int pageSize;
    private int currentPage = 1;

    public FilteringPager(SimplePager dataSource, int pageSize) {
        this.dataSource = dataSource;
        this.pageSize = pageSize;
    }

    public List<String> getNextPage() {
        if (hasNextPage()) {
            currentPage++;
            return getCurrentPage();
        }
        throw new IllegalStateException("No Next Page.");
    }

    public List<String> getCurrentPage() {
        List<String> output = new ArrayList<>();
        List<String> data = dataSource.getData();

        int expectedBefore = (currentPage-1)*pageSize;
        int itemsSaved = 0;

        for (String str : data) {
            if (str == null) {
                continue;
            }
            if (expectedBefore != 0) {
                expectedBefore--;
                continue;
            }

            if (itemsSaved != pageSize) {
                itemsSaved++;
                output.add(str);
            }
        }

        return output;
    }

    public List<String> getPreviousPage() {
        if (hasPreviousPage()) {
            currentPage--;
            return getCurrentPage();
        }
        throw new IllegalStateException("No Previous Page.");
    }

    public boolean hasNextPage() {
        int itemCount = 0;
        for (String str : dataSource.getData()) {
            if (str != null) {
                itemCount++;
            }
        }
        return currentPage*pageSize < itemCount;
    }

    public boolean hasPreviousPage() {
        return currentPage > 1;
    }

    public int getCurrentPageNo() {
        return currentPage - 1;
    }
}
