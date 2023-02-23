/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : mall_tiny_oa

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2023-01-26
*/

/*创建表实例*/
-- -------------------------------------
-- Table structure for ums_example_table
-- -------------------------------------
/*DROP TABLE IF EXISTS `ums_example_table`;
CREATE TABLE `ums_example_table` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `money` decimal(10,2) DEFAULT NULL COMMENT '金额',
	`create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `concent` text,
  `status` int(1) DEFAULT '1' COMMENT '帐号启用状态：0->禁用；1->启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='实例表';*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------------
-- Table structure for ums_department
-- ----------------------------------
DROP TABLE IF EXISTS `ums_department`;
CREATE TABLE `ums_department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `depname` varchar(64) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `admin_count` int(11) DEFAULT NULL COMMENT '后台用户数量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  `sort` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台部门表';

-- ----------------------------
-- Table structure for ums_user_department_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_department_relation`;
CREATE TABLE `ums_user_department_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `department_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8 COMMENT='后台用户部门关系表';

-- -------------------------------------
-- Table structure for ams_process
-- -------------------------------------
DROP TABLE IF EXISTS `ams_process`;
CREATE TABLE `ams_process` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `apply_user_id` bigint(20) DEFAULT NULL COMMENT '申请用户ID',
  `examine_user_id` bigint(20) DEFAULT NULL COMMENT '审核用户ID',
  `department_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `priority` varchar(255) DEFAULT NULL COMMENT '优先级',
  `apply_type_id` bigint(20) DEFAULT NULL COMMENT '申请类型ID',
  `apply_type_name` varchar(255) DEFAULT NULL COMMENT '申请类型名称',
  `steps_concent` text DEFAULT NULL COMMENT '审核步骤详情 JSON对象格式',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` int(1) DEFAULT '1' COMMENT '审核状态：1->审核中；2->审核结束;3->审核驳回;4->审核撤销',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='审批流程表';

-- ----------------------------
-- Table structure for ams_process_examine_user
-- ----------------------------
DROP TABLE IF EXISTS `ams_process_examine_user`;
CREATE TABLE `ams_process_examine_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `examine_user_id` bigint(20) DEFAULT NULL COMMENT '审核人ID',
  `process_id` bigint(20) DEFAULT NULL COMMENT '审核流程ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='审核人-流程关系表';


-- ----------------------------
-- Table structure for ams_reimbursement
-- ----------------------------
DROP TABLE IF EXISTS `ams_reimbursement`;
CREATE TABLE `ams_reimbursement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pro_id` bigint(20) DEFAULT NULL COMMENT '关联流程表ID',
  `department` varchar(255) DEFAULT NULL COMMENT '所属部门',
  `reim_reason` varchar(255) DEFAULT NULL COMMENT '报销事由',
  `reim_money` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `uppercase` varchar(255) DEFAULT NULL COMMENT '金额大写',
  `pay_people` varchar(255) DEFAULT NULL COMMENT '收款方户名',
  `bank_account` varchar(255) DEFAULT NULL COMMENT '银行账号',
  `bank_name` varchar(255) DEFAULT NULL COMMENT '开户行',
  `bill_num` int(10) DEFAULT NULL COMMENT '单据数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报销单表';

-- ----------------------------
-- Table structure for ams_reimbursement
-- ----------------------------
DROP TABLE IF EXISTS `ams_reimbursement_details`;
CREATE TABLE `ams_reimbursement_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reim_id` bigint(20) DEFAULT NULL COMMENT '关联报销表ID',
  `happen_time` datetime DEFAULT NULL COMMENT '费用日期',
  `reim_course` varchar(255) DEFAULT NULL COMMENT '费用科目',
  `reim_explain` varchar(255) DEFAULT NULL COMMENT '费用说明',
  `bill_list` text DEFAULT NULL COMMENT '票据',
  `reim_money` decimal(10,2) DEFAULT NULL COMMENT '报销金额',
  `uppercase` varchar(255) DEFAULT NULL COMMENT '金额大写',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报销单-明细表';

-- ----------------------------
-- Table structure for ams_pay_apply
-- ----------------------------
DROP TABLE IF EXISTS `ams_pay_apply`;
CREATE TABLE `ams_pay_apply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pro_id` bigint(20) DEFAULT NULL COMMENT '关联流程表ID',
  `department` varchar(255) DEFAULT NULL COMMENT '所属部门',
  `collection_compnay` varchar(255) DEFAULT NULL COMMENT '收款单位名称',
  `bank_name` varchar(255) DEFAULT NULL COMMENT '开户行',
  `bank_account` varchar(255) DEFAULT NULL COMMENT '银行账号',
  `pay_money` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `pay_type` varchar(255) DEFAULT NULL COMMENT '付款方式',
  `uppercase` varchar(255) DEFAULT NULL COMMENT '金额大写',
  `usefull` varchar(255) DEFAULT NULL COMMENT '用途',
  `type_id` bigint(20) DEFAULT NULL COMMENT '报账类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='付款申请单表';


-- ----------------------------
-- Table structure for ams_advancepay
-- ----------------------------
DROP TABLE IF EXISTS `ams_advancepay`;
CREATE TABLE `ams_advancepay` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pro_id` bigint(20) DEFAULT NULL COMMENT '关联流程表ID',
  `department` varchar(255) DEFAULT NULL COMMENT '所属部门',
  `collection_compnay` varchar(255) DEFAULT NULL COMMENT '收款单位名称',
  `invoice_compnay` varchar(255) DEFAULT NULL COMMENT '开票单位名称',
  `pay_money` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `advancepay_money` decimal(10,2) DEFAULT NULL COMMENT '预付款金额',
  `uppercase` varchar(255) DEFAULT NULL COMMENT '金额大写',
  `usefull` varchar(255) DEFAULT NULL COMMENT '用途',
  `type_id` bigint(20) DEFAULT NULL COMMENT '报账类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预付款项报账单表';

-- ----------------------------
-- Table structure for ams_buyplan
-- ----------------------------
DROP TABLE IF EXISTS `ams_buyplan`;
CREATE TABLE `ams_buyplan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pro_id` bigint(20) DEFAULT NULL COMMENT '关联流程表ID',
  `department` varchar(255) DEFAULT NULL COMMENT '所属部门',
  `goods_name` varchar(255) DEFAULT NULL COMMENT '物资名称',
  `dep_person` varchar(255) DEFAULT NULL COMMENT '使用部门或使用人',
  `goods_unit` varchar(255) DEFAULT NULL COMMENT '商品单位',
  `goods_nums` int(10) DEFAULT NULL COMMENT '商品数量',
  `ones_money` decimal(10,2) DEFAULT NULL COMMENT '预计单价',
  `goods_money` decimal(10,2) DEFAULT NULL COMMENT '计划金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购计划审批表';

-- ----------------------------
-- Table structure for ams_contract
-- ----------------------------
DROP TABLE IF EXISTS `ams_contract`;
CREATE TABLE `ams_contract` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pro_id` bigint(20) DEFAULT NULL COMMENT '关联流程表ID',
  `department` varchar(255) DEFAULT NULL COMMENT '所属部门',
  `launch_basis` varchar(255) DEFAULT NULL COMMENT '发起依据',
  `contract_code` varchar(255) DEFAULT NULL COMMENT '合同编号',
  `contract_name` varchar(255) DEFAULT NULL COMMENT '合同名称',
  `first_part` varchar(255) DEFAULT NULL COMMENT '甲方名称',
  `second_part` varchar(255) DEFAULT NULL COMMENT '乙方名称',
  `contract_money` decimal(10,2) DEFAULT NULL COMMENT '合同金额',
  `contract_content` varchar(255) DEFAULT NULL COMMENT '基本内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='合同会签表';

-- ----------------------------
-- Table structure for ams_project
-- ----------------------------
DROP TABLE IF EXISTS `ams_project`;
CREATE TABLE `ams_project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pro_id` bigint(20) DEFAULT NULL COMMENT '关联流程表ID',
  `department` varchar(255) DEFAULT NULL COMMENT '所属部门',
  `collection_compnay` varchar(255) DEFAULT NULL COMMENT '收款单位名称',
  `bank_name` varchar(255) DEFAULT NULL COMMENT '开户行名称',
  `bank_account` varchar(255) DEFAULT NULL COMMENT '开户行帐号',
  `contract_name` varchar(255) DEFAULT NULL COMMENT '合同名称',
  `contract_code` varchar(255) DEFAULT NULL COMMENT '合同编号',
  `contract_money` decimal(10,2) DEFAULT NULL COMMENT '合同金额',
  `actual_money` decimal(10,2) DEFAULT NULL COMMENT '实际结算金额',
  `pay_reason` varchar(255) DEFAULT NULL COMMENT '付款事由说明',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `cumul_invoice` decimal(10,2) DEFAULT NULL COMMENT '累计已开发票金额',
  `cumul_pay` decimal(10,2) DEFAULT NULL COMMENT '累计已付款金额',
  `shuld_pay` decimal(10,2) DEFAULT NULL COMMENT '应付款金额',
  `ths_time` decimal(10,2) DEFAULT NULL COMMENT '本次开票金额',
  `ths_time_want` decimal(10,2) DEFAULT NULL COMMENT '本次申请付款金额',
  `uppercase` varchar(255) DEFAULT NULL COMMENT '金额大写',
  `type_id` bigint(20) DEFAULT NULL COMMENT '报账类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工程项目付款审批单表';


-------------------------
--为用户表添加电话
-------------------------
-- alter table [表名] add [字段名] 字段属性 default 缺省值 default 是可选参数
alter table `ums_admin` add `telephone` varchar(32) default NULL COMMENT '联系电话';

-- 删除用户表电话
-- alter table `ums_admin` drop `real_name`;
-- alter table `ums_admin` add `telephone` varchar(32) default NULL COMMENT '联系电话';

-------------------------
--为流程表添加部门
-------------------------
-- alter table `ams_process` add `department_id` bigint(20) DEFAULT NULL COMMENT '部门ID';