/*
 Navicat Premium Data Transfer

 Source Server         : 119
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : 127.0.0.1:3306
 Source Schema         : nacos

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 22/02/2025 14:59:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `encrypted_data_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '加密key',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (58, 'jjjsingle-dev.yaml', 'DEFAULT_GROUP', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:3306/jjjshop_single_cloud_git?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true\n    username: root\n    password: root\n\n  data:\n    redis:\n      database: 0\n      host: 127.0.0.1\n      password: 123456\n      port: 6379\n\n# 这个配置会将执行的sql打印出来，在开发或测试的时候可以用\nmybatis-plus:\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl      ', '2c03ece38afc162e257850342e225109', '2025-01-14 13:47:31', '2025-02-20 17:26:40', NULL, '119.98.5.59', '', 'single', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (59, 'jjjsingle-gateway-dev.yaml', 'DEFAULT_GROUP', 'jjjshop:\n  route:\n    config:\n      data-type: nacos\n      data-id: jjjsingle-gateway-router\nspring:\n  data:\n    redis:\n      database: 0\n      host: 127.0.0.1\n      port: 6379\n      password: 123456\nknife4j:\n  production: false\n', '424a6852c0648c33538da138e80c1edc', '2025-01-14 13:47:49', '2025-01-20 10:08:35', NULL, '119.98.5.30', '', 'single', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (60, 'jjjsingle-gateway-router.json', 'DEFAULT_GROUP', '[{\n  \"id\": \"jjjsingle-system\",\n  \"order\": 0,\n  \"predicates\": [{\n    \"name\": \"Path\",\n    \"args\": {\n      \"_genkey_0\": \"/api/**\"\n    }\n  }],\n  \"filters\": [],\n  \"uri\": \"lb://jjjsingle-system\"\n}]\n', 'de2642d12a73db64f4f93c0ed7b54e9b', '2025-01-14 13:48:05', '2025-02-22 13:47:59', NULL, '119.98.5.59', '', 'single', '', '', '', 'json', '', '');
INSERT INTO `config_info` VALUES (61, 'jjjsingle-prod.yaml', 'DEFAULT_GROUP', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:3306/jjjshop_single_cloud_git?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true\n    username: root\n    password: root\n\n  data:\n    redis:\n      database: 0\n      host: 127.0.0.1\n      password: 123456\n      port: 6379', '96940d21d0e644af46bf9cf37109a0d6', '2025-02-17 14:05:03', '2025-02-22 14:58:11', NULL, '119.98.5.59', '', 'single', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (62, 'jjjsingle-gateway-prod.yaml', 'DEFAULT_GROUP', 'jjjshop:\n  route:\n    config:\n      data-type: nacos\n      data-id: jjjsingle-gateway-router\nspring:\n  data:\n    redis:\n      database: 0\n      host: 127.0.0.1\n      port: 6379\n      password: 123456\nknife4j:\n  production: false\n', '424a6852c0648c33538da138e80c1edc', '2025-02-17 14:54:14', '2025-02-20 17:27:42', NULL, '119.98.5.59', '', 'single', '', '', '', 'yaml', '', '');

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '加密key',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `encrypted_data_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '加密key',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of group_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint UNSIGNED NOT NULL,
  `nid` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '加密key',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 190 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (57, 155, 'jjjsingle.yaml', 'DEFAULT_GROUP', '', 'server:\n  undertow:\n    # max-http-post-size: 10MB\n    worker-threads: 16\n    buffers:\n      websocket: 8192\n      io: 16384\n  error:\n    include-exception: true\n    include-stacktrace: ALWAYS\n    include-message: ALWAYS\n  compression:\n    enabled: true\n    min-response-size: 1024\n    mime-types: application/javascript,application/json,application/xml,text/html,text/xml,text/plain,text/css,image/*\nmanagement:\n  health:\n    mail:\n      enabled: false\n  endpoints:\n    web:\n      exposure:\n        include: \"*\"\n    health:\n      sensitive: true\n  endpoint:\n    health:\n      show-details: ALWAYS\nspring:\n  servlet:\n    multipart:\n      max-file-size: 10MB\n      max-request-size: 10MB\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n  aop:\n    proxy-target-class: true\n  activiti:\n    check-process-definitions: false\n    async-executor-activate: false\n    job-executor-activate: false\n  jpa:\n    open-in-view: false\n  freemarker:\n    check-template-location: false\n    suffix: .ftl\n    content-type: text/html\n    charset: UTF-8\n    cache: false\n    prefer-file-system-access: false\n    template-loader-path:\n      - classpath:/templates\n  mvc:\n    static-path-pattern: /**\n    pathmatch:\n      matching-strategy: ant_path_matcher\n  resource:\n    static-locations: classpath:/static/,classpath:/public/\n', '7161e909797ffc850a5ce57b96763176', '2010-05-05 00:00:00', '2025-02-14 03:02:08', NULL, '119.98.5.168', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (0, 156, 'jjjsingle-prod.yaml', 'DEFAULT_GROUP', '', 'spring', '2a2d595e6ed9a0b24f027f2b63b134d6', '2010-05-05 00:00:00', '2025-02-17 06:05:03', NULL, '119.98.5.59', 'I', 'single', '');
INSERT INTO `his_config_info` VALUES (61, 157, 'jjjsingle-prod.yaml', 'DEFAULT_GROUP', '', 'spring', '2a2d595e6ed9a0b24f027f2b63b134d6', '2010-05-05 00:00:00', '2025-02-17 06:07:17', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (61, 158, 'jjjsingle-prod.yaml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://192.168.31.200:3307/jjjshop_single_cloud_git?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true\n    username: root\n    password: root\n\n  data:\n    redis:\n      database: 0\n      host: 127.0.0.1\n      password: 123456\n      port: 6379', 'e09776a27bcdb8fdf0423393eb8c62c1', '2010-05-05 00:00:00', '2025-02-17 06:46:32', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (0, 159, 'jjjsingle-gateway-prod.yaml', 'DEFAULT_GROUP', '', '1', 'c4ca4238a0b923820dcc509a6f75849b', '2010-05-05 00:00:00', '2025-02-17 06:54:14', NULL, '119.98.5.59', 'I', 'single', '');
INSERT INTO `his_config_info` VALUES (62, 160, 'jjjsingle-gateway-prod.yaml', 'DEFAULT_GROUP', '', '1', 'c4ca4238a0b923820dcc509a6f75849b', '2010-05-05 00:00:00', '2025-02-17 06:55:16', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (61, 161, 'jjjsingle-prod.yaml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:3306/jjjshop_single_cloud_git?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true\n    username: root\n    password: cLGfTNk3sBRmRBnw\n\n  data:\n    redis:\n      database: 0\n      host: 127.0.0.1\n      password: 123456\n      port: 6379', '3de16adee2fc0b76e8915ce506f0274f', '2010-05-05 00:00:00', '2025-02-17 07:31:35', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (61, 162, 'jjjsingle-prod.yaml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:3306/jjjshop_single_cloud_git?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true\n    username: root\n    password: cLGfTNk3sBRmRBnw\n\n  data:\n    redis:\n      database: 0\n      host: 127.0.0.1\n      password: 123456\n      port: 6379', '9864a0c6c4e4dbc4c6b9d395aa9c8869', '2010-05-05 00:00:00', '2025-02-17 09:16:52', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (61, 163, 'jjjsingle-prod.yaml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    nacos:\n      discovery:\n        ip: 127.0.0.1\n  datasource:\n    url: jdbc:mysql://127.0.0.1:3306/jjjshop_single_cloud_git?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true\n    username: root\n    password: cLGfTNk3sBRmRBnw\n\n  data:\n    redis:\n      database: 0\n      host: 127.0.0.1\n      password: 123456\n      port: 6379', 'fb0383e60c5af5005c22a299a15345db', '2010-05-05 00:00:00', '2025-02-17 09:31:46', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (61, 164, 'jjjsingle-prod.yaml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:3306/jjjshop_single_cloud_git?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true\n    username: root\n    password: cLGfTNk3sBRmRBnw\n\n  data:\n    redis:\n      database: 0\n      host: 127.0.0.1\n      password: 123456\n      port: 6379', '9864a0c6c4e4dbc4c6b9d395aa9c8869', '2010-05-05 00:00:00', '2025-02-17 09:50:21', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (60, 165, 'jjjsingle-gateway-router.json', 'DEFAULT_GROUP', '', '[{\n  \"id\": \"jjjsingle-system\",\n  \"order\": 0,\n  \"predicates\": [{\n    \"name\": \"Path\",\n    \"args\": {\n      \"_genkey_0\": \"/api/shop/**\",\n      \"_genkey_1\": \"/api/admin/**\",\n      \"_genkey_3\": \"/api/front/**\"\n    }\n  }],\n  \"filters\": [],\n  \"uri\": \"lb://jjjsingle-system\"\n}]\n', '842df6ff5390bc365eb43cd6cd828be1', '2010-05-05 00:00:00', '2025-02-19 10:04:07', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (60, 166, 'jjjsingle-gateway-router.json', 'DEFAULT_GROUP', '', '[{\n  \"id\": \"jjjsingle-system\",\n  \"order\": 0,\n  \"predicates\": [{\n    \"name\": \"Path\",\n    \"args\": {\n      \"_genkey_0\": \"/api/shop/**\",\n      \"_genkey_1\": \"/api/admin/**\",\n      \"_genkey_3\": \"/api/front/**\"\n    }\n  }],\n  \"filters\": [\"RewritePath=/api/?(?<segment>.*)\"],\n  \"uri\": \"lb://jjjsingle-system\"\n}]\n', 'd3f8441992f79839bbda0bd7a4c066fe', '2010-05-05 00:00:00', '2025-02-19 10:05:34', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (60, 167, 'jjjsingle-gateway-router.json', 'DEFAULT_GROUP', '', '[{\n  \"id\": \"jjjsingle-system\",\n  \"order\": 0,\n  \"predicates\": [{\n    \"name\": \"Path\",\n    \"args\": {\n      \"_genkey_0\": \"/api/shop/**\",\n      \"_genkey_1\": \"/api/admin/**\",\n      \"_genkey_3\": \"/api/front/**\"\n    }\n  }],\n  \"filters\": [],\n  \"uri\": \"lb://jjjsingle-system\"\n}]\n', '842df6ff5390bc365eb43cd6cd828be1', '2010-05-05 00:00:00', '2025-02-19 10:14:53', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (60, 168, 'jjjsingle-gateway-router.json', 'DEFAULT_GROUP', '', '[{\n  \"id\": \"jjjsingle-system\",\n  \"order\": 0,\n  \"predicates\": [{\n    \"name\": \"Path\",\n    \"args\": {\n      \"_genkey_0\": \"/api/shop/**\",\n      \"_genkey_1\": \"/api/admin/**\",\n      \"_genkey_3\": \"/api/front/**\"\n    }\n  }],\n  \"filters\": [],\n  \"uri\": \"lb://jjjsingle-system\"\n},{\n  \"id\": \"api\",\n  \"order\": 0,\n  \"predicates\": [{\n    \"name\": \"Path\",\n    \"args\": {\n      \"_genkey_0\": \"/api/**\"\n    }\n  }],\n  \"filters\": [],\n  \"uri\": \"lb://jjjsingle-api\"\n}]\n', '417ae4b493709647cb6aa09ccad14c70', '2010-05-05 00:00:00', '2025-02-19 10:16:32', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (60, 169, 'jjjsingle-gateway-router.json', 'DEFAULT_GROUP', '', '[{\n  \"id\": \"jjjsingle-system\",\n  \"order\": 0,\n  \"predicates\": [{\n    \"name\": \"Path\",\n    \"args\": {\n      \"_genkey_0\": \"/api/shop/**\",\n      \"_genkey_1\": \"/api/admin/**\",\n      \"_genkey_3\": \"/api/front/**\"\n    }\n  }],\n  \"filters\": [],\n  \"uri\": \"lb://jjjsingle-system\"\n}]\n', '842df6ff5390bc365eb43cd6cd828be1', '2010-05-05 00:00:00', '2025-02-19 10:18:12', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (60, 170, 'jjjsingle-gateway-router.json', 'DEFAULT_GROUP', '', '[{\n  \"id\": \"jjjsingle-system\",\n  \"order\": 0,\n  \"predicates\": [{\n    \"name\": \"Path\",\n    \"args\": {\n      \"_genkey_0\": \"/api/shop/**\",\n      \"_genkey_1\": \"/api/admin/**\",\n      \"_genkey_3\": \"/api/front/**\"\n    }\n  }],\n  \"filters\": [],\n  \"uri\": \"lb://jjjsingle-system\"\n},{\n  \"id\": \"api\",\n  \"order\": 0,\n  \"predicates\": [{\n    \"name\": \"Path\",\n    \"args\": {\n      \"_genkey_0\": \"/api/shop/**\",\n      \"_genkey_1\": \"/api/admin/**\",\n      \"_genkey_3\": \"/api/front/**\"\n    }\n  }],\n  \"filters\": [],\n  \"uri\": \"lb://jjjsingle-api\"\n}]\n', '9804c4cfd9b9b880f46f99fcd65a5da2', '2010-05-05 00:00:00', '2025-02-19 10:18:36', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (60, 171, 'jjjsingle-gateway-router.json', 'DEFAULT_GROUP', '', '[{\n  \"id\": \"jjjsingle-system\",\n  \"order\": 0,\n  \"predicates\": [{\n    \"name\": \"Path\",\n    \"args\": {\n      \"_genkey_0\": \"/api/shop/**\",\n      \"_genkey_1\": \"/api/admin/**\",\n      \"_genkey_3\": \"/api/front/**\"\n    }\n  }],\n  \"filters\": [],\n  \"uri\": \"lb://jjjsingle-system\"\n}]\n', '842df6ff5390bc365eb43cd6cd828be1', '2010-05-05 00:00:00', '2025-02-19 10:20:54', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (60, 172, 'jjjsingle-gateway-router.json', 'DEFAULT_GROUP', '', '[{\n  \"id\": \"jjjsingle-system\",\n  \"order\": 0,\n  \"predicates\": [{\n    \"name\": \"Path\",\n    \"args\": {\n      \"_genkey_0\": \"/api/shop/**\",\n      \"_genkey_1\": \"/api/admin/**\",\n      \"_genkey_3\": \"/api/front/**\"\n    }\n  }],\n  \"filters\": [\"StripPrefix=1\"],\n  \"uri\": \"lb://jjjsingle-system\"\n}]\n', 'c1027783b9ba1c101af538850680f744', '2010-05-05 00:00:00', '2025-02-19 10:22:45', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (60, 173, 'jjjsingle-gateway-router.json', 'DEFAULT_GROUP', '', '[{\n  \"id\": \"jjjsingle-system\",\n  \"order\": 0,\n  \"predicates\": [{\n    \"name\": \"Path\",\n    \"args\": {\n      \"_genkey_0\": \"/api/shop/**\",\n      \"_genkey_1\": \"/api/admin/**\",\n      \"_genkey_3\": \"/api/front/**\"\n    }\n  }],\n\n  \n  \"filters\":\n    - DedupeResponseHeader=Access-Control-Allow-Credentials Access-Control-Allow-Origin\n    - StripPrefix=1\n  \"uri\": \"lb://jjjsingle-system\"\n}]\n', '23dbfe1b08e74fc503776e1d014e906a', '2010-05-05 00:00:00', '2025-02-19 10:24:06', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (60, 174, 'jjjsingle-gateway-router.json', 'DEFAULT_GROUP', '', '[{\n  \"id\": \"jjjsingle-system\",\n  \"order\": 0,\n  \"predicates\": [{\n    \"name\": \"Path\",\n    \"args\": {\n      \"_genkey_0\": \"/api/shop/**\",\n      \"_genkey_1\": \"/api/admin/**\",\n      \"_genkey_3\": \"/api/front/**\"\n    }\n  }],\n\n  \n\n  \"uri\": \"lb://jjjsingle-system\",\n  \"filters\":\n     - DedupeResponseHeader=Access-Control-Allow-Credentials Access-Control-Allow-Origin\n     - StripPrefix=1\n}]\n', '77cfd59a39361ab753c5c38f45d7ef33', '2010-05-05 00:00:00', '2025-02-19 10:30:32', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (60, 175, 'jjjsingle-gateway-router.json', 'DEFAULT_GROUP', '', '[{\n  \"id\": \"jjjsingle-system\",\n  \"order\": 0,\n  \"predicates\": [{\n    \"name\": \"Path\",\n    \"args\": {\n      \"_genkey_0\": \"/api/shop/**\",\n      \"_genkey_1\": \"/api/admin/**\",\n      \"_genkey_3\": \"/api/front/**\"\n    }\n  }],\n  \"filters\": [{\"name\":\"StripPrefix\",\"args\":{\"_genkey_0\":\"1\"}}],\n  \"uri\": \"lb://jjjsingle-system\"\n}]\n', 'c6fedc8e905b36b84a931b302258652e', '2010-05-05 00:00:00', '2025-02-19 10:32:36', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (57, 176, 'jjjsingle.yaml', 'DEFAULT_GROUP', '', 'server:\n  undertow:\n    # max-http-post-size: 10MB\n    worker-threads: 16\n  error:\n    include-exception: true\n    include-stacktrace: ALWAYS\n    include-message: ALWAYS\n  compression:\n    enabled: true\n    min-response-size: 1024\n    mime-types: application/javascript,application/json,application/xml,text/html,text/xml,text/plain,text/css,image/*\nmanagement:\n  health:\n    mail:\n      enabled: false\n  endpoints:\n    web:\n      exposure:\n        include: \"*\"\n    health:\n      sensitive: true\n  endpoint:\n    health:\n      show-details: ALWAYS\nspring:\n  servlet:\n    multipart:\n      max-file-size: 10MB\n      max-request-size: 10MB\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n  aop:\n    proxy-target-class: true\n  activiti:\n    check-process-definitions: false\n    async-executor-activate: false\n    job-executor-activate: false\n  jpa:\n    open-in-view: false\n  freemarker:\n    check-template-location: false\n    suffix: .ftl\n    content-type: text/html\n    charset: UTF-8\n    cache: false\n    prefer-file-system-access: false\n    template-loader-path:\n      - classpath:/templates\n  mvc:\n    static-path-pattern: /**\n    pathmatch:\n      matching-strategy: ant_path_matcher\n  resource:\n    static-locations: classpath:/static/,classpath:/public/\n', '6c60ebfeaf6563ea883ffe19d1d4f6b3', '2010-05-05 00:00:00', '2025-02-20 08:00:55', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (57, 177, 'jjjsingle.yaml', 'DEFAULT_GROUP', '', 'server:\n  undertow:\n    # max-http-post-size: 10MB\n    worker-threads: 16\n  error:\n    include-exception: true\n    include-stacktrace: ALWAYS\n    include-message: ALWAYS\n  compression:\n    enabled: true\n    min-response-size: 1024\n    mime-types: application/javascript,application/json,application/xml,text/html,text/xml,text/plain,text/css,image/*\nmanagement:\n  health:\n    mail:\n      enabled: false\n  endpoints:\n    web:\n      exposure:\n        include: \"*\"\n    health:\n      sensitive: true\n  endpoint:\n    health:\n      show-details: ALWAYS\nspring:\n  servlet:\n    multipart:\n      max-file-size: 10MB\n      max-request-size: 10MB\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n  aop:\n    proxy-target-class: true\n  activiti:\n    check-process-definitions: false\n    async-executor-activate: false\n    job-executor-activate: false\n  jpa:\n    open-in-view: false\n  freemarker:\n    check-template-location: false\n  mvc:\n    static-path-pattern: /**\n    pathmatch:\n      matching-strategy: ant_path_matcher\n  resource:\n    static-locations: classpath:/static/,classpath:/public/\n', 'a0e97228648b3095fbce4cf913d8912c', '2010-05-05 00:00:00', '2025-02-20 08:11:21', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (57, 178, 'jjjsingle.yaml', 'DEFAULT_GROUP', '', '\nspring:\n\n\n  activiti:\n    check-process-definitions: false\n    async-executor-activate: false\n    job-executor-activate: false\n\n\n', '87c936fd89562e548ed4e32f25a0cb4a', '2010-05-05 00:00:00', '2025-02-20 08:20:46', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (58, 179, 'jjjsingle-dev.yaml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://192.168.31.200:3307/jjjshop_single_cloud_git?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true\n    username: root\n    password: root\n\n  data:\n    redis:\n      database: 0\n      host: 127.0.0.1\n      password: 123456\n      port: 6379', 'dc73266acf9f0aa1948728f8baaa5b42', '2010-05-05 00:00:00', '2025-02-20 08:28:56', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (57, 180, 'jjjsingle.yaml', 'DEFAULT_GROUP', '', '\nspring:\n\n\n', '17f826a4011a873b15b21319c9211287', '2010-05-05 00:00:00', '2025-02-20 09:11:47', NULL, '119.98.5.59', 'D', 'single', '');
INSERT INTO `his_config_info` VALUES (58, 181, 'jjjsingle-dev.yaml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://192.168.31.200:3307/jjjshop_single_cloud_git?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true\n    username: root\n    password: root\n\n  data:\n    redis:\n      database: 0\n      host: 127.0.0.1\n      password: 123456\n      port: 6379\n\n# 这个配置会将执行的sql打印出来，在开发或测试的时候可以用\nmybatis-plus:\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl      ', '134296d0d23fbf1c97054719c5acc60a', '2010-05-05 00:00:00', '2025-02-20 09:26:40', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (61, 182, 'jjjsingle-prod.yaml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:3306/jjjshop_single_cloud_git?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true\n    username: root\n    password: cLGfTNk3sBRmRBnw\n\n  data:\n    redis:\n      database: 0\n      host: 127.0.0.1\n      password: 123456\n      port: 6379', '3de16adee2fc0b76e8915ce506f0274f', '2010-05-05 00:00:00', '2025-02-20 09:27:26', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (62, 183, 'jjjsingle-gateway-prod.yaml', 'DEFAULT_GROUP', '', 'jjjshop:\n  route:\n    config:\n      data-type: nacos\n      data-id: jjjsingle-gateway-router\nspring:\n  data:\n    redis:\n      database: 0\n      host: 127.0.0.1\n      port: 6379\n      password: 123456\nknife4j:\n  production: false\n', '751024295cee67c4a48157cb9d8cf6af', '2010-05-05 00:00:00', '2025-02-20 09:27:42', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (61, 184, 'jjjsingle-prod.yaml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:3306/jjjshop_single_cloud_git?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true\n    username: root\n    password: root\n\n  data:\n    redis:\n      database: 0\n      host: 127.0.0.1\n      password: 123456\n      port: 6379', '96940d21d0e644af46bf9cf37109a0d6', '2010-05-05 00:00:00', '2025-02-20 09:40:37', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (60, 185, 'jjjsingle-gateway-router.json', 'DEFAULT_GROUP', '', '[{\n  \"id\": \"jjjsingle-system\",\n  \"order\": 0,\n  \"predicates\": [{\n    \"name\": \"Path\",\n    \"args\": {\n      \"_genkey_0\": \"/api/shop/**\",\n      \"_genkey_1\": \"/api/admin/**\",\n      \"_genkey_3\": \"/api/front/**\"\n    }\n  }],\n  \"filters\": [],\n  \"uri\": \"lb://jjjsingle-system\"\n}]\n', '842df6ff5390bc365eb43cd6cd828be1', '2010-05-05 00:00:00', '2025-02-21 02:36:01', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (61, 186, 'jjjsingle-prod.yaml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:3306/jjjshop_single_cloud_git?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true\n    username: root\n    password: cLGfTNk3sBRmRBnw\n\n  data:\n    redis:\n      database: 0\n      host: 127.0.0.1\n      password: 123456\n      port: 6379', '9864a0c6c4e4dbc4c6b9d395aa9c8869', '2010-05-05 00:00:00', '2025-02-21 02:37:51', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (61, 187, 'jjjsingle-prod.yaml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:3306/jjjshop_single_cloud_git?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true\n    username: root\n    password: root\n\n  data:\n    redis:\n      database: 0\n      host: 127.0.0.1\n      password: 123456\n      port: 6379', '96940d21d0e644af46bf9cf37109a0d6', '2010-05-05 00:00:00', '2025-02-22 01:54:21', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (60, 188, 'jjjsingle-gateway-router.json', 'DEFAULT_GROUP', '', '[{\n  \"id\": \"jjjsingle-system\",\n  \"order\": 0,\n  \"predicates\": [{\n    \"name\": \"Path\",\n    \"args\": {\n      \"_genkey_0\": \"/api/shop/**\",\n      \"_genkey_1\": \"/api/admin/**\",\n      \"_genkey_3\": \"/api/front/**\",\n      \"_genkey_4\": \"/api/job/**\",\n      \"_genkey_5\": \"/api/resource/**\"\n    }\n  }],\n  \"filters\": [],\n  \"uri\": \"lb://jjjsingle-system\"\n}]\n', '1052036b1125f8c4b0bcdfd0a2e8aef6', '2010-05-05 00:00:00', '2025-02-22 05:47:59', NULL, '119.98.5.59', 'U', 'single', '');
INSERT INTO `his_config_info` VALUES (61, 189, 'jjjsingle-prod.yaml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:3306/jjjshop_single_cloud_git?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true\n    username: root\n    password: cLGfTNk3sBRmRBnw\n\n  data:\n    redis:\n      database: 0\n      host: 127.0.0.1\n      password: 123456\n      port: 6379', '9864a0c6c4e4dbc4c6b9d395aa9c8869', '2010-05-05 00:00:00', '2025-02-22 06:58:11', NULL, '119.98.5.59', 'U', 'single', '');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `action` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `uk_role_permission`(`role`, `resource`, `action`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of permissions
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `uk_username_role`(`username`, `role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tenant_info
-- ----------------------------
INSERT INTO `tenant_info` VALUES (5, '1', 'single', 'single', '单商户', 'nacos', 1736833595500, 1736833595500);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);
INSERT INTO `users` VALUES ('nacos6789', '$2a$10$pe094wryQNMfP62Md05fguxWnIIcchpm4p58Av0vkcPKf7LY3O1pm', 1);
INSERT INTO `users` VALUES ('trhxsbbrrwjhamxm', '$2a$10$b0cbXewZxF1J9g3.QefSPOPoqVfn1oSlujJGm0cdb3e87ItnY2WQ2', 1);

SET FOREIGN_KEY_CHECKS = 1;
