
drop table Sample;
drop table Treatment;
drop table Therapy;
drop table Patient;

CREATE TABLE Patient (
  pID CHAR(20) PRIMARY KEY,
  MetastaticDx DATE,
  BirthDate DATE NOT NULL,
  Sex INT NOT NULL,
  CancerType CHAR(20) NOT NULL,
  DateDx DATE NOT NULL
);


CREATE TABLE Sample (
  sID CHAR(20),
  DateExtracted DATE,
  DateCollected DATE,
  SequencingDepth INT,
  pID CHAR(20),
  PRIMARY KEY (sID),
  FOREIGN KEY (pID) REFERENCES Patient(pID) ON DELETE SET NULL
);


CREATE TABLE Therapy(
  Name CHAR(50) PRIMARY KEY,
  Class CHAR(20),
  StandardDose INT NOT NULL,
  Cancer CHAR(20),
  CostPerDose INT
);


CREATE TABLE Treatment(
  Name CHAR(50),
  pID CHAR(20),
  StartDate DATE,
  Status INT NOT NULL,
  ProgressionType CHAR(20),
  ProgressionDate DATE,
  PRIMARY KEY (Name, pID, StartDate),
  FOREIGN KEY (Name) REFERENCES Therapy(Name) ON DELETE CASCADE ,
  FOREIGN KEY (pID) REFERENCES Patient(pID) ON DELETE CASCADE
);


insert into Patient values('ID1',date_format('2017-10-02', '%Y-%m-%d'),date_format('1932-04-19', '%Y-%m-%d'), 0, 'Prostate', date_format('2015-04-15','%Y-%m-%d'));
insert into Patient values('ID2',date_format('2017-10-02', '%Y-%m-%d'),date_format('1932-04-19', '%Y-%m-%d'), 0, 'Prostate', date_format('2002-09-08','%Y-%m-%d'));
insert into Patient values('ID3',date_format('2019-03-05', '%Y-%m-%d'),date_format('1932-01-15', '%Y-%m-%d'), 0, 'Prostate', date_format('2008-06-08','%Y-%m-%d'));
insert into Patient values('ID4',date_format('2017-10-02', '%Y-%m-%d'),date_format('1932-04-19', '%Y-%m-%d'), 1, 'Lung', date_format('2008-06-08','%Y-%m-%d'));
insert into Patient values('ID5',date_format('2017-10-02', '%Y-%m-%d'),date_format('1932-04-19', '%Y-%m-%d'), 1,'Breast', date_format('2008-06-08','%Y-%m-%d'));
insert into Patient values('ID6',date_format('2017-10-02', '%Y-%m-%d'),date_format('1932-04-19', '%Y-%m-%d'), 1,'Breast', date_format('2008-06-08','%Y-%m-%d'));

insert into Sample values('ID1_cfDNA1',date_format('2010-09-02', '%Y-%m-%d'),date_format('2007-11-02', '%Y-%m-%d'), 1300, 'ID1');
insert into Sample values('ID1_Tissue1',date_format('2012-05-02', '%Y-%m-%d'),date_format('2009-08-01', '%Y-%m-%d'), 300, 'ID3');
insert into Sample values('ID1_Tissue2',date_format('2013-01-02', '%Y-%m-%d'),date_format('2000-02-02', '%Y-%m-%d'), 400, 'ID1');
insert into Sample values('ID1_WBC',date_format('2014-02-03', '%Y-%m-%d'),date_format('2012-12-02', '%Y-%m-%d'), 200, 'ID2');
insert into Sample values('ID2_Tissue1',date_format('2017-10-02', '%Y-%m-%d'),date_format('2011-04-02', '%Y-%m-%d'), 350, 'ID2');
insert into Sample values('ID4_cfDNA1',date_format('2011-10-02', '%Y-%m-%d'),date_format('2011-04-02', '%Y-%m-%d'), 1410, 'ID4');
insert into Sample values('ID5_cfDNA1',date_format('2000-03-02', '%Y-%m-%d'),date_format('2014-04-02', '%Y-%m-%d'), 1500, 'ID5');

insert into Therapy values('Ipatasertib','AKT inhibitor',400, 'Prostate', 15000);
insert into Therapy values('Abiraterone','AR Inhibitor',1000, 'Prostate', 3000);
insert into Therapy values('Tamoxifen','ER inhibitor',10, 'Breast', 2300);
insert into Therapy values('Cisplatin','Chemotherapy',100, 'Lung', 1000);
insert into Therapy values('Avelumab','Immunotherapy',10, 'Bladder', 12230);

insert into Treatment values('Abiraterone', 'ID1', date_format('2017-05-02', '%Y-%m-%d'), 0, '', date_format('2017-05-02', '%Y-%m-%d'));
insert into Treatment values('Tamoxifen', 'ID2', date_format('2002-09-02', '%Y-%m-%d'), 1, 'Clinical', date_format('2007-11-12', '%Y-%m-%d'));
insert into Treatment values('Avelumab', 'ID2',date_format('2000-05-02', '%Y-%m-%d'), 1, 'Chemical', date_format('2017-05-02', '%Y-%m-%d'));
insert into Treatment values('Avelumab', 'ID2', date_format('2007-07-19', '%Y-%m-%d'), 1, '', date_format('2007-09-02', '%Y-%m-%d'));

