package pl.com.rbo.clinic;

import java.time.LocalDate;

/**
 * Created by rafal on 29.04.19.
 */

public class SearchCriteriaTestStory {

    private SearchCriteria criteria;

    private SearchCriteriaTestStory(SearchCriteria criteria){
        this.criteria = criteria;
    }

    public static SearchCriteriaTestStory With(Doctor doctor){
        return new SearchCriteriaTestStory(new SearchCriteria(doctor));
    }

    public static SearchCriteriaTestStory With(Specialty specialty){
        return new SearchCriteriaTestStory(new SearchCriteria(specialty));
    }

    public SearchCriteriaTestStory from(LocalDate date){
        criteria.setStartDate(date);
        return this;
    }

    public SearchCriteria get(){
        return criteria;
    }
}
