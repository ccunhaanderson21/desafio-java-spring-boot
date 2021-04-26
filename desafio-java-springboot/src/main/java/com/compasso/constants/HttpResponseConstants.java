package com.compasso.constants;

public class HttpResponseConstants {

	  private HttpResponseConstants() {
	    super();
	  }

	  //200
	  public static final int CODE_RETORNO_SUCESSO = 201;
	  
	  //400
	  public static final int CODE_JSON_FORMATADO = 400;
	  public static final int CODE_ERRO_VALIDAR_DADOS = 401;
	  public static final int CODE_ERRO_DADOS_N_LOCALIZADO = 404;

	  public static final String MESSAGE_ERRO = "O recurso não foi localizado";
	  public static final String MESSAGE_ERRO_VALIDAR_DADOS = "Formatos de campos inválidos";
	  
}
