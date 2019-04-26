insert into corso_java.clienti (nome, cognome, email, telefono) values ('Arianna', 'Brandolini', 'ari.bramdolini@gmail.com', '');
insert into corso_java.ordini (id_cliente, data_consegna, importo) values (7, null, 900);

delete from corso_java.clienti where ID = 5;

select email from corso_java.clienti where nome='Rossella' and cognome='Bozzini';

select * from corso_java.clienti where nome like '%ina%';

select c.nome, c.cognome, o.importo, o.id as 'numero ordine'
from corso_java.clienti as c, corso_java.ordini as o
where o.id_cliente = c.id and importo >= 500 
order by o.importo desc
limit 2;

update corso_java.ordini 
set importo = 1000
where id = 3;

UPDATE clienti SET telefono='0123456789' WHERE nome='Diego' AND cognome='Romani'