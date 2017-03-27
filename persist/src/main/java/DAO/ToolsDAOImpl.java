package dao;

import entity.catalog.Tools;
import models.PaginationResult;
import models.ToolsInfo;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ToolsDAOImpl implements ToolsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Tools findTools(String code) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Tools.class);
        crit.add(Restrictions.eq("code", code));
        return (Tools) crit.uniqueResult();
    }

    @Override
    public ToolsInfo findToolsInfo(String toolsId) {
        return null;
    }

    public ToolsInfo findProductInfo(String toolsId) {
        Tools tools = this.findTools(toolsId);
        if (tools == null) {
            return null;
        }
        return new ToolsInfo(tools.gettoolsId(), tools.getmodelId(), tools.gettypeId(), tools.getcost());
    }

    @Override
    public void save(ToolsInfo toolsInfo) {
        String code = toolsInfo.gettoolsId();

        Tools tools = null;

        boolean isNew = false;
        if (code != null) {
           tools = this.findTools(code);
        }
        if (tools == null) {
            isNew = true;
            tools = new Tools();
        }
        tools.settoolsId(code);
        tools.setmodelId(toolsInfo.getmodelId());
        tools.settypeId(toolsInfo.gettypeId());
        tools.setcost(toolsInfo.getcost());

        if (toolsInfo.getFileData() != null) {
            byte[] image = toolsInfo.getFileData().getBytes();

        }
        if (isNew) {
            this.sessionFactory.getCurrentSession().persist(tools);
        }
        // If error in DB, Exceptions will be thrown out immediately
        // Nếu có lỗi tại DB, ngoại lệ sẽ ném ra ngay lập tức
        this.sessionFactory.getCurrentSession().flush();
    }

    @Override
    public PaginationResult<ToolsInfo> queryProducts(int page, int maxResult, int maxNavigationPage,
                                                     String likeName) {
        String sql = "Select new " + ToolsInfo.class.getName() //
                + "(p.code, p.name, p.price) " + " from "//
                + Tools.class.getName() + " p ";
        if (likeName != null && likeName.length() > 0) {
            sql += " Where lower(p.name) like :likeName ";
        }
        sql += " order by p.createDate desc ";
        //
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery(sql);
        if (likeName != null && likeName.length() > 0) {
            query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
        }
        return new PaginationResult<ToolsInfo>(query, page, maxResult, maxNavigationPage);
    }

    @Override
    public PaginationResult<ToolsInfo> queryProducts(int page, int maxResult, int maxNavigationPage) {
        return queryProducts(page, maxResult, maxNavigationPage, null);
    }

}