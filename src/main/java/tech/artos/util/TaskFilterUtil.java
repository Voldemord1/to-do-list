package tech.artos.util;

import org.springframework.stereotype.Component;

@Component
public class TaskFilterUtil {

    private boolean filtered;

    public boolean isFiltered() {
        return filtered;
    }

    public void setFiltered(boolean filtered) {
        this.filtered = filtered;
    }
}
