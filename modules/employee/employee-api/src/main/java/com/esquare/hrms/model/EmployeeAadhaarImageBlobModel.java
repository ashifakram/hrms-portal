/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.esquare.hrms.model;

import java.sql.Blob;

/**
 * The Blob model class for lazy loading the aadhaarImage column in Employee.
 *
 * @author Brian Wing Shun Chan
 * @see Employee
 * @generated
 */
public class EmployeeAadhaarImageBlobModel {

	public EmployeeAadhaarImageBlobModel() {
	}

	public EmployeeAadhaarImageBlobModel(long employeeId) {
		_employeeId = employeeId;
	}

	public EmployeeAadhaarImageBlobModel(
		long employeeId, Blob aadhaarImageBlob) {

		_employeeId = employeeId;
		_aadhaarImageBlob = aadhaarImageBlob;
	}

	public long getEmployeeId() {
		return _employeeId;
	}

	public void setEmployeeId(long employeeId) {
		_employeeId = employeeId;
	}

	public Blob getAadhaarImageBlob() {
		return _aadhaarImageBlob;
	}

	public void setAadhaarImageBlob(Blob aadhaarImageBlob) {
		_aadhaarImageBlob = aadhaarImageBlob;
	}

	private long _employeeId;
	private Blob _aadhaarImageBlob;

}