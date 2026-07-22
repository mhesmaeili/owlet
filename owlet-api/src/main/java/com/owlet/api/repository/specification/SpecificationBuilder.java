package com.owlet.api.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


public final class SpecificationBuilder {


    private SpecificationBuilder() {
    }


    public static <T> Specification<T> contains(
            String keyword,
            String... fields) {


        return (root, query, cb) -> {


            if (keyword == null ||
                    keyword.isBlank()) {

                return cb.conjunction();
            }


            List<Predicate> predicates =
                    new ArrayList<>();


            for (String field : fields) {

                predicates.add(
                        cb.like(
                                cb.lower(
                                        root.get(field)
                                ),
                                "%" +
                                        keyword.toLowerCase()
                                        +
                                        "%"
                        )
                );

            }


            return cb.or(
                    predicates.toArray(
                            new Predicate[0]
                    )
            );

        };

    }

}