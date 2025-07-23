package br.dev.nathan.projeto.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

// para o json saber como tratar o enum
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoSituacaoUsuario {

	ATIVO ("A", "Ativo"),
	INATIVO ("I", "Inativo"),
	PENDENTE ("P", "Pendente");
	
	private String codigo;
	private String descricao;
	
	private TipoSituacaoUsuario(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	// Quando mandar um cadastro apenas com o char que representa, o software precisa saber qual dos campos ele vai pegar para identificar o enum no usuario
	@JsonValue
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@JsonCreator
	public static TipoSituacaoUsuario doValor(String codigo) {
		
		if (codigo.equals("A")) {
			return ATIVO;
		} else if (codigo.equals("I")) {
			return INATIVO;
		} else if (codigo.equals("P")) {
			return PENDENTE;
		} else {
			return null;
		}
	}
	
}
