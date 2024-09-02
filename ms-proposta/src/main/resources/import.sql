INSERT INTO tb_user (nome, sobrenome, cpf, telefone, renda) VALUES ('João', 'Silva', '12345678901', '11987654321', 5000.00);
INSERT INTO tb_user (nome, sobrenome, cpf, telefone, renda) VALUES ('Maria', 'Oliveira', '23456789012', '21987654321', 7000.00);
INSERT INTO tb_user (nome, sobrenome, cpf, telefone, renda) VALUES ('Carlos', 'Santos', '34567890123', '31987654321', 6000.00);

INSERT INTO tb_proposta (valor_solicitado, prazo_para_pagamento, aprovado, user_id) VALUES (15000.00, 24, true, 1);
INSERT INTO tb_proposta (valor_solicitado, prazo_para_pagamento, aprovado, user_id) VALUES (30000.00, 36, false, 1);
INSERT INTO tb_proposta (valor_solicitado, prazo_para_pagamento, aprovado, user_id) VALUES (20000.00, 12, true, 2);
INSERT INTO tb_proposta (valor_solicitado, prazo_para_pagamento, aprovado, user_id) VALUES (40000.00, 48, false, 2);
INSERT INTO tb_proposta (valor_solicitado, prazo_para_pagamento, aprovado, user_id) VALUES (18000.00, 18, true, 3);
INSERT INTO tb_proposta (valor_solicitado, prazo_para_pagamento, aprovado, user_id) VALUES (35000.00, 24, false, 3);
