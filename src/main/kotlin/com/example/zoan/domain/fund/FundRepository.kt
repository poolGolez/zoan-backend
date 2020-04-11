package com.example.zoan.domain.fund

import com.example.zoan.domain.fund.Fund
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FundRepository : CrudRepository<Fund, Long> {
}
