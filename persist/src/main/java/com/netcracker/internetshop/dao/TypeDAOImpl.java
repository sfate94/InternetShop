package com.netcracker.internetshop.dao;

import com.netcracker.internetshop.entity.catalog.TypeTools;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("typeDAO")
public class TypeDAOImpl implements TypeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TypeTools> getTypeTools() {
        return (List<TypeTools>) sessionFactory.getCurrentSession().createCriteria(TypeTools.class).list();
    }
}
