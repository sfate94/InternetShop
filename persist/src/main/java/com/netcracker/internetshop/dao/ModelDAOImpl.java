package com.netcracker.internetshop.dao;

import com.netcracker.internetshop.entity.catalog.Model;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("modelDAO")
public class ModelDAOImpl implements ModelDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Model> getModels() {
        return (List<Model>) sessionFactory.getCurrentSession().createCriteria(Model.class).list();
    }

    @Override
    public List<Model> getModelsByTypeId(String typeId) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Model.class);
        criteria.add(Restrictions.eq("typeId", typeId));
        return (List<Model>) criteria.list();
    }
}
