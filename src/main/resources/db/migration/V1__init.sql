CREATE TABLE transferencias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    conta_origem VARCHAR(10) NOT NULL,
    conta_destino VARCHAR(10) NOT NULL,
    valor DECIMAL(19,2) NOT NULL,
    data_transferencia DATE NOT NULL,
    data_agendamento DATE NOT NULL,
    taxa DECIMAL(19,2)
);