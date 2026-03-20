package com.app.task.api.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record TaskResponseDto(
	Long id, String title,
	String details,
	LocalDate date,
	LocalTime time,
	boolean done,
	Long userId
){ }
