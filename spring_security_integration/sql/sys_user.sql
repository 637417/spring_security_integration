/*
Navicat PGSQL Data Transfer

Source Server         : localhost
Source Server Version : 90500
Source Host           : localhost:5432
Source Database       : test_db
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90500
File Encoding         : 65001

Date: 2016-12-15 13:35:35
*/
-- ----------------------------
-- SEQUENCE
-- ----------------------------
CREATE SEQUENCE "public"."sys_log_id"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

ALTER TABLE "public"."sys_log_id" OWNER TO "postgres";

SELECT setval('"public"."sys_log_id"', 1, true);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_user";
CREATE TABLE "public"."sys_user" (
"user_id" int4 NOT NULL,
"user_name" varchar(30) COLLATE "default",
"user_password" varchar(255) COLLATE "default",
"department_id" varchar(30) COLLATE "default",
"function" varchar(30) COLLATE "default",
"status" int4,
"create_time" varchar(30) COLLATE "default",
"entry_time" varchar(30) COLLATE "default",
"education" varchar(30) COLLATE "default",
"mailbox" varchar(30) COLLATE "default",
"contact_tel" varchar(30) COLLATE "default",
"contact_address" varchar(30) COLLATE "default",
"emergent_contact" varchar(30) COLLATE "default",
"emergent_contact_tel" varchar(30) COLLATE "default"
)
WITH (OIDS=FALSE);

COMMENT ON TABLE "public"."sys_user" IS '系统用户';
COMMENT ON COLUMN "public"."sys_user"."user_id" IS '用户编号';
COMMENT ON COLUMN "public"."sys_user"."user_name" IS '用户名';
COMMENT ON COLUMN "public"."sys_user"."user_password" IS '用户密码';
COMMENT ON COLUMN "public"."sys_user"."department_id" IS '所属部门';
COMMENT ON COLUMN "public"."sys_user"."function" IS '职务/职称';
COMMENT ON COLUMN "public"."sys_user"."status" IS '状态';
COMMENT ON COLUMN "public"."sys_user"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."sys_user"."entry_time" IS '入职时间';
COMMENT ON COLUMN "public"."sys_user"."education" IS '学历';
COMMENT ON COLUMN "public"."sys_user"."mailbox" IS '邮箱';
COMMENT ON COLUMN "public"."sys_user"."contact_tel" IS '联系电话';
COMMENT ON COLUMN "public"."sys_user"."contact_address" IS '联系地址';
COMMENT ON COLUMN "public"."sys_user"."emergent_contact" IS '紧急联系人';
COMMENT ON COLUMN "public"."sys_user"."emergent_contact_tel" IS '紧急联系人电话';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO "public"."sys_user" VALUES (nextval('sys_user_id'), 'test', '123456', '技术部', '工程师', '1', '2016-12-15 13:56:12', '2015-12-21 13:56:12', '大专', '123@qq.com', '15230214569', '成都市双流县', '小王', '13625644879');

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table sys_user
-- ----------------------------
ALTER TABLE "public"."sys_user" ADD PRIMARY KEY ("user_id");
