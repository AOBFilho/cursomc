package com.aobfilho.cursomc.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1,"Pessoa Física"),
	PESSOJURIDICA(2,"Pessoa Jurídica");
	
	private Integer codigo;
	private String descricao;
	
	private TipoCliente(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public static TipoCliente toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		
		for (TipoCliente x : TipoCliente.values()) {
			if (codigo.equals(x.getCodigo())){
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: "+ codigo);
	}
}
