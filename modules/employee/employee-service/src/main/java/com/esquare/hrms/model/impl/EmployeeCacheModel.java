/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.esquare.hrms.model.impl;

import com.esquare.hrms.model.Employee;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Employee in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EmployeeCacheModel
	implements CacheModel<Employee>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EmployeeCacheModel)) {
			return false;
		}

		EmployeeCacheModel employeeCacheModel = (EmployeeCacheModel)object;

		if (employeeId == employeeCacheModel.employeeId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, employeeId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", employeeId=");
		sb.append(employeeId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", email=");
		sb.append(email);
		sb.append(", designation=");
		sb.append(designation);
		sb.append(", contactNumber=");
		sb.append(contactNumber);
		sb.append(", address=");
		sb.append(address);
		sb.append(", panNumber=");
		sb.append(panNumber);
		sb.append(", aadhaarNumber=");
		sb.append(aadhaarNumber);
		sb.append(", passportNumber=");
		sb.append(passportNumber);
		sb.append(", photo=");
		sb.append(photo);
		sb.append(", panImage=");
		sb.append(panImage);
		sb.append(", aadhaarImage=");
		sb.append(aadhaarImage);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Employee toEntityModel() {
		EmployeeImpl employeeImpl = new EmployeeImpl();

		if (uuid == null) {
			employeeImpl.setUuid("");
		}
		else {
			employeeImpl.setUuid(uuid);
		}

		employeeImpl.setEmployeeId(employeeId);
		employeeImpl.setGroupId(groupId);
		employeeImpl.setCompanyId(companyId);
		employeeImpl.setUserId(userId);

		if (userName == null) {
			employeeImpl.setUserName("");
		}
		else {
			employeeImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			employeeImpl.setCreateDate(null);
		}
		else {
			employeeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			employeeImpl.setModifiedDate(null);
		}
		else {
			employeeImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			employeeImpl.setName("");
		}
		else {
			employeeImpl.setName(name);
		}

		if (email == null) {
			employeeImpl.setEmail("");
		}
		else {
			employeeImpl.setEmail(email);
		}

		if (designation == null) {
			employeeImpl.setDesignation("");
		}
		else {
			employeeImpl.setDesignation(designation);
		}

		if (contactNumber == null) {
			employeeImpl.setContactNumber("");
		}
		else {
			employeeImpl.setContactNumber(contactNumber);
		}

		if (address == null) {
			employeeImpl.setAddress("");
		}
		else {
			employeeImpl.setAddress(address);
		}

		if (panNumber == null) {
			employeeImpl.setPanNumber("");
		}
		else {
			employeeImpl.setPanNumber(panNumber);
		}

		if (aadhaarNumber == null) {
			employeeImpl.setAadhaarNumber("");
		}
		else {
			employeeImpl.setAadhaarNumber(aadhaarNumber);
		}

		if (passportNumber == null) {
			employeeImpl.setPassportNumber("");
		}
		else {
			employeeImpl.setPassportNumber(passportNumber);
		}

		if (photo == null) {
			employeeImpl.setPhoto("");
		}
		else {
			employeeImpl.setPhoto(photo);
		}

		if (panImage == null) {
			employeeImpl.setPanImage("");
		}
		else {
			employeeImpl.setPanImage(panImage);
		}

		if (aadhaarImage == null) {
			employeeImpl.setAadhaarImage("");
		}
		else {
			employeeImpl.setAadhaarImage(aadhaarImage);
		}

		employeeImpl.resetOriginalValues();

		return employeeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		employeeId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		email = objectInput.readUTF();
		designation = objectInput.readUTF();
		contactNumber = objectInput.readUTF();
		address = objectInput.readUTF();
		panNumber = objectInput.readUTF();
		aadhaarNumber = objectInput.readUTF();
		passportNumber = objectInput.readUTF();
		photo = objectInput.readUTF();
		panImage = objectInput.readUTF();
		aadhaarImage = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(employeeId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (email == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(email);
		}

		if (designation == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(designation);
		}

		if (contactNumber == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(contactNumber);
		}

		if (address == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(address);
		}

		if (panNumber == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(panNumber);
		}

		if (aadhaarNumber == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(aadhaarNumber);
		}

		if (passportNumber == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(passportNumber);
		}

		if (photo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(photo);
		}

		if (panImage == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(panImage);
		}

		if (aadhaarImage == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(aadhaarImage);
		}
	}

	public String uuid;
	public long employeeId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String email;
	public String designation;
	public String contactNumber;
	public String address;
	public String panNumber;
	public String aadhaarNumber;
	public String passportNumber;
	public String photo;
	public String panImage;
	public String aadhaarImage;

}