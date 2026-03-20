package com.app.task.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record TaskRequestDto(
	Long userId, String title,
	String details,
	@JsonFormat(pattern = "yyyy-MM-dd") LocalDate date,
	@JsonFormat(pattern = "HH:mm[:ss]") LocalTime time,  // ← [] rend :ss optionnel
	boolean isDone
) { }
