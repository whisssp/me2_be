package com.me2.repository;

import com.me2.entity.PromotionEntity;
import com.me2.service.dto.admin.filter.PromotionAdminFilterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<PromotionEntity, Long> {

    PromotionEntity findPromotionEntityByCode(String code);

    @Query("select p from PromotionEntity p where p.status <> 'DELETED' "
            + "and (cast(:#{#filters.id} as text) is null or (cast(p.id as text) like concat('%', :#{#filters.id?.toString()}, '%'))) "
            + "and ((:#{#filters.type} is null) or (p.type = :#{#filters.type})) "
            + "and ((:#{#filters.isActivated} is null) or (p.isActivated = :#{#filters.isActivated})) "
            + "and ((:#{#filters.code} is null) or (p.code like concat('%', :#{#filters.code}, '%'))) "
            + "and ((:#{#filters.name} is null) or (p.name like concat('%', :#{#filters.name}, '%'))) "
            + "and ((:#{#filters.content} is null) or (p.content like concat('%', :#{#filters.content}, '%'))) "
            + "and ((:#{#filters.fromQuantity} is null) or (p.quantity >= :#{#filters.fromQuantity})) "
            + "and ((:#{#filters.toQuantity} is null) or (p.quantity <= :#{#filters.toQuantity})) "
            + "and ((:#{#filters.fromQuantityUsed} is null) or (p.quantityUsed >= :#{#filters.fromQuantityUsed})) "
            + "and ((:#{#filters.toQuantityUsed} is null) or (p.quantityUsed >= :#{#filters.toQuantityUsed})) "
            + "and ((cast(cast(:#{#filters.fromStartDate} as text) as timestamp) is null) or (cast(cast(p.startDate as text) as timestamp)>= cast(cast(:#{#filters.fromStartDate} as text) as timestamp))) "
            + "and ((cast(cast(:#{#filters.toStartDate} as text) as timestamp) is null) or (cast(cast(p.startDate as text) as timestamp)<= cast(cast(:#{#filters.toStartDate} as text) as timestamp))) "
            + "and ((cast(cast(:#{#filters.fromEndDate} as text) as timestamp) is null) or (cast(cast(p.endDate as text) as timestamp)>= cast(cast(:#{#filters.fromEndDate} as text) as timestamp))) "
            + "and ((cast(cast(:#{#filters.toEndDate} as text) as timestamp) is null) or (cast(cast(p.endDate as text) as timestamp)>= cast(cast(:#{#filters.toEndDate} as text) as timestamp))) "
            + "and ((cast(cast(:#{#filters.fromCreatedDate} as text) as timestamp) is null) or (cast(cast(p.createdDate as text) as timestamp)>= cast(cast(:#{#filters.fromCreatedDate} as text) as timestamp))) "
            + "and ((cast(cast(:#{#filters.toCreatedDate} as text) as timestamp) is null) or (cast(cast(p.createdDate as text) as timestamp)>= cast(cast(:#{#filters.toCreatedDate} as text) as timestamp))) "
            + "and ((cast(cast(:#{#filters.fromLastModifiedDate} as text) as timestamp) is null) or (cast(cast(p.lastModifiedDate as text) as timestamp)>= cast(cast(:#{#filters.fromLastModifiedDate} as text) as timestamp))) "
            + "and ((cast(cast(:#{#filters.toLastModifiedDate} as text) as timestamp) is null) or (cast(cast(p.lastModifiedDate as text) as timestamp)>= cast(cast(:#{#filters.toLastModifiedDate} as text) as timestamp))) "
    )
    Page<PromotionEntity> findAllByFiltersForAdmin(@Param("filters") PromotionAdminFilterDTO filters, Pageable pageable);
}