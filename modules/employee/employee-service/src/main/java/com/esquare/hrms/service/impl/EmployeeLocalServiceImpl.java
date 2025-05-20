/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.esquare.hrms.service.impl;

import com.esquare.hrms.model.Employee;
import com.esquare.hrms.service.base.EmployeeLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.esquare.hrms.model.Employee",
	service = AopService.class
)
public class EmployeeLocalServiceImpl extends EmployeeLocalServiceBaseImpl {
	
	
	@Override
	public List<Employee> findByName(String name){
		
		return employeePersistence.findByName(name);
	}
	
	@Override
	public List<Employee> findByKeywords(String keyword){
		
		return employeeFinder.findByKeywords(keyword);
	}
}