package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	@Transactional(readOnly = true)
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	@Transactional(readOnly = true)
	public Page<SaleReportDTO> getSaleReport(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable) {

		if(maxDate == null) {
			maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}

		if(minDate == null) {
			minDate = maxDate.minusYears(1L);
		}

		return repository.getSaleReportData(minDate, maxDate, name, pageable);
	}

	@Transactional(readOnly = true)
	public List<SaleSummaryDTO> getSaleSummary(LocalDate minDate, LocalDate maxDate) {

		if(maxDate == null) {
			maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}

		if(minDate == null) {
			minDate = maxDate.minusYears(1L);
		}

		return repository.getSaleSummaryData(minDate, maxDate);
	}
}
