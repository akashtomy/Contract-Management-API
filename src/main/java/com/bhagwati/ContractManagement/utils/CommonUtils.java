package com.bhagwati.ContractManagement.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Common utils.
 *
 * @author Akash Thomas.
 */
@Slf4j
@Service
public class CommonUtils {


    /**
     * Gets pagination orders.
     *
     * @param sortBy the sort by
     * @return the pagination orders
     */
    public List<Sort.Order> getPaginationOrders(String sortBy) {
        java.util.List<Sort.Order> orders = new ArrayList<>();
        if (StringUtils.hasText(sortBy)) {
            // asc:parameterName, desc:parameterName
            String[] sortList = sortBy.split(",");
            for (String sort : sortList) {
                Sort.Order order = new Sort.Order(
                        "DESC".equalsIgnoreCase(sort.split(":")[0]) ? Sort.Direction.DESC
                                : Sort.Direction.ASC,
                        sort.split(":")[1]);
                orders.add(order);
            }
            return orders;
        } else {
            orders.add(new Sort.Order(Sort.Direction.DESC, "modifiedDatetime"));
            return orders;
        }
    }
}
