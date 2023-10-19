-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 23, 2018 at 10:38 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 7.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `onlineexam`
--

-- --------------------------------------------------------

--
-- Table structure for table `assessor_reg_table`
--

CREATE TABLE `assessor_reg_table` (
  `userid` varchar(50) NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `address` varchar(200) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(200) NOT NULL,
  `date` varchar(40) NOT NULL,
  `time` varchar(50) NOT NULL,
  `userType` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `assessor_reg_table`
--

INSERT INTO `assessor_reg_table` (`userid`, `firstname`, `lastname`, `address`, `password`, `email`, `date`, `time`, `userType`) VALUES
('asses1', 'lola', 'bols', 'uuu', 'sss', 'a@gmail.com', '17:06:2018', '01:08:33', 'assessor');

-- --------------------------------------------------------

--
-- Table structure for table `examcodelist`
--

CREATE TABLE `examcodelist` (
  `examcode` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `examcodelist`
--

INSERT INTO `examcodelist` (`examcode`) VALUES
('CSC427');

-- --------------------------------------------------------

--
-- Table structure for table `examdetails`
--

CREATE TABLE `examdetails` (
  `examcode` varchar(200) NOT NULL,
  `examtitle` varchar(50) NOT NULL,
  `examduration` varchar(50) NOT NULL,
  `examdate` varchar(50) NOT NULL,
  `examtime` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `examdetails`
--

INSERT INTO `examdetails` (`examcode`, `examtitle`, `examduration`, `examdate`, `examtime`) VALUES
('CSC427', 'Programming', '05:00', '17:07:2018', '12:41:36');

-- --------------------------------------------------------

--
-- Table structure for table `examquestions`
--

CREATE TABLE `examquestions` (
  `counter` varchar(5) NOT NULL,
  `questionbody` varchar(1000) NOT NULL,
  `optionA` varchar(500) NOT NULL,
  `optionB` varchar(500) NOT NULL,
  `optionC` varchar(500) NOT NULL,
  `optionD` varchar(500) NOT NULL,
  `correctanswer` varchar(500) NOT NULL,
  `examcode` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `examquestions`
--

INSERT INTO `examquestions` (`counter`, `questionbody`, `optionA`, `optionB`, `optionC`, `optionD`, `correctanswer`, `examcode`) VALUES
('1', 'which programming language is the best', 'c++', 'python', 'pascal', 'java', 'java', 'CSC427'),
('2', 'which of this is a primitive datatype', 'Array', 'LinkedLink', 'Map', 'int', 'int', 'CSC427'),
('3', 'A variable that is declared in a method or function is called', 'Temporary variable', 'Global variable', 'Local variable', 'Loose variable', 'Local variable', 'CSC427'),
('4', 'Factorial of a number can be easily solved using ----------------------', 'recursion', 'linkedlisk', 'array', 'dynamic', 'recursion', 'CSC427'),
('5', 'In java, which data structure does not allow duplication of elements', 'Map', 'ArrayList', 'Set', 'LinkedList', 'Set', 'CSC427');

-- --------------------------------------------------------

--
-- Table structure for table `examtaker_reg_table`
--

CREATE TABLE `examtaker_reg_table` (
  `userid` varchar(50) NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `address` varchar(200) NOT NULL,
  `password` varchar(50) NOT NULL,
  `photoid` varchar(500) NOT NULL,
  `email` varchar(200) NOT NULL,
  `date` varchar(40) NOT NULL,
  `time` varchar(50) NOT NULL,
  `userType` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `examtaker_reg_table`
--

INSERT INTO `examtaker_reg_table` (`userid`, `firstname`, `lastname`, `address`, `password`, `photoid`, `email`, `date`, `time`, `userType`) VALUES
('adam12', 'lola', 'bols', 'isolu', 'sss', 'C:\\Users\\Tunde\\Documents\\NetBeansProjects\\OnlineExam\\web\\images\\photocam\\examtaker\\9193932.jpeg', 'a@gmail.com', '17:06:2018', '12:12:24', 'examtaker'),
('adam122', 'biod', 'sffg', 'gfgbb', 'dff', 'C:\\Users\\Tunde\\Documents\\NetBeansProjects\\OnlineExam\\web\\images\\photocam\\examtaker\\9193932.jpeg', 'a', '09:07:2018', '10:43:10', 'examtaker'),
('d2', 'lola', 'bols', 'hhh', 'sss', 'C:\\Users\\Tunde\\Documents\\NetBeansProjects\\OnlineExam\\web\\images\\photocam\\examtaker\\9193932.jpeg', 'a@gmail.com', '17:06:2018', '12:52:39', 'assessor'),
('h6', 'h', 'h', 'h', 'hhh', 'C:\\Users\\Tunde\\Documents\\NetBeansProjects\\OnlineExam\\web\\images\\photocam\\examtaker\\9193932.jpeg', 'h', '28:06:2018', '01:37:38', 'examtaker');

-- --------------------------------------------------------

--
-- Table structure for table `login_table`
--

CREATE TABLE `login_table` (
  `userid` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `userType` varchar(50) NOT NULL,
  `checkk` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login_table`
--

INSERT INTO `login_table` (`userid`, `password`, `userType`, `checkk`) VALUES
('adam12', 'sss', 'examtaker', 'no'),
('proctor1', 'ddd', 'proctor', 'no'),
('asses1', 'sss', 'assessor', 'no'),
('proc2', 'aaa', 'proctor', 'no'),
('d0', 'sss', 'proctor', 'no'),
('h6', 'hhh', 'examtaker', 'no'),
('adam122', 'dff', 'examtaker', 'no');

-- --------------------------------------------------------

--
-- Table structure for table `proctor_reg_table`
--

CREATE TABLE `proctor_reg_table` (
  `userid` varchar(50) NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `address` varchar(200) NOT NULL,
  `password` varchar(50) NOT NULL,
  `photoid` varchar(500) NOT NULL,
  `email` varchar(200) NOT NULL,
  `date` varchar(40) NOT NULL,
  `time` varchar(50) NOT NULL,
  `userType` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `proctor_reg_table`
--

INSERT INTO `proctor_reg_table` (`userid`, `firstname`, `lastname`, `address`, `password`, `photoid`, `email`, `date`, `time`, `userType`) VALUES
('d0', 'ss', 'ss', 's', 'sss', 'C:\\Users\\Tunde\\Documents\\NetBeansProjects\\OnlineExam\\web\\images\\photocam\\proctor\\9193932.jpeg', 'ss', '27:06:2018', '04:18:07', 'proctor'),
('proc2', 'ed', 'ws', 'ww', 'aaa', 'C:\\Users\\Tunde\\Documents\\NetBeansProjects\\OnlineExam\\web\\images\\photocam\\proctor\\9193932.jpeg', 'ed', '27:06:2018', '03:38:59', 'proctor'),
('proctor1', 'lola', 'bols', 'eee', 'ddd', 'C:\\Users\\Tunde\\Documents\\NetBeansProjects\\OnlineExam\\web\\images\\photocam\\proctor\\9193932.jpeg', 'ab@gmail.com', '17:06:2018', '01:07:21', 'proctor');

-- --------------------------------------------------------

--
-- Table structure for table `scheduleexam`
--

CREATE TABLE `scheduleexam` (
  `userid` varchar(50) NOT NULL,
  `examid` varchar(50) NOT NULL,
  `examdate` varchar(50) NOT NULL,
  `texamtime` varchar(50) NOT NULL,
  `check_exam` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `scheduleexam`
--

INSERT INTO `scheduleexam` (`userid`, `examid`, `examdate`, `texamtime`, `check_exam`) VALUES
('adam12', 'CSC427', '23:08:2018', '03:30', 'not done');

-- --------------------------------------------------------

--
-- Table structure for table `storeindexxandanswers`
--

CREATE TABLE `storeindexxandanswers` (
  `userid` varchar(50) NOT NULL,
  `indexx` varchar(20) NOT NULL,
  `answers` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `storequestionnumber`
--

CREATE TABLE `storequestionnumber` (
  `userid` varchar(20) NOT NULL,
  `questionNumber` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `assessor_reg_table`
--
ALTER TABLE `assessor_reg_table`
  ADD PRIMARY KEY (`userid`);

--
-- Indexes for table `examcodelist`
--
ALTER TABLE `examcodelist`
  ADD PRIMARY KEY (`examcode`);

--
-- Indexes for table `examdetails`
--
ALTER TABLE `examdetails`
  ADD PRIMARY KEY (`examcode`);

--
-- Indexes for table `examtaker_reg_table`
--
ALTER TABLE `examtaker_reg_table`
  ADD PRIMARY KEY (`userid`);

--
-- Indexes for table `proctor_reg_table`
--
ALTER TABLE `proctor_reg_table`
  ADD PRIMARY KEY (`userid`);

--
-- Indexes for table `storeindexxandanswers`
--
ALTER TABLE `storeindexxandanswers`
  ADD PRIMARY KEY (`indexx`);

--
-- Indexes for table `storequestionnumber`
--
ALTER TABLE `storequestionnumber`
  ADD PRIMARY KEY (`userid`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
