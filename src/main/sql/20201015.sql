CREATE TABLE `mmp_card` (
  `card_no` bigint(20) unsigned NOT NULL COMMENT '',
  `mobile` bigint(20) unsigned DEFAULT NULL COMMENT '',
  `first_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '',
  `last_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '',
  `birthday` datetime DEFAULT NULL COMMENT '',
  `card_type` tinyint(4) unsigned NOT NULL COMMENT '',
  `is_employee` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '',
  `is_major` tinyint(2) unsigned NOT NULL COMMENT '',
  `join_date` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '',
  `card_status` tinyint(4) unsigned DEFAULT NULL COMMENT '',
  `member_status` tinyint(4) unsigned DEFAULT NULL COMMENT '',
  `member_sub_status` tinyint(4) unsigned DEFAULT NULL COMMENT '',
  `update_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`card_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='';

CREATE TABLE `mmp_card_extend` (
  `card_no` bigint(20) unsigned NOT NULL COMMENT '',
  `combine_card_no` bigint(20) unsigned DEFAULT NULL COMMENT '',
  `card_initial_password` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '',
  `card_password` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '',
  `valid_start_date` datetime DEFAULT NULL COMMENT '',
  `valid_end_date` datetime DEFAULT NULL COMMENT '',
  `employee_no` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '',
  `employee_initial_point_value` int(11) unsigned DEFAULT NULL COMMENT '',
  `clear_date` datetime DEFAULT NULL COMMENT '',
  `create_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `create_by` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '',
  `update_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ' ',
  `update_by` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '',
  PRIMARY KEY (`card_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='';

CREATE TABLE `mmp_card_consume_record` (
  `card_consume_record_id` bigint(20) unsigned NOT NULL COMMENT '',
  `card_no` bigint(20) unsigned NOT NULL COMMENT '',
  `consume_number` int(11) unsigned DEFAULT NULL COMMENT '',
  `consume_value` decimal(30,2) DEFAULT NULL COMMENT '',
  `old_consume_number` int(11) unsigned DEFAULT NULL COMMENT '',
  `old_consume_value` decimal(30,2) DEFAULT NULL COMMENT '',
  `operation_type` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '',
  `operation_type_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '',
  `operation_object_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`card_consume_record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='';

CREATE TABLE `mmp_member_card_relation_0` (
  `card_no` bigint(20) unsigned NOT NULL COMMENT '',
  `member_id` bigint(20) unsigned NOT NULL COMMENT '',
  `create_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='';







