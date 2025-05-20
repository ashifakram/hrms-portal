package com.esquare.hrms.service.persistence.impl;

import com.esquare.hrms.model.Employee;
import com.esquare.hrms.service.EmployeeLocalServiceUtil;
import com.esquare.hrms.service.persistence.EmployeeFinder;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Session;

import java.util.Collections;
import java.util.List;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		service = EmployeeFinder.class
)
public class EmployeeFinderImpl extends EmployeeFinderBaseImpl implements EmployeeFinder{
	
	public List<Employee> findByKeywords(String keyword) {
	    Session session = null;

	    try {
	        session = openSession();

	        ClassLoader classLoader = getClass().getClassLoader();

	        DynamicQuery employeeQuery = DynamicQueryFactoryUtil.forClass(Employee.class, classLoader);

	        Criterion nameCriterion = RestrictionsFactoryUtil.ilike("name", "%" + keyword + "%");
	        Criterion addressCriterion = RestrictionsFactoryUtil.ilike("address", "%" + keyword + "%");
	        Criterion designationCriterion = RestrictionsFactoryUtil.ilike("designation", "%" + keyword + "%");

	        // Combine with OR logic
	        Criterion orCriterion = RestrictionsFactoryUtil.or(nameCriterion,
	                                    RestrictionsFactoryUtil.or(addressCriterion, designationCriterion));

	        employeeQuery.add(orCriterion);

	        return EmployeeLocalServiceUtil.dynamicQuery(employeeQuery);
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    finally {
	        closeSession(session);
	    }

	    return Collections.emptyList();
	}

}
