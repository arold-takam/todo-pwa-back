package com.app.task.internal.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class TaskEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title", nullable = false, unique = true)
	private String title;
	
	@Column(name = "details")
	private String details;
	
	@Column(name = "status", nullable = false)
	private boolean done;
	
	@Column(name = "date_due")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	@Column(name = "time_due")
	@JsonFormat(pattern = "HH:mm:[ss]")
	private LocalTime time;
	
	@Column(name = "user_id", nullable = false)
	private Long userId;
}
