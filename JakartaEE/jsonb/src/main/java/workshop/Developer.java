package workshop;

import javax.json.bind.annotation.JsonbPropertyOrder;

/**
 * @author Subodh Kumar
 */
@JsonbPropertyOrder(value = {"exceptionType", "reportFilePath", "viewName", "searchCriteria"})
public class Developer {

    private String exceptionType;
    private String reportFilePath;
    private String viewName;
    private SearchCriteria searchCriteria;

    public Developer() {
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getReportFilePath() {
        return reportFilePath;
    }

    public void setReportFilePath(String reportFilePath) {
        this.reportFilePath = reportFilePath;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public SearchCriteria getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public String toString() {
        return "Developer{" + "exceptionType=" + exceptionType + ", reportFilePath=" + reportFilePath + ", viewName=" + viewName + ", searchCriteria=" + searchCriteria + '}';
    }
}
