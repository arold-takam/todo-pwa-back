package com.app.task.internal.repository;

import com.app.task.internal.domain.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

	List<TaskEntity> findAllByUserId(Long  id);
	
	@Modifying
	@Query("delete from TaskEntity t where t.userId = :userId")
	void deleteAllByUserId(Long userId);

}
