package com.tutor.common;

import com.tutor.common.dto.FilterColumn;
import com.tutor.common.dto.SearchRequest;
import com.tutor.common.dto.SortColumn;
import com.tutor.common.dto.ResponseDataModel;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommonCriteria {

    public <E, D> ResponseDataModel<D> findAll(
            JpaSpecificationExecutor<E> repository,
            SearchRequest searchRequest,
            Function<E, D> mapper
    ) {
        Specification<E> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (searchRequest.getFilterList() != null && !searchRequest.getFilterList().isEmpty()) {
                for (FilterColumn filter : searchRequest.getFilterList()) {
                    if (filter.getColumnName() != null && filter.getValue() != null) {
                        switch (filter.getOperation().toUpperCase()) {
                            case "EQUAL":
                                predicates.add(criteriaBuilder.equal(root.get(filter.getColumnName()), filter.getValue()));
                                break;
                            case "LIKE":
                                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(filter.getColumnName())), "%" + filter.getValue().toLowerCase() + "%"));
                                break;
                            case "GT":
                                predicates.add(criteriaBuilder.greaterThan(root.get(filter.getColumnName()), filter.getValue()));
                                break;
                            case "LT":
                                predicates.add(criteriaBuilder.lessThan(root.get(filter.getColumnName()), filter.getValue()));
                                break;
                            // Add more operations as needed
                        }
                    }
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return preparePagingAndSorting(repository, searchRequest, mapper, spec);
    }

    private <E, D> ResponseDataModel<D> preparePagingAndSorting(JpaSpecificationExecutor<E> repository, SearchRequest searchRequest, Function<E, D> mapper, Specification<E> spec) {
        List<Sort.Order> orders = new ArrayList<>();
        if (searchRequest.getSortCriteria() != null && !searchRequest.getSortCriteria().isEmpty()) {
            for (SortColumn sortColumn : searchRequest.getSortCriteria()) {
                if (sortColumn.getColumnName() != null && sortColumn.getDirection() != null) {
                    Sort.Direction direction = Sort.Direction.fromString(sortColumn.getDirection());
                    orders.add(new Sort.Order(direction, sortColumn.getColumnName()));
                }
            }
        }
        Sort sort = orders.isEmpty() ? Sort.unsorted() : Sort.by(orders);

        Pageable pageable = PageRequest.of(searchRequest.getPage(), searchRequest.getSize(), sort);
        Page<E> entityPage = repository.findAll(spec, pageable);

        List<D> content = entityPage.getContent().stream().map(mapper).collect(Collectors.toList());

        return ResponseDataModel.<D>builder()
                .content(content)
                .numberOfRecords(entityPage.getTotalElements())
                .pageIndex(entityPage.getNumber())
                .pageSize(entityPage.getSize())
                .build();
    }
}
