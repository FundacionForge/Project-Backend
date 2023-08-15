/* Entity Role */
insert into role(name) values ('ROLE_USER'), ('ROLE_ADMIN');

/* Entity Turnos */
insert into shifts(name) values ('Temprano'), ('Tarde');

/* Entity de grados */
insert into degrees(name, assigned_room, academic_level) values ('1ro', 'A101', 'Primaria'), ('2do', 'A102', 'Primaria'), ('3ro', 'A103', 'Primaria'), ('4to', 'A104', 'Primaria'), ('5to', 'A105', 'Primaria'), ('6to', 'A106', 'Primaria'), ('1ro', 'B201', 'Secundaria'), ('2do', 'B202', 'Secundaria'), ('3ro', 'B203', 'Secundaria'), ('4to', 'B204', 'Secundaria'), ('5to', 'B205', 'Secundaria');

/* Entity de cursos */
insert into courses(name, description, image) values ('Educación fisica', 'Promueve el desarrollo físico a través de actividades deportivas y juegos que fomentan la cooperación y el trabajo en equipo', 'https://img.freepik.com/vector-gratis/ilustracion-concepto-hacer-deporte_114360-1120.jpg'), ('Educación civica', 'Enseña sobre los derechos y deberes ciudadanos, el funcionamiento del gobierno y la participación en la comunidad.', 'https://img.freepik.com/vector-gratis/ilustracion-concepto-segmento_114360-290.jpg'), ('Ciencias ambientales', 'Explora el mundo natural, incluyendo la vida, los seres vivos, los ecosistemas, la materia y energía.', 'https://img.freepik.com/vector-gratis/ilustracion-concepto-maestro_114360-2743.jpg'), ('Matematica', 'Introduce conceptos numéricos, operaciones básicas (suma, resta, multiplicación, división) y resolución de problemas matemáticos fundamentales.', 'https://img.freepik.com/vector-gratis/ilustracion-concepto-matematicas_114360-3972.jpg'), ('Comunicación', 'Desarrolla habilidades de lectura y escritura, gramática, comprensión de textos y análisis literario.', 'https://img.freepik.com/vector-gratis/ilustracion-concepto-equipo-contenido_114360-4100.jpg'), ('Arte', 'Fomenta la creatividad a través del dibujo, la pintura y otras formas de expresión artística.', 'https://img.freepik.com/vector-gratis/ilustracion-concepto-comunidad-diseno_114360-1402.jpg'), ('Ingles', 'Introduce a los estudiantes en el aprendizaje de un idioma diferente al nativo, centrándose en habilidades lingüísticas básicas.', 'https://img.freepik.com/vector-gratis/ilustracion-concepto-meta_114360-1308.jpg');

/* Entity de cualidades */
insert into qualifications(name) values ('Especialización en Educación Física'), ('Especialización en Educación Cívica'), ('Especialización en Ciencias Ambientales'), ('Especialización en Matemáticas'), ('Especialización en Comunicación'), ('Especialización en Arte'), ('Especialización en Inglés');
