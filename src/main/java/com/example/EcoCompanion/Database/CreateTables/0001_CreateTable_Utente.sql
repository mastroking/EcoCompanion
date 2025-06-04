USE [EcoCompanionDB]
GO

/****** Object:  Table [dbo].[Utente]    Script Date: 04/06/2025 11:24:34 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Utente](
	[IDUtente] [bigint] IDENTITY(1,1) NOT NULL,
	[Nome] [nvarchar](50) NULL,
	[Cognome] [nvarchar](50) NULL,
	[DataNascita] [datetime] NULL,
	[CodiceFiscale] [nvarchar](16) NULL,
	[Mail] [nvarchar](max) NULL,
	[Password] [nvarchar](max) NULL,
	[UserName] [nvarchar](50) NULL,
	[DataRegistrazione] [datetime] NULL,
	[UltimoAccesso] [datetime] NULL,
	[Genere] [varchar](50) NULL,
	[PuntiEcologici] [bigint] NULL,
	[Livello] [int] NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO


