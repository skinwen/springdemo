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
  subject_name VARCHAR(100) COMMENT '主题描述',
  crt_dat DATETIME DEFAULT current_timestamp COMMENT '创建时间',
  upt_dat DATETIME DEFAULT current_timestamp COMMENT '更新时间'
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


CREATE TABLE subject_item(
  id BIGINT(10) AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  subject_id BIGINT(100) COMMENT '主题描述',
  subject_item_name VARCHAR(100) COMMENT '子话题',
  crt_dat DATETIME DEFAULT current_timestamp COMMENT '创建时间',
  upt_dat DATETIME DEFAULT current_timestamp COMMENT '更新时间',
  INDEX idx_n1(subject_id)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE subject_item_content(
  id BIGINT(10) AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  subject_item_id BIGINT(100) COMMENT '主题描述',
  theme VARCHAR(100) COMMENT '主题',
  content TEXT COMMENT '内容',
  md5 VARCHAR(64) COMMENT '摘要',
  can_show CHAR(1) COMMENT '是否显示',
  crt_dat DATETIME DEFAULT current_timestamp COMMENT '创建时间',
  upt_dat DATETIME DEFAULT current_timestamp COMMENT '更新时间',
  INDEX idx_n1(subject_item_id),
  INDEX idx_n2(md5)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
DROP TABLE subject_screen;
CREATE TABLE subject_screen(
  id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  theme VARCHAR(100) COMMENT '主题',
  target VARCHAR(100) COMMENT '目的',
  content VARCHAR(500) COMMENT '内容',
  img_url VARCHAR(200) COMMENT '图片地址',
  release_time DATETIME DEFAULT current_timestamp COMMENT '发布时间',
  crt_dat DATETIME DEFAULT current_timestamp COMMENT '创建时间',
  upt_dat DATETIME DEFAULT current_timestamp COMMENT '更新时间',
  md5 VARCHAR(64) COMMENT '摘要',
  type CHAR(1) COMMENT '类型，1：恋爱解析，2：聊天截图',
  INDEX idx_n1(md5)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
