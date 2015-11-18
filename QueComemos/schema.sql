
    create table ComidasQueDisgusta (
        PreferenciaAlimenticia_PreferenciaAlimenticiaID bigint not null,
        comidasQueDisgusta varchar(255)
    );

    create table ComidasQueGusta (
        PreferenciaAlimenticia_PreferenciaAlimenticiaID bigint not null,
        comidasQueGusta varchar(255)
    );

    create table CondicionesPreexistentes (
        TipoCondicion varchar(31) not null,
        CondicionID bigint not null auto_increment,
        nombre varchar(255),
        primary key (CondicionID)
    );

    create table Condimentos (
        preparaciones_PreparacionID bigint not null,
        condimentos_PreparacionID bigint not null
    );

    create table Consultas (
        ConsultaID bigint not null auto_increment,
        horaConsulta datetime,
        usr_usuarioID bigint,
        primary key (ConsultaID)
    );

    create table Grupos (
        GrupoID bigint not null auto_increment,
        nombre varchar(255),
        preferenciaAlimenticia_PreferenciaAlimenticiaID bigint,
        primary key (GrupoID)
    );

    create table Ingredientes (
        PreparacionID bigint not null auto_increment,
        cantidad double precision not null,
        medida varchar(255),
        nombre varchar(255),
        preparaciones_PreparacionID bigint not null,
        ingredientes_PreparacionID bigint not null,
        primary key (PreparacionID)
    );

    create table PreferenciasAlimenticias (
        PreferenciaAlimenticiaID bigint not null auto_increment,
        primary key (PreferenciaAlimenticiaID)
    );

    create table Preparacion_explicacion (
        Preparacion_PreparacionID bigint not null,
        explicacion varchar(255)
    );

    create table Recetas (
        RecetaID bigint not null auto_increment,
        calorias double precision,
        dificultad varchar(255),
        nombre varchar(255),
        temporada varchar(255),
        preparacion_PreparacionID bigint,
        primary key (RecetaID)
    );

    create table RecetasDeUsuario (
        Usuarios_usuarioID bigint not null,
        misRecetas_RecetaID bigint not null
    );

    create table RecetasFavoritasXUsuario (
        Usuarios_usuarioID bigint not null,
        favoritas_RecetaID bigint not null
    );

    create table RecetasXConsulta (
        Consultas_ConsultaID bigint not null,
        resultadoConsulta_RecetaID bigint not null
    );

    create table Recetas_CondicionesPreexistentes (
        Recetas_RecetaID bigint not null,
        inadecuados_CondicionID bigint not null
    );

    create table SubRecetas (
        Recetas_RecetaID bigint not null,
        subRecetas_RecetaID bigint not null
    );

    create table Usuarios (
        usuarioID bigint not null auto_increment,
        estatura double precision,
        fechaNacimiento date,
        marcaFavoritasLasConsultas bit not null,
        nombre varchar(255),
        peso double precision,
        rutina varchar(255),
        sexo varchar(255),
        preferenciaAlimenticia_PreferenciaAlimenticiaID bigint,
        primary key (usuarioID)
    );

    create table Usuarios_CondicionesPreexistentes (
        Usuarios_usuarioID bigint not null,
        condicionesPreexistentes_CondicionID bigint not null
    );

    create table preparaciones (
        PreparacionID bigint not null auto_increment,
        primary key (PreparacionID)
    );

    create table usuariosXGrupo (
        Grupos_GrupoID bigint not null,
        usuarios_usuarioID bigint not null
    );

    alter table Condimentos 
        add constraint UK_2abto5jpyvdet58va0qt057ro  unique (condimentos_PreparacionID);

    alter table Ingredientes 
        add constraint UK_tkgu9lk76guk1nqy2l0k9i6ne  unique (ingredientes_PreparacionID);

    alter table ComidasQueDisgusta 
        add constraint FK_182gm7w4tnxwo1b05f60d9w5x 
        foreign key (PreferenciaAlimenticia_PreferenciaAlimenticiaID) 
        references PreferenciasAlimenticias (PreferenciaAlimenticiaID);

    alter table ComidasQueGusta 
        add constraint FK_ixshd28sdfgsty1focyct48i9 
        foreign key (PreferenciaAlimenticia_PreferenciaAlimenticiaID) 
        references PreferenciasAlimenticias (PreferenciaAlimenticiaID);

    alter table Condimentos 
        add constraint FK_2abto5jpyvdet58va0qt057ro 
        foreign key (condimentos_PreparacionID) 
        references Ingredientes (PreparacionID);

    alter table Condimentos 
        add constraint FK_f68sfi4pxc33nk5wjj5dke0ea 
        foreign key (preparaciones_PreparacionID) 
        references preparaciones (PreparacionID);

    alter table Consultas 
        add constraint FK_irpguisx6rpqk9xb3bl2ah7wy 
        foreign key (usr_usuarioID)
        references Usuarios (usuarioID);

    alter table Grupos 
        add constraint FK_6qpq23q78ynd1h3h439b2gcmk 
        foreign key (preferenciaAlimenticia_PreferenciaAlimenticiaID) 
        references PreferenciasAlimenticias (PreferenciaAlimenticiaID);

    alter table Ingredientes 
        add constraint FK_tkgu9lk76guk1nqy2l0k9i6ne 
        foreign key (ingredientes_PreparacionID) 
        references Ingredientes (PreparacionID);

    alter table Ingredientes 
        add constraint FK_9lxkpi3qphawdf02buylvi4qg 
        foreign key (preparaciones_PreparacionID) 
        references preparaciones (PreparacionID);

    alter table Preparacion_explicacion 
        add constraint FK_ho8afkmdessyfxj7wj0q6gn1h 
        foreign key (Preparacion_PreparacionID) 
        references preparaciones (PreparacionID);

    alter table Recetas 
        add constraint FK_sq6iinv15apd5y5ny5qstw9s 
        foreign key (preparacion_PreparacionID) 
        references preparaciones (PreparacionID);

    alter table RecetasDeUsuario 
        add constraint FK_bcnbncgbs3j4295lpbm515qks 
        foreign key (misRecetas_RecetaID) 
        references Recetas (RecetaID);

    alter table RecetasDeUsuario 
        add constraint FK_jfivswn8ui8i1cqj0voiqxfb4 
        foreign key (Usuarios_usuarioID) 
        references Usuarios (usuarioID);

    alter table RecetasFavoritasXUsuario 
        add constraint FK_32k1fdiaickw9hu2loyatkddf 
        foreign key (favoritas_RecetaID) 
        references Recetas (RecetaID);

    alter table RecetasFavoritasXUsuario 
        add constraint FK_gqfik5yasj86ybp3lpys5lco 
        foreign key (Usuarios_usuarioID) 
        references Usuarios (usuarioID);

    alter table RecetasXConsulta 
        add constraint FK_irx0m6no3eo0u67mtpt1ovcqc 
        foreign key (resultadoConsulta_RecetaID) 
        references Recetas (RecetaID);

    alter table RecetasXConsulta 
        add constraint FK_s4gqhp9d6ln4axyep1tgcsgeh 
        foreign key (Consultas_ConsultaID) 
        references Consultas (ConsultaID);

    alter table Recetas_CondicionesPreexistentes 
        add constraint FK_s0t5mh2av4kv50gbgi2mx9x5k 
        foreign key (inadecuados_CondicionID) 
        references CondicionesPreexistentes (CondicionID);

    alter table Recetas_CondicionesPreexistentes 
        add constraint FK_ed7l6rksco76of6yvnjew9utx 
        foreign key (Recetas_RecetaID) 
        references Recetas (RecetaID);

    alter table SubRecetas 
        add constraint FK_2e213g4e4gnteg9snf5ichiu5 
        foreign key (subRecetas_RecetaID) 
        references Recetas (RecetaID);

    alter table SubRecetas 
        add constraint FK_3oswcdhhrj5vpo4w3lj24qn2j 
        foreign key (Recetas_RecetaID) 
        references Recetas (RecetaID);

    alter table Usuarios 
        add constraint FK_hauijrqryo3lp3exv4cldopvj 
        foreign key (preferenciaAlimenticia_PreferenciaAlimenticiaID) 
        references PreferenciasAlimenticias (PreferenciaAlimenticiaID);

    alter table Usuarios_CondicionesPreexistentes 
        add constraint FK_834l9ak9i0koc4ne6op1dbfii 
        foreign key (condicionesPreexistentes_CondicionID) 
        references CondicionesPreexistentes (CondicionID);

    alter table Usuarios_CondicionesPreexistentes 
        add constraint FK_pjruphjkj54b7ocft6mciouj3 
        foreign key (Usuarios_usuarioID) 
        references Usuarios (usuarioID);

    alter table usuariosXGrupo 
        add constraint FK_srl7l0me2405gsl2n56137w40 
        foreign key (usuarios_usuarioID) 
        references Usuarios (usuarioID);

    alter table usuariosXGrupo 
        add constraint FK_beaynbiakq0uiu9wwdvx5vrla 
        foreign key (Grupos_GrupoID) 
        references Grupos (GrupoID);
