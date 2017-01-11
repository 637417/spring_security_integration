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

Date: 2016-12-15 13:35:47
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
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_log";
CREATE TABLE "public"."sys_log" (
"log_id" int8 NOT NULL,
"user_id" int4,
"user_name" varchar(30) COLLATE "default",
"operation" varchar(50) COLLATE "default",
"create_time" varchar(30) COLLATE "default",
"visit_ip" varchar(30) COLLATE "default",
"request_parm" varchar(1024) COLLATE "default",
"result" varchar(1024) COLLATE "default",
"remarks" varchar(30) COLLATE "default"
)
WITH (OIDS=FALSE);

COMMENT ON TABLE "public"."sys_log" IS '系统日志';
COMMENT ON COLUMN "public"."sys_log"."log_id" IS '记录流水号';
COMMENT ON COLUMN "public"."sys_log"."user_id" IS '用户编号';
COMMENT ON COLUMN "public"."sys_log"."user_name" IS '用户名称';
COMMENT ON COLUMN "public"."sys_log"."operation" IS '所执行操作';
COMMENT ON COLUMN "public"."sys_log"."create_time" IS '执行时间';
COMMENT ON COLUMN "public"."sys_log"."visit_ip" IS '请求访问IP地址';
COMMENT ON COLUMN "public"."sys_log"."request_parm" IS '请求参数';
COMMENT ON COLUMN "public"."sys_log"."result" IS '相应结果';
COMMENT ON COLUMN "public"."sys_log"."remarks" IS '备注';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table sys_log
-- ----------------------------
ALTER TABLE "public"."sys_log" ADD PRIMARY KEY ("log_id");
