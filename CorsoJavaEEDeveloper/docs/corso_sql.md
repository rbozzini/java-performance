# Guida SQL

shell MYSql:

    mysqlsh --uri root@localhost

Creazione database

```sql
CREATE SCHEMA `corso_java_2` DEFAULT CHARACTER SET utf8mb4

Creazione tabella

```sql
CREATE TABLE `corso_java`.`clienti` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `cognome` VARCHAR(255) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `telefono` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`ID`));
```

Creazione tabella

```sql
CREATE TABLE `corso_java`.`ordini` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `id_cliente` INT NULL,
  `data_consegna` DATETIME NULL,
  `importo` DOUBLE NULL,
  PRIMARY KEY (`ID`));
```

Aggiunge una foreign key alla tabella:

```sql
ALTER TABLE `corso_java`.`ordini` 
ADD INDEX `id_cliente_idx` (`id_cliente` ASC) VISIBLE;
;
ALTER TABLE `corso_java`.`ordini` 
ADD CONSTRAINT `id_cliente`
  FOREIGN KEY (`id_cliente`)
  REFERENCES `corso_java`.`clienti` (`ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
```

Aggiungere un nuovo record alla tabella:

```sql
insert into corso_java.clienti (nome, cognome, email, telefono) 
values ('Rossella', 'Bozzini', 'rossellabozzini@gmail.com', '3473813462');
```

Eliminare un record:

```sql
delete from corso_java.clienti where ID = 2;
```

Ricerca:

```sql
select email from corso_java.clienti where nome='Rossella' and cognome='Bozzini'
```

```sql
select * from corso_java.clienti where nome like '%ina%';
```

```sql
select c.nome, c.cognome, o.importo, o.id as 'numero ordine'
from corso_java.clienti as c, corso_java.ordini as o
where o.id_cliente = c.id;
```

```sql
select c.nome, c.cognome, o.importo, o.id as 'numero ordine'
from corso_java.clienti as c, corso_java.ordini as o
where o.id_cliente = c.id and importo >= 500 
order by o.importo desc
limit 2;
```

Modifica record:
```sql
update corso_java.ordini 
set importo = 1000
where id = 3;
```



```sql

```
