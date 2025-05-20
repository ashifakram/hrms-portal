/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.esquare.hrms.model;

import java.sql.Blob;

/**
 * The Blob model class for lazy loading the panImage column in Employee.
 *
 * @author Brian Wing Shun Chan
 * @see Employee
 * @generated
 */
public class EmployeePanImageBlobModel {

	public EmployeePanImageBlobModel() {
	}

	public EmployeePanImageBlobModel(long employeeId) {
		_employeeId = employeeId;
	}

	public EmployeePanImageBlobModel(long employeeId, Blob panImageBlob) {
		_employeeId = employeeId;
		_panImageBlob = panImageBlob;
	}

	public long getEmployeeId() {
		return _employeeId;
	}

	public void setEmployeeId(long employeeId) {
		_employeeId = employeeId;
	}

	public Blob getPanImageBlob() {
		return _panImageBlob;
	}

	public void setPanImageBlob(Blob panImageBlob) {
		_panImageBlob = panImageBlob;
	}

	private long _employeeId;
	private Blob _panImageBlob;

}