package com.ga.mapper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.jpa.domain.Specification;

import com.ga.persistence.entity.Group;

public final class GroupSpecifications {

	private GroupSpecifications() {
	}

	public static Specification<Group> hasGroupCriteria(final String searchTerm,final SingularAttribute<Group, String> term) {

		System.out.println(searchTerm);
		return new Specification<Group>() {

			String containsLikePattern = searchTerm;

			@Override
			public Predicate toPredicate(Root<Group> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				return cb.like(root.<String> get(term), "%"
						+ containsLikePattern + "%");

			}

		};

	}

}
