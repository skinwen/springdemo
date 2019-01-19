CREATE TABLE user_info(
  id BIGINT(10) AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  mobile_no VARCHAR(15) COMMENT '手机号码',
  password VARCHAR(10) COMMENT '密码',
  has_charge INT COMMENT '是否充值',
  crt_dat DATETIME DEFAULT current_timestamp COMMENT '创建时间',
  upt_dat DATETIME DEFAULT current_timestamp COMMENT '更新时间'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;