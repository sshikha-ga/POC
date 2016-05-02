package com.ga.mapper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.jpa.domain.Specification;

import com.ga.persistence.entity.UserGroup;

public final class UserGroupSpecifications {

	private UserGroupSpecifications() {
	}

	public static Specification<UserGroup> hasGroupCriteria(final String searchTerm,final SingularAttribute<UserGroup, String> term) {

		System.out.println(searchTerm);
		return new Specification<UserGroup>() {

			String containsLikePattern = searchTerm;

			@Override
			public Predicate toPredicate(Root<UserGroup> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				return cb.like(root.<String> get(term), "%"
						+ containsLikePattern + "%");

			}

		};

	}

}
