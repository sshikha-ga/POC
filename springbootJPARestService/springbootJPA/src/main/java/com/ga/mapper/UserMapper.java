package com.ga.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ga.persistence.entity.Group;
import com.ga.persistence.entity.User;
import com.ga.persistence.entity.UserGroup;
import com.ga.persistence.entity.UserGroup_;

@Repository
@Transactional
public class UserMapper implements IUsermapper {

    @PersistenceContext
    private EntityManager em;

    public List<UserGroup> getUser() {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        CriteriaQuery<UserGroup> cq = criteriaBuilder.createQuery(UserGroup.class);
        Root<UserGroup> ug = cq.from(UserGroup.class);
        Join<UserGroup, User> user = ug.join(UserGroup_.user);
        cq.select(ug);

        CriteriaQuery<UserGroup> select = cq.select(ug);
        Subquery<Integer> subquery = cq.subquery(Integer.class);
        Root fromGroup = subquery.from(Group.class);
        subquery.select(fromGroup.get("GROUP_ID"));

        Predicate p = criteriaBuilder.equal(fromGroup.get("groupName"), "AGENT");
        Predicate p1 = criteriaBuilder.equal(fromGroup.get("groupName"), "MANAGER");

        Predicate p2 = criteriaBuilder.or(p, p1);

        subquery.where(p2);

        cq.where(ug.get("group").get("GROUP_ID").in(subquery));

        TypedQuery<UserGroup> typedQuery = em.createQuery(cq);
        List<UserGroup> resultList = typedQuery.getResultList();

        return resultList;

    }

    @Override
    public List<UserGroup> findActiveUsers() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        CriteriaQuery<UserGroup> cq = criteriaBuilder.createQuery(UserGroup.class);
        Root<UserGroup> ug = cq.from(UserGroup.class);
        Join<UserGroup, User> user = ug.join(UserGroup_.user);
        cq.select(ug).distinct(true);

        CriteriaQuery<UserGroup> select = cq.select(ug);
        Subquery<Integer> subquery = cq.subquery(Integer.class);
        Root fromGroup = subquery.from(Group.class);
        subquery.select(fromGroup.get("GROUP_ID"));

        Predicate p = criteriaBuilder.equal(fromGroup.get("groupName"), "BOOKER");
        Predicate p1 = criteriaBuilder.equal(fromGroup.get("groupName"), "AGENT");

        Predicate p2 = criteriaBuilder.equal(user.get("status"), "A");

        Predicate p3 = criteriaBuilder.or(p, p1);

        Predicate p4 = criteriaBuilder.and(p3, p2);

        subquery.where(p4);

        cq.where(ug.get("group").get("GROUP_ID").in(subquery)).distinct(true);

        TypedQuery<UserGroup> typedQuery = em.createQuery(cq);
        List<UserGroup> resultList = typedQuery.getResultList();

        return resultList;
    }

    @Override
    public List<User> findUserCriterias(List<String[]> arrayList, String clause) {

        List<Predicate> predicateList = new ArrayList<Predicate>();
        Predicate p5;

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = criteriaBuilder.createQuery(User.class);
        Root<User> u = cq.from(User.class);

        if (clause.equalsIgnoreCase("or")) {

            for (String[] array : arrayList) {

                if (array[0].equalsIgnoreCase("firstName")) {
                    predicateList.add(criteriaBuilder.or(criteriaBuilder.equal(u.get("firstName"), array[1])));
                } else if (array[0].equalsIgnoreCase("lastName")) {
                    predicateList.add(criteriaBuilder.or(criteriaBuilder.equal(u.get("lastName"), array[1])));
                } else if (array[0].equalsIgnoreCase("email")) {
                    predicateList.add(criteriaBuilder.or(criteriaBuilder.equal(u.get("email"), array[1])));
                } else if (array[0].equalsIgnoreCase("status")) {
                    predicateList.add(criteriaBuilder.or(criteriaBuilder.equal(u.get("status"), array[1])));
                } else if (array[0].equalsIgnoreCase("userId")) {
                    predicateList.add(criteriaBuilder.or(criteriaBuilder.equal(u.get("userId"), array[1])));
                }
            }

            Predicate p = criteriaBuilder.disjunction();
            p = criteriaBuilder.or(predicateList.toArray(new Predicate[predicateList.size()]));
            cq.where(p);

        } else {
            for (String[] array : arrayList) {

                if (array[0].equalsIgnoreCase("firstName")) {
                    predicateList.add(criteriaBuilder.equal(u.get("firstName"), array[1]));
                } else if (array[0].equalsIgnoreCase("lastName")) {
                    predicateList.add(criteriaBuilder.equal(u.get("lastName"), array[1]));
                } else if (array[0].equalsIgnoreCase("email")) {
                    predicateList.add(criteriaBuilder.equal(u.get("email"), array[1]));
                } else if (array[0].equalsIgnoreCase("status")) {
                    predicateList.add(criteriaBuilder.equal(u.get("status"), array[1]));
                } else if (array[0].equalsIgnoreCase("userId")) {
                    predicateList.add(criteriaBuilder.equal(u.get("userId"), array[1]));
                }
            }

            cq.where(predicateList.toArray(new Predicate[predicateList.size()]));
        }

        TypedQuery<User> typedQuery = em.createQuery(cq);
        List<User> resultList = typedQuery.getResultList();

        return resultList;
    }

    @Override
    public List<UserGroup> findUserGroupCriterias(List<String[]> arrayList, String clause) {

        List<Predicate> predicateList = new ArrayList<Predicate>();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        CriteriaQuery<UserGroup> cq = criteriaBuilder.createQuery(UserGroup.class);
        Root<UserGroup> ug = cq.from(UserGroup.class);
        Join<UserGroup, User> user = ug.join(UserGroup_.user);
        cq.select(ug).distinct(true);

        CriteriaQuery<UserGroup> select = cq.select(ug);
        Subquery<Integer> subquery = cq.subquery(Integer.class);
        Root fromGroup = subquery.from(Group.class);
        subquery.select(fromGroup.get("GROUP_ID"));

        if (clause.equalsIgnoreCase("or")) {

            for (String[] array : arrayList) {

                if (array[0].equalsIgnoreCase("firstName")) {
                    predicateList.add(criteriaBuilder.or(criteriaBuilder.equal(user.get("firstName"), array[1])));
                } else if (array[0].equalsIgnoreCase("lastName")) {
                    predicateList.add(criteriaBuilder.or(criteriaBuilder.equal(user.get("lastName"), array[1])));
                } else if (array[0].equalsIgnoreCase("email")) {
                    predicateList.add(criteriaBuilder.or(criteriaBuilder.equal(user.get("email"), array[1])));
                } else if (array[0].equalsIgnoreCase("status")) {
                    predicateList.add(criteriaBuilder.or(criteriaBuilder.equal(user.get("status"), array[1])));
                } else if (array[0].equalsIgnoreCase("userId")) {
                    predicateList.add(criteriaBuilder.or(criteriaBuilder.equal(user.get("userId"), array[1])));
                } else if (array[0].equalsIgnoreCase("groupName")) {
                    predicateList.add(criteriaBuilder.or(criteriaBuilder.equal(fromGroup.get("groupName"), array[1])));
                } else if (array[0].equalsIgnoreCase("GROUP_ID")) {
                    predicateList.add(criteriaBuilder.or(criteriaBuilder.equal(fromGroup.get("GROUP_ID"), array[1])));
                }
            }

            Predicate p = criteriaBuilder.disjunction();
            p = criteriaBuilder.or(predicateList.toArray(new Predicate[predicateList.size()]));
            subquery.where(p);
        } else {
            for (String[] array : arrayList) {

                if (array[0].equalsIgnoreCase("firstName")) {
                    predicateList.add(criteriaBuilder.equal(user.get("firstName"), array[1]));
                } else if (array[0].equalsIgnoreCase("lastName")) {
                    predicateList.add(criteriaBuilder.equal(user.get("lastName"), array[1]));
                } else if (array[0].equalsIgnoreCase("email")) {
                    predicateList.add(criteriaBuilder.equal(user.get("email"), array[1]));
                } else if (array[0].equalsIgnoreCase("status")) {
                    predicateList.add(criteriaBuilder.equal(user.get("status"), array[1]));
                } else if (array[0].equalsIgnoreCase("userId")) {
                    predicateList.add(criteriaBuilder.equal(user.get("userId"), array[1]));
                } else if (array[0].equalsIgnoreCase("groupName")) {
                    predicateList.add(criteriaBuilder.equal(fromGroup.get("groupName"), array[1]));
                } else if (array[0].equalsIgnoreCase("GROUP_ID")) {
                    predicateList.add(criteriaBuilder.equal(fromGroup.get("GROUP_ID"), array[1]));
                }
            }

            subquery.where(predicateList.toArray(new Predicate[predicateList.size()]));
        }

        cq.where(ug.get("group").get("GROUP_ID").in(subquery)).distinct(true);

        TypedQuery<UserGroup> typedQuery = em.createQuery(cq);
        List<UserGroup> resultList = typedQuery.getResultList();

        return resultList;

    }
}
