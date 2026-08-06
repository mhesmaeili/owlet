package com.owlet.api.repository.specification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {
    private String key;             // نام فیلد (مثلاً "session.id" یا "point")
    private SearchOperation operation; // نوع عملگر (مثلاً EQUAL یا IN)
    private Object value;           // مقدار (مثلاً UUID یا یک List برای IN)
}