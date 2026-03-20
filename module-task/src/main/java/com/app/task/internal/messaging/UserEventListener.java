package com.app.task.internal.messaging;

import com.app.task.internal.repository.TaskRepository;
import com.app.user.shared.UserCreatedEvent;
import com.app.user.shared.UserDeletedEvent;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@AllArgsConstructor
public class UserEventListener {
	private final TaskRepository repository;
	
//	--------------------------------------------------------------------------------------------------------------------------------
	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void onUserCreated(UserCreatedEvent event){
		System.out.println("Modulith Event: New user created with ID: " + event.id());
	}
	
	@Async
	@Transactional(propagation =  Propagation.REQUIRES_NEW)
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void onUserDeleted(UserDeletedEvent event){
		repository.deleteAllByUserId(event.id());
		
		System.out.println("Modulith Event: Tasks deleted successfully for user_id: "+ event.id());
	}
}