create index IX_59F35402 on HRMS_Employee (name[$COLUMN_LENGTH:75$]);
create index IX_B87E2A13 on HRMS_Employee (panNumber[$COLUMN_LENGTH:75$]);
create unique index IX_CBA1C1F9 on HRMS_Employee (uuid_[$COLUMN_LENGTH:75$], groupId);