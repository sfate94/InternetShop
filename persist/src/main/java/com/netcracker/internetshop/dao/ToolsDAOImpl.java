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
    public List<Tools> getToolsByTypeId(String typeId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Tools.class);
        return (List<Tools>)crit.list();
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
    public List<Tools> queryTools(int page, int maxResult) {
        String sql = "select t.Tools_ID, t.Cost, t.Length, t.Weight, t.Height, t.Power, t.Speed, m.Model_Name, tt.TypeName, t.Image from tools t, model m, type_tools tt where t.Model_ID = m.Model_ID && t.Type_ID = tt.Type_ID limit ?,?;";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setParameter(0, page*maxResult);
        query.setParameter(1, maxResult);
        List<Object[]> result = query.list();
        List<Tools> toolsList = new ArrayList<>();
        for (Object[] array : result) {
            Tools tools = new Tools();
            tools.setToolsId((String) array[0]);
            tools.setCost((Integer) array[1]);
            tools.setLength((Integer) array[2]);
            tools.setWeight((Integer) array[3]);
            tools.setHeight((Integer) array[4]);
            tools.setPower((Integer) array[5]);
            tools.setSpeed((Integer) array[6]);
            Model model = new Model();
            model.setModelName((String) array[7]);
            tools.setModel(model);
            TypeTools typeTools = new TypeTools();
            typeTools.setTypeName((String) array[8]);
            tools.setTypeTools(typeTools);
            tools.setImage((byte[]) array[9]);
            toolsList.add(tools);
        }
        return toolsList;
    }
}