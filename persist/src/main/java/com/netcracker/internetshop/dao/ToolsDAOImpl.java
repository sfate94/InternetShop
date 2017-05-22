package com.netcracker.internetshop.dao;

import com.netcracker.internetshop.entity.catalog.Model;
import com.netcracker.internetshop.entity.catalog.Tools;
import com.netcracker.internetshop.entity.catalog.TypeTools;
import com.netcracker.internetshop.models.ToolsInfo;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository("productDAO")
public class ToolsDAOImpl implements ToolsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Tools findTools(String toolsId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Tools.class);
        crit.add(Restrictions.eq("toolsId", toolsId));
        return (Tools) crit.uniqueResult();
    }

    @Override
    public ToolsInfo findToolsInfo(String toolsId) {
        Tools tools = this.findTools(toolsId);
        if (tools == null) {
            return null;
        }
        return new ToolsInfo(tools);
    }

    @Override
    public void save(ToolsInfo toolsInfo) {
        String toolsId = toolsInfo.getToolsId();
        Tools tools = null;
        boolean isNew = false;
        if (toolsId != null) {
            tools = this.findTools(toolsId);
        }
        if (tools == null) {
            isNew = true;
            tools = new Tools();
        }
        tools.setToolsId(toolsId);
        Model model = new Model();
        model.setModelId(toolsInfo.getDeviceModel().getModelId());
        tools.setModel(model);
        TypeTools typeTools = new TypeTools();
        typeTools.setTypeId(toolsInfo.getTypeToolsInfo().getTypeId());
        tools.setTypeTools(typeTools);
        tools.setCost(toolsInfo.getCost());
        tools.setLength(toolsInfo.getLength());
        tools.setHeight(toolsInfo.getHeight());
        tools.setWeight(toolsInfo.getWeight());
        tools.setPower(toolsInfo.getPower());
        tools.setSpeed(toolsInfo.getSpeed());

        if (toolsInfo.getFileData() != null) {
            byte[] image = toolsInfo.getFileData().getBytes();
            if (image != null && image.length > 0) {
                tools.setImage(image);
            }
        }
        if (isNew) {
            this.sessionFactory.getCurrentSession().persist(tools);
        }
        this.sessionFactory.getCurrentSession().flush();
    }

    @Override
    public Long getCount() {
        return (Long) sessionFactory.getCurrentSession().createCriteria(Tools.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getCountByTypeId(String typeId) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Tools.class);
        criteria.add(Restrictions.eq("typeTools.typeId", typeId));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<Tools> queryTools(int page, int maxResult) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Tools.class);
        criteria.setFirstResult(page*maxResult);
        criteria.setMaxResults(maxResult);
        return (List<Tools>)criteria.list();
    }

    @Override
    public List<Tools> queryToolsByTypeId(int page, int maxResult, String typeId) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Tools.class);
        criteria.add(Restrictions.eq("typeTools.typeId", typeId));
        criteria.setFirstResult(page*maxResult);
        criteria.setMaxResults(maxResult);
        return (List<Tools>)criteria.list();
    }
}