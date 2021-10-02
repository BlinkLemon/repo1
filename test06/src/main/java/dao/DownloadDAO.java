package dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;



import bean.Download;

/**
 * A data access object (DAO) providing persistence and search support for
 * Download entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see bean.Download
 * @author MyEclipse Persistence Tools
 */

public class DownloadDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(DownloadDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String LINK_NAME = "linkName";
	public static final String SENDER = "sender";
	public static final String SHOW_OR_NOT = "showOrNot";
	public static final String IP = "ip";

	protected void initDao() {
		// do nothing
	}

	public void save(Download transientInstance) {
		log.debug("saving Download instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Download persistentInstance) {
		log.debug("deleting Download instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Download findById(java.lang.Integer id) {
		log.debug("getting Download instance with id: " + id);
		try {
			Download instance = (Download) getHibernateTemplate().get(
					"bean.Download", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Download instance) {
		log.debug("finding Download instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Download instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Download as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findByLinkName(Object linkName) {
		return findByProperty(LINK_NAME, linkName);
	}

	public List findBySender(Object sender) {
		return findByProperty(SENDER, sender);
	}

	public List findByShowOrNot(Object showOrNot) {
		return findByProperty(SHOW_OR_NOT, showOrNot);
	}

	public List findByIp(Object ip) {
		return findByProperty(IP, ip);
	}

	public List findAll() {
		log.debug("finding all Download instances");
		try {
			String queryString = "from Download";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Download merge(Download detachedInstance) {
		log.debug("merging Download instance");
		try {
			Download result = (Download) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Download instance) {
		log.debug("attaching dirty Download instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Download instance) {
		log.debug("attaching clean Download instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DownloadDAO getFromApplicationContext(ApplicationContext ctx) {
		return (DownloadDAO) ctx.getBean("DownloadDAO");
	}
	
	public int count(String type){
		String sql="select count(*) from download where showOrNot='True'";
		if(type.equals("t")){
			sql="select count(*) from download where type='��ʦ' and  showOrNot='True'";
		}
		else if(type.equals("s")){
			sql="select count(*) from download where type='ѧ��' and  showOrNot='True'";
		}
		Session session=getSession();
		Query q=session.createSQLQuery(sql);
		return Integer.parseInt(q.list().get(0).toString());
	}
	
	public List findByZhPage(String type,int curr){
		int size=2;
		String hql="from bean.Download where showOrNot='True' order by time desc";
        if(type.equals("t")){
			hql="from bean.Download where type='��ʦ' and  showOrNot='True' order by time desc";
		}
		else if(type.equals("s")){
			hql="from bean.Download where type='ѧ��' and  showOrNot='True' order by time desc";
		}		
		Session session=getSession();
		Query q=session.createQuery(hql);
		q.setFirstResult(size*(curr-1));
		q.setMaxResults(size);
		List l=q.list();
    	if(session!=null){
      	  session.close();
      	}		
		return l;
	}
	
	public List findAllexcel(){
    	Session session = getSession();
		String hql;
		
			hql = "from  bean.Download";
		
			
		
		Query query = session.createQuery(hql);
		return query.list();
    }
}