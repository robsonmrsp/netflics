package com.robsonmrsp.netflics.core.persistence;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.robsonmrsp.netflics.core.model.AbstractEntity;
import com.robsonmrsp.netflics.core.model.AbstractMultitenantEntity;
import com.robsonmrsp.netflics.core.security.SpringSecurityUserContext;

@Component
public class DataCreateUpdateListener {

	@Autowired
	private SpringSecurityUserContext context;

	@PreUpdate
	private void preUpdate(Object object) {
		if (object instanceof AbstractEntity) {
			AbstractEntity entity = (AbstractEntity) object;
			entity.setLastUpdateDatetime(LocalDateTime.now());
			entity.setUserChange(context.getCurrentUserName());
		}
		if (object instanceof AbstractMultitenantEntity) {
			AbstractMultitenantEntity entity = (AbstractMultitenantEntity) object;
			entity.setTenant(context.getTenant());
		}
	}

	@PrePersist
	private void preInsert(Object object) {
		if (object instanceof AbstractEntity) {
			AbstractEntity entity = (AbstractEntity) object;
			entity.setCreateDatetime(LocalDateTime.now());
			entity.setUserCreate(context.getCurrentUserName());
		}
		if (object instanceof AbstractMultitenantEntity) {
			AbstractMultitenantEntity entity = (AbstractMultitenantEntity) object;
			entity.setTenant(context.getTenant());
		}
	}
}