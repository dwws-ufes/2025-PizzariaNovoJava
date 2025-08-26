CREATE TABLE IF NOT EXISTS pizza (
 id integer,
 nome TEXT NOT NULL,
 descricao TEXT
);

-- Inserindo dados de exemplo
INSERT INTO pizza (id, nome, descricao) VALUES
    (1,'Mussarela', 'Pizza tradicional de queijo mussarela'),
    (2,'Calabresa', 'Pizza de calabresa com cebola'),
    (2,'Marguerita', 'Pizza com tomate, manjericão e mussarela');
