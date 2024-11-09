BEGIN;

CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    confirmar_senha VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) CHECK (tipo IN ('ADMINISTRATOR', 'EMPLOYEE')) NOT NULL,
    imagem_perfil BYTEA  -- Coluna para armazenar a imagem como dados binários
);

CREATE TABLE tarefa (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT NOT NULL,
    data_vencimento DATE NOT NULL,
    prioridade VARCHAR(50) CHECK (prioridade IN ('Essencial', 'Importante', 'Desejável')) NOT NULL,
    status VARCHAR(50) CHECK (status IN ('Em Andamento', 'Concluída')) NOT NULL,
    usuario_id INTEGER REFERENCES usuario(id) ON DELETE CASCADE
);

COMMIT;