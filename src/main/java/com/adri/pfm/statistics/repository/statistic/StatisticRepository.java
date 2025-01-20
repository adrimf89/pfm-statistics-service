package com.adri.pfm.statistics.repository.statistic;

import com.adri.pfm.statistics.model.statistic.Statistic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatisticRepository extends MongoRepository<Statistic, String> {

    Optional<Statistic> findByYearAndMonthAndAccountIdAndCategory(int year, int month, long accountId, String category);

    Optional<Statistic> findByYearAndAccountIdAndCategoryAndMonthIsNull(int year, long accountId, String category);
}
