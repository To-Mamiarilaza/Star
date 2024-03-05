CREATE USER Star IDENTIFIED BY star;
GRANT CONNECT, RESOURCE TO Star;
GRANT CREATE VIEW, RESOURCE TO Star;
CONNECT Star/star;

CREATE TABLE Composant (
    id varchar(5) PRIMARY KEY,
    nom varchar(20),
    pu number(7,2)
);

CREATE SEQUENCE seq_comp;

CREATE FUNCTION getSeqComposant RETURN number is seq number(8);
BEGIN
select seq_comp.nextVal into seq from dual;
return (seq);
END;
/

CREATE TABLE Combinaison (
    id number(3) PRIMARY KEY,
    idComp varchar(5),
    quantite number(4,2),
    idResult varchar(5),
    produit varchar(5),
    FOREIGN KEY (idComp) REFERENCES Composant,
    FOREIGN KEY (idResult) REFERENCES Composant,
    FOREIGN KEY (produit) REFERENCES Composant
);
CREATE SEQUENCE Seq_combinaison;

CREATE TABLE HistoriqueFabrication (
    id varchar(7) PRIMARY KEY,
    dateProduction DATE,
    idProduit varchar(5),
    quantite number(6),
    idRegion VARCHAR(7),
    prixUnitaire decimal(8,2),
    FOREIGN KEY(idProduit) REFERENCES Composant,
    FOREIGN KEY(idRegion) REFERENCES Region
);

CREATE SEQUENCE seq_hist;

CREATE FUNCTION getSeqHist RETURN number is seq number(8);
BEGIN
select seq_hist.nextVal into seq from dual;
return (seq);
END;
/

CREATE TABLE HistoriqueVente (
    id varchar(7) PRIMARY KEY,
    dateVente DATE,
    idProduit varchar(5),
    quantite number(6),
    idRegion VARCHAR(7),
    prixUnitaire decimal(8,2),
    FOREIGN KEY(idProduit) REFERENCES Composant,
    FOREIGN KEY(idRegion) REFERENCES Region
);

CREATE SEQUENCE seq_vente;

CREATE FUNCTION getSeqVente RETURN number is seq number(8);
BEGIN
select seq_vente.nextVal into seq from dual;
return (seq);
END;
/

CREATE TABLE Stock (
    id VARCHAR(7) PRIMARY KEY,
    idProduit VARCHAR(5),
    quantite NUMBER(8),
    idRegion VARCHAR(7),
    cump DECIMAL(8,2),
    FOREIGN KEY(idProduit) REFERENCES Composant,
    FOREIGN KEY(idRegion) REFERENCES Region
);

CREATE SEQUENCE seq_stock;

CREATE FUNCTION getSeqStock RETURN number is seq number(8);
BEGIN
select seq_stock.nextVal into seq from dual;
return (seq);
END;
/

CREATE TABLE Region (
    id VARCHAR(7)PRIMARY KEY,
    nom VARCHAR(50)
);

CREATE SEQUENCE seq_region;

CREATE FUNCTION getSeqRegion RETURN number is seq number(8);
BEGIN
select seq_region.nextVal into seq from dual;
return (seq);
END;
/

CREATE VIEW StockLibelle AS
SELECT p.nom as produit, s.quantite, s.cump, s.idRegion
FROM Stock s JOIN Composant p on s.idProduit = p.id;

CREATE VIEW HistVenteLibelle AS
SELECT h.dateVente, c.nom as produit, h.quantite, r.nom as region
FROM HistoriqueVente h JOIN Composant c on h.idproduit = c.id JOIN Region r on h.idregion = r.id
ORDER BY h.id desc;

CREATE VIEW HistFabLibelle AS
SELECT h.dateproduction, c.nom as produit, h.quantite, r.nom as region
FROM HistoriqueFabrication h JOIN Composant c on h.idproduit = c.id JOIN Region r on h.idregion = r.id
ORDER BY h.id desc;

CREATE VIEW CombinaisonDetail AS
SELECT comp.id, nom, pu, quantite, idResult, comb.produit, (select count(*) from Combinaison where idResult = comp.id and produit = comb.produit) as nbComposant
FROM Combinaison comb join Composant comp on comp.id = idComp
;

-- Insertion donnees pour nouveau projet
INSERT INTO Composant VALUES ('P1', 'Limonade', 3000);
INSERT INTO Composant VALUES ('P2', 'Limonade GM', 6000);
INSERT INTO Composant VALUES ('P3', 'Bierre', 2500);
INSERT INTO Composant VALUES ('P4', 'Bonbon Anglais', 3000);

INSERT INTO Region VALUES('RGN0001', 'Antananarivo');
INSERT INTO Region VALUES('RGN0002', 'Antsirabe'); 
INSERT INTO Region VALUES('RGN0003', 'Tamatave'); 
INSERT INTO Region VALUES('RGN0004', 'Mahajanga'); 
 

-- Insertion des donnees de constitution
INSERT INTO Composant VALUES ('P4', 'Bierre PM', null);

-- -- -- -- 
INSERT INTO Composant VALUES ('BOU', 'Bouteille', 500);

INSERT INTO Composant VALUES ('CO', 'Conservateur', 500);

INSERT INTO Composant VALUES ('OR', 'Orge', 1200);

INSERT INTO Composant VALUES ('ALC', 'Alcool', 5000);

INSERT INTO Composant VALUES ('ETI', 'Etiquette', null);
INSERT INTO Composant VALUES ('PAP', 'Papier', 50);
INSERT INTO Composant VALUES ('IMP', 'Impression', 50);

INSERT INTO Composant VALUES ('EA', 'Eau', 10);
INSERT INTO Composant VALUES ('GA', 'Gaz', 100);
INSERT INTO Composant VALUES ('EG', 'Eau gazeux', null);

INSERT INTO Composant VALUES ('P3', 'Bierre', null);

-- -- -- -- 
INSERT INTO Composant VALUES ('AR', 'Arome', 1000);
INSERT INTO Composant VALUES ('SU', 'Sucre', 3000);

INSERT INTO Composant VALUES ('FA', 'Fanta', null);

-- -- -- -- 
INSERT INTO Composant VALUES ('CI', 'Citron', 200);
INSERT INTO Composant VALUES ('SP', 'Special', null);
INSERT INTO Composant VALUES ('P1', 'Limonade', null);


-- Donnees de combinaison
INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'BOU', 0.6, 'P3', 'P3'); 
INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'CO', 0.1, 'P3', 'P3'); 
INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'OR', 1, 'P3', 'P3'); 
INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'ALC', 0.05, 'P3', 'P3'); 

INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'EA', 0.6, 'EG', 'P3'); 
INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'GA', 0.1, 'EG', 'P3'); 
INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'EG', 0.7, 'P3', 'P3'); 

INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'ETI', 1, 'P3', 'P3'); 
INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'PAP', 1, 'ETI', 'P3'); 
INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'IMP', 1, 'ETI', 'P3'); 

INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'P3', 0.6, null, 'P3');

-- -- -- -- 
INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'P3', 0.5, 'P4', 'P4');
INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'P4', 0.6, null, 'P4');
 
-- -- -- -- 
INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'EA', 0.5, 'EG', 'FA');
INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'GA', 0.1, 'EG', 'FA');
INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'EG', 0.6, 'FA', 'FA');
INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'AR', 0.1, 'FA', 'FA');
INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'SU', 0.3, 'FA', 'FA');

INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'FA', 0.6, null, 'FA');

-- -- -- -- 
INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'AR', 0.1, 'SP', 'LI');
INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'SU', 0.4, 'SP', 'LI');
INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'EG', 0.6, 'SP', 'LI');
INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'EA', 0.5, 'EG', 'LI');
INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'GA', 0.1, 'EG', 'LI');
INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'SP', 0.6, 'LI', 'LI');
INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'CI', 5, 'LI', 'LI');

INSERT INTO Combinaison VALUES (Seq_combinaison.nextval, 'LI', 0.6, null, 'LI');




