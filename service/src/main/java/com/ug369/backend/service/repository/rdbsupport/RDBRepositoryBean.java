package com.ug369.backend.service.repository.rdbsupport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;


/**
 * Created by Roy on 2017/3/30.
 */
public class RDBRepositoryBean<R extends JpaRepository<S, ID>, S, ID extends Serializable>
        extends JpaRepositoryFactoryBean<R, S, ID> {


    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new BaseDaoFactory(entityManager);
    }


    private class BaseDaoFactory<S, ID extends Serializable> extends JpaRepositoryFactory {

        public BaseDaoFactory(EntityManager entityManager) {
            super(entityManager);
        }

        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {

            return AbstractRDBRepository.class;
        }

        @Override
        protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {

            JpaEntityInformation entityInformation = this.getEntityInformation(information.getDomainType());
            return new AbstractRDBRepository<>(entityInformation, entityManager);

        }
    }
}
