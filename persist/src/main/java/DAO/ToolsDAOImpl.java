package dao;

import entity.catalog.Model;
import entity.catalog.Tools;
import models.PaginationResult;
import models.ToolsInfo;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import validator.ProductInfoValidator;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
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
        return new ToolsInfo(tools.getToolsId(), tools.getModelId(), tools.getTypeId(), tools.getCost(),tools.getLength(),tools.getHeight(),tools.getWeight(),tools.getPower(),tools.getSpeed());
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
        tools.setModelId(toolsInfo.getModelId());
        tools.setTypeId(toolsInfo.getTypeId());
        tools.setCost(toolsInfo.getCost());
        tools.setLength(toolsInfo.getLenght());
        tools.setHeight(toolsInfo.getHeight());
        tools.setWeight(toolsInfo.getWeight());
        tools.setPower(toolsInfo.getPower());
        tools.setSpeed(toolsInfo.getSpeed());

        /*if (toolsInfo.getFileData() != null) {
            byte[] image = toolsInfo.getFileData().getBytes();
            if (image != null && image.length > 0) {
                tools.setImage(image);
            }
        }*/
        if (isNew) {
            this.sessionFactory.getCurrentSession().persist(tools);
        }
        this.sessionFactory.getCurrentSession().flush();
    }

    @Override
    public List<Tools> queryTools(int page, int maxResult, int maxNavigationPage,
                                      String likeName) {
        /*String sql = "Select new " + ToolsInfo.class.getName() //
                + "(p.code, p.name, p.price) " + " from "//
                + Tools.class.getName() + " p ";
        if (likeName != null && likeName.length() > 0) {
            sql += " Where lower(p.name) like :likeName ";
        }

        Query query = session.createQuery(sql);
        if (likeName != null && likeName.length() > 0) {
            query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
        }*/
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Tools.class);
        return (List<Tools>) criteria.list();
    }

    @Override
    public List<Tools> queryTools(int page, int maxResult, int maxNavigationPage) {
        return queryTools(page, maxResult, maxNavigationPage, null);
    }

}