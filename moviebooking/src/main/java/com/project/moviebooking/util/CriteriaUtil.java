//package com.project.moviebooking.util;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CompoundSelection;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Expression;
//import javax.persistence.criteria.Order;
//import javax.persistence.criteria.Path;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import javax.persistence.criteria.Selection;
//
//import com.daynilgroup.feemanager.constants.Constants;
//import com.daynilgroup.feemanager.entity.mappedclass.BaseEntity;
//import com.daynilgroup.feemanager.entity.mappedclass.BaseEntity_;
//import com.daynilgroup.feemanager.model.PageModel;
//import com.daynilgroup.feemanager.model.PaginationRequestModel;
//import com.daynilgroup.feemanager.util.inf.CriteriaHelper;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.StringUtils;
//
//
//@Component
//public class CriteriaUtil implements CriteriaHelper {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public void addDefaultPredicates(CriteriaBuilder criteriaBuilder, List<Predicate> predicates,
//            Root<? extends BaseEntity> root) {
//        predicates.add(criteriaBuilder.or(criteriaBuilder.isFalse(root.get(BaseEntity_.DELETED)),
//                criteriaBuilder.isNull(root.get(BaseEntity_.DELETED))));
//    }
//
//    public void addDefaultPredicates(CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Path<?> root) {
//        predicates.add(criteriaBuilder.or(criteriaBuilder.isFalse(root.get(BaseEntity_.DELETED)),
//                criteriaBuilder.isNull(root.get(BaseEntity_.DELETED))));
//    }
//
//    public List<Predicate> getDefaultPredicates(CriteriaBuilder criteriaBuilder, Path<?> root) {
//        List<Predicate> predicates = new ArrayList<>();
//        addDefaultPredicates(criteriaBuilder, predicates, root);
//        return predicates;
//    }
//
//    public List<Predicate> getDefaultPredicates(CriteriaBuilder criteriaBuilder, List<Predicate> predicates,
//            Root<? extends BaseEntity> root) {
//        predicates.add(criteriaBuilder.or(criteriaBuilder.isFalse(root.get(BaseEntity_.DELETED)),
//                criteriaBuilder.isNull(root.get(BaseEntity_.DELETED))));
//        return predicates;
//    }
//
//    public void addPredicates(CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Expression expression,
//            String value) {
//        if (StringUtils.hasText(value)) {
//            predicates.add(criteriaBuilder.like(expression, "%" + value + "%"));
//        }
//    }
//
//    public void addPredicates(CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Expression expression,
//            Long value) {
//        if (value != null) {
//            predicates.add(criteriaBuilder.equal(expression, value));
//        }
//    }
//
//    public void addPredicates(CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Expression expression,
//            List<Long> list) {
//        if (!CollectionUtils.isEmpty(list)) {
//            predicates.add(criteriaBuilder.in(expression).value(list));
//        }
//    }
//
//    public void addPredicates(CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Expression expression,
//            List<Long> list, boolean isNot) {
//        if (!CollectionUtils.isEmpty(list)) {
//            Predicate in = criteriaBuilder.in(expression).value(list);
//            if (isNot) {
//                in = criteriaBuilder.not(in);
//            }
//            predicates.add(in);
//        }
//    }
//
//    public void addPredicates(CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Expression expression,
//            Long fromDate, Long toDate) {
//        if (toDate == null) {
//            toDate = new Date().getTime();
//        }
//        if (fromDate != null) {
//            Predicate between = criteriaBuilder.between(expression, DateUtil.getStartDate(new Date(fromDate)),
//                    DateUtil.getEndDate(new Date(toDate)));
//            predicates.add(between);
//        }
//    }
//
//    public void addPredicates(CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Expression expression,
//            Date date) {
//        if (date != null) {
//            Predicate between = criteriaBuilder.between(expression, DateUtil.getStartDate(date),
//                    DateUtil.getEndDate(date));
//            predicates.add(between);
//        }
//    }
//
//    public void addPredicates(CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Expression expression,
//            Enum value) {
//        if (value != null) {
//            predicates.add(criteriaBuilder.equal(expression, value));
//        }
//    }
//
//    public void addPredicates(CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Expression expression,
//            Boolean value) {
//        if (value != null) {
//            predicates.add(criteriaBuilder.equal(expression, value));
//        }
//    }
//    
//    public void addPredicatesIsNull(CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Expression expression,
//            Boolean value) {
//        if (value != null && value) {
//            predicates.add(criteriaBuilder.isNull(expression));
//        }
//    }
//    
//    public void addPredicatesIsNotNull(CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Expression expression,
//            Boolean value) {
//        if (value != null && value) {
//            predicates.add(criteriaBuilder.isNotNull(expression));
//        }
//    }
//
//    public Long getCount(CriteriaBuilder criteriaBuilder, Class<?> countFromClass, List<Predicate> predicates) {
//        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
//        populateCriteriaQuery(countQuery, criteriaBuilder.countDistinct(countQuery.from(countFromClass)), predicates);
//        return entityManager.createQuery(countQuery).getSingleResult();
//    }
//    
//    public boolean isCount(CriteriaQuery<?> criteriaQuery) {
//		return criteriaQuery.getResultType().equals(Long.class);
//	}
//
//    public void populateCriteriaQuery(CriteriaQuery<?> criteriaQuery, Selection selection, List<Predicate> predicates,
//            Order order) {
//        populateCriteriaQuery(criteriaQuery, selection, predicates, order != null ? Arrays.asList(order) : null);
//    }
//
//    public void populateCriteriaQuery(CriteriaQuery<?> criteriaQuery, Selection selection, List<Predicate> predicates,
//            List<Order> orders) {
//        criteriaQuery.distinct(true);
//        criteriaQuery.select(selection);
//        criteriaQuery.where(predicates.toArray(new Predicate[0]));
//        criteriaQuery.orderBy(orders);
//    }
//
//    public void populateCriteriaQuery(CriteriaQuery<?> criteriaQuery, Selection selection, List<Predicate> predicates) {
//        criteriaQuery.distinct(true);
//        criteriaQuery.select(selection);
//        criteriaQuery.where(predicates.toArray(new Predicate[0]));
//    }
//
//    public PageModel getPageModel(TypedQuery typedQuery, Long totalCount, List<Predicate> predicates, PaginationRequestModel paginationModel) {
//        PaginationUtil.setPaginationToQuery(typedQuery, paginationModel);
//
//        Long pageCount = 0L;
//        List data = new ArrayList<>();
//
//        if (totalCount > 0) {
//            data = typedQuery.getResultList();
//            pageCount = totalCount / typedQuery.getMaxResults();
//            if (totalCount % typedQuery.getMaxResults() > 0) {
//                pageCount++;
//            }
//        }
//        return PageModel.builder().data(data).pageCount(pageCount).totalCount(totalCount).build();
//    }
//
//    public PageModel getPageModel(CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery,
//            Class<?> countFromClass, Selection selection, List<Predicate> predicates, Order order,
//            PaginationRequestModel paginationModel) {
//
//        criteriaQuery.distinct(true);
//        criteriaQuery.select(selection);
//        criteriaQuery.where(predicates.toArray(new Predicate[0]));
//        criteriaQuery.orderBy(order);
//
//        TypedQuery typedQuery = entityManager.createQuery(criteriaQuery);
//        PaginationUtil.setPaginationToQuery(typedQuery, paginationModel);
//
//        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
//        countQuery.select(criteriaBuilder.countDistinct(countQuery.from(countFromClass)));
//        countQuery.where(predicates.toArray(new Predicate[0]));
//        Long totalCount = entityManager.createQuery(countQuery).getSingleResult();
//
//        Long pageCount = 0L;
//        List data = new ArrayList<>();
//
//        if (totalCount > 0) {
//            data = typedQuery.getResultList();
//            pageCount = totalCount / typedQuery.getMaxResults();
//            if (totalCount % typedQuery.getMaxResults() > 0) {
//                pageCount++;
//            }
//        }
//        return PageModel.builder().data(data).pageCount(pageCount).totalCount(totalCount).build();
//    }
//
//    public List getList(CriteriaQuery<?> criteriaQuery, Selection<?> selection, List<Predicate> predicates,
//            Order order) {
//        populateCriteriaQuery(criteriaQuery, selection, predicates, order);
//        TypedQuery<?> typedQuery = entityManager.createQuery(criteriaQuery);
//        return typedQuery.getResultList();
//    }
//
//    public PageModel getPageModel(CriteriaQuery<?> listQuery, CriteriaQuery<Long> countQuery,
//            PaginationRequestModel pagination) {
//        TypedQuery<?> listTypedQuery = entityManager.createQuery(listQuery);
//        TypedQuery<Long> countTypedQuery = entityManager.createQuery(countQuery);
//
//        PaginationUtil.setPaginationToQuery(listTypedQuery, pagination);
//
//        List<?> list = listTypedQuery.getResultList();
//        Long totalCount = !CollectionUtils.isEmpty(list)
//                ? countTypedQuery.getSingleResult()
//                : 0L;
//
//        return getPageModel(list, totalCount, listTypedQuery.getMaxResults());
//    }
//
//    public PageModel getPageModel(List<?> list, Long totalCount, Integer pageSize) {
//        Long pageCount = 0L;
//        if (totalCount > 0) {
//            pageCount = totalCount / pageSize;
//            if (totalCount % pageSize > 0) {
//                pageCount++;
//            }
//        }
//        return PageModel.builder().data(list).pageCount(pageCount).totalCount(totalCount).build();
//    }
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public void addPredicates(CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Expression expression,
//			Date date, String operator) {
//		if (date != null) {
//			Predicate predicate = null;
//			switch (operator) {
//			case Constants.CRITERIA_GREATER_EQUAL:
//				predicate = criteriaBuilder.greaterThanOrEqualTo(expression, DateUtil.getStartDate(date));
//				break;
//			case Constants.CRITERIA_lESS_EQUAL:
//				predicate = criteriaBuilder.lessThanOrEqualTo(expression, DateUtil.getEndDate(date));
//				break;
//			default:
//				break;
//			}
//			predicates.add(predicate);
//		}
//	}
//	
//	public <T> T getSingleResult(CriteriaQuery<T> criteriaQuery, Selection<T> selection, List<Predicate> predicates) {
//		populateCriteriaQuery(criteriaQuery, selection, predicates);
//		return entityManager.createQuery(criteriaQuery).getSingleResult();
//	}
//
//	public void addPredicatesEqual(CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Expression expression,
//			String value) {
//		if (StringUtils.hasText(value)) {
//			predicates.add(criteriaBuilder.equal(expression, value));
//		}
//	}
//	
//
//	public Order getDefaultOrder(CriteriaBuilder criteriaBuilder, String sortBy, Sort.Direction direction,
//			Root<?> root) {
//		Order order = null;
//		if (sortBy == null) {
//			sortBy = BaseEntity_.CREATED_ON;
//			Expression<?> ex = criteriaBuilder.selectCase()
//					.when(root.get(BaseEntity_.UPDATED_ON).isNull(), root.get(sortBy))
//					.otherwise(root.get(BaseEntity_.UPDATED_ON));
//			order = criteriaBuilder.desc(ex);
//		} else if (Sort.Direction.ASC.equals(direction)) {
//			order = criteriaBuilder.asc(root.get(sortBy));
//		} else {
//			order = criteriaBuilder.desc(root.get(sortBy));
//		}
//		return order;
//	}
//
//	public List getDropdownRespone(CriteriaQuery<?> criteriaQuery,
//			CompoundSelection<?> selection, List<Predicate> predicates, Order order) {
//		populateCriteriaQuery(criteriaQuery, selection, predicates, order);
//		return entityManager.createQuery(criteriaQuery).setMaxResults(10).getResultList();
//	}
//
//	public void addPredicatesEqual(CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Path<?> expression,
//			BigDecimal value) {
//		if (value != null) {
//            predicates.add(criteriaBuilder.equal(expression, value));
//        }
//	}
//
//	public void addPredicates(CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Path<Object> expression,
//			BigDecimal price) {
//		if (price != null) {
//            predicates.add(criteriaBuilder.equal(expression, price));
//        }
//	}
//
//}
