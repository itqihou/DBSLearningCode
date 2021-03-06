CREATE DATABASE SIMDB
GO
USE [SIMDB]
GO
/****** Object:  Schema [SJZ]    Script Date: 2021/5/7 22:57:15 ******/
CREATE SCHEMA [SJZ]
GO
/****** Object:  Table [SJZ].[Administrator]    Script Date: 2021/5/7 22:57:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [SJZ].[Administrator](
	[ano] [char](10) NOT NULL,
	[aname] [char](20) NOT NULL,
	[asex] [char](2) NOT NULL,
	[aage] [int] NOT NULL,
	[adept] [char](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ano] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[aname] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [SJZ].[Course]    Script Date: 2021/5/7 22:57:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [SJZ].[Course](
	[cno] [char](4) NOT NULL,
	[cname] [char](40) NOT NULL,
	[tno] [char](10) NULL,
	[cpno] [char](4) NULL,
	[ccredit] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[cno] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [SJZ].[SC]    Script Date: 2021/5/7 22:57:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [SJZ].[SC](
	[sno] [char](10) NOT NULL,
	[cno] [char](4) NOT NULL,
	[tname] [char](20) NULL,
	[grade] [float] NULL,
	[term] [char](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[sno] ASC,
	[cno] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [SJZ].[Student]    Script Date: 2021/5/7 22:57:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [SJZ].[Student](
	[sno] [char](10) NOT NULL,
	[sname] [char](20) NOT NULL,
	[ssex] [char](2) NOT NULL,
	[sage] [int] NOT NULL,
	[sdept] [char](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[sno] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[sname] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [SJZ].[Teacher]    Script Date: 2021/5/7 22:57:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [SJZ].[Teacher](
	[tno] [char](10) NOT NULL,
	[tname] [char](20) NOT NULL,
	[tsex] [char](2) NOT NULL,
	[tage] [int] NOT NULL,
	[tdept] [char](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[tno] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[tname] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [SJZ].[Users]    Script Date: 2021/5/7 22:57:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [SJZ].[Users](
	[account] [char](10) NOT NULL,
	[passwrd] [char](10) NOT NULL,
	[userRole] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[account] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [SJZ].[Course]  WITH CHECK ADD FOREIGN KEY([cpno])
REFERENCES [SJZ].[Course] ([cno])
GO
ALTER TABLE [SJZ].[Course]  WITH CHECK ADD FOREIGN KEY([tno])
REFERENCES [SJZ].[Teacher] ([tno])
GO
ALTER TABLE [SJZ].[SC]  WITH CHECK ADD  CONSTRAINT [cno] FOREIGN KEY([cno])
REFERENCES [SJZ].[Course] ([cno])
GO
ALTER TABLE [SJZ].[SC] CHECK CONSTRAINT [cno]
GO
ALTER TABLE [SJZ].[SC]  WITH CHECK ADD  CONSTRAINT [sno] FOREIGN KEY([sno])
REFERENCES [SJZ].[Student] ([sno])
GO
ALTER TABLE [SJZ].[SC] CHECK CONSTRAINT [sno]
GO
ALTER TABLE [SJZ].[SC]  WITH CHECK ADD  CONSTRAINT [tname] FOREIGN KEY([tname])
REFERENCES [SJZ].[Teacher] ([tname])
GO
ALTER TABLE [SJZ].[SC] CHECK CONSTRAINT [tname]
GO
ALTER TABLE [SJZ].[Administrator]  WITH CHECK ADD CHECK  (([adept]='大数据工程学院学生科' OR [adept]='学生处' OR [adept]='经管学院教务科' OR [adept]='理学院教务科' OR [adept]='大数据工程学院教务科' OR [adept]='教务处'))
GO
ALTER TABLE [SJZ].[Administrator]  WITH CHECK ADD CHECK  (([aage]>=(20) AND [aage]<=(60)))
GO
ALTER TABLE [SJZ].[Administrator]  WITH CHECK ADD CHECK  (([asex]='女' OR [asex]='男'))
GO
ALTER TABLE [SJZ].[Student]  WITH CHECK ADD CHECK  (([sage]>(20) AND [sage]<(30)))
GO
ALTER TABLE [SJZ].[Student]  WITH CHECK ADD CHECK  (([sdept]='信息技术' OR [sdept]='计算机科学与技术' OR [sdept]='数字媒体技术' OR [sdept]='物联网' OR [sdept]='光电' OR [sdept]='数据科学与大数据技术'))
GO
ALTER TABLE [SJZ].[Student]  WITH CHECK ADD CHECK  (([ssex]='女' OR [ssex]='男'))
GO
ALTER TABLE [SJZ].[Teacher]  WITH CHECK ADD CHECK  (([tage]>=(20) AND [tage]<=(60)))
GO
ALTER TABLE [SJZ].[Teacher]  WITH CHECK ADD CHECK  (([tdept]='信息技术' OR [tdept]='计算机科学与技术' OR [tdept]='数字媒体技术' OR [tdept]='物联网' OR [tdept]='光电' OR [tdept]='数据科学与大数据技术'))
GO
ALTER TABLE [SJZ].[Teacher]  WITH CHECK ADD CHECK  (([tsex]='女' OR [tsex]='男'))
GO

/*
INSERT INTO SJZ.Course
    (cno,cname,ccredit)
VALUES('1', '数据库', 4),
('2', '数学', 2),
('3', '信息系统', 4),
('4', '操作系统', 3),
('5', '数据结构', 4),
('6', '数据处理', 2),
('7', 'PASCAL语言', 4);
GO
INSERT INTO SJZ.SC
    (sno,cno,grade)
VALUES('2020101', '1', 92),
('2020101', '2', 85),
('2020101', '3', 88),
('2020101', '4', 90),
('2020101', '5', 80);
GO
*/