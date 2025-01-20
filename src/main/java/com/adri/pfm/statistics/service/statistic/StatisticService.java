package com.adri.pfm.statistics.service.statistic;

import com.adri.pfm.statistics.model.statistic.Statistic;
import com.adri.pfm.statistics.model.transaction.Category;
import com.adri.pfm.statistics.model.transaction.Transaction;
import com.adri.pfm.statistics.repository.statistic.StatisticRepository;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;

import static java.util.Objects.nonNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticService {

    private final StatisticRepository statisticRepository;

    public void addTransactionToStatistic(final Transaction transaction) {
        Preconditions.checkArgument(nonNull(transaction), "Transaction cannot be null");
        Preconditions.checkArgument(nonNull(transaction.getCategory()), "Transaction category cannot be null");
        Preconditions.checkArgument(nonNull(transaction.getDate()), "Transaction date cannot be null");
        Preconditions.checkArgument(nonNull(transaction.getAmount()), "Transaction amount cannot be null");
        log.info("Adding transaction id '{}' to statistics", transaction.getTransactionId());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(transaction.getDate());
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        Statistic statisticByMonth = getOrCreateStatisticByMonth(year, month, transaction.getAccountId(), transaction.getCategory());
        statisticByMonth.setAmount(statisticByMonth.getAmount().add(transaction.getAmount()));
        statisticRepository.save(statisticByMonth);

        Statistic statisticByYear = getOrCreateStatisticByYear(year, transaction.getAccountId(), transaction.getCategory());
        statisticByYear.setAmount(statisticByYear.getAmount().add(transaction.getAmount()));
        statisticRepository.save(statisticByYear);
    }

    private Statistic getOrCreateStatisticByMonth(int year, int month, long accountId, Category category) {
        return statisticRepository.findByYearAndMonthAndAccountIdAndCategory(year, month, accountId, category.getName())
                .orElseGet(() -> createNewStatistic(year, month, accountId, category));
    }

    private Statistic getOrCreateStatisticByYear(int year, long accountId, Category category) {
        return statisticRepository.findByYearAndAccountIdAndCategoryAndMonthIsNull(year, accountId, category.getName())
                .orElseGet(() -> createNewStatistic(year, null, accountId, category));
    }

    private Statistic createNewStatistic(Integer year, Integer month, long accountId, Category category) {
        Statistic statistic = new Statistic();
        statistic.setAccountId(accountId);
        statistic.setYear(year);
        statistic.setMonth(month);
        statistic.setCategory(category.getName());
        statistic.setCategoryGroup(category.getCategoryGroup());
        statistic.setAmount(BigDecimal.ZERO);
        return statistic;
    }
}
