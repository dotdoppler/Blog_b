/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50708
Source Host           : 127.0.0.1:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50708
File Encoding         : 65001

Date: 2016-08-01 22:53:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hashtag
-- ----------------------------
DROP TABLE IF EXISTS `hashtag`;
CREATE TABLE `hashtag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `createdAt` varchar(255) DEFAULT NULL,
  `updatedAt` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `hashtag_name_uindex` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hashtag
-- ----------------------------
INSERT INTO `hashtag` VALUES ('1', '万青', '2016-07-03 20:10:18', '2016-07-03 20:10:33');
INSERT INTO `hashtag` VALUES ('2', 'Song', '2016-07-04 10:09:55', '2016-07-04 10:10:01');
INSERT INTO `hashtag` VALUES ('3', 'Silicon Valley', '2016-07-04 11:10:17', '2016-07-04 11:10:22');
INSERT INTO `hashtag` VALUES ('4', '陈勋奇', 'Aug 1, 2016', 'Aug 1, 2016');
INSERT INTO `hashtag` VALUES ('5', '堕落天使', 'Aug 1, 2016', 'Aug 1, 2016');
INSERT INTO `hashtag` VALUES ('6', 'ColdPlay', 'Aug 1, 2016', 'Aug 1, 2016');
INSERT INTO `hashtag` VALUES ('7', 'OK', 'Aug 1, 2016', 'Aug 1, 2016');

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdAt` varchar(255) DEFAULT NULL,
  `updatedAt` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `link` varchar(255) NOT NULL,
  `content` longtext,
  `renderedContent` longtext,
  `postStatus` varchar(255) NOT NULL,
  `postFormat` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('1', '2016-07-03 14:16:06', '2016-07-03 14:16:09', '大石碎胸口', 'big-stone-crash-chest', null, null, 'PUBLISHED', 'MARKDOWN');
INSERT INTO `post` VALUES ('2', '2016-07-03 14:19:11', null, '十万嬉皮', '10000Hippies', null, null, 'PUBLISHED', 'MARKDOWN');
INSERT INTO `post` VALUES ('3', '2016-07-03 17:48:00', null, 'rootless tree', 'rootless-tree', null, 'What I want from you is empty your head\r\n我希望你不要东想西想\r\nThey say be true, don\'t stay in your bed\r\n人们说要现实点 不要再留在你的床上\r\nWe do what we need to be free\r\n我们自由地做着爱做的事\r\nAnd it leans on me like a rootless tree\r\n但我被迫面对事实 就像无根的树\r\nWhat I want from us is empty our minds\r\n我希望我们不要东想西想\r\nWe fake the thoughts, and fracture the times\r\n我们伪装了思想 断开了境遇\r\nWe go blind when we\'ve needed to see\r\n需要用眼时我们变得盲目\r\nAnd this leans on me, like a rootless...\r\n但我被迫面对事实 就像无根的树\r\nAnd all we\'ve been through leave it\r\n我们经历的一切\r\nThere\'s nothing in you\r\n这不是你的错\r\nAnd did you hate me,\r\n如果你恨我\r\nhate me so good\r\n恨我 那很好\r\nThat you just let me out, let me out, let me out\r\n这样你就可以让我走 让我走\r\nOf this hell when you\'re around\r\n从你周围的地狱走开\r\nLet me out, let me out, let me out\r\n让我走 让我走 让我走\r\nHell when you\'re around\r\n从你周围的地狱走开\r\nLet me out, let me out, let me out\r\n让我走 让我走 让我走\r\nWhat I want from this\r\n我希望能从中\r\nIs learn to let go\r\n学到放手\r\nNo not of you\r\n不是你\r\nOf all that\'s been told\r\n故事里的人物都不是真的\r\nKillers re-invent and believe\r\n冷血之人重新创造了 并相信了这个故事\r\nAnd this leans on me, like a rootless...\r\n但我被迫面对事实 就像无根的树\r\nAnd all we\'ve been through\r\n我们经历的一切\r\nI said leave it,\r\n我说离开吧\r\nThere\'s nothing in you\r\n这不是你的错\r\nAnd did you hate me\r\n如果你恨我\r\nhate me so good\r\n恨我 那很好\r\nThat you just let me out, let me out, let me out\r\n这样你就可以让我走 让我走\r\nOf this hell when you\'re around\r\n从你周围的地狱走开\r\nLet me out, let me out, let me out\r\n让我走 让我走 让我走\r\nOf this hell when you\'re around\r\n从你周围的地狱走开\r\nLet me out, let me out, let me out\r\n让我走 让我走 让我走\r\nHell when you\'re around\r\n当你在我身边 这就像地狱\r\nLet me out, let me out, let me out\r\n让我走 让我走 让我走\r\nLet me out, let me out, let me out, let me out\r\nLet me out, let me out, let me out, let me out\r\nLet me out, let me out, let me out let me out\r\nAnd all we\'ve been through\r\n我们所经历的一切\r\nI said leave it,\r\n我说走吧\r\nIt\'s nothing in you\r\n这不是你的错\r\nAnd did you hate me,\r\n如果你恨我\r\nhate me so good\r\n恨我 那很好\r\nThat you just let me out, let me out, let me out\r\n这样你就可以让我走 让我走\r\nHell when you\'re around\r\n当你在我身边 这就像地狱\r\nLet me out, let me out, let me out,\r\n让我走 让我走 让我走\r\nHell when you\'re around\r\n当你在我身边 这就像地狱\r\nLet me out, let me out, let me out\r\n让我走 让我走 让我走\r\nHell when you\'re around\r\n当你在我身边 这就像地狱\r\nLet me out, let me out, let me out\r\n让我走 让我走 让我走\r\nHell when you\'re around\r\n当你在我身边 这就像地狱', 'PUBLISHED', 'MARKDOWN');
INSERT INTO `post` VALUES ('8', 'Jul 26, 2016', 'Jul 28, 2016', 'Frances HaHaHa', 'frances-hahaha', '这是一部关于青春、友谊、阶级、抱负、失败与救赎的电影，来自诺亚·鲍姆巴赫的当代版《曼哈顿》。这位导演曾执导过《鱿鱼和鲸》、《婚礼上的玛戈特》以及《格林伯格》，以擅长描写中产阶层知识分子的生活方式和精神困境而著称。女演员格蕾塔·葛韦格可以称为他的“缪斯”，总是扮演一些让人 ', '<p>这是一部关于青春、友谊、阶级、抱负、失败与救赎的电影，来自诺亚·鲍姆巴赫的当代版《曼哈顿》。这位导演曾执导过《鱿鱼和鲸》、《婚礼上的玛戈特》以及《格林伯格》，以擅长描写中产阶层知识分子的生活方式和精神困境而著称。女演员格蕾塔·葛韦格可以称为他的“缪斯”，总是扮演一些让人 </p>', 'PUBLISHED', 'MARKDOWN');
INSERT INTO `post` VALUES ('9', 'Aug 1, 2016', 'Aug 1, 2016', '愛のバクダン', 'love-bomb', '今日も一日 不完全な世界\r\n今天又是一天 不完整的世界\r\nきみが泣かなくても 誰か泣く\r\n你不哭泣 谁哭泣\r\n \r\n人がこしらえた 悲しみのからくり\r\n在人为制造的悲伤中\r\nなんとかできるのは ふとしたSmile\r\n', '<p>今日も一日 不完全な世界<br/>今天又是一天 不完整的世界<br/>きみが泣かなくても 誰か泣く<br/>你不哭泣 谁哭泣</p>\n<p>人がこしらえた 悲しみのからくり<br/>在人为制造的悲伤中<br/>なんとかできるのは ふとしたSmile</p>', 'PUBLISHED', 'MARKDOWN');
INSERT INTO `post` VALUES ('11', 'Aug 1, 2016', null, 'Flying Pickets ', 'flying-pickets', 'All I needed was the love you gave.\r\n我需要的不过是你给过我的爱\r\nAll I needed for another day\r\n我想要多一天的时间\r\nAnd all I ever knew;\r\n我只知道\r\nOnly you.\r\n我想要的只有你', '<p>All I needed was the love you gave.<br/>我需要的不过是你给过我的爱<br/>All I needed for another day<br/>我想要多一天的时间<br/>And all I ever knew;<br/>我只知道<br/>Only you.<br/>我想要的只有你</p>', 'PUBLISHED', 'MARKDOWN');
INSERT INTO `post` VALUES ('13', 'Aug 1, 2016', 'Aug 1, 2016', 'Fix You', 'fix-you', 'Could it be worse?\r\n这一切还会更糟吗\r\nLights will guide you home\r\n那些灯光会引领你回家\r\nAnd ignite your bones\r\n温暖你的骨头，给你力量\r\nAnd I will try to fix you\r\n我会试着帮你再次变得坚强\r\nAnd high up above or down below\r\n人生总有许多起起落落\r\nWhen you\'re too in love to let it go\r\n当你因为爱得太深 而妥协着不去挽回\r\n\r\n', '<p>Could it be worse?<br/>这一切还会更糟吗<br/>Lights will guide you home<br/>那些灯光会引领你回家<br/>And ignite your bones<br/>温暖你的骨头，给你力量<br/>And I will try to fix you<br/>我会试着帮你再次变得坚强<br/>And high up above or down below<br/>人生总有许多起起落落<br/>When you&rsquo;re too in love to let it go<br/>当你因为爱得太深 而妥协着不去挽回</p>', 'PUBLISHED', 'MARKDOWN');

-- ----------------------------
-- Table structure for posts_tags
-- ----------------------------
DROP TABLE IF EXISTS `posts_tags`;
CREATE TABLE `posts_tags` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `post_id` bigint(20) DEFAULT NULL,
  `tag_id` bigint(20) DEFAULT NULL,
  `createdAt` bigint(20) DEFAULT NULL,
  `updatedAt` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `posts_tags_hashtag_id_fk` (`tag_id`),
  KEY `posts_tags_post_id_fk` (`post_id`),
  CONSTRAINT `posts_tags_hashtag_id_fk` FOREIGN KEY (`tag_id`) REFERENCES `hashtag` (`id`) ON DELETE SET NULL,
  CONSTRAINT `posts_tags_post_id_fk` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of posts_tags
-- ----------------------------
INSERT INTO `posts_tags` VALUES ('1', '1', '1', null, null);
INSERT INTO `posts_tags` VALUES ('2', '1', '2', null, null);
INSERT INTO `posts_tags` VALUES ('3', '2', '3', null, null);
INSERT INTO `posts_tags` VALUES ('4', '2', '2', null, null);
INSERT INTO `posts_tags` VALUES ('7', '11', '2', null, null);
INSERT INTO `posts_tags` VALUES ('8', '11', '4', null, null);
INSERT INTO `posts_tags` VALUES ('9', '11', '5', null, null);
INSERT INTO `posts_tags` VALUES ('10', '13', '2', null, null);
INSERT INTO `posts_tags` VALUES ('11', '13', '6', null, null);
INSERT INTO `posts_tags` VALUES ('12', null, null, null, null);

-- ----------------------------
-- Table structure for setting
-- ----------------------------
DROP TABLE IF EXISTS `setting`;
CREATE TABLE `setting` (
  `id` bigint(20) NOT NULL,
  `siteName` varchar(255) DEFAULT NULL,
  `slogan` varchar(255) DEFAULT NULL,
  `notification` varchar(255) DEFAULT NULL,
  `createdAt` varchar(255) DEFAULT NULL,
  `updatedAt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of setting
-- ----------------------------
INSERT INTO `setting` VALUES ('1', 'Never Stop', 'Hey, You', 'Hello,World', '2016-07-03 11:15:55', '');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `createdAt` varchar(255) DEFAULT NULL,
  `updatedAt` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '2016-07-04 14:27:17', '2016-07-04 14:27:20', 'admin', 'xyz@gmail.pp', '$2a$10$ms6NpV4F6hjql2da/ogQ6u/bOINsk/w.JEDRoMPj0Pu8xWL1mtC32', 'ADMIN');
