package com.example.zoan.domain.fund

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FundRepository : CrudRepository<Fund, Long>
