package workshop;

import java.util.Map;
import javax.json.bind.annotation.JsonbProperty;

/**
 * @author Subodh Kumar
 */
public class SearchCriteria {

    @JsonbProperty(value = "ex_status")
    private Map<String, String> exStatus;

    public SearchCriteria() {
    }

    public Map<String, String> getExStatus() {
        return exStatus;
    }

    public void setExStatus(Map<String, String> exStatus) {
        this.exStatus = exStatus;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" + "exStatus=" + exStatus + '}';
    }
}
