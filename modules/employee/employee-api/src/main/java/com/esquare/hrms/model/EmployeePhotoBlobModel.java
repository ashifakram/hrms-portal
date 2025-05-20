/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.esquare.hrms.model;

import java.sql.Blob;

/**
 * The Blob model class for lazy loading the photo column in Employee.
 *
 * @author Brian Wing Shun Chan
 * @see Employee
 * @generated
 */
public class EmployeePhotoBlobModel {

	public EmployeePhotoBlobModel() {
	}

	public EmployeePhotoBlobModel(long employeeId) {
		_employeeId = employeeId;
	}

	public EmployeePhotoBlobModel(long employeeId, Blob photoBlob) {
		_employeeId = employeeId;
		_photoBlob = photoBlob;
	}

	public long getEmployeeId() {
		return _employeeId;
	}

	public void setEmployeeId(long employeeId) {
		_employeeId = employeeId;
	}

	public Blob getPhotoBlob() {
		return _photoBlob;
	}

	public void setPhotoBlob(Blob photoBlob) {
		_photoBlob = photoBlob;
	}

	private long _employeeId;
	private Blob _photoBlob;

}