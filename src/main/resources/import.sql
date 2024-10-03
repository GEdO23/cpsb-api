INSERT INTO TB_CPSB_RACA (nm_raca, ds_raca) VALUES ( 'SRD', 'Não apresenta origem definida.' );
INSERT INTO TB_CPSB_RACA (nm_raca, ds_raca) VALUES ( 'Buldogue', 'Trufa e narinas devem ser grandes, amplas e na cor preta, jamais de cor fígado, vermelha ou marrom. O focinho é achatado e largo, curvando-se para cima e muito profundo do canto do olho ao canto da boca. Possui prognatismo inferior. O pêlo é de textura fina, curto, fechado e liso. Cor de pelagem: Unicolor ou “smut” (com fuligem, isto é, de uma só cor com máscara preta ou focinho preto e um sombreamento na pelagem); Tigrado, fulvo, marrom claro, branco ou malhado (combinação de branco com qualquer das cores precedentes). As cores fígado (chocolate), preto e castanho (tricolor) são altamente indesejáveis. Peso: Machos 25 kg; fêmeas 23 kg. Alturaː Entre 31 e 40 centímetros na altura da cernelha.');
INSERT INTO TB_CPSB_RACA (nm_raca, ds_raca) VALUES ( 'Husky', 'A pelagem do Husky possui subpelo e é bastante variada em questão de cor, desde o completamente branco ao preto-e-branco, chocolate-e-branco, cinza-e-branco, além de outras variações.');
INSERT INTO TB_CPSB_RACA (nm_raca, ds_raca) VALUES ( 'Pastor-alemão', 'Sua pelagem — de sub-pelo denso e acinzentado — em questão de cor varia em: pelagem bicolor marrom (avermelhado ou amarelado) com capa preta; pelagem bicolor Melanistico (quase inteiramente preto com patas e outras marcas castanhas; pelagem unicolor totalmente preta; e pelagem Sable (agouti) em suas infinitas tonalidades desde o "cinza negro, ou prateado, passando pelo cinza dourado e o avermelhado, sendo as marcações podendo ser uniformes, em mantos ou encarvoados. Sempre deve possuir máscara preta e nariz preto.');
INSERT INTO TB_CPSB_RACA (nm_raca, ds_raca) VALUES ( 'Poodle', 'Fisicamente, o tamanho do poodle varia entre o grande e o toy, tendo, em seus exemplares maiores, os mais bondosos, submissos e saudáveis, com a adenite sebácea sendo classificada como sua principal enfermidade. As qualidades físicas do padrão standard o tornaram um bom cão de companhia para famílias que vivem em grandes áreas, como fazendas.');

INSERT INTO TB_CPSB_PET (nm_pet, id_raca) VALUES ('Brendan', 1);
INSERT INTO TB_CPSB_PET (nm_pet, id_raca) VALUES ('Rex', 4);
INSERT INTO TB_CPSB_PET (nm_pet, id_raca) VALUES ('Toby', 3);
INSERT INTO TB_CPSB_PET (nm_pet, id_raca) VALUES ('Spike', 2);

INSERT INTO TB_CPSB_ROLE (nm_role) VALUES ( 'ROLE_ADMIN' );
INSERT INTO TB_CPSB_ROLE (nm_role) VALUES ( 'ROLE_USER' );

INSERT INTO TB_CPSB_USUARIO (nm_username, ds_password) VALUES ( 'Gabriel', '$2y$08$OzTn1weNsLrAe2HGsvmt.uOYduu0Yi2yZ6ZlaHJxK.s/.nD2.Uoda' ); // 12345
INSERT INTO TB_CPSB_USUARIO (nm_username, ds_password) VALUES ( 'Michelle', '$2y$08$OzTn1weNsLrAe2HGsvmt.uOYduu0Yi2yZ6ZlaHJxK.s/.nD2.Uoda' ); // 12345
INSERT INTO TB_CPSB_USUARIO (nm_username, ds_password) VALUES ( 'João Pedro', '$2y$08$OzTn1weNsLrAe2HGsvmt.uOYduu0Yi2yZ6ZlaHJxK.s/.nD2.Uoda' ); // 12345

INSERT INTO TB_CPSB_USUARIO_ROLE (id_usuario, id_role) VALUES ( 1, 1 );
INSERT INTO TB_CPSB_USUARIO_ROLE (id_usuario, id_role) VALUES ( 2, 1 );
INSERT INTO TB_CPSB_USUARIO_ROLE (id_usuario, id_role) VALUES ( 3, 2 );
