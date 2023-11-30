package com.bhagwati.ContractManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDto extends AuditableDto implements Serializable {
    private BigDecimal totalReceivedAmount = BigDecimal.ZERO;
    private BigDecimal totalSpendAmount = BigDecimal.ZERO;
    private List<TransactionDto> transactions;
    private List<TransactionDto> agreementTransactions;

}