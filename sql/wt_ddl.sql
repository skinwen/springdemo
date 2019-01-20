CREATE TABLE user_info(
  id BIGINT(10) AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  mobile_no VARCHAR(15) COMMENT '手机号码',
  password VARCHAR(10) COMMENT '密码',
  has_charge INT COMMENT '是否充值',
  crt_dat DATETIME DEFAULT current_timestamp COMMENT '创建时间',
  upt_dat DATETIME DEFAULT current_timestamp COMMENT '更新时间',
  INDEX idx_n1(mobile_no)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE subject(
  id BIGINT(10) AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  subject_desc VARCHAR(100) COMMENT '主题描述',
  subject_item_desc varchar(20) COMMENT '子话题描述',
  crt_dat DATETIME DEFAULT current_timestamp COMMENT '创建时间',
  upt_dat DATETIME DEFAULT current_timestamp COMMENT '更新时间'
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


CREATE TABLE subject_item(
  id BIGINT(10) AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  subject_id BIGINT(100) COMMENT '主题描述',
  subject_item_desc varchar(20) COMMENT '子话题描述',
  subject_item_name VARCHAR(100) COMMENT '子话题',
  item_theme VARCHAR(100) COMMENT '子话题主题',
  item_content VARCHAR(500) COMMENT '子话题内容',
  item_md5 VARCHAR(64) COMMENT '摘要',
  crt_dat DATETIME DEFAULT current_timestamp COMMENT '创建时间',
  upt_dat DATETIME DEFAULT current_timestamp COMMENT '更新时间',
  INDEX idx_n1(subject_id)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
